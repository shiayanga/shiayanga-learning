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

    private String secret;
    private Long bignumber;
    private String uuid;

    @Override
    public String toString() {
        return "CustomProperties{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secret='" + secret + '\'' +
                ", bignumber=" + bignumber +
                ", uuid='" + uuid + '\'' +
                '}';
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getBignumber() {
        return bignumber;
    }

    public void setBignumber(Long bignumber) {
        this.bignumber = bignumber;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
