package cn.milai.ibserver.landvsair.ex;

/**
 * 指定 roomId 的房间已经在运行的异常
 * @author milai
 * @date 2021.05.25
 */
public class RoomAlreadyRunningException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoomAlreadyRunningException(long roomId) {
		super(String.format("房间已在运行: roomId = %s", roomId));
	}
}
