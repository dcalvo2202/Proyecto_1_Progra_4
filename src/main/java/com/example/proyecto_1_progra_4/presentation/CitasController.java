package com.example.proyecto_1_progra_4.presentation;

import com.example.proyecto_1_progra_4.Citas;
import com.example.proyecto_1_progra_4.logic.CitasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitasController {

    private final CitasService citasService;

    public CitasController(CitasService citasService) {
        this.citasService = citasService;
    }

    @GetMapping
    public List<Citas> listarCitas() {
        return citasService.obtenerCitas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Citas> obtenerCita(@PathVariable Integer id) {
        return citasService.obtenerCitaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Citas crearCita(@RequestBody Citas cita) {
        return citasService.guardarCita(cita);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> cancelarCita(@PathVariable Integer id) {
//        if (citasService.cancelarCita(id)) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
}
