package cn.milai.ibserver.landvsair.ex;

/**
 * 用户加入的房间未找到
 * @author milai
 * @date 2021.05.24
 */
public class NoJoinedRoomException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoJoinedRoomException(long userId) {
		super(String.format("未找到用户已经加入的房间: userId = %s", userId));
	}

}
