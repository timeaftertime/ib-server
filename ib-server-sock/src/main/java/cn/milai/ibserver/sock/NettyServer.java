package cn.milai.ibserver.sock;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;

@Component
public class NettyServer {

	@Autowired
	private ServerBootstrap b;
	
	private static final int PORT = 8080;
	
	@PostConstruct
	public void start() throws InterruptedException {
		b.bind(PORT).sync();
	}
	
}
