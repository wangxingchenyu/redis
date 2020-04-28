package com.zhileiedu.spring.redis.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @Author: wzl
 * @Date: 2020/4/28 16:08
 * 自定义redisTemplate
 */
@Configuration
public class MyRedisTempalate {

	@Bean
	public StringRedisTemplate ooxx(RedisConnectionFactory fc) {
		StringRedisTemplate tp = new StringRedisTemplate(fc);
		tp.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
		return tp;
	}


}
