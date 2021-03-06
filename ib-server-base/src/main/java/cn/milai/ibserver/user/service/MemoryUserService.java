package cn.milai.ibserver.user.service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;

import cn.milai.common.base.Digests;
import cn.milai.common.base.Randoms;
import cn.milai.common.decoupling.Resp;
import cn.milai.ibserver.user.service.dto.LoginReq;
import cn.milai.ibserver.user.service.dto.LoginResp;

/**
 * 无鉴权、不持久化的用户服务默认实现
 * @author milai
 * @date 2020.12.27
 */
@Configuration
@ConditionalOnMissingBean(value = UserService.class)
public class MemoryUserService implements UserService {

	private static final int RAND_LENGTH = 8;

	private AtomicLong nowUserId = new AtomicLong();

	private Map<Long, String> idToToken = new ConcurrentHashMap<>();
	private Map<String, Long> tokenToId = new ConcurrentHashMap<>();

	@Override
	public Resp<LoginResp> login(LoginReq req) {
		long userId = nowUserId.incrementAndGet();
		String token = Digests.sha256(
			String.format("%d_%d_%s", userId, new Date().getTime(), Randoms.fixedLowerDigit(RAND_LENGTH))
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
