package com.moon.springdata.jpa.test;

import com.moon.springdata.jpa.dao.ArticleDao;
import com.moon.springdata.jpa.dao.CommentDao;
import com.moon.springdata.jpa.domain.Article;
import com.moon.springdata.jpa.domain.ArticleData;
import com.moon.springdata.jpa.domain.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 一对多，多对一表关系测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-03 16:06
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml")
public class OneToManyTest {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private CommentDao commentDao;

    // 一对多/多对一表关系测试
    @Test
    public void testSave() {
        // 创建文章对象
        Article article = new Article();
        article.setTitle("君の名は");
        article.setAuthor("新海诚");
        article.setCreateTime(new Date());

        // 创建文章评论对象
        Comment comment1 = new Comment();
        comment1.setComment("真不错");
        Comment comment2 = new Comment();
        comment2.setComment("挺好的");

        // 建立两个对象间的关系
        comment1.setArticle(article);
        comment2.setArticle(article);

        Set<Comment> comments = new HashSet<>();
        comments.add(comment1);
        comments.add(comment2);
        article.setComments(comments);

        // 保存操作
        articleDao.save(article);
        commentDao.save(comment1);
        commentDao.save(comment2);
    }

}
