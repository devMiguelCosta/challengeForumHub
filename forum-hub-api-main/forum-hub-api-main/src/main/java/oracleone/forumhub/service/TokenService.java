package oracleone.forumhub.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import oracleone.forumhub.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.time.Instant;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String gerarToken(Usuario usuario){
        var key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        var agora = Instant.now();

        return Jwts.builder()
                .subject(usuario.getId().toString())
                .issuedAt(Date.from(agora))
                .expiration(Date.from(agora.plusSeconds(expiration)))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }


    public String getSubject(String token){
        var key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

}
