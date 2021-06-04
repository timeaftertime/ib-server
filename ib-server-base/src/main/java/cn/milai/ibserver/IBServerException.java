package cn.milai.ibserver;

/**
 * ib-server 顶级异常
 * @author milai
 * @date 2021.01.01
 */
public class IBServerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IBServerException(Exception e) {
		super(e);
	}

	public IBServerException(String format, Object... args) {
		super(String.format(format, args));
	}

}
