package com.marvellous.booking_system.security;

import com.marvellous.booking_system.entity.AppUser;
import com.marvellous.booking_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;


import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService 
{
	
    private final UserRepository userRepository = null;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser u = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return User.builder()
                .username(u.getUsername())
                .password(u.getPassword())
                .disabled(!u.isEnabled())
                .authorities(u.getRoles().stream().map(Enum::name).collect(Collectors.toList()).toArray(new String[0]))
                .build();
    }
}

