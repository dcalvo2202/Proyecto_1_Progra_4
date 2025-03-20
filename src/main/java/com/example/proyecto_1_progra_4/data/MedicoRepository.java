package com.example.proyecto_1_progra_4.data;

import com.example.proyecto_1_progra_4.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    Optional<Medico> findByMedicoId(Integer medicoId);
    Optional<Medico> findByEspecialidadId(Integer especialidadId);
    Optional<Medico> findByEstado(String estado);
}
