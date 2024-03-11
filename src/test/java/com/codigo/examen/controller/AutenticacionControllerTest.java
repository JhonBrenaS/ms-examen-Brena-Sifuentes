package com.codigo.examen.controller;

import com.codigo.examen.entity.Usuario;
import com.codigo.examen.request.SignInRequest;
import com.codigo.examen.request.SignUpRequest;
import com.codigo.examen.response.AuthenticationResponse;
import com.codigo.examen.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AutenticacionControllerTest {

    @InjectMocks
    private AutenticacionController autenticacionController;

    @Mock
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSignUpUser() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("USER");

        Usuario usuario = new Usuario();
        usuario.setUsername(signUpRequest.getUsername());

        when(authenticationService.signUpUser(signUpRequest)).thenReturn(usuario);

        ResponseEntity<Usuario> response = autenticacionController.signUpUser(signUpRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(usuario, response.getBody());
    }

    @Test
    public void testSignUpAdmin() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("USER");

        Usuario usuario = new Usuario();
        usuario.setUsername(signUpRequest.getUsername());

        when(authenticationService.signUpAdmin(signUpRequest)).thenReturn(usuario);

        ResponseEntity<Usuario> response = autenticacionController.signUpAdmin(signUpRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(usuario, response.getBody());
    }

    @Test
    public void testSignin() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername("username");

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken("token");

        when(authenticationService.signin(signInRequest)).thenReturn(authenticationResponse);

        ResponseEntity<AuthenticationResponse> response = autenticacionController.signin(signInRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(authenticationResponse, response.getBody());
    }
}
