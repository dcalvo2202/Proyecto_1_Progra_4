package com.example.proyecto_1_progra_4.presentation;

import com.example.proyecto_1_progra_4.logic.Medico;
import com.example.proyecto_1_progra_4.logic.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final Service service;

    public MedicoController(Service medicoService) {
        this.service = medicoService;
    }

    @GetMapping
    public Iterable<Medico> listarMedicos() {
        return service.obtenerMedicos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtenerMedico(@PathVariable Integer id) {
        return service.obtenerMedicoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Medico registrarMedico(@RequestBody Medico medico) {
        return service.guardarMedico(medico);
    }

//    @PutMapping("/{id}/aprobar")
//    public ResponseEntity<Medico> aprobarMedico(@PathVariable Integer id) {
//        return medicoService.aprobarMedico(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
}
