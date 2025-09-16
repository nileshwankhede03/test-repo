package com.marvellous.booking_system.service;

import com.marvellous.booking_system.config.JwtTokenProvider;
import com.marvellous.booking_system.dto.AuthRequest;
import com.marvellous.booking_system.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public AuthResponse login(AuthRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        var authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String token = tokenProvider.createToken(auth.getName(), authorities);
        return new AuthResponse(token, auth.getName());
    }
}
