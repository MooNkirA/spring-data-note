package com.moon.springdata.redis.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

/**
 * Spring Data Redis Set 类型使用测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-03 18:29
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml")
public class RedisSetTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private SetOperations<String, String> ops;

    // 测试使用的键
    private final static String KEY = "key-set";
    private final static String KEY1 = "key-set1";
    private final static String KEY2 = "key-set2";

    @Before
    public void init() {
        // 指定非 hash 类型的数据序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        // 获取操作数据句柄
        ops = redisTemplate.opsForSet();
    }

    /* 增加数据 */
    @Test
    public void testAdd() {
        // 增加一个或多个数据，如果重复的数据不会增加
        ops.add(KEY, "夜神月", "香风智乃", "樱木花道", "仙道彰", "路飞", "女帝波雅·汉库克", "夜神月");
    }

    /* 查询所有元素 */
    @Test
    public void testMembers() {
        Set<String> members = ops.members(KEY);
        if (members != null) {
            members.forEach(System.out::println);
        }
    }

    /* 随机获取一个元素 */
    @Test
    public void testRandomMember() {
        String value = ops.randomMember(KEY);
        System.out.println("随机获取一个元素：" + value);
    }

    /* 随机获取多个元素（可能会获取重复的值） */
    @Test
    public void testRandomMembers() {
        // 随机获取多个元素，count 参数指定获取元素的个数。
        List<String> randomMembers = ops.randomMembers(KEY, 3);
        if (randomMembers != null) {
            randomMembers.forEach(System.out::println);
        }
    }

    /* 删除元素 */
    @Test
    public void testRemove() {
        // 删除指定的元素，并返回成功移除的个数
        Long count = ops.remove(KEY, "樱木花道", "仙道彰", "路飞");
        System.out.println("成功移除的元素总数：" + count);
    }

    /* 随机删除元素 */
    @Test
    public void testPop() {
        // 随机删除指定个数的元素，并返回成功移除的元素
        List<String> pops = ops.pop(KEY, 2);
        if (pops != null) {
            pops.forEach(System.out::println);
        }
    }

    /* 多集合操作 - 交集 */
    @Test
    public void testIntersect() {
        ops.add(KEY1, "夜神月", "香风智乃", "樱木花道");
        ops.add(KEY2, "路飞", "女帝波雅·汉库克", "夜神月");

        // 获取两个key的值的交集
        Set<String> result = ops.intersect(KEY1, KEY2);
        if (result != null) {
            result.forEach(System.out::println);
        }
    }

    /* 多集合操作 - 并集 */
    @Test
    public void testUnion() {
        ops.add(KEY1, "夜神月", "香风智乃", "樱木花道");
        ops.add(KEY2, "路飞", "女帝波雅·汉库克", "夜神月");

        // 获取两个key的值的交集
        Set<String> result = ops.union(KEY1, KEY2);
        if (result != null) {
            result.forEach(System.out::println);
        }
    }

    /* 多集合操作 - 差集 */
    @Test
    public void testDifference() {
        ops.add(KEY1, "夜神月", "香风智乃", "樱木花道");
        ops.add(KEY2, "路飞", "女帝波雅·汉库克", "夜神月");

        /*
         * 获取两个key的值的差集
         *  规则是获取第一个集合中存在，但是在第二个集合中不存在的元素
         */
        Set<String> result = ops.difference(KEY2, KEY1);
        if (result != null) {
            result.forEach(System.out::println);
        }
    }

}
