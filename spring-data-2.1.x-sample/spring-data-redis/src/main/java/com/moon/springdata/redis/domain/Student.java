package com.moon.springdata.redis.domain;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * 测试使用的实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-02-04 10:20
 * @description
 */
public class Student implements Serializable {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("age=" + age)
                .toString();
    }
}
