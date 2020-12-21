package cn.milai.ib.server.sock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.milai.ib.server.sock.Network.Message;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

@Configuration
public class NettyConfig {

	@Autowired
	private ServerHandler serverHandler;

	@Bean
	public ServerBootstrap bootstrap() {
		ServerBootstrap b = new ServerBootstrap();
		b.group(boss(), worker());
		b.channel(NioServerSocketChannel.class);
		b.childHandler(childHandler());
		return b;
	}

	@Bean
	public ChannelHandler childHandler() {
		return new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel ch) throws Exception {
				ch.pipeline().addLast(new ProtobufEncoder());
				ch.pipeline().addLast(new ProtobufDecoder(Message.getDefaultInstance()));
				ch.pipeline().addLast(serverHandler);
			}
		};
	}

	@Bean
	public EventLoopGroup boss() {
		return new NioEventLoopGroup();
	}

	public EventLoopGroup worker() {
		return new NioEventLoopGroup();
	}

}
