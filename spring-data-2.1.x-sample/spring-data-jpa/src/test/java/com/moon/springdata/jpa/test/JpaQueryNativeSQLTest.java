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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Spring Data JPA 原生 SQL 查询。
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-03 11:11
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml") // 加载配置文件
public class JpaQueryNativeSQLTest {

    @Autowired
    private ArticleDao articleDao;

    @Test
    public void testFindByNativeSql() {
        List<Article> articles = articleDao.findByNativeSql("金田一少年事件簿", "MooN");
        for (Article article : articles) {
            System.out.println(article);
        }
    }

}
