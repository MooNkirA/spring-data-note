package com.moon.springdata.jpa.dao;

import com.moon.springdata.jpa.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 文章类型 type 表持久接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-03 17:21
 * @description
 */
public interface TypeDao extends JpaRepository<Type, Integer>, JpaSpecificationExecutor<Type> {
}
