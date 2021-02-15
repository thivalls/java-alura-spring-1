package br.com.alura.forum.config.security;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthMiddleware extends OncePerRequestFilter {
    private TokenService tokenService;

    public AuthMiddleware(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.checkToken(request);
        boolean isValid = tokenService.isValid(token);
        System.out.println(isValid);
        filterChain.doFilter(request, response);
    }

    private String checkToken(HttpServletRequest request) {
        String fullToken = request.getHeader("Authorization");
        if (fullToken.isEmpty() || fullToken == null || !fullToken.startsWith("Bearer ")) {
            return null;
        }
        return fullToken.substring(7, fullToken.length());
    }
}
