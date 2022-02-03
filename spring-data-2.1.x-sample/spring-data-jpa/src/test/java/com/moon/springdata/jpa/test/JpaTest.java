package com.moon.springdata.jpa.test;

import com.moon.springdata.jpa.dao.ArticleDao;
import com.moon.springdata.jpa.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
public class JpaTest {

    @Autowired
    private ArticleDao articleDao;

    /* 新增数据 */
    @Test
    public void testSave() {
        Article article = new Article();
        article.setTitle("金田一少年事件簿");
        article.setAuthor("MooN");
        article.setCreateTime(new Date());

        articleDao.save(article);
    }

    /* 根据id查询 */
    @Test
    public void testFindById() {
        Optional<Article> optional = articleDao.findById(3);
        optional.ifPresent(System.out::println);
    }

    /* 查询所有 */
    @Test
    public void testFindAll() {
        List<Article> articles = articleDao.findAll();
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    /* 修改数据 */
    @Test
    public void testUpdate() {
        Article article = new Article();
        article.setAuthor("只改这部分属性");
        article.setId(2);
        /*
         * Sping data Jpa 的保存和修改使用的都是 save 方法
         *  如果主键属性有值，则进行修改操作
         *  如果主键属性没有值，则进行新增操作
         */
        articleDao.save(article);
    }

    /* 修改数据（先查询，后修改） */
    @Test
    public void testUpdate2() {
        articleDao.findById(3).ifPresent(a -> {
            a.setAuthor("kira");
            a.setTitle("我是查询后修改的！");
            articleDao.save(a);
        });
    }

    /* 删除数据 */
    @Test
    public void testDelete() {
        articleDao.deleteById(8);
    }

}
