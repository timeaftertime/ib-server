package cn.milai.ibserver.landvsair.handler.msg;

import cn.milai.ib.container.plugin.control.cmd.BaseCmd;

/**
 * {@link ClientCmd} 默认实现
 * @author milai
 * @date 2021.05.31
 */
public class BaseClientCmd extends BaseCmd implements ClientCmd {

	private long userId;

	public BaseClientCmd(int type, long userId) {
		super(type);
		this.userId = userId;
	}

	@Override
	public long getUserId() { return userId; }

}
