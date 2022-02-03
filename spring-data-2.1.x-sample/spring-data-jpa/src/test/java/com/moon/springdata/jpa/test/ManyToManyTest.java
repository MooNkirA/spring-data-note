package com.moon.springdata.jpa.test;

import com.moon.springdata.jpa.dao.ArticleDao;
import com.moon.springdata.jpa.dao.CommentDao;
import com.moon.springdata.jpa.dao.TypeDao;
import com.moon.springdata.jpa.domain.Article;
import com.moon.springdata.jpa.domain.Comment;
import com.moon.springdata.jpa.domain.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 多对多表关系测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-03 16:06
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml")
public class ManyToManyTest {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private TypeDao typeDao;

    // 多对多表关系测试
    @Test
    public void testSave() {
        // 创建文章对象
        Article article1 = new Article();
        article1.setTitle("秒速5センチメートル");
        article1.setAuthor("新海诚");
        article1.setCreateTime(new Date());
        Article article2 = new Article();
        article2.setTitle("言の葉の庭");
        article2.setAuthor("新海诚");
        article2.setCreateTime(new Date());

        // 创建文章评论对象
        Type type1 = new Type();
        type1.setName("动画");
        Type type2 = new Type();
        type2.setName("恋爱");

        // 建立两个对象间的关系
        Set<Type> types = new HashSet<>();
        types.add(type1);
        types.add(type2);
        article1.setTypes(types);
        article2.setTypes(types);

        Set<Article> articles = new HashSet<>();
        articles.add(article1);
        articles.add(article2);
        type1.setArticles(articles);
        type2.setArticles(articles);

        // 保存操作
        articleDao.save(article1);
        articleDao.save(article2);
        typeDao.save(type1);
        typeDao.save(type2);
    }

}
