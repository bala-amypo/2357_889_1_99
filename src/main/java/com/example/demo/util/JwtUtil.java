package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Component
public class JwtUtil {

    // FIXED: Secure 256-bit key (32 chars) to satisfy WeakKeyException
    private static final String SECRET = "YourSuperSecretKeyMustBe32BytesLong!";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // --- Generation ---

    public String generateToken(String email, Long userId, Set<String> roles) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("roles", roles)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // --- Extraction Methods (Restored for JwtFilter) ---

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Method expected by your JwtFilter
    public String extractEmail(String token) {
        return extractUsername(token); 
    }

    // Method expected by your JwtFilter
    public Long extractUserId(String token) {
        return extractClaim(token, claims -> claims.get("userId", Long.class));
    }

    // Method expected by your JwtFilter
    public List<?> extractRoles(String token) {
        return extractClaim(token, claims -> claims.get("roles", List.class));
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Helper for your tests
    public Claims getClaims(String token) {
        return extractAllClaims(token);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // --- Validation Methods ---

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Simple validation (used by some tests)
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    // Overloaded validation (used by JwtFilter)
    public Boolean validateToken(String token, String userEmail) {
        final String username = extractEmail(token);
        return (username.equals(userEmail) && !isTokenExpired(token));
    }
}