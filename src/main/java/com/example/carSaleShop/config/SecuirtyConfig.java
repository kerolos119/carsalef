package com.example.carSaleShop.config;

import com.example.carSaleShop.service.CustomUsertDetailServices;
import com.example.carSaleShop.service.JwtFilterServices;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecuirtyConfig {
    @Autowired
    @Lazy
    private CustomUsertDetailServices usertDetailServices;

    @Autowired
    @Lazy
    private JwtFilterServices filterServices;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(encoder());
        auth.setUserDetailsService(usertDetailServices);
        return auth;
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {

            auth.requestMatchers("/swagger-ui/index.html").permitAll();
            auth.requestMatchers("/auth").permitAll();
            auth.requestMatchers(HttpMethod.GET).permitAll();
            auth.requestMatchers(HttpMethod.POST).hasRole("ADMIN");
            auth.requestMatchers(HttpMethod.PUT).hasRole("ADMIN");
            auth.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN");
            auth.requestMatchers(HttpMethod.POST, "ADMIN").permitAll();
            auth.anyRequest().permitAll();
            auth.anyRequest().authenticated();
        });
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore((Filter) filterServices, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public AuthenticationManager manager(HttpSecurity httpSecurity) throws Exception{
        AuthenticationManagerBuilder managerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        managerBuilder
                .inMemoryAuthentication()
                .withUser("user")
                .password(encoder().encode("password"))
                .roles("ADMIN");
        return managerBuilder.build();
    }

}

