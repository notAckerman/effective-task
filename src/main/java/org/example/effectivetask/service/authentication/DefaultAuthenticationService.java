package org.example.effectivetask.service.authentication;

import lombok.RequiredArgsConstructor;
import org.example.effectivetask.model.dto.authentication.AuthenticationRequest;
import org.example.effectivetask.model.dto.authentication.AuthenticationResponse;
import org.example.effectivetask.model.dto.authentication.RegistrationRequest;
import org.example.effectivetask.model.entity.Role;
import org.example.effectivetask.model.entity.User;
import org.example.effectivetask.model.enums.UserRole;
import org.example.effectivetask.repository.RoleRepository;
import org.example.effectivetask.repository.UserRepository;
import org.example.effectivetask.service.jwt.JwtService;
import org.example.effectivetask.util.exception.BadCredentialsException;
import org.example.effectivetask.util.exception.NotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultAuthenticationService implements AuthenticationService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            Authentication authenticate = authenticationManager.authenticate(getAuthenticationToken(request));
            User user = (User) authenticate.getPrincipal();
            String token = jwtService.generateToken(user);
            return new AuthenticationResponse(token);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Неверный логин или пароль");
        }
    }

    @Override
    public AuthenticationResponse register(RegistrationRequest request) {
        User user = userRepository.save(getUser(request));
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    private static UsernamePasswordAuthenticationToken getAuthenticationToken(AuthenticationRequest request) {
        return new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
    }

    private User getUser(RegistrationRequest request) {
        return User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .authorities(getDefaultAuthorities())
                .build();
    }

    private Set<Role> getDefaultAuthorities() {
        return Collections.singleton(
                roleRepository.findByRole(UserRole.ROLE_USER).orElseThrow(() -> new NotFoundException("Role not found"))
        );
    }
}
