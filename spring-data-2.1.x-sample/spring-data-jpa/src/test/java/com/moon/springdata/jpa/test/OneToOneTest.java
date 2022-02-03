package com.moon.springdata.jpa.test;

import com.moon.springdata.jpa.dao.ArticleDao;
import com.moon.springdata.jpa.domain.Article;
import com.moon.springdata.jpa.domain.ArticleData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * 一对一表关系测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-03 16:06
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml")
public class OneToOneTest {

    @Autowired
    private ArticleDao articleDao;

    // 一对一表关系测试
    @Test
    public void testSave() {
        // 创建文章对象
        Article article = new Article();
        article.setTitle("月色真美");
        article.setAuthor("柿原优子");
        article.setCreateTime(new Date());

        // 创建文章内容对象
        ArticleData articleData = new ArticleData();
        articleData.setContent("该作的背景作画相当细致，在校园风景、街景上，光影与行人的应用让场景看起来更为鲜活并富有风情。");

        // 建立两个对象间的关系
        article.setArticleData(articleData);
        articleData.setArticle(article);

        // 保存操作
        articleDao.save(article);
    }

}
