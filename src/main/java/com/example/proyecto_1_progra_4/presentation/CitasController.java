package com.example.proyecto_1_progra_4.presentation;

import com.example.proyecto_1_progra_4.logic.Citas;
import com.example.proyecto_1_progra_4.logic.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/citas")
public class CitasController {

    private final Service service;

    public CitasController(Service citasService) {
        this.service = citasService;
    }

    @GetMapping
    public Iterable<Citas> listarCitas() {
        return service.obtenerCitas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Citas> obtenerCita(@PathVariable Integer id) {
        return service.obtenerCitaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Citas crearCita(@RequestBody Citas cita) {
        return service.guardarCita(cita);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> cancelarCita(@PathVariable Integer id) {
//        if (citasService.cancelarCita(id)) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
}
