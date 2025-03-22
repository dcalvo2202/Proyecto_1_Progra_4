package com.example.proyecto_1_progra_4.data;

import com.example.proyecto_1_progra_4.logic.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends CrudRepository<Medico, Integer> {
    Optional<Medico> findByEspecialidadId(Integer especialidadId);
}