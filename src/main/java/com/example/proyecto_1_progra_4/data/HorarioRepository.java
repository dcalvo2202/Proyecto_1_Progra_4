package com.example.proyecto_1_progra_4.data;


import com.example.proyecto_1_progra_4.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {
Optional<Horario> findById(Integer id);
List<Horario> findByMedioId(String medicoId);
List<Horario> findByDiaSemana(String diaSemana);
}
