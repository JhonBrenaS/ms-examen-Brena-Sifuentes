package com.codigo.examen.controller;

import com.codigo.examen.entity.Usuario;
import com.codigo.examen.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private UsuarioService usuarioService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUsuario() {
        Usuario usuario = new Usuario();
        usuario.setUsername("USER");

        when(usuarioService.createUsuario(usuario)).thenReturn(ResponseEntity.ok(usuario));

        ResponseEntity<ResponseEntity<Usuario>> response = adminController.createUsuario(usuario);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(usuario, response.getBody().getBody());
    }

    @Test
    public void testGetUsuarioById() {
        Long userId = 1L;
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(userId);
        usuario.setUsername("USER");

        when(usuarioService.getUsuarioById(userId)).thenReturn(ResponseEntity.of(Optional.of(usuario)));

        ResponseEntity<ResponseEntity<Usuario>> response = adminController.getUsuarioById(userId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(usuario, response.getBody().getBody());
    }

    @Test
    public void testUpdateUsuario() {
        Long userId = 1L;
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(userId);
        usuario.setUsername("USER");

        when(usuarioService.updateUsuario(userId, usuario)).thenReturn(ResponseEntity.ok(usuario));

        ResponseEntity<ResponseEntity<Usuario>> response = adminController.updateUsuario(userId, usuario);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(usuario, response.getBody().getBody());
    }

    @Test
    public void testDeleteUsuario() {
        Long userId = 1L;

        when(usuarioService.deleteUsuario(userId)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<?> response = adminController.deleteUsuario(userId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
