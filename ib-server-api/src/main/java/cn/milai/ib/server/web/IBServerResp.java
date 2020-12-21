package cn.milai.ib.server.web;

import cn.milai.common.api.RespCode;

/**
 * 状态码枚举
 * @author milai
 * @date 2020.12.21
 */
public enum IBServerResp implements RespCode {

	UNKNOWN_ERROR("UNKNOWN_ERROR", "系统异常"),
	USERNAME_NOT_EXISTS("USERNAME_NOT_EXISTS", "用户不存在"),
	PASSWORD_ERROR("PASSWORD_ERROR", "密码错误"),
	USERNAME_ALREADY_EXISTS("USERNAME_ALREADY_EXISTS", "用户名已经存在"),
	INVALID_TOKEN("INVALID_TOKEN", "无效 Token"),
	;

	private String code;
	private String desc;

	IBServerResp(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getDesc() {
		return desc;
	}

}
