package com.erocraft.config;


import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author 14179
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.erocraft.mapper")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}