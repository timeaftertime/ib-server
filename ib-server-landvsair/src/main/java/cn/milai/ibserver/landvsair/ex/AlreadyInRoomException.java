package cn.milai.ibserver.landvsair.ex;

/**
 * 用户(加入房间时)已在房间里的异常
 * @author milai
 * @date 2021.05.24
 */
public class AlreadyInRoomException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyInRoomException(long userId, long roomId) {
		super(String.format("用户已房间里: userId = %s, roomId = %s", userId, roomId));
	}

}
