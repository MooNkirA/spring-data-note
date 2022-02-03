package com.moon.review.jpa.test;

import com.moon.review.jpa.domain.Article;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * jpa 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-02 11:47
 * @description
 */
public class HibernateJpaTest {

    // 持久化管理器工厂
    private EntityManagerFactory factory;
    // 持久化管理器
    private EntityManager entityManager;

    @Before
    public void before() {
        // 持久化单元名称，与 META-INF/persistence.xml 配置中的 persistence-unit 标签的 name 属性一致
        String persistenceUnitName = "jpa";
        // 1. 初始化持久化管理器工厂
        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        // 2. 初始化持久化管理器
        entityManager = factory.createEntityManager();
    }

    /* 新增测试 */
    @Test
    public void testSave() {
        // 3. 获取事务管理器（事务没有开启）
        EntityTransaction transaction = entityManager.getTransaction();
        // 4. 开启事务
        transaction.begin();

        // 5. 数据库操作
        Article article = new Article();
        article.setTitle("这是一个测试文章标题");
        article.setAuthor("MooN");
        article.setCreateTime(new Date());
        entityManager.persist(article); // 插入数据

        // 6. 事务提交
        transaction.commit();
    }

    /* 查询测试 */
    @Test
    public void testFindById() {
        // 5. 根据 id 查询数据
        Article article = entityManager.find(Article.class, 3);
        System.out.println(article);
    }

    /* 更新测试 */
    @Test
    public void testUpdate() {
        // 3. 获取事务管理器（事务没有开启）
        EntityTransaction transaction = entityManager.getTransaction();
        // 4. 开启事务
        transaction.begin();

        // 5. 数据库操作（更新需要先查询）
        Article article = entityManager.find(Article.class, 3);
        // 修改
        article.setTitle("我是修改了");
        article.setAuthor("MooN！！");
        article.setCreateTime(new Date());
        entityManager.merge(article); // 更新数据

        // 6. 事务提交
        transaction.commit();
    }

    /* 删除测试 */
    @Test
    public void testDelete() {
        // 3. 获取事务管理器（事务没有开启）
        EntityTransaction transaction = entityManager.getTransaction();
        // 4. 开启事务
        transaction.begin();

        // 5. 数据库操作（删除前先查询）
        Article article = entityManager.find(Article.class, 2);
        entityManager.remove(article); // 删除数据

        // 6. 事务提交
        transaction.commit();
    }

    @After
    public void after() {
        // 7. 关闭资源
        entityManager.close();
    }

}
