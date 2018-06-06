package com.blackcrowsys.authservice.config;

import com.blackcrowsys.commonutils.db.FlywayMigrator;
import com.blackcrowsys.commonutils.db.Migration;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbConfiguration {

    @Autowired
    private DataSource dataSource;

    @Bean
    public Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        return flyway;
    }

    @Bean
    public Migration migration() {
        Migration migration = new FlywayMigrator(flyway());
        return migration;
    }
}
