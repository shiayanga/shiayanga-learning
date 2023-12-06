package com.github.shiayanga.springboot01_demo;

import com.github.shiayanga.pojo.Person;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot01DemoApplicationTests {

    @Resource
    Person person;
    @Test
    void contextLoads() {
        System.out.println(person);
    }

}
