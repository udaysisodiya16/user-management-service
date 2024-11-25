package com.capstone.usermanagementservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.MissingCsrfTokenException;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    public static Boolean validateToken(String token, SecretKey secretKey, UserDetails userDetails) {
        JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();
        Claims claims = jwtParser.parseSignedClaims(token).getPayload();
        if (claims.getSubject().equals(userDetails.getUsername()) &&
                claims.getExpiration().before(new Date())) {
            throw new IllegalArgumentException("Token Expired");
        }
        return true;
    }

    public static String generateToken(SecretKey secretKey, UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities());
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(secretKey)
                .compact();
    }

    // Extract username from JWT token
    public static String extractUsername(SecretKey secretKey, String token) {
        return extractClaims(secretKey, token).getSubject();
    }

    public static Claims extractClaims(SecretKey secretKey, String token) {
        JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();
        return jwtParser.parseSignedClaims(token).getPayload();
    }

    // Extract JWT token
    public static String extractJwtToken(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        // Extract JWT from the "Authorization" header
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        throw new MissingCsrfTokenException("");
    }
}
