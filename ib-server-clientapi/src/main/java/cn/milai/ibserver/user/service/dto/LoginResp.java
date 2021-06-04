package cn.milai.ibserver.user.service.dto;

/**
 * 登录响应
 * @author milai
 * @date 2020.12.27
 */
public class LoginResp {

	/**
	 * 用户 id
	 */
	private Long userId;

	/**
	 * 用户登录状态标识
	 */
	private String token;

	public LoginResp(long userId, String token) {
		this.userId = userId;
		this.token = token;
	}

	public Long getUserId() {
		return userId;
	}

	public String getToken() {
		return token;
	}

}
