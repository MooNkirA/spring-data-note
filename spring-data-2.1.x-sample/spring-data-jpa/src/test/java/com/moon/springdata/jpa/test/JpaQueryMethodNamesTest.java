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
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Spring Data JPA 方法命名规则查询测试。
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-03 11:11
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml") // 加载配置文件
public class JpaQueryMethodNamesTest {

    @Autowired
    private ArticleDao articleDao;

    @Test
    public void testFindByTitle() {
        List<Article> articles = articleDao.findByTitle("金田一少年事件簿");
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    public void testFindByTitleLike() {
        List<Article> articles = articleDao.findByTitleLike("%一%");
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    public void testFindByTitleAndAuthor() {
        List<Article> articles = articleDao.findByTitleAndAuthor("金田一少年事件簿", "MooN");
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    public void testFindByAidIsLessThan() {
        List<Article> articles = articleDao.findByIdIsLessThan(9);
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    public void testFindByAidBetween() {
        List<Article> articles = articleDao.findByIdBetween(6, 9);
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    public void testFindByAidIn() {
        List<Integer> list = Stream.of(6, 8, 10).collect(Collectors.toList());
        List<Article> articles = articleDao.findByIdIn(list);
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    public void testFindByCreateTimeAfter() {
        List<Article> articles = articleDao.findByCreateTimeAfter(new Date());
        for (Article article : articles) {
            System.out.println(article);
        }
    }

}
