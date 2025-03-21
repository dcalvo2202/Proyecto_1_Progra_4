package com.example.proyecto_1_progra_4.data;


import com.example.proyecto_1_progra_4.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {
Optional<Especialidad> findByNombre(String nombre);
}
