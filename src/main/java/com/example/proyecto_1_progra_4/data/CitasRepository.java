package com.example.proyecto_1_progra_4.data;

import com.example.proyecto_1_progra_4.logic.Cita;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitasRepository extends CrudRepository<Cita, Integer> {
    List<Cita> findByMedicoIdOrderByFechaDescHoraDesc(Integer medicoId);

    List<Cita> findByMedicoIdAndEstadoOrderByFechaDescHoraDesc(Integer medicoId, String estado);

    List<Cita> findByMedicoIdAndPacienteNombreContainingIgnoreCaseOrderByFechaDescHoraDesc(Integer medicoId, String nombrePaciente);

    List<Cita> findByMedicoIdAndEstadoAndPacienteNombreContainingIgnoreCaseOrderByFechaDescHoraDesc(Integer medicoId, String estado, String nombrePaciente);

    boolean existsByMedicoId(Integer medicoId);

    boolean existsByMedicoIdAndEstado(Integer medicoId, String estado);
}
