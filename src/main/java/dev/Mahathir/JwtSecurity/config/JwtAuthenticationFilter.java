package dev.Mahathir.JwtSecurity.config;

import dev.Mahathir.JwtSecurity.service.TokenBlackListService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;


    public JwtAuthenticationFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
//    public JwtAuthenticationFilter(InMemoryTokenBlacklist inMemoryTokenBlacklist) {
//        this.inMemoryTokenBlacklist = inMemoryTokenBlacklist;
//    }
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        final var header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        final var jwt = header.substring(7);
//        if(TokenBlacklistValidator.isBlacklisted(jwt)){
//            ;//invalid token
//        }
//        if(TokenBlacklistValidator.isBlacklisted(jwt.toString())){
//
//        }
        var userEmail = JwtService.extractUsername(jwt);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (TokenBlackListService.isBlacklisted(jwt)) System.err.println("Token on blackList");
            final var userDetails = userDetailsService.loadUserByUsername(userEmail);
            if (JwtService.isTokenValid(jwt, userDetails) && !TokenBlackListService.isBlacklisted(jwt)) {
                final var authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
                filterChain.doFilter(request, response);
            }
        }

    }
}
