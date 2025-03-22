package com.example.proyecto_1_progra_4.presentation;

import com.example.proyecto_1_progra_4.logic.Service;
import com.example.proyecto_1_progra_4.logic.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final Service service;

    public UsuarioController(Service usuarioService) {
        this.service = usuarioService;
    }

    @GetMapping
    public Iterable<Usuario> listarUsuarios() {
        return service.obtenerUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Integer id) {
        return service.obtenerUsuarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return service.guardarUsuario(usuario);
    }
}
