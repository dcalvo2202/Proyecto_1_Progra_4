package com.example.proyecto_1_progra_4.presentation;

// import ch.qos.logback.core.model.Model;
import com.example.proyecto_1_progra_4.data.UsuarioRepository;
import com.example.proyecto_1_progra_4.logic.Cita;
import com.example.proyecto_1_progra_4.logic.Medico;
import com.example.proyecto_1_progra_4.service.Service;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/citas")
public class CitasController {

    private final Service service;

    @Autowired
    private UsuarioRepository usuarioRepository;
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

    @GetMapping("/medicos/buscar")
    public List<Medico> buscarMedicos(@RequestParam String especialidad, @RequestParam String ciudad) {
        return service.buscarMedicosPorEspecialidadYCiudad(especialidad, ciudad);
    }

    @GetMapping("/medicos/{medicoId}/disponibilidad")
    public List<LocalTime> obtenerDisponibilidad(@PathVariable Integer medicoId, @RequestParam LocalDate fecha) {
        return service.obtenerEspaciosDisponibles(medicoId, fecha);
    }

    @GetMapping("/medicos/{medicoId}/citas-disponibles")
    public List<Cita> obtenerCitasDisponibles(@PathVariable Integer medicoId, @RequestParam LocalDate fecha) {
        return service.obtenerCitasDisponiblesPorMedicoYFecha(medicoId, fecha);
    }

    @GetMapping("/paciente/{pacienteId}")
    public List<Cita> obtenerCitasPorPaciente(@PathVariable Integer pacienteId) {
        return service.obtenerCitasPorPaciente(pacienteId);
    }

    @GetMapping("/medicos/{medicoId}/agenda")
    public Map<LocalDate, List<LocalTime>> verAgendaExtendida(
            @PathVariable Integer medicoId,
            @RequestParam(defaultValue = "7") int dias) {
        return service.obtenerHorarioExtendidoMedico(medicoId, dias);
    }

}

