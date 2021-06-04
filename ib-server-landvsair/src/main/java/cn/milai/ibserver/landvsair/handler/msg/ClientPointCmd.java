package cn.milai.ibserver.landvsair.handler.msg;

import cn.milai.ib.container.plugin.control.cmd.PointCmd;

/**
 * 
 * @author milai
 * @date 2021.05.31
 */
public class ClientPointCmd extends PointCmd implements ClientCmd {

	private long userId;

	public ClientPointCmd(int type, double x, double y, long userId) {
		super(type, x, y);
		this.userId = userId;
	}

	@Override
	public long getUserId() { return userId; }

}
