package cn.milai.ibserver.landvsair.ex;

/**
 * 房间未找到异常
 * @author milai
 * @date 2021.05.24
 */
public class RoomNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoomNotFoundException(long roomId) {
		super(String.format("房间未找到: roomId = %s", roomId));
	}

}
