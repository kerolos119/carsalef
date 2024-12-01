package com.example.carSaleShop.utils;

import com.example.carSaleShop.document.UserEntity;
import com.example.carSaleShop.model.TokenInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

@Component
public class JwtUtils {
    private String key = "YourBase64EncodedKeyHere==ksjbfkjahbsd";  // Your key (in plain string or hex format)
    private Key getSigningKey() {
        // Convert the plain string key to bytes
        byte[] keyBytes = key.getBytes(); // This converts the string directly into byte[]

        // Now generate a proper HMAC key from the bytes
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generate(UserEntity admin){
        HashMap<String ,Object> claims= new HashMap<>();
        claims.put("_id",admin.getId());
        claims.put("email",admin.getUsername());
        claims.put("password",admin.getPassword());
        claims.put("role",admin.getRoles());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000 * 60 * 60 * 10))
                .signWith(getSigningKey())
                .compact();
    }
    private TokenInfo buildTokenInfo(Claims claims){
        return TokenInfo.builder()
                .roles(claims.get("role").toString())
                .ExpiredAt(claims.getExpiration())
                .IssuedAt(claims.getIssuedAt())
                .userId(claims.get("_id").toString())
                .email(claims.get("email").toString())
                .build();
    }

    public Boolean isValid(String token)
    {
        Claims claims=Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
        if (claims.getExpiration().getTime()<System.currentTimeMillis()) {
            return false;
        }
        if (claims.getIssuedAt().getTime()>System.currentTimeMillis()) {
            return false;
        }
        return true;
    }
    public TokenInfo extractInfo(String token)
    {
        Claims claims=Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
        return TokenInfo.builder().email(claims.get("email").toString())
                .userId(claims.get("_id").toString())
                .roles(claims.get("role").toString())
                .IssuedAt(claims.getIssuedAt())
                .ExpiredAt(claims.getExpiration())
                .build();
    }
}
