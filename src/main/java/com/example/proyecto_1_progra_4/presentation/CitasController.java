package com.example.proyecto_1_progra_4.presentation;

import com.example.proyecto_1_progra_4.logic.Cita;
import com.example.proyecto_1_progra_4.logic.Service;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/citas")
public class CitasController {

    private final Service service;

    public CitasController(Service citasService) {
        this.service = citasService;
    }

    @GetMapping
    public Iterable<Cita> listarCitas() {
        return service.obtenerCitas();
    }


    @PostMapping
    public ResponseEntity<?> crearCita(@Valid @RequestBody Cita cita, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errores = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errores);
        }
        return ResponseEntity.ok(service.guardarCita(cita));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerCita(@PathVariable Integer id) {
        return service.obtenerCitaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/medico/{id}")
    public List<Cita> listarCitas(@PathVariable Integer id,
                                  @RequestParam(required = false) String estado,
                                  @RequestParam(required = false) String paciente) {
        if (estado != null && paciente != null) {
            return service.filtrarCitasPorEstadoYNombre(id, estado, paciente);
        } else if (estado != null) {
            return service.filtrarCitasPorEstado(id, estado);
        } else if (paciente != null) {
            return service.filtrarCitasPorNombrePaciente(id, paciente);
        } else {
            return service.listarCitasPorMedico(id);
        }
    }

    @PutMapping("/{id}/atender")
    public ResponseEntity<Cita> atenderCita(@PathVariable Integer id, @RequestBody String anotaciones) {
        Optional<Cita> citaOpt = service.obtenerCitaPorId(id);
        if (citaOpt.isPresent()) {
            Cita cita = citaOpt.get();
            cita.setEstado("ATENDIDA");
            cita.setAnotaciones(anotaciones);
            return ResponseEntity.ok(service.guardarCita(cita));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Cita> cancelarCita(@PathVariable Integer id) {
        Optional<Cita> citaOpt = service.obtenerCitaPorId(id);
        if (citaOpt.isPresent()) {
            Cita cita = citaOpt.get();
            cita.setEstado("CANCELADA");
            return ResponseEntity.ok(service.guardarCita(cita));
        }
        return ResponseEntity.notFound().build();
    }
  //  @GetMapping("/medico/{medicoId}/ordenadas")
   // public ResponseEntity<List<Cita>> listarCitasOrdenadas(@PathVariable Integer medicoId) {
    //List<Cita> citas = service.listarCitasPorMedicoOrdenadas(medicoId);
   // return new ResponseEntity<>(citas, HttpStatus.OK);
   // }
}

