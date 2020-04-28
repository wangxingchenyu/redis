package com.zhileiedu.spring.redis.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.stereotype.Component;

/**
 * @Author: wzl
 * @Date: 2020/4/28 11:25
 */
@Component
public class TestRedis {

	@Autowired
	RedisTemplate redisTemplate;

	@Autowired
	@Qualifier("ooxx")
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	ObjectMapper objectMapper;

	public void getSetMessage() throws InterruptedException {

		// 执行插入数据
		Jackson2HashMapper jm = new Jackson2HashMapper(objectMapper, false);

		Person person = new Person("alex", 10, "wangzhilei@jd.com");

		stringRedisTemplate.opsForHash().putAll("alex1", jm.toHash(person));


		RedisConnection connection = stringRedisTemplate.getConnectionFactory().getConnection();

		connection.subscribe(new MessageListener() {
			@Override
			public void onMessage(Message message, byte[] bytes) {
				byte[] body = message.getBody();
				System.out.println("receive Message:" + new String(body));
			}
		}, "hello".getBytes());


		/**
		 * 发布，订阅
		 */
		while (true) {
			stringRedisTemplate.convertAndSend("hello", "messageFromMySelf");
			Thread.sleep(3000);
		}


	}


}
