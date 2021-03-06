package br.com.alura.forum.config.security;

import br.com.alura.forum.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.config.expireAt}")
    private String expireAt;

    @Value("${jwt.config.secret}")
    private String jwtSecret;

    public String createToken(Authentication authentication) {
        Usuario user = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date expireDate = new Date(hoje.getTime() + Long.parseLong(expireAt));
        return Jwts.builder()
                .setIssuer("API FORUM ALURA")
                .setSubject(user.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public boolean isValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
