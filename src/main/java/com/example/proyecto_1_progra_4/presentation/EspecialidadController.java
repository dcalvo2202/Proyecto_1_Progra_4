package com.example.proyecto_1_progra_4.presentation;

import com.example.proyecto_1_progra_4.Especialidad;
import com.example.proyecto_1_progra_4.logic.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

@GetMapping
    public List<Especialidad> listarEspecialidades() {
    return especialidadService.listarEspecialidades();
}
    @GetMapping("/{id}")
        public Optional<Especialidad> obtenerEspecialidad(@PathVariable Integer id) {
return especialidadService.obtenerEspecialidadPorId(id);
}

@PostMapping
public Especialidad crearEspecialidad(@RequestBody Especialidad especialidad) {
return especialidadService.guardarEspecialidad(especialidad);}
}

