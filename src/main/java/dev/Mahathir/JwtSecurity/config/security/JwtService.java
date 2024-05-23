package dev.Mahathir.JwtSecurity.config.security;

import dev.Mahathir.JwtSecurity.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;
@Service
public class JwtService {

    private static final String SECRET_KEY = "635266556A576E5A7234753778214125442A472D4B6150645367566B59703273";
    private static final int tokenValidityDuration = 1000 * 60 * 60 * 24;
    private JwtService() {
    }

    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static String generateToken1(User user) {
        Claims claims  = Jwts.claims();
        claims.put("roles", user.getRoles());
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidityDuration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public static String generateToken(User user) {
        return generateToken1( user);
    }

    public static boolean isTokenValid(
            String token,
            UserDetails userDetails
    ) {
        final var username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public static boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private static Claims extractClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private static Key getSigningKey() {
        final var keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private static <C> C extractClaim(
            String token,
            Function<Claims, C> claimsResolver
    ) {
        final var claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }

}
