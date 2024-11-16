package com.example.carSaleShop.config;

import com.example.carSaleShop.service.CustomUsertDetailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecuirtyConfig {
    @Autowired
    CustomUsertDetailServices usertDetailServices;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(encoder());
        auth.setUserDetailsService(usertDetailServices);
        return auth;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security)throws Exception{
        security.authorizeHttpRequests(auth->{
//            auth.requestMatchers("/v1/car/**").permitAll();
//            auth.requestMatchers(HttpMethod.GET).permitAll();
//            auth.requestMatchers(HttpMethod.POST).hasRole("ADMIN");
//            auth.requestMatchers(HttpMethod.PUT).hasRole("ADMIN");
//            auth.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN");
            auth.anyRequest().authenticated();
        }).csrf(AbstractHttpConfigurer::disable)
                .formLogin(httpSecurityFormLoginConfigurer -> {

        }).httpBasic(httpSecurityHttpBasicConfigurer -> {

        });

        return security.build();
    }


    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }


}
