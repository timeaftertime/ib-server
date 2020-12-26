package cn.milai.ibserver.web.controller.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserVO {

	private Integer id;
	@NotNull
	@Length(min = 1, max = 16, message = "用户名长度为 1~16位")
	private String username;
	@NotNull
	@Length(min = 1, max = 32, message = "密码长度不合法")
	private String password;

	public interface LOGIN {

	}

	public interface REGISTER {

	}

}
