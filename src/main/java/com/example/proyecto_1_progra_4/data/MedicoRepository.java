package com.example.proyecto_1_progra_4.data;

import com.example.proyecto_1_progra_4.logic.Medico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepository extends CrudRepository<Medico, Integer> {
    List<Medico> findAllByEspecialidadContainingIgnoreCaseAndCiudadContainingIgnoreCase(String especialidad, String ciudad);
}