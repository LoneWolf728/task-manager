package com.example.taskmanager.security;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter that intercepts each HTTP request to validate JWT tokens.
 * Extends Spring's OncePerRequestFilter to ensure it executes only once per request.
 * Responsible for extracting, validating JWT tokens and setting up authentication.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    /**
     * Constructs the filter with required components.
     *
     * @param jwtTokenProvider Service for JWT token operations
     * @param userDetailsService Service to load user details
     */
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Main filter method that processes each HTTP request.
     * Validates JWT token, loads user details, and sets up authentication if token is valid.
     *
     * @param request The HTTP request
     * @param response The HTTP response
     * @param filterChain The filter chain for additional processing
     * @throws ServletException If a servlet error occurs
     * @throws IOException If an I/O error occurs during request processing
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        // Extract JWT token from the request
        String jwt = getJwtFromRequest(request);

        // Validate token and set up authentication context
        if (jwt != null && jwtTokenProvider.validateToken(jwt)) {
            // Extract username from the token
            String username = jwtTokenProvider.getUsernameFromJWT(jwt);
            // Load the user details 
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Create authentication token with user details and authorities
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Set authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continue with filter chain processing
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.startsWith("/api/auth/");
    }

    /**
     * Extracts JWT token from the HTTP request.
     * Looks for the token in the Authorization header with Bearer prefix.
     *
     * @param request The HTTP request
     * @return The JWT token if found, null otherwise
     */
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remove "Bearer " prefix
        }
        return null;
    }
}