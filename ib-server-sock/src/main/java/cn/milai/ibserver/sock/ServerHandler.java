package cn.milai.ibserver.sock;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.milai.ibserver.sock.Network.Message;
import cn.milai.ibserver.web.service.TokenService;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 连接统一入口处理器
 * @author milai
 */
@Component
@Sharable
public class ServerHandler extends SimpleChannelInboundHandler<Message> {

	private static final int LOGIN_WAIT_SECCONDS = 5;

	@Autowired
	private OnlineManager onlines;

	@Resource(name = "waitForToken")
	private Set<ChannelHandlerContext> waitForToken;

	@Autowired
	private TokenService ts;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
		switch (message.getType()) {
			case LOGIN :
				login(ctx, message);
				break;
			case LOGOUT :
				logout(ctx);
				break;
			case LINK_REQUEST :
				break;
			case LINK_ADMIT :
				break;
			case LINK_REJECT :
				break;
			case RESPONSE :
				break;
			case UNRECOGNIZED :
				break;
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		waitForToken.add(ctx);
		ctx.executor().schedule(() -> {
			if (waitForToken.contains(ctx)) {
				closeUnauthorized(ctx);
			}
		}, LOGIN_WAIT_SECCONDS, TimeUnit.SECONDS);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		forceOffline(ctx);
	}

	private void login(ChannelHandlerContext ctx, Message message) {
		if (!waitForToken.contains(ctx)) {
			forceOffline(ctx);
			return;
		}

		JSONObject json = JSON.parseObject(message.getData());
		String token = json.getString("token");
		if (token == null) {
			forceOffline(ctx);
			return;
		}
		int userId = ts.tokenToId(token).getData();
		onlines.online(userId, ctx);
	}

	private void logout(ChannelHandlerContext ctx) {
		forceOffline(ctx);
	}

	private boolean forceOffline(ChannelHandlerContext ctx) {
		closeUnauthorized(ctx);
		onlines.offline(ctx);
		return false;
	}

	private void closeUnauthorized(ChannelHandlerContext ctx) {
		ctx.close();
		waitForToken.remove(ctx);
	}
}
