package cn.milai.ibserver.user.service.dto;

/**
 * 登录请求
 * @author milai
 * @date 2020.12.27
 */
public class LoginReq {

	/**
	 * 用户名，长度 6~16，只包含数字、字母、下划线
	 */
	private String username;

	/**
	 * SHA256 后的十六进制密码字符串，长度固定 64，只包含大写字母、数字
	 */
	private String password;

	public LoginReq(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
