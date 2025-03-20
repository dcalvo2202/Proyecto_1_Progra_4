package com.example.proyecto_1_progra_4.data;

import com.example.proyecto_1_progra_4.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    Optional<Paciente> findByPacienteId(Integer id);
}
