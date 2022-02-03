package com.moon.springdata.jpa.test;

import com.moon.springdata.jpa.dao.ArticleDao;
import com.moon.springdata.jpa.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA 继承父接口方法中查询方法使用测试。
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-03 11:11
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml") // 加载配置文件
public class JpaQueryBasicTest {

    @Autowired
    private ArticleDao articleDao;

    /* 查询总记录数 */
    @Test
    public void testCount() {
        long count = articleDao.count();
        System.out.println("总记录数：" + count);
    }

    /* 根据id查询记录是否存在 */
    @Test
    public void testExistsById() {
        boolean b = articleDao.existsById(99);
        System.out.println("ID为5是否存在：" + b);
    }

    /* 根据主键查询单条数据 */
    @Test
    public void testFindById() {
        // 根据一个主键查询
        articleDao.findById(3).ifPresent(System.out::println);
    }

    /* 根据多个主键查询多条数据 */
    @Test
    public void testFindAllById() {
        // 根据多个主键查询
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(6);
        list.add(10);
        List<Article> articles = articleDao.findAllById(list);
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    /* 查询所有 */
    @Test
    public void testFindAll() {
        List<Article> articles = articleDao.findAll();
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    /* 查询所有数据并排序 */
    @Test
    public void testFindAllWithSort() {
        // 按照 aid 主键倒序排列，需要注意：这个排列的字段是实体类的属性名称，非表字段名称！！
        Sort sort = Sort.by(Sort.Order.desc("id"));
        List<Article> articles = articleDao.findAll(sort);
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    /* 分页查询所有数据 */
    @Test
    public void testFindAllWithPage() {
        /*
         * 创建分页条件对象Pageable
         *  参数 page：当前是第几页(从0开始)
         *  参数 size：每页大小
         */
        Pageable pageable = PageRequest.of(0, 2);
        Page<Article> page = articleDao.findAll(pageable);

        // 获取查询的总记录数
        System.out.println("总记录数:" + page.getTotalElements());
        // 获取总页数
        System.out.println("总页数:" + page.getTotalPages());
        // 获取每页大小
        System.out.println("每页大小:" + page.getSize());

        // 获取当前查询的元素
        List<Article> content = page.getContent();
        for (Article article : content) {
            System.out.println(article);
        }
    }

    /* 查询所有数据（分页+排序） */
    @Test
    public void testFindAllWithPageAndPage() {
        // 创建排序对象，按照主键 aid 倒序排列
        Sort sort = Sort.by(Sort.Order.desc("id"));

        /*
         * 创建分页条件对象 Pageable
         *  参数 page：当前是第几页(从0开始)
         *  参数 size：每页大小
         *  参数 sort：排序对象 Sort
         */
        Pageable pageable = PageRequest.of(0, 2, sort);
        Page<Article> page = articleDao.findAll(pageable);

        System.out.println("总记录数:" + page.getTotalElements());
        System.out.println("总页数:" + page.getTotalPages());
        System.out.println("每页大小:" + page.getSize());
        for (Article article : page.getContent()) {
            System.out.println(article);
        }
    }

}
