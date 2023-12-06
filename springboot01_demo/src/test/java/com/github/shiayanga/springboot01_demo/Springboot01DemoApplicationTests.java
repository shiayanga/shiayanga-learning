package com.github.shiayanga.springboot01_demo;

import com.github.shiayanga.custom.CustomProperties;
import com.github.shiayanga.pojo.Person;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class Springboot01DemoApplicationTests {

    @Resource
    Person person;
    @Test
    void contextLoads() {
        System.out.println(person);
    }

    /**
     * 测试自定义配置文件
     */
    @Resource
    CustomProperties customProperties;
    @Test
    void customPropertiesTest(){
        System.out.println(customProperties.toString());
    }

    @Resource
    ApplicationContext applicationContext;
    @Test
    void customConfigTest(){
        System.out.println(applicationContext.containsBean("iCustomService"));
    }

    @Test
    void referenceTest(){
        System.out.println(person);
    }

    @Value("${person.description}")
    private String description;
    @Test
    void placeholderTest(){
        System.out.println(description);
    }
}
