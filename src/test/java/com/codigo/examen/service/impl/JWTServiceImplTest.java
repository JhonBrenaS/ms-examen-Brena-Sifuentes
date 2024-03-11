package com.codigo.examen.service.impl;

import com.codigo.examen.entity.Rol;
import com.codigo.examen.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JWTServiceImplTest {

    @InjectMocks
    private JWTServiceImpl jwtService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGenerateToken() {
        Usuario usuario = new Usuario();
        usuario.setUsername("username");
        usuario.setPassword("password");
        usuario.setEnabled(true);
        usuario.setAccountnonexpire(false);
        usuario.setAccountnonlocked(false);
        usuario.setCredentialsnonexpired(false);
        Set<String> roles = new HashSet<>();

        String token = jwtService.generateToken(usuario);

        assertNotNull(token);
    }

    /*@Test
    public void testExtractUserName() {
        String token = "xxxxxxxxxxxx";

        String extractedUsername = jwtService.extractUserName(token);

        assertEquals("subject", extractedUsername);
    }*/

    @Test
    public void testValidateToken() {
        Usuario usuario = new Usuario();
        usuario.setUsername("username");
        usuario.setPassword("password");
        usuario.setEnabled(true);
        usuario.setAccountnonexpire(false);
        usuario.setAccountnonlocked(false);
        usuario.setCredentialsnonexpired(false);
        Set<String> roles = new HashSet<>();
        roles.add("USER");

        String token = jwtService.generateToken(usuario);

        UserDetails userDetails = new User(usuario.getUsername(), usuario.getPassword(), usuario.getAuthorities());

        boolean isValid = jwtService.validateToken(token, userDetails);

        assertTrue(isValid);
    }
}
