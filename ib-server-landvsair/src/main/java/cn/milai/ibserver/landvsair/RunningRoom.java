package cn.milai.ibserver.landvsair;

import java.util.ArrayList;
import java.util.List;

import cn.milai.ib.container.Container;

/**
 * 房间
 * @author milai
 * @date 2021.05.22
 */
public class RunningRoom {

	private long id;

	private List<Long> userIds = new ArrayList<>();

	private Container container;

	public RunningRoom(long id, Container container) {
		this.id = id;
		this.container = container;
	}

	public long getId() { return id; }

	public Container getContainer() { return container; }

	/**
	 * 添加指定 {@code userId} 到房间用户 id 列表 
	 * @param userId
	 * @return 是否真正进行了添加操作
	 */
	public synchronized boolean addUserId(long userId) {
		if (userIds.contains(userId)) {
			return false;
		}
		return userIds.add(userId);
	}

	/**
	 * 从房间用户 id 列表移除指定 {@code userId} 
	 * @param userId
	 * @return 是否真正进行了移除操作
	 */
	public synchronized boolean removeUserId(long userId) {
		return userIds.remove(userId);
	}

}