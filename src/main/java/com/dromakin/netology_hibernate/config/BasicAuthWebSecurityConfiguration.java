/*
 * File:     BasicAuthWebSecurityConfiguration
 * Package:  com.dromakin.netology_hibernate.config
 * Project:  netology_hibernate
 *
 * Created by dromakin as 07.10.2023
 *
 * author - dromakin
 * maintainer - dromakin
 * version - 2023.10.07
 * copyright - ORGANIZATION_NAME Inc. 2023
 */
package com.dromakin.netology_hibernate.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicAuthWebSecurityConfiguration {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/actuator/**", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/persons/by-city").hasRole("CITY")
                        .requestMatchers("/api/persons/age-less-then").hasRole("AGE")
                        .requestMatchers("/api/persons/by-name-surname").hasRole("NAME")
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .logout(LogoutConfigurer::permitAll)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        var user = User.builder()
                .passwordEncoder(passwordEncoder()::encode)
                .username("user")
                .password("password")
                .roles("NO ROLE")
                .build();

        var admin = User.builder()
                .passwordEncoder(passwordEncoder()::encode)
                .username("admin")
                .password("123")
                .roles("AGE", "CITY", "NAME")
                .build();

        var tester = User.builder()
                .passwordEncoder(passwordEncoder()::encode)
                .username("tester")
                .password("123")
                .roles("NAME")
                .build();

        var userDetailsManager = new InMemoryUserDetailsManager(user);

        userDetailsManager.createUser(admin);
        userDetailsManager.createUser(tester);

        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
