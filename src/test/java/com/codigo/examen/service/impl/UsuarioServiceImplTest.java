package com.codigo.examen.service.impl;

import com.codigo.examen.entity.Rol;
import com.codigo.examen.entity.Usuario;
import com.codigo.examen.repository.RolRepository;
import com.codigo.examen.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUsuario() {
        Usuario usuario = new Usuario();
        usuario.setUsername("usuario123");
        usuario.setEmail("usuario12@gmail.com");
        usuario.setPassword("123456");

        when(usuarioRepository.findByUsername("usuario123")).thenReturn(Optional.empty());
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioService.createUsuario(usuario);

        assertEquals(ResponseEntity.ok(usuario), response);

        verify(usuarioRepository, times(1)).findByUsername("usuario123");
        verify(usuarioRepository, times(1)).save(usuario);
    }
}
