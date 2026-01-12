
package com.mladec.cricketteam.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:mem:mladec;DB_CLOSE_DELAY=-1;MODE=MySQL");
        ds.setUsername("sa");
        ds.setPassword("");
        return ds;
    }

    @Bean(name = "playersJdbcTemplate")
    public JdbcTemplate playersJdbcTemplate(@Qualifier("dataSource") DataSource ds) { return new JdbcTemplate(ds); }

    @Bean(name = "battingJdbcTemplate")
    public JdbcTemplate battingJdbcTemplate(@Qualifier("dataSource") DataSource ds) { return new JdbcTemplate(ds); }

    @Bean(name = "bowlingJdbcTemplate")
    public JdbcTemplate bowlingJdbcTemplate(@Qualifier("dataSource") DataSource ds) { return new JdbcTemplate(ds); }

    // Initialize schema and data safely as a Spring bean, avoiding @PostConstruct ordering issues
    @Bean
    public DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") DataSource ds) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db/schema.sql"));
        populator.addScript(new ClassPathResource("db/data.sql"));
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(ds);
        initializer.setDatabasePopulator(populator);
        return initializer;
    }
}
