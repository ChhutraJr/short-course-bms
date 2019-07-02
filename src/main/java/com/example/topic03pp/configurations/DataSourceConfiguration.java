package com.example.topic03pp.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean("dataSource")
    @Profile("my-pgsql")
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/bms_db");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("root");

        return  driverManagerDataSource;
    }

    @Bean("dataSource")
    @Profile("my-h2")
    public DataSource inMemoryDB() {
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        databaseBuilder.setType(EmbeddedDatabaseType.H2);

        databaseBuilder.addScript("db/schema.sql");
        databaseBuilder.addScript("db/data.sql");

        return databaseBuilder.build();
    }


}
