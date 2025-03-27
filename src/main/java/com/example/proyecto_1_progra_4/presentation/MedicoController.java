package com.example.proyecto_1_progra_4.presentation;

import com.example.proyecto_1_progra_4.logic.Medico;
import com.example.proyecto_1_progra_4.logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/medicos")
public class MedicoController {
   @Autowired
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

    @PutMapping("/{id}/perfil")
    public ResponseEntity<Medico> actualizarPerfil(@PathVariable Integer id, @RequestBody Medico medicoActualizado) {
        return service.obtenerMedicoPorId(id)
                .map(medico -> {
                    medico.setEspecialidad(medicoActualizado.getEspecialidad());
                    medico.setCiudad(medicoActualizado.getCiudad());
                    medico.setClinica(medicoActualizado.getClinica());
                    medico.setFrecuencia(medicoActualizado.getFrecuencia());
                    medico.setRutaFoto(medicoActualizado.getRutaFoto());
                    return ResponseEntity.ok(service.guardarMedico(medico));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/primera-vez")
    public ResponseEntity<Boolean> esPrimeraVez(@PathVariable Integer id) {
        boolean primeraVez = service.esPrimeraVezMedico(id);
        return ResponseEntity.ok(primeraVez);
    }

}
