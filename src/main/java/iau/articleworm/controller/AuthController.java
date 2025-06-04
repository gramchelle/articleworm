package iau.articleworm.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iau.articleworm.dto.Article.ArticleDto;
import iau.articleworm.dto.Auth.*;
import iau.articleworm.dto.User.UserSaveDto;
import iau.articleworm.config.JwtUtil;
import iau.articleworm.model.Article;
import iau.articleworm.model.User;
import iau.articleworm.repository.UserRepository;
import iau.articleworm.service.concrete.CustomUserDetailsService;
import iau.articleworm.service.concrete.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserSaveDto request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(encodedPassword);

        if (request.getRole() == null || request.getRole().isEmpty()) {
            request.setRole("READER");
        }

        userService.createUser(request);

        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

            final String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(Collections.singletonMap("token", jwt));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @GetMapping("/test-user/only/i/can/try/this/url/{username}")
    public ResponseEntity<?> testLoadUser(@PathVariable String username) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            return ResponseEntity.ok(
                "Username: " + userDetails.getUsername() + ", Password: " + userDetails.getPassword()
            );
        } catch (Exception e) {
            return ResponseEntity.status(404).body("User not found");
        }
    }

}
