package cn.milai.ibserver.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 用户生成 Redis key 工具类
 * 2020.01.28
 * @author milai
 */
@Component
public class RedisKey {

	@Value("${spring.application.name}")
	private String appName;

	/**
	 * 用户所连接的服务器 IP
	 * @param userId
	 * @return
	 */
	public String linkedServer(long userId) {
		return String.format("%s|linkedServer|%d", appName, userId);
	}

}
