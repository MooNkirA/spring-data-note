package com.moon.springdata.redis.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Spring Data Redis 字符串类型使用测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-03 18:29
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml")
public class RedisStringTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private ValueOperations<String, String> ops;

    @Before
    public void init() {
        // 指定非 hash 类型的数据序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        // 获取操作数据句柄
        ops = redisTemplate.opsForValue();
    }

    /* 保存数据 */
    @Test
    public void testSet() {
        // 存入数据
        ops.set("key1", "MooNkirA");
    }

    /* 保存有效时间的数据 */
    @Test
    public void testSetWithTimeout() {
        // 存入数据，有效时间为10s
        ops.set("key2", "N", 10, TimeUnit.SECONDS);
    }

    /* 保存并替换指定范围的原值 */
    @Test
    public void testSetWithOffset() {
        // 存入数据，从指定的索引开始替换。如果 key 值原不存在，则新增键，但值为空。
        ops.set("key1", "aa", 2);
    }

    /* 当key不存在时保存 */
    @Test
    public void testSetIfAbsent() {
        // 当 key 不存在时执行保存操作；当 key 存在时则什么都不做
        ops.setIfAbsent("key3", "斩月");
    }

    /* 批量保存 */
    @Test
    public void testMultiSet() {
        Map<String, String> map = new HashMap<>();
        for (int i = 4; i < 8; i++) {
            map.put("key" + i, "天锁斩月");
        }
        // 当 key 不存在时执行保存操作；当 key 存在时则什么都不做
        ops.multiSet(map);
    }

    /* 追加保存 */
    @Test
    public void testAppend() {
        // 追加保存，当key存在时，会执行追加操作；当key不存在时，会执行保存操作
        ops.append("key7", "appendString");
    }

    /* 根据键查询 */
    @Test
    public void testGet() {
        // 根据键获取值
        String value = ops.get("key1");
        System.out.println(value);
    }

    /* 根据键查询并对值截取 */
    @Test
    public void testGetSubstring() {
        /*
         * 首先根据 key 获取 value，然后再根据 value 进行截取，从start位置截取到end位置（包含start和end）
         *  K key：键
         *  long start：截取的开始位置
         *  long end：截取的结束位置
         */
        String value = ops.get("key1", 1, 3);
        System.out.println(value);
    }

    /* 批量获取 */
    @Test
    public void testMultiGet() {
        /*
         * 首先根据 key 获取 value，然后再根据 value 进行截取，从start位置截取到end位置（包含start和end）
         *  K key：键
         *  long start：截取的开始位置
         *  long end：截取的结束位置
         */
        List<String> list = ops.multiGet(Arrays.asList("key1", "key4", "key5"));
        if (list != null) {
            list.forEach(System.out::println);
        }
    }

    /* 获取 value 字符的长度 */
    @Test
    public void testSize() {
        // 根据key获取value的长度
        Long length = ops.size("key1");
        System.out.println("key1的值的长度：" + length);
    }

    /* value 自增 */
    @Test
    public void testIncrement() {
        String key = "testIncrement";
        ops.set(key, "18");
        // 默认自增 1
        ops.increment(key);
        System.out.println(ops.get(key)); // 结果：19
        // 指定自增 5
        ops.increment(key, 5);
        System.out.println(ops.get(key)); // 结果：24
    }

    /* value 自减 */
    @Test
    public void testDecrement() {
        String key = "testDecrement";
        ops.set(key, "18");
        // 默认自减 1
        ops.decrement(key);
        System.out.println(ops.get(key)); // 结果：17
        // 指定自减 5
        ops.decrement(key, 5);
        System.out.println(ops.get(key)); // 结果：12
    }

    /* 删除单个键。字符串类型的删除是通过 redisTemplate 对象删除整个键 */
    @Test
    public void testDelete() {
        // 删除单个键
        redisTemplate.delete("testIncrement");
    }

    /* 批量删除多个键 */
    @Test
    public void testMultiDelete() {
        // 删除多个键
        redisTemplate.delete(Arrays.asList("key4", "key5", "key6"));
    }

}
