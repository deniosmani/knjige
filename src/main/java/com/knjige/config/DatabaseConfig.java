package com.knjige.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    
    @Value("${DATABASE_URL}")
    private String databaseUrl;
    
    @Value("${MYSQLUSER}")
    private String username;
    
    @Value("${MYSQLPASSWORD}")
    private String password;

    @Bean
    public DataSource dataSource() {
        String jdbcUrl = "jdbc:" + databaseUrl;
        
        return DataSourceBuilder
                .create()
                .url(jdbcUrl)
                .username(username)
                .password(password)
                .build();
    }
} 