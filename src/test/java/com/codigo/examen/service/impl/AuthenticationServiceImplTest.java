package com.codigo.examen.service.impl;

import com.codigo.examen.repository.RolRepository;
import com.codigo.examen.repository.UsuarioRepository;
import com.codigo.examen.request.SignUpRequest;
import com.codigo.examen.service.JWTService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;

public class AuthenticationServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private RolRepository rolRepository;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JWTService jwtService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void validaSignUp() {
        SignUpRequest signUpRequest = new SignUpRequest();
    }

    @Test
    void validasignUpAdmin(){
        SignUpRequest signUpRequest = new SignUpRequest();
    }
}
