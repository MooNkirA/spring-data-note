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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Spring Data JPA 动态条件 Specifications 查询
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-03 11:11
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml") // 加载配置文件
public class JpaQuerySpecificationsTest {

    @Autowired
    private ArticleDao articleDao;

    /* 动态条件拼接查询。以不为空的属性作为查询条件，按照标题和作者进行查询 */
    @Test
    public void testFindAll() {
        // 模拟动态的查询条件
        String title = "金田一少年事件簿";
        String author = "";

        List<Article> articles = articleDao.findAll(new Specification<Article>() {
            /**
             * 查询条件拼接方法
             *
             * @param root  代表实体对象,我们可以通过它获取属性值
             * @param cq    用于生成SQL语句
             * @param cb    用于拼接查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                List<Predicate> list = new ArrayList<>();
                if (!StringUtils.isEmpty(title)) {
                    // 拼接作为查询条件
                    Predicate predicate = cb.equal(root.get("title").as(String.class), title);
                    list.add(predicate);
                }
                if (!StringUtils.isEmpty(author)) {
                    // 拼接作为查询条件
                    Predicate predicate = cb.equal(root.get("author").as(String.class), author);
                    list.add(predicate);
                }

                return cb.and(list.toArray(new Predicate[]{}));
            }
        });

        for (Article article : articles) {
            System.out.println(article);
        }
    }

    /* 动态条件拼接查询 - 分页查询 */
    @Test
    public void testFindAllWithPage() {
        // 模拟动态的查询条件
        String title = "";
        String author = "";

        // 设置分页
        Pageable pageable = PageRequest.of(0, 3);
        // 使用 lambda 表达式方式创建
        Page<Article> page = articleDao.findAll((Specification<Article>) (root, cq, cb) -> {

            List<Predicate> list = new ArrayList<>();
            if (!StringUtils.isEmpty(title)) {
                // 拼接作为查询条件
                Predicate predicate = cb.equal(root.get("title").as(String.class), title);
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(author)) {
                // 拼接作为查询条件
                Predicate predicate = cb.equal(root.get("author").as(String.class), author);
                list.add(predicate);
            }

            return cb.and(list.toArray(new Predicate[]{}));
        }, pageable);

        for (Article article : page.getContent()) {
            System.out.println(article);
        }
    }

    /* 动态条件拼接查询 - 分页与排序查询 */
    @Test
    public void testFindAllWithPageAndSort() {
        // 模拟动态的查询条件
        String title = "金田一少年事件簿";
        String author = "";

        // 创建分页与排序
        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Order.desc("id")));

        Page<Article> page = articleDao.findAll((Specification<Article>) (root, cq, cb) -> {

            List<Predicate> list = new ArrayList<>();
            if (!StringUtils.isEmpty(title)) {
                //拼接作为查询条件
                Predicate predicate = cb.equal(root.get("title").as(String.class), title);
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(author)) {
                //拼接作为查询条件
                Predicate predicate = cb.equal(root.get("author").as(String.class), author);
                list.add(predicate);
            }

            return cb.and(list.toArray(new Predicate[]{}));
        }, pageable);

        for (Article article : page.getContent()) {
            System.out.println(article);
        }
    }

}
