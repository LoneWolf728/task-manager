package com.example.taskmanager.service;

import com.example.taskmanager.dto.JwtResponse;
import com.example.taskmanager.dto.LoginRequest;
import com.example.taskmanager.dto.RegisterRequest;
import com.example.taskmanager.exception.BadRequestException;
import com.example.taskmanager.exception.UnauthorizedException;
import com.example.taskmanager.model.Role;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of the AuthService interface.
 * Provides authentication operations including user login and registration.
 */
@Service
public class AuthServiceImpl implements AuthService {

    /**
     * Manages authentication processing.
     */
    private final AuthenticationManager authenticationManager;
    
    /**
     * Creates and validates JWT tokens.
     */
    private final JwtTokenProvider jwtTokenProvider;
    
    /**
     * Data access for user entities.
     */
    private final UserRepository userRepository;
    
    /**
     * Encodes passwords for secure storage.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs the service with required dependencies.
     *
     * @param authenticationManager For authenticating user credentials
     * @param jwtTokenProvider For generating JWT tokens
     * @param userRepository For accessing user data
     * @param passwordEncoder For encoding user passwords
     */
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Authenticates a user and generates a JWT token.
     * Process involves validating credentials, generating a token, and fetching user details.
     *
     * @param loginRequest DTO containing username and password credentials
     * @return JWT response containing authentication token and user details
     * @throws UnauthorizedException if authentication fails
     */
    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        try {
            System.out.println("Attempting to authenticate user: " + loginRequest.getUsername());
            // Attempt authentication with provided credentials
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            System.out.println("Authentication successful for user: " + loginRequest.getUsername());
            
            // Generate JWT token from the authenticated user
            String jwt = jwtTokenProvider.generateToken(authentication);
            
            // Retrieve full user details to get roles
            User user = userRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new UnauthorizedException("Invalid credentials"));
            
            // Extract role names for the response
            Set<String> roles = user.getRoles().stream()
                    .map(role -> role.getName().name())
                    .collect(Collectors.toSet());
            
            // Return JWT token with user information
            return new JwtResponse(jwt, user.getUsername(), roles);
        } catch (Exception e) {
            System.out.println("Authentication failed for user: " + loginRequest.getUsername() + ", error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            throw new UnauthorizedException("Invalid username or password");
        }
    }

    /**
     * Registers a new user in the system.
     * Creates a user with provided credentials and assigns the default USER role.
     *
     * @param registerRequest DTO containing new user details
     * @throws BadRequestException if username already exists
     */
    @Override
    public void register(RegisterRequest registerRequest) {
        // Check if username is already taken
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new BadRequestException("Username is already taken");
        }

        // Create and configure new user entity
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword())); // Store encoded password

        // Assign default user role
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(Role.RoleName.ROLE_USER));
        user.setRoles(roles);

        // Save the new user
        userRepository.save(user);
    }
}