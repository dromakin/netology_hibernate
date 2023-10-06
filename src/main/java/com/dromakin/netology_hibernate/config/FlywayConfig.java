/*
 * File:     FlywayConfig
 * Package:  com.dromakin.netology_hibernate.config
 * Project:  netology_hibernate
 *
 * Created by dromakin as 06.10.2023
 *
 * author - dromakin
 * maintainer - dromakin
 * version - 2023.10.06
 * copyright - ORGANIZATION_NAME Inc. 2023
 */
package com.dromakin.netology_hibernate.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {
    @Bean
    public Flyway flyway() {
        return Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/netology", "postgres", "postgres")
                .locations("db/migration/postgresql")
                .defaultSchema("public")
                .baselineOnMigrate(true)
                .load();
    }
}
