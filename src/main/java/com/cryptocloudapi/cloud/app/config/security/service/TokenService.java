package com.cryptocloudapi.cloud.app.config.security.service;

import com.cryptocloudapi.cloud.entity.User;
import com.cryptocloudapi.cloud.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service @RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenService {

    @Value("${cripto-app.jwt.expiration}")
    String expiration;

    @Value("${cripto-app.jwt.secret}")
    String secret;


   private final UserRepository usuarioRepository;

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Date today = new Date();


        return Jwts.builder().setIssuer("Crypto Cloud Api")
                .setSubject(user.getId().toString())
                .setIssuedAt(today)
                .setExpiration(new Date(today.getTime() + Long.parseLong(expiration)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    public String validToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return token == null || !token.startsWith("Bearer") ? null : token.substring(7);
    }


    public void validateTokenAndAuth(String token){
        Long idUser = returnUserLoggedInId(token);
        User user = usuarioRepository.findById(idUser).orElse(null);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Long returnUserLoggedInId(String token) {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Long.parseLong(body.getSubject());
    }
}