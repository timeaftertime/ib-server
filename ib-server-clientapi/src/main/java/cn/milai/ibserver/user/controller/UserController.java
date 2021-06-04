package cn.milai.ibserver.user.controller;

import cn.milai.common.decoupling.Resp;
import cn.milai.ibserver.user.service.dto.LoginReq;
import cn.milai.ibserver.user.service.dto.LoginResp;

/**
 * 用户相关前端接口
 * @author milai
 * @date 2021.01.16
 */
public interface UserController {

	/**
	 * 登录
	 * @param user
	 * @return
	 */
	Resp<LoginResp> login(LoginReq req);
}
