package cn.milai.ibserver.landvsair.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.milai.ib.container.DramaContainer;
import cn.milai.ib.container.plugin.control.ControlPlugin;
import cn.milai.ibserver.landvsair.RunningRoomService;
import cn.milai.ibserver.landvsair.cmd.CmdType;
import cn.milai.ibserver.landvsair.handler.msg.BaseClientCmd;
import cn.milai.ibserver.landvsair.handler.msg.ClientPointCmd;
import cn.milai.ibserver.landvsair.msg.ControlMsg;
import cn.milai.ibserver.landvsair.msg.LandVSAirMsgCode;
import cn.milai.ibserver.user.handler.ServerOnlineHandler;
import cn.milai.nexus.annotation.ExceptionHandler;
import cn.milai.nexus.annotation.MsgController;
import cn.milai.nexus.annotation.MsgMapping;
import io.netty.channel.ChannelHandlerContext;

/**
 * {@link ControlMsg} 处理器
 * @author milai
 * @date 2021.05.03
 */
@MsgController
public class ControlHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ControlHandler.class);

	@Autowired
	private RunningRoomService runningRoomService;

	@Autowired
	private ServerOnlineHandler onlineHandler;

	@ExceptionHandler(IllegalArgumentException.class)
	public void handleParamIllegal(ChannelHandlerContext ctx, IllegalArgumentException e) {
		LOG.debug("非法参数: e = {}", e.getMessage());
	}

	@MsgMapping({ LandVSAirMsgCode.CONTROL })
	public void onControlMsg(ChannelHandlerContext ctx, ControlMsg msg) {
		long userId = onlineHandler.getUserId(ctx);
		DramaContainer container = (DramaContainer) runningRoomService.getUserContainer(userId);
		CmdType type = CmdType.of(msg.getType());
		if (CmdType.isKeyCmd(type)) {
			container.fire(ControlPlugin.class, c -> c.addCmd(new BaseClientCmd(type.getValue(), userId)));
			return;
		}
		if (CmdType.isMouseCmd(type)) {
			container.fire(ControlPlugin.class, c -> {
				c.addCmd(new ClientPointCmd(type.getValue(), msg.getX(), msg.getY(), userId));
			});
			return;
		}
		throw new IllegalArgumentException(String.format("未知 Control 消息类型: msg = %s", msg));
	}

}
