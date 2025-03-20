package com.example.proyecto_1_progra_4.presentation;

import com.example.proyecto_1_progra_4.Medico;
import com.example.proyecto_1_progra_4.logic.MedicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    public List<Medico> listarMedicos() {
        return medicoService.obtenerMedicos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtenerMedico(@PathVariable Integer id) {
        return medicoService.obtenerMedicoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Medico registrarMedico(@RequestBody Medico medico) {
        return medicoService.guardarMedico(medico);
    }

//    @PutMapping("/{id}/aprobar")
//    public ResponseEntity<Medico> aprobarMedico(@PathVariable Integer id) {
//        return medicoService.aprobarMedico(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
}
