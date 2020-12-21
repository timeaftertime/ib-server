package cn.milai.ib.server.web.service;

import cn.milai.common.api.Resp;

/**
 * Token 服务
 * @author milai
 * @date 2020.12.21
 */
public interface TokenService {

	/**
	 * 生成并保存指定用户 id 对应的 Token，若成功, 返回生成的 Token
	 * @param id
	 * @return
	 */
	Resp<String> createAndSetToken(int id);

	/**
	 * 获取 token 对应的用户 id
	 * @param token
	 * @return
	 */
	Resp<Integer> tokenToId(String token);

}
