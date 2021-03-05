package cn.milai.ibserver.handler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import cn.milai.common.decoupling.Resp;
import cn.milai.ibserver.IBServerException;
import cn.milai.ibserver.conf.RedisKey;
import cn.milai.ibserver.service.UserService;
import cn.milai.nexus.handler.OnlineHandler;
import cn.milai.nexus.util.Channels;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 服务端连接处理器
 * @author milai
 * @date 2020.12.19
 */
@Component
public class ServerOnlineHandler implements OnlineHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ServerOnlineHandler.class);

	private final Map<Long, ChannelId> USER_TO_CHANNEL_ID = new ConcurrentHashMap<>();

	/**
	 * 等待验证的 {@link Channel} 集合
	 */
	private final ChannelGroup WAIT_FOR_AUTH = new DefaultChannelGroup(
		"waitForAuth", GlobalEventExecutor.INSTANCE
	);

	/**
	 * 在线的 {@link Channel} 集合i
	 */
	private final ChannelGroup ONLINE = new DefaultChannelGroup("online", GlobalEventExecutor.INSTANCE);

	/**
	 * 连接建立后等待验证的最大秒数
	 */
	private static final int LOGIN_WAIT_SECCONDS = 5;

	@Autowired
	private RedisTemplate<String, String> redis;
	@Autowired
	private RedisKey redisKey;
	@Autowired
	private UserService us;

	/**
	 * 获取当前服务器 IP
	 * @return
	 */
	private static String getIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			throw new RuntimeException("获取服务器 IP 失败", e);
		}
	}

	@Override
	public void connect(ChannelHandlerContext ctx) {
		addWaitForAuth(ctx);
		ctx.executor().schedule(() -> {
			synchronized (WAIT_FOR_AUTH) {
				if (isWaitingForAuth(ctx)) {
					offline(ctx);
				}
			}
		}, LOGIN_WAIT_SECCONDS, TimeUnit.SECONDS);
	}

	@Override
	public void disconnect(ChannelHandlerContext ctx) {
		offline(ctx);
	}

	/**
	 * 使用给定 token 和 userId 来验证给定 {@link ChannelHandlerContext}
	 * 返回是否验证成功
	 * @param ctx
	 * @param token
	 * @param userId
	 * @return
	 */
	public boolean auth(ChannelHandlerContext ctx, String token, long userId) {
		synchronized (WAIT_FOR_AUTH) {
			if (!isWaitingForAuth(ctx)) {
				return false;
			}
			removeWaitForAuth(ctx);
		}
		Resp<Long> resp = us.getIdByToken(token);
		if (!resp.isSuccess()) {
			throw new IBServerException("获取 token 对应 userId 失败, token = %s", token);
		}
		if (resp.getData() == null || !resp.getData().equals(userId)) {
			LOG.info("token 验证失败，token = {}, req_user_id = {}, real_user_id = {}", token, userId, resp.getData());
			return false;
		}
		online(userId, ctx);
		return true;
	}

	/**
	 * 获取 {@link ChannelHandlerContext} 对应的 userId，若未登录，返回 null
	 * @param ctx
	 * @return
	 */
	public Long getUserId(ChannelHandlerContext ctx) {
		return Channels.longAttr(ctx, Channels.ATTR_USER_ID);
	}

	/**
	 * 将 {@link ChannelHandlerContext} 使用给定的 userId 上线 
	 * @param userId
	 * @param ctx
	 */
	private void online(long userId, ChannelHandlerContext ctx) {
		USER_TO_CHANNEL_ID.put(userId, ctx.channel().id());
		redis.opsForValue().set(redisKey.linkedServer(userId), getIP());
		ONLINE.add(ctx.channel());
		Channels.setAttr(ctx, Channels.ATTR_USER_ID, userId);
	}

	/**
	 * 下线指定 {@link ChannelHandlerContext}
	 * @param ctx
	 * @return
	 */
	private Long offline(ChannelHandlerContext ctx) {
		Long userId = Channels.longAttr(ctx, Channels.ATTR_USER_ID);
		if (userId != null) {
			redis.delete(redisKey.linkedServer(userId));
		}
		ctx.close();
		return userId;
	}

	private boolean isWaitingForAuth(ChannelHandlerContext ctx) {
		return WAIT_FOR_AUTH.contains(ctx.channel());
	}

	private void addWaitForAuth(ChannelHandlerContext ctx) {
		WAIT_FOR_AUTH.add(ctx.channel());
	}

	/**
	 * 移除一个正在等待验证的连接，返回是否真的进行了移除操作
	 * @param ctx
	 * @return
	 */
	private boolean removeWaitForAuth(ChannelHandlerContext ctx) {
		return WAIT_FOR_AUTH.remove(ctx.channel());
	}

}
