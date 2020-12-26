package cn.milai.ibserver.web.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import cn.milai.common.api.Resp;
import cn.milai.ibserver.web.IBServerResp;
import cn.milai.ibserver.web.service.TokenService;
import cn.milai.ibserver.web.util.MD5Util;
import cn.milai.ibserver.web.util.RedisKeyUtil;
import cn.milai.ibserver.web.util.StringUtil;

@Service
public class TokenServiceImpl implements TokenService {

	private static final int RANDOM_STR_LEN = 4;
	private static final Duration TOKEN_TIMEOUT = Duration.ofMinutes(10);

	@Autowired
	private StringRedisTemplate srt;

	@Override
	public Resp<String> createAndSetToken(int id) {
		String token = MD5Util.md5(id + StringUtil.random(RANDOM_STR_LEN) + System.nanoTime());
		srt.opsForValue().set(RedisKeyUtil.TOKEN + token, id + "", TOKEN_TIMEOUT);
		return Resp.success(token);
	}

	@Override
	public Resp<Integer> tokenToId(String token) {
		String idStr = srt.opsForValue().get(token);
		if (idStr == null) {
			return Resp.fail(IBServerResp.INVALID_TOKEN);
		}
		return Resp.success(Integer.parseInt(idStr));
	}

}
