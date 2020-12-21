package cn.milai.ib.server.web.service;

import cn.milai.common.api.Resp;
import cn.milai.ib.server.web.controller.vo.UserVO;

/**
 * 用户服务
 * @author milai
 * @date 2020.12.21
 */
public interface UserService {

	/**
	 * 使用指定用户信息登录
	 * @param user
	 * @return
	 */
	Resp<Void> login(UserVO user);

	/**
	 * 使用指定用户信息注册
	 * @param user
	 * @return
	 */
	Resp<Void> register(UserVO user);

}
