package com.example.proyecto_1_progra_4.presentation;

import com.example.proyecto_1_progra_4.logic.Paciente;
import com.example.proyecto_1_progra_4.logic.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final Service service;

    
    public PacienteController(Service pacienteService) {
        this.service = pacienteService;
    }

    @GetMapping
    public Iterable<Paciente> listaPacientes() {
        return service.obtenerPacientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtenerPaciente(@PathVariable Integer id) {
        return service.obtenerPacientePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Paciente registrarPaciente(@RequestBody Paciente paciente) {
        return service.guardarPaciente(paciente);
    }
}