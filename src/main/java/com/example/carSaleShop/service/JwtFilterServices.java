package com.example.carSaleShop.service;

import com.example.carSaleShop.excception.CustomException;
import com.example.carSaleShop.model.TokenInfo;
import com.example.carSaleShop.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JwtFilterServices {
    @Autowired
    @Lazy
    JwtUtils jwtUtils;

    @Autowired
    CustomUsertDetailServices userDetailsService;



    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authorization=request.getHeader("Authorization");

        if (authorization !=null && authorization.startsWith("Bearer ")){
            String token=authorization.substring(7);

            if (!jwtUtils.isValid(token)){
                throw new CustomException("invalid token", HttpStatus.UNAUTHORIZED);

            }

            TokenInfo tokenInfo=jwtUtils.extractInfo(token);
            if (userDetailsService.isValid(tokenInfo)){
                throw new CustomException("invalid token", HttpStatus.UNAUTHORIZED);
            }
            List<GrantedAuthority> authirities= Collections.singletonList(new SimpleGrantedAuthority(tokenInfo.getRoles()));
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(tokenInfo.getEmail() , null ,authirities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request,response);
        }


    }
}
