package com.moon.springdata.redis.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
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
 * Spring Data Redis list 类型使用测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-03 18:29
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml")
public class RedisListTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private ListOperations<String, String> ops;

    private final static String KEY = "key-list"; // 测试使用的键

    @Before
    public void init() {
        // 指定非 hash 类型的数据序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        // 获取操作数据句柄
        ops = redisTemplate.opsForList();
    }

    /* 左增加数据 */
    @Test
    public void testLeftPush() {
        // 将所有指定的值插入存储在键的列表的头部（或尾部）。如果键不存在，则在执行推送操作之前将其创建为空列表。
        // 从左边添加一个元素
        ops.leftPush(KEY, "L");
        // 从左边添加多个元素
        ops.leftPushAll(KEY, "夜神月", "香风智乃", "樱木花道");
    }

    /* 右增加数据 */
    @Test
    public void testRightPush() {
        // 将所有指定的值插入存储在键的列表的头部（或尾部）。如果键不存在，则在执行推送操作之前将其创建为空列表。
        // 从右边添加一个元素
        ops.rightPush(KEY, "流川枫");
        // 从右边添加多个元素
        ops.rightPushAll(KEY, "仙道彰", "路飞", "女帝波雅·汉库克");
    }

    /* 根据索引获取数据 */
    @Test
    public void testFindIndex() {
        // 0和正数代表从左边开始查找，第一个元素索引是0
        String val = ops.index(KEY, 1);
        System.out.println("左边第2个元素是：" + val);
        // 负数代表从右边开始查找，第一个元素索引是-1
        String val2 = ops.index(KEY, -2);
        System.out.println("右边第2个元素是：" + val2);
    }

    /* 根据范围获取数据 */
    @Test
    public void testRange() {
        // 根据 key 查询一个范围(包含开始索引,结束索引)，索引从0开始
        List<String> range = ops.range(KEY, 1, 4);
        if (range != null) {
            range.forEach(System.out::println);
        }
    }

    /* 删除左边第一个元素 */
    @Test
    public void testLeftLop() {
        // 弹出左边第1个元素
        String s = ops.leftPop(KEY);
        System.out.println("删除左边第1个元素是：" + s);
    }

    /* 删除右边第一个元素 */
    @Test
    public void testRightPop() {
        // 弹出右边第1个元素
        String s = ops.rightPop(KEY);
        System.out.println("删除右边第1个元素是：" + s);
    }

    /* 删除指定的元素 */
    @Test
    public void testRemove() {
        /*
         * Long remove(K key, long count, Object value)
         *  key：键值
         *  count：删除元素的个数
         *      count > 0：删除左边起第几个等于指定值的元素
         *      count < 0：删除右边起第几个等于指定值的元素
         *      count = 0：删除所有等于value的元素。
         *  value：元素值
         */
        ops.remove(KEY, 1, "L");
    }

}
