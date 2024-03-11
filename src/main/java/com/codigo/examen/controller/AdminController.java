package com.codigo.examen.controller;

import com.codigo.examen.entity.Usuario;
import com.codigo.examen.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ms-examen/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UsuarioService usuarioService;

    //CREAR
    @PostMapping
    public ResponseEntity<ResponseEntity<Usuario>> createUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.createUsuario(usuario));
    }


    //OBTENER
    @GetMapping("/{id}")
    public ResponseEntity<ResponseEntity<Usuario>> getUsuarioById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
    }

    //ACTUALIZAR
    @PutMapping("update/{id}")
    public ResponseEntity<ResponseEntity<Usuario>> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.updateUsuario(id, usuario));
    }

    //ELIMINAR
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.deleteUsuario(id));
    }
}
