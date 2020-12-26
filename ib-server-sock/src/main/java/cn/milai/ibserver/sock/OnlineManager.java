package cn.milai.ibserver.sock;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import io.netty.channel.ChannelHandlerContext;

/**
 * 在线连接管理器
 * @author milai
 * @date 2020.12.19
 */
@Component
public class OnlineManager {

	@Resource(name = "onlines")
	private Map<Integer, ChannelHandlerContext> onlines;

	/**
	 * 将一个连接以 userId 的身份上线
	 * @param userId
	 * @param ctx
	 */
	public void online(int userId, ChannelHandlerContext ctx) {
		onlines.put(userId, ctx);
	}

	/**
	 * 将某个连接下线，返回对应的 userId，若连接不在线，返回 null
	 * @param ctx
	 * @return
	 */
	public Integer offline(ChannelHandlerContext ctx) {
		for (Integer userId : onlines.keySet()) {
			if (onlines.get(userId) == ctx) {
				onlines.remove(userId);
				return userId;
			}
		}
		return null;
	}

}
