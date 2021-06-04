package cn.milai.ibserver.landvsair.container.plugin;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import cn.milai.ib.Controllable;
import cn.milai.ib.container.listener.ContainerListener;
import cn.milai.ib.container.listener.ContainerListeners;
import cn.milai.ib.container.plugin.control.AbstractControlPlugin;
import cn.milai.ib.container.plugin.control.CmdQueue;
import cn.milai.ib.container.plugin.control.ControlPlugin;
import cn.milai.ib.container.plugin.control.cmd.Cmd;
import cn.milai.ibserver.landvsair.handler.msg.ClientCmd;

/**
 * 服务端 {@link ControlPlugin} 实现
 * @author milai
 * @date 2021.05.31
 */
@Component
public class ServerControlPlugin extends AbstractControlPlugin implements ControlPlugin {

	private static final int CMD_PER_FRAME = 10;

	private Map<Long, CmdQueue> userQueues = new ConcurrentHashMap<>();

	@Override
	protected List<ContainerListener> newListeners() {
		return Arrays.asList(ContainerListeners.refreshListener(c -> {
			long start = System.currentTimeMillis();
			List<Controllable> controllables = sortedControllables();
			for (Long userId : userQueues.keySet()) {
				CmdQueue queue = userQueues.get(userId);
				for (int i = 0; i < CMD_PER_FRAME; i++) {
					if (!queue.runNext(controllables)) {
						break;
					}
				}
			}
			metric(KEY_DELAY, System.currentTimeMillis() - start);
		}));
	}

	@Override
	public void addCmd(Cmd cmd) {
		if (!(cmd instanceof ClientCmd)) {
			return;
		}
		ClientCmd c = (ClientCmd) cmd;
		userQueues.computeIfAbsent(c.getUserId(), id -> new CmdQueue()).add(cmd);
	}

	@Override
	public void clearCmds() {
		for (CmdQueue queue : userQueues.values()) {
			queue.clear();
		}
	}

}
