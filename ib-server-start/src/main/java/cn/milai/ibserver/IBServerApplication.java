package cn.milai.ibserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.milai.nexus.EnableNexus;

@EnableNexus
@SpringBootApplication
public class IBServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IBServerApplication.class, args);
	}

}
