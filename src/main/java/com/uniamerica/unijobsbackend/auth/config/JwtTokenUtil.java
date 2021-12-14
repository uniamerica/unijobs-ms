package com.uniamerica.unijobsbackend.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniamerica.unijobsbackend.auth.model.UserSecurity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 1000 * 1000; // MINUTOS

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    protected ObjectMapper objectMapper;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public boolean isRefreshToken(String token) {
        return Optional
                .ofNullable(getClaimFromToken(token, claims -> claims.get("irt", Boolean.class)))
                .orElse(Boolean.FALSE);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserSecurity user) {
        final Map<String, Object> claims = new HashMap<>();
        final long expiration = System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 3;

        return doGenerateToken(claims, user, expiration);
    }

    public String generateRefreshToken(UserSecurity user) {
        final long expiration = System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 6;
        final Map<String, Object> refreshClaims = Map.of("irt", true);

        return doGenerateToken(refreshClaims, user, expiration);
    }

    private String doGenerateToken(Map<String, Object> claims, UserSecurity subject, long expiration) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject.getUsername())
                .claim("id_usuario", subject.getId())
                .claim("nome", subject.getNome())
                .claim("celular", subject.getCelular())
                .claim("ra", subject.getRa())
                .claim("roles", subject.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
