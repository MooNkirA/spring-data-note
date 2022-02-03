package com.moon.springdata.jpa.test;

import com.moon.springdata.jpa.dao.ArticleDao;
import com.moon.springdata.jpa.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Spring Data JPA 使用 JPQL 查询。
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-03 11:11
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml") // 加载配置文件
public class JpaQueryJPQLTest {

    @Autowired
    private ArticleDao articleDao;

    @Test
    public void testFindByCondition1() {
        List<Article> articles = articleDao.findByCondition1("金田一少年事件簿", "MooN");
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    public void testFindByCondition2() {
        List<Article> articles = articleDao.findByCondition2("金田一少年事件簿", "MooN");
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    public void testFindByCondition3() {
        List<Article> articles = articleDao.findByCondition3("一");
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    public void testFindByCondition4() {
        List<Article> articles = articleDao.findByCondition4("金田一少年事件簿");
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    public void testFindByCondition5() {
        Pageable pageable = PageRequest.of(0, 3);
        List<Article> articles = articleDao.findByCondition5(pageable, "金田一少年事件簿");
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    public void testFindByCondition6() {
        List<Integer> list = Stream.of(6, 10).collect(Collectors.toList());
        List<Article> articles = articleDao.findByCondition6(list);
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    public void testFindByCondition7() {
        Article articleParam = new Article();
        articleParam.setTitle("金田一少年事件簿");
        articleParam.setAuthor("MooN");
        List<Article> articles = articleDao.findByCondition7(articleParam);
        for (Article article : articles) {
            System.out.println(article);
        }
    }

}
