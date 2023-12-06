package com.github.shiayanga.custom;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 测试自定义配置文件
 * 使用 @PropertySource 注解
 * 引入自定义配置文件的名称及位置
 */
@Component
@PropertySource("classpath:custom.properties")
@ConfigurationProperties(prefix = "custom")
public class CustomProperties {
    private  int id;
    private String name;

    @Override
    public String toString() {
        return "CustomProperties:{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
