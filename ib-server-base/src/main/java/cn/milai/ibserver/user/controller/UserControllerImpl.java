package cn.milai.ibserver.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.milai.common.decoupling.Resp;
import cn.milai.ibserver.user.service.UserService;
import cn.milai.ibserver.user.service.dto.LoginReq;
import cn.milai.ibserver.user.service.dto.LoginResp;

/**
 * {@link UserController} 默认实现
 * @author milai
 * @date 2021.01.16
 */
@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

	@Autowired
	private UserService us;

	@Override
	@PostMapping("/login")
	public Resp<LoginResp> login(LoginReq req) {
		return us.login(req);
	}

}
