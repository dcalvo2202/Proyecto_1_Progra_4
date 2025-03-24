package com.example.proyecto_1_progra_4.presentation;

import com.example.proyecto_1_progra_4.logic.LoginRequest;
import com.example.proyecto_1_progra_4.logic.Service;
import com.example.proyecto_1_progra_4.logic.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final Service service;

    public LoginController(Service service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody LoginRequest loginRequest) {
        Optional<Usuario> usuario = service.login(loginRequest.getId(), loginRequest.getClave());

        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}