package com.moon.springdata.redis.test;

import com.moon.springdata.redis.domain.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Spring Data Redis Hash 类型使用测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-03 18:29
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml")
public class RedisHashTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // private HashOperations<String, String, Object> ops;
    /*
     *  HashOperations<H, HK, HV>
     *      H：指定键的类型
     *      HK：hash 类型值的键类型
     *      HV：hash 类型值的值类型（可以是自定义类，但此类必须实现 Serializable 序列化接口）
     */
    private HashOperations<String, String, Student> ops;

    @Before
    public void init() {
        // 指定非 hash 类型的数据序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer()); // hash 类型值中键的序列化类型
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer()); // hash 类型值中值的序列化类型
        // 获取操作数据句柄
        ops = redisTemplate.opsForHash();
    }

    /* 保存数据 */
    @Test
    public void testPut() {
        Student student = new Student();
        student.setName("夜神月");
        student.setAge(18);
        Student student2 = new Student();
        student2.setName("L");
        student2.setAge(19);

        ops.put("key1", "key1-1", student);
        ops.put("key1", "key1-2", student2);
    }

    /* 判断值中的某个 hashKey 是否存在 */
    @Test
    public void testHasKey() {
        String key = "key1";
        String valueKey = "key1-2";
        Boolean b = ops.hasKey(key, valueKey);
        System.out.println(key + "键中的" + valueKey + "是否存在：" + b);
    }

    /* 根据 key 与 hashKey 获取数据 */
    @Test
    public void testGet() {
        Student s = ops.get("key1", "key1-1");
        System.out.println(s);
    }

    /* 获取指定 key 中所有 hashKey 的集合 */
    @Test
    public void testKeys() {
        Set<String> keys = ops.keys("key1");
        keys.forEach(System.out::println);
    }

    /* 获取指定 key 中所有 hashValue 的集合 */
    @Test
    public void testValues() {
        List<Student> values = ops.values("key1");
        values.forEach(System.out::println);
    }

    /* 获取指定 key 中所有 hash 对象的集合 */
    @Test
    public void testEntries() {
        Map<String, Student> map = ops.entries("key1");
        map.entrySet().forEach(e -> System.out.println(e.getKey() + " :: " + e.getValue()));
    }

    /* 根据 key 与 hashKey 删除数据 */
    @Test
    public void testDelete() {
        // 可以删除多个数据，当 hash 类型中的数据全部被删除后，整个 hash 类型的键也被删除
        Long num = ops.delete("key1", "key1-2", "key1-1");
        System.out.println("成功删除的数量" + num);
    }

}
