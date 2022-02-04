package com.moon.springdata.redis.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

/**
 * Spring Data Redis ZSet 类型使用测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-03 18:29
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml")
public class RedisZSetTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private ZSetOperations<String, String> ops;

    // 测试使用的键
    private final static String KEY = "key-zset";
    private final static String KEY1 = "key-zset1";
    private final static String KEY2 = "key-zset2";

    @Before
    public void init() {
        // 指定非 hash 类型的数据序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        // 获取操作数据句柄
        ops = redisTemplate.opsForZSet();
    }

    /* 增加单个数据 */
    @Test
    public void testAdd() {
        /*
         * Boolean add(K key, V value, double score)
         *  key：键值
         *  value：值
         *  score：分数值，用于排名（排序）
         */
        ops.add(KEY, "夜神月", 190);
        ops.add(KEY, "香风智乃", 90);
        ops.add(KEY, "樱木花道", 25);
        ops.add(KEY, "L", 191);
        ops.add(KEY, "金田一", 180);
    }

    /* 分数的增减 */
    @Test
    public void testIncrementScore() {
        // incrementScore 可以用来增减分数，正数就是增加，负数则减少
        // 增加分数
        ops.incrementScore(KEY, "樱木花道", 10);
        // 减少分数
        ops.incrementScore(KEY, "香风智乃", -20);
    }

    /* 查询元素的分数 */
    @Test
    public void testScore() {
        // 查询元素的分数
        Double score = ops.score(KEY, "金田一");
        System.out.println("分数：" + score);
    }

    /* 查询元素的排名 */
    @Test
    public void testRank() {
        // 查询元素在集合中的排名，排名从0开始
        Long rank = ops.rank(KEY, "夜神月");
        System.out.println("排名：" + rank);
    }

    /* 列表查询:分为两大类，正序和逆序。以下只列表正序的，逆序的只需在方法前加上reverse即可 */
    /* 根据排名区间来获取元素列表 */
    @Test
    public void testRange() {
        // 正序根据排名区间来获取元素列表，包含开始与结束索引，索引从0开始
        Set<String> range = ops.range(KEY, 0, 2);
        if (range != null) {
            range.forEach(System.out::println);
        }

        // 逆序根据排名区间来获取元素列表，包含开始与结束索引，索引从0开始
        Set<String> reverseRange = ops.reverseRange(KEY, 0, 2);
        if (reverseRange != null) {
            reverseRange.forEach(System.out::println);
        }
    }

    /* 根据分数排序区间来获取元素列表 */
    @Test
    public void testRangeWithScores() {
        // 正序根据分数排序区间来获取元素列表，包含开始与结束索引，索引从0开始
        Set<ZSetOperations.TypedTuple<String>> typedTuples = ops.rangeWithScores(KEY, 1, 3);
        if (typedTuples != null) {
            typedTuples.forEach(i -> {
                System.out.println("值：" + i.getValue() + "；分数：" + i.getScore());
            });
        }

        // 逆序根据分数排序区间来获取元素列表，包含开始与结束索引，索引从0开始
        Set<ZSetOperations.TypedTuple<String>> reverseTypedTuples = ops.reverseRangeWithScores(KEY, 1, 3);
        if (reverseTypedTuples != null) {
            reverseTypedTuples.forEach(i -> {
                System.out.println("值：" + i.getValue() + "；分数：" + i.getScore());
            });
        }
    }

    /* 根据分数范围来获取元素列表 */
    @Test
    public void testRangeByScore() {
        // 正序根据指定的最小分数与最大分数范围来获取元素列表（包含最小和最大值）
        Set<String> range = ops.rangeByScore(KEY, 70, 180);
        if (range != null) {
            range.forEach(System.out::println);
        }

        // 逆序根据指定的最小分数与最大分数范围来获取元素列表（包含最小和最大值）
        Set<String> reverseRange = ops.reverseRangeByScore(KEY, 70, 180);
        if (reverseRange != null) {
            reverseRange.forEach(System.out::println);
        }
    }

    /* 根据分数范围来获取元素和分数列表 */
    @Test
    public void testRangeByScoreWithScores() {
        // 正序根据指定的最小分数与最大分数范围来获取元素和分数列表（包含最小和最大值）
        Set<ZSetOperations.TypedTuple<String>> typedTuples = ops.rangeByScoreWithScores(KEY, 70, 190);
        if (typedTuples != null) {
            typedTuples.forEach(i -> System.out.println("值：" + i.getValue() + "；分数：" + i.getScore()));
        }

        // 逆序根据指定的最小分数与最大分数范围来获取元素和分数列表（包含最小和最大值）
        Set<ZSetOperations.TypedTuple<String>> reverseTypedTuples = ops.reverseRangeByScoreWithScores(KEY, 70, 190);
        if (reverseTypedTuples != null) {
            reverseTypedTuples.forEach(i -> System.out.println("值：" + i.getValue() + "；分数：" + i.getScore()));
        }
    }

    /* 统计集合大小 */
    @Test
    public void testZCard() {
        // 统计指定的键的集合大小
        Long zCard = ops.zCard(KEY);
        System.out.println("集合大小：" + zCard);
    }

    /* 统计分数区间的元素数量 */
    @Test
    public void testCount() {
        // 根据指定的最小分数与最大分数范围来统计元素的数量（包含最小和最大值）
        Long count = ops.count(KEY, 70, 190);
        System.out.println("70~190分数的元素总计：" + count);
    }

    /* 删除指定的元素 */
    @Test
    public void testRemove() {
        // 删除指定单个或者多个元素
        ops.remove(KEY, "L", "夜神月");
    }

    /* 根据排名区间删除元素 */
    @Test
    public void testRemoveRange() {
        // 根据排名区间删除，排名是从0开始
        ops.removeRange(KEY, 0, 2);
    }

    /* 根据分数区间删除元素 */
    @Test
    public void testRemoveRangeByScore() {
        // 根据指定的最小分数与最大分数区间删除元素，包含最小值和最大值
        ops.removeRangeByScore(KEY, 90, 190);
    }

}
