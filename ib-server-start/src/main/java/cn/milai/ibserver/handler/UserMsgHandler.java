package cn.milai.ibserver.handler;

import org.springframework.beans.factory.annotation.Autowired;

import cn.milai.ibserver.msg.AuthMsg;
import cn.milai.ibserver.msg.MsgCode;
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
		onlines.auth(ctx, msg.getToken(), msg.getUserId());
	}

}
