/*
 * File:     FlywayInitializer
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

import lombok.AllArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@DependsOn("entityManagerFactory")
public class FlywayInitializer {

//    private Flyway flyway;

//    @EventListener(ApplicationReadyEvent.class)
//    public void insertData() {
//        flyway.migrate();
//    }

    @Autowired
    public FlywayInitializer(Flyway flyway) {
        flyway.migrate();
    }


}
