package cn.milai.ibserver.user.handler;

import org.springframework.beans.factory.annotation.Autowired;

import cn.milai.ibserver.MsgCode;
import cn.milai.ibserver.user.msg.AuthMsg;
import cn.milai.nexus.annotation.MsgController;
import cn.milai.nexus.annotation.MsgMapping;
import io.netty.channel.ChannelHandlerContext;

/**
 * 用户相关消息处理器
 * @author milai
 * @date 2020.12.30
 */
@MsgController
public class UserMsgHandler {

	@Autowired
	private ServerOnlineHandler onlines;

	@MsgMapping(MsgCode.AUTH)
	public void handleAuth(ChannelHandlerContext ctx, AuthMsg msg) {
		// 若验证失败属于异常情况，将直接断开连接，不再返回响应码
		onlines.auth(ctx, msg.getToken(), msg.getUserId());
	}

}
