package com.moon.springdata.jpa.dao;

import com.moon.springdata.jpa.domain.ArticleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * article_data 持久层接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-03 16:04
 * @description
 */
public interface ArticleDataDao extends JpaRepository<ArticleData, Integer>, JpaSpecificationExecutor<ArticleData> {
}
