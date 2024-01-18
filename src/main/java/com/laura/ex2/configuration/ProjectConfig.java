package com.laura.ex2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class ProjectConfig {

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        String usuariosQuery= """
                SELECT usuario,password,enabled
                FROM users
                WHERE usuario = ?
                """;
        String authoritiesQuery= """
                SELECT usuario,authority
                FROM authorities
                WHERE usuario = ?
                """;

        var userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery(usuariosQuery);
        userDetailsManager.setAuthoritiesByUsernameQuery(authoritiesQuery);

        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}

