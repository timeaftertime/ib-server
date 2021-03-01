package cn.milai.ibserver.service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import cn.milai.common.api.Resp;
import cn.milai.common.base.Digests;
import cn.milai.common.base.Randoms;
import cn.milai.ibserver.service.dto.LoginReq;
import cn.milai.ibserver.service.dto.LoginResp;

/**
 * 无鉴权、不持久化的用户服务默认实现
 * @author milai
 * @date 2020.12.27
 */
public class MemoryUserService implements UserService {

	private static final int RAND_LENGTH = 8;

	private AtomicLong nowUserId = new AtomicLong();

	private Map<Long, String> idToToken = new ConcurrentHashMap<>();
	private Map<String, Long> tokenToId = new ConcurrentHashMap<>();

	@Override
	public Resp<LoginResp> login(LoginReq req) {
		long userId = nowUserId.incrementAndGet();
		String token = Digests.sha256(
			String.format("%d_%d_%s", userId, new Date().getTime(), Randoms.randLowerOrDigit(RAND_LENGTH))
		);
		tokenToId.put(token, userId);
		idToToken.put(userId, token);
		return Resp.success(new LoginResp(userId, token));
	}

	@Override
	public Resp<Long> getIdByToken(String token) {
		return Resp.success(tokenToId.get(token));
	}

}
