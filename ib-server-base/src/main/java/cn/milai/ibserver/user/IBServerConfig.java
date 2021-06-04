package cn.milai.ibserver.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import cn.milai.nexus.EnableNexus;

/**
 * ibserver 配置
 * @author milai
 * @date 2021.01.14
 */
@EnableNexus
@Configuration
public class IBServerConfig {

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		return template;
	}

}
