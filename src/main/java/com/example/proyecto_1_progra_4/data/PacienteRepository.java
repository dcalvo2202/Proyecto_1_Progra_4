package com.example.proyecto_1_progra_4.data;

import com.example.proyecto_1_progra_4.logic.Paciente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, Integer> {
}
