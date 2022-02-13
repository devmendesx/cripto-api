package com.cryptocloudapi.cloud.app.config.security;

import com.cryptocloudapi.cloud.app.config.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service @RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenAuth extends OncePerRequestFilter {

    private final TokenService tokenService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = tokenService.validToken(request);
        boolean validTokenService = tokenService.isValidToken(token);
        if(validTokenService){
            tokenService.validateTokenAndAuth(token);
        }
        filterChain.doFilter(request, response);
    }
}
