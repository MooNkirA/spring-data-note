package com.moon.springdata.redis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring Data Redis 基础使用测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-03 18:29
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml") // 加载配置文件
public class RedisBasicTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testSave() {
        // 指定非 hash 类型的数据序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        // 获取操作简单字符串类型数据的数据句柄
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        // 存入数据
        ops.set("SpringDataRedisTest1", "MooNkirA");

        // 查询数据
        String value = ops.get("SpringDataRedisTest");
        System.out.println("查询的数据：" + value);
    }

}
