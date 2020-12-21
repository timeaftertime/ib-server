package cn.milai.ib.server;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.netty.channel.ChannelHandlerContext;

@MapperScan(basePackages = { "cn.milai.ib.server.web.dao" })
@Configuration
public class IBServerConfig {

	@Bean
	public Map<Integer, ChannelHandlerContext> onlines() {
		return new ConcurrentHashMap<>();
	}

	@Bean
	public Set<ChannelHandlerContext> waitForToken() {
		return new HashSet<>();
	}

}
