package cn.milai.ibserver.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.milai.common.api.Resp;
import cn.milai.ibserver.web.controller.vo.UserVO;
import cn.milai.ibserver.web.controller.vo.UserVO.LOGIN;
import cn.milai.ibserver.web.controller.vo.UserVO.REGISTER;
import cn.milai.ibserver.web.service.TokenService;
import cn.milai.ibserver.web.service.UserService;
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
	public Resp<String> login(@Validated(LOGIN.class) UserVO user, HttpServletResponse response) {
		log.info("登录请求, user = {}", user);
		Resp<Void> loginResp = us.login(user);
		if (!loginResp.isSuccess()) {
			return Resp.fail(loginResp);
		}
		Resp<String> resp = ts.createAndSetToken(user.getId());
		if (resp.isSuccess()) {
			response.addCookie(new Cookie("ib_token", resp.getData()));
		}
		return resp;
	}

	@PostMapping("/register")
	public Resp<Void> register(@Validated(REGISTER.class) UserVO user) {
		log.info("注册请求, user = {}", user);
		return us.register(user);
	}

}
