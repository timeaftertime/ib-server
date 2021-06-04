package cn.milai.ibserver.landvsair.handler;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.milai.ibserver.MsgCode;
import cn.milai.ibserver.user.handler.ServerOnlineHandler;
import cn.milai.nexus.handler.interceptor.AllowMsgCodeInterceptor;
import cn.milai.nexus.handler.interceptor.Interceptor;
import cn.milai.nexus.handler.msg.Msg;
import io.netty.channel.ChannelHandlerContext;

/**
 * 拦截未验证消息的 {@link Interceptor}
 * @author milai
 * @date 2021.05.29
 */
@Component
public class AuthRequiredInterceptor extends AllowMsgCodeInterceptor {

	@Autowired
	private ServerOnlineHandler onlines;

	public AuthRequiredInterceptor() {
		super(new HashSet<>(Arrays.asList(MsgCode.AUTH)));
	}

	@Override
	protected boolean doPreHandle(ChannelHandlerContext ctx, Msg msg, Object handler) {
		return onlines.getUserId(ctx) != null;
	}

}
