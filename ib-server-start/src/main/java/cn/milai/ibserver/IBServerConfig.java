package cn.milai.ibserver;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import cn.milai.ibserver.service.MemoryUserService;
import cn.milai.ibserver.service.UserService;

/**
 * ibserver 配置
 * @author milai
 * @date 2021.01.14
 */
@Configuration
public class IBServerConfig {

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		return template;
	}

	@Bean
	@ConditionalOnMissingBean(value = UserService.class)
	public MemoryUserService memoryUserService() {
		return new MemoryUserService();
	}

}
