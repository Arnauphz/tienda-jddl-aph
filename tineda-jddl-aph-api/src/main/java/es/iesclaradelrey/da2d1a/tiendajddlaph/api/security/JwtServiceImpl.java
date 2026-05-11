package es.iesclaradelrey.da2d1a.tiendajddlaph.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    private final SecretKey signingKey;
    private final long accessTokenExpiration;
    private final long refreshTokenExpiration;

    public JwtServiceImpl(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-token.expiration}") long accessTokenExpiration,
            @Value("${jwt.refresh-token.expiration}") long refreshTokenExpiration
    ) {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

    @Override
    public String generateAccessToken(UserDetails user) {
        return buildToken(user, accessTokenExpiration, TokenType.ACCESS);
    }

    @Override
    public String generateRefreshToken(UserDetails user) {
        return buildToken(user, refreshTokenExpiration, TokenType.REFRESH);
    }

    private String buildToken(UserDetails user, long expiration, TokenType type) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .subject(user.getUsername())
                .claim("type", type.name())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(signingKey)
                .compact();
    }

    @Override
    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    @Override
    public Boolean isTokenExpired(String token) {
        return parseClaims(token).getExpiration().before(new Date());
    }

    @Override
    public Boolean isTokenValid(String token, UserDetails user) {
        try {
            String username = extractUsername(token);
            return username.equals(user.getUsername()) && !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}