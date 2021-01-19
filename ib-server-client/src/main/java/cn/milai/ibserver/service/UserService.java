package cn.milai.ibserver.service;

import cn.milai.common.api.Resp;
import cn.milai.ibserver.service.dto.LoginReq;
import cn.milai.ibserver.service.dto.LoginResp;

/**
 * token 服务
 * @author milai
 * @date 2020.12.21
 */
public interface UserService {

	/**
	 * 使用指定用户信息登录，若成功，返回对应的用户 id 和 token
	 * @param req
	 * @return
	 */
	Resp<LoginResp> login(LoginReq req);

	/**
	 * 获取 token 对应的用户 id，若不存在，返回 null
	 * @param token
	 * @return
	 */
	Resp<Long> getIdByToken(String token);

}
