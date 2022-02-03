package com.moon.springdata.jpa.test;

import com.moon.springdata.jpa.dao.ArticleDao;
import com.moon.springdata.jpa.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Spring Data JPA 继承父接口方法使用测试。
 * 新增、删除、聚合测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-03 11:11
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml") // 加载配置文件
public class JpaSaveAndDeleteTest {

    @Autowired
    private ArticleDao articleDao;

    /* 保存单条数据 */
    @Test
    public void testSave() {
        Article article = new Article();
        article.setTitle("射雕三部曲");
        article.setAuthor("金庸");
        article.setCreateTime(new Date());

        // 保存一个实体
        articleDao.save(article);
    }

    /* 保存单条数据，并且立即刷新缓存 */
    @Test
    public void testSaveAndFlush() {
        Article article = new Article();
        article.setTitle("鬼灭之刃");
        article.setAuthor("吾峠呼世晴");
        article.setCreateTime(new Date());

        // 保存一个实体,并且立即刷新缓存
        articleDao.saveAndFlush(article);
    }

    /* 保存多条数据 */
    @Test
    public void testSaveAll() {
        List<Article> list = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            Article article = new Article();
            article.setTitle("bleach" + i);
            article.setAuthor("斩月");
            article.setCreateTime(new Date());
            list.add(article);
        }

        // 保存多条数据
        articleDao.saveAll(list);
    }

    /* 根据主键id删除单条数据 */
    @Test
    public void testDeleteById() {
        // 根据id删除
        articleDao.deleteById(14);
    }

    /* 根据主键id删除单条数据 */
    @Test
    public void testDeleteOne() {
        // 根据实体删除，但是此实体必须要有主键
        Article article = new Article();
        article.setId(13);
        articleDao.delete(article);
    }

    /* 批量删除数据（底层都是先查询，再多条删除语句） */
    @Test
    public void testDeleteAll() {
        // 直接删除表所有数据，一般不会使用
        // articleDao.deleteAll();

        // 根据多个实体删除数据，实体类需要有主键id
        List<Article> list = new ArrayList<>();
        for (int i = 15; i < 18; i++) {
            Article article = new Article();
            article.setId(i);
            list.add(article);
        }
        articleDao.deleteAll(list);
    }

    /* 批量删除数据（直接删除，一条删除语句） */
    @Test
    public void testDeleteInBatch() {
        // 直接删除表所有数据，一般不会使用
        // articleDao.deleteAllInBatch();

        // 根据多个实体删除数据，实体类需要有主键id
        List<Article> list = new ArrayList<>();
        for (int i = 18; i < 21; i++) {
            Article article = new Article();
            article.setId(i);
            list.add(article);
        }
        articleDao.deleteInBatch(list);
    }

}
