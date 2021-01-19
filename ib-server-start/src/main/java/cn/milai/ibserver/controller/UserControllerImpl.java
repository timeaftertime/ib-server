package cn.milai.ibserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.milai.common.api.Resp;
import cn.milai.ibserver.service.UserService;
import cn.milai.ibserver.service.dto.LoginReq;
import cn.milai.ibserver.service.dto.LoginResp;

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
