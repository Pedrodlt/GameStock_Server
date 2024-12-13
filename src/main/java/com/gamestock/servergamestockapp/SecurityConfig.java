/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamestock.servergamestockapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author pedro
 */
@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*http.csrf().disable()
    .authorizeRequests().anyRequest().permitAll();

        return http.build();*/
        
        http.csrf(csrf -> csrf.disable())  // Desactiva CSRF deprecado
    .authorizeHttpRequests(auth -> auth
        .anyRequest().permitAll()  // Permite todas las solicitudes sin autenticación
    )
    .httpBasic(withDefaults());
        
       /*http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users", "/api/users/login").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults()); // Configura autenticación HTTP Básica */

        return http.build();
    }
    
}
