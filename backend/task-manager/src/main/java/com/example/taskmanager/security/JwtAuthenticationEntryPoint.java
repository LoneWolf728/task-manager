package com.example.taskmanager.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Entry point for handling authentication failures in the JWT authentication flow.
 * Implements Spring Security's AuthenticationEntryPoint to define the response
 * when unauthenticated users attempt to access protected resources.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * Called when an unauthenticated user attempts to access a protected resource.
     * Sends a 401 Unauthorized response with an error message.
     *
     * @param request The HTTP request that resulted in an authentication failure
     * @param response The HTTP response to modify
     * @param authException The exception that caused the authentication failure
     * @throws IOException If an I/O error occurs while sending the error response
     * @throws ServletException If a servlet error occurs
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Authentication token is missing or invalid");
    }
}