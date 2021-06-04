package cn.milai.ibserver.landvsair.handler.msg;

import cn.milai.ib.container.plugin.control.cmd.Cmd;

/**
 * 来自客户端的 {@link Cmd}
 * @author milai
 * @date 2021.05.31
 */
public interface ClientCmd extends Cmd {

	/**
	 * 获取发出指令的用户 id
	 * @return
	 */
	long getUserId();
}
