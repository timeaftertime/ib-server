package cn.milai.ibserver.landvsair;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.milai.ib.container.Container;
import cn.milai.ibserver.landvsair.ex.AlreadyInRoomException;
import cn.milai.ibserver.landvsair.ex.NoJoinedRoomException;
import cn.milai.ibserver.landvsair.ex.RoomAlreadyRunningException;
import cn.milai.ibserver.landvsair.ex.RoomNotFoundException;

/**
 * {@link RunningRoom} 管理器
 * @author milai
 * @date 2021.05.23
 */
@Component
public class RunningRoomService {

	private Map<Long, RunningRoom> runningRooms = new HashMap<>();

	private Map<Long, RunningRoom> userJoinedRoom = new HashMap<>();

	/**
	 * 添加一个指定 id 和 {@link Container} 的 {@link RunningRoom}
	 * @param roomId
	 * @param container
	 */
	public void runRoom(long roomId, Container container) {
		synchronized (runningRooms) {
			if (runningRooms.containsKey(roomId)) {
				throw new RoomAlreadyRunningException(roomId);
			}
			runningRooms.put(roomId, new RunningRoom(roomId, container));
		}
	}

	/**
	 * 移除指定 id 对应的 {@link RunningRoom}
	 * @param roomId
	 */
	public void stopRoom(long roomId) {
		synchronized (runningRooms) {
			if (runningRooms.get(roomId) == null) {
				throw new RoomNotFoundException(roomId);
			}
			runningRooms.remove(roomId);
		}
	}

	/**
	 * 获取指定 {@code userId} 关联的 {@link Container}
	 * @param userId
	 * @return
	 */
	public Container getUserContainer(long userId) {
		return roomOfUser(userId).getContainer();
	}

	/**
	 * 使指定 userId 加入指定 {@link RunningRoom}
	 * @param userId
	 * @param roomId
	 * @return 是否真正进行了加入操作
	 * @throws 如果已经在其他房间里
	 */
	public boolean join(long userId, long roomId) throws AlreadyInRoomException {
		RunningRoom room = userJoinedRoom.get(userId);
		if (room != null && room.getId() != roomId) {
			throw new AlreadyInRoomException(userId, room.getId());
		}
		return roomOf(roomId).addUserId(userId);
	}

	/**
	 * 使一个 userId 退出已经加入的 {@link RunningRoom}
	 * @param userId
	 * @return 是否真正进行了移除操作
	 * @throws NoJoinedRoomException 如果 userId 尚未加入 {@link RunningRoom}
	 */
	public boolean quit(long userId) throws NoJoinedRoomException {
		return roomOfUser(userId).removeUserId(userId);
	}

	private RunningRoom roomOf(long roomId) {
		RunningRoom room = runningRooms.get(roomId);
		if (room == null) {
			throw new RoomNotFoundException(roomId);
		}
		return room;
	}

	private RunningRoom roomOfUser(long userId) {
		RunningRoom room = userJoinedRoom.get(userId);
		if (room == null) {
			throw new NoJoinedRoomException(userId);
		}
		return room;
	}
}
