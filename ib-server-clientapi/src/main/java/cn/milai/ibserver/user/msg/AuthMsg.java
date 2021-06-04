package cn.milai.ibserver.user.msg;

/**
 * 身份验证消息
 * @author milai
 * @date 2020.12.26
 */
public class AuthMsg {

	private long userId;
	private String token;

	public AuthMsg() {
	}

	public AuthMsg(long userId, String token) {
		this.userId = userId;
		this.token = token;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
