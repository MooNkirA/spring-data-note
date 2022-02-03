package com.moon.springdata.jpa.test;

import com.moon.springdata.jpa.dao.ArticleDao;
import com.moon.springdata.jpa.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Spring Data JPA 基础增删改查操作测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-02 23:10
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml") // 加载配置文件
public class SpringDataJpaTest {

    @Autowired
    private ArticleDao articleDao;

    /* 新增测试 */
    @Test
    public void testSave() {
        Article article = new Article();
        article.setTitle("金田一少年事件簿");
        article.setAuthor("MooN");
        article.setCreateTime(new Date());

        articleDao.save(article);
    }

}
