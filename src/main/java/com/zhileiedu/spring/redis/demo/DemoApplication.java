package com.zhileiedu.spring.redis.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext cxt = SpringApplication.run(DemoApplication.class, args);

		TestRedis redis = cxt.getBean(TestRedis.class);
		try {
			redis.getSetMessage();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
