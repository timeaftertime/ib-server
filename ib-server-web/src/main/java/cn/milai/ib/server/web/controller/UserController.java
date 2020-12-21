package cn.milai.ib.server.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.milai.common.api.Resp;
import cn.milai.ib.server.web.controller.vo.UserVO;
import cn.milai.ib.server.web.controller.vo.UserVO.LOGIN;
import cn.milai.ib.server.web.controller.vo.UserVO.REGISTER;
import cn.milai.ib.server.web.service.TokenService;
import cn.milai.ib.server.web.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService us;
	@Autowired
	private TokenService ts;

	@PostMapping("/login")
	public Resp<String> login(@Validated(LOGIN.class) UserVO user) {
		log.info("登录请求, user = {}", user);
		Resp<Void> loginResp = us.login(user);
		if (!loginResp.isSuccess()) {
			return Resp.fail(loginResp);
		}
		return ts.createAndSetToken(user.getId());
	}

	@PostMapping("/register")
	public Resp<Void> register(@Validated(REGISTER.class) UserVO user) {
		log.info("注册请求, user = {}", user);
		return us.register(user);
	}

}
