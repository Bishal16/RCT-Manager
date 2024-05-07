package dev.Mahathir.JwtSecurity.config;

import dev.Mahathir.JwtSecurity.service.TokenBlackListService;
import dev.Mahathir.JwtSecurity.service.TokenBlacklistChecker;
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
    private final TokenBlacklistChecker tokenBlacklistChecker;


    public JwtAuthenticationFilter(UserDetailsService userDetailsService,
                                   TokenBlacklistChecker tokenBlacklistChecker) {
        this.userDetailsService = userDetailsService;
        this.tokenBlacklistChecker = tokenBlacklistChecker;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {


        final var header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            //System.err.println("Header problem");
            filterChain.doFilter(request, response);
            return;
        }
        final var jwt = header.substring(7);

        var userEmail = JwtService.extractUsername(jwt);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            if (tokenBlacklistChecker.isBlacklisted(jwt)) {
                System.err.println("Token on blackList");
                throw new ServletException("Invalid token"); /////////////////////////////////////////---------------------------needs work
            }

            final var userDetails = userDetailsService.loadUserByUsername(userEmail);

            if (JwtService.isTokenValid(jwt, userDetails) && !tokenBlacklistChecker.isBlacklisted(jwt)) {
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
