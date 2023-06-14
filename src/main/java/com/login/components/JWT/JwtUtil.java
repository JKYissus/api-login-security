package com.login.components.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret.key}")
    String secret;

    @Value("${jwt.time.expiration}")
    Long expiration;

    public String generateToken(Map<String, Object> user) {

        Key secretKey = this.generateSecretKey();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setClaims(user)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String validateToken(String token) {

        try {
            Key secretKey = this.generateSecretKey();
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            Claims claims = claimsJws.getBody();

            Date expirationDate = claims.getExpiration();
            if (expirationDate.before(new Date())) {
                 return null;
            }

            long remainingTime = expirationDate.getTime() - System.currentTimeMillis();
            if (remainingTime < expiration) {
                claims.setExpiration(new Date(System.currentTimeMillis() + expiration));
            }

            return Jwts.builder()
                    .setClaims(claims)
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact();

        } catch (Exception e) {
            return null;
        }
    }

    public Key generateSecretKey() {
        byte[] jwtSecretBytes = secret.getBytes();
        return new SecretKeySpec(jwtSecretBytes, "HmacSHA256");
    }
}
