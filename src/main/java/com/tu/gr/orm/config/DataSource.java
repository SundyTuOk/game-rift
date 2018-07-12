package com.tu.gr.orm.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSource {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.druid.datasource1")
    public javax.sql.DataSource firstDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

//        @Bean
//        @ConfigurationProperties("spring.datasource.druid.datasource2")
//        public javax.sql.DataSource secondDataSource(){
//            return DruidDataSourceBuilder.create().build();
//        }

//        @Bean
//        public DataSource dynamicDataSource(javax.sql.DataSource firstDataSource, javax.sql.DataSource secondDataSource) {
//            Map<String, javax.sql.DataSource> targetDataSources = new HashMap<>();
//            targetDataSources.put("dataSource1", firstDataSource);
//            targetDataSources.put("dataSource2", secondDataSource);
//            return new DynamicDataSource(firstDataSource, targetDataSources);
//        }
}
