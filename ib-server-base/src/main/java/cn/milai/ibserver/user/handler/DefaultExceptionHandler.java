package cn.milai.ibserver.user.handler;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.milai.nexus.handler.catcher.ExceptionCatcher;
import io.netty.channel.ChannelHandlerContext;

/**
 * 默认 Socket 异常处理器
 * @author milai
 * @date 2020.12.27
 */
@Component
public class DefaultExceptionHandler implements ExceptionCatcher {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultExceptionHandler.class);

	@Autowired
	private ServerOnlineHandler onlines;

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LOG.error("userId = {}, e = {}", onlines.getUserId(ctx), ExceptionUtils.getStackTrace(cause));
		onlines.disconnect(ctx);
	}

}
