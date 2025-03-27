package com.example.proyecto_1_progra_4.data;

import com.example.proyecto_1_progra_4.logic.Cita;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface CitasRepository extends CrudRepository<Cita, Integer> {
    List<Cita> findByMedicoIdOrderByFechaDescHoraDesc(Integer medicoId);

    List<Cita> findByMedicoIdAndEstadoOrderByFechaDescHoraDesc(Integer medicoId, String estado);

    List<Cita> findByMedicoIdAndPacienteNombreContainingIgnoreCaseOrderByFechaDescHoraDesc(Integer medicoId, String nombrePaciente);

    List<Cita> findByMedicoIdAndEstadoAndPacienteNombreContainingIgnoreCaseOrderByFechaDescHoraDesc(Integer medicoId, String estado, String nombrePaciente);

    List<Cita> findByMedicoIdOrderByFechaAscHoraAsc(Integer medicoId);

    List<Cita> findByMedicoIdAndFecha(Integer medicoId, LocalDate fecha);

    boolean existsByMedicoId(Integer medicoId);

    boolean existsByMedicoIdAndFechaAndHora(Integer medicoId, LocalDate fecha, LocalTime hora);

    boolean existsByMedicoIdAndEstado(Integer medicoId, String estado);
}

