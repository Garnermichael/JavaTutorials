package com.zetcode.conf;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    @Bean    
    @ConfigurationProperties(prefix = "spring.derbyds")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.mysqlds")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
}
