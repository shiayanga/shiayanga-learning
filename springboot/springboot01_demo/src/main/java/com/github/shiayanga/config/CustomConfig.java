package com.github.shiayanga.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义配置类
 * 使用 @Configuration 注解定义一个配置类
 * 此注解表明该类为配置类
 */
@Configuration
public class CustomConfig {
    /**
     * 将返回值对象作为组件，添加到spring容器中；标识ID默认是方法名
     * @return com.github.shiayanga.config.CustomService
     *
     * 使用 @Bean(name="") 自定义标识名
     */
    @Bean(name = "iCustomService")
    public CustomService customService(){
        return new CustomService();
    }
}
