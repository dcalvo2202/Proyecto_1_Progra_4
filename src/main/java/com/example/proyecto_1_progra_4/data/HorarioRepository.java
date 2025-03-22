package com.example.proyecto_1_progra_4.data;


import com.example.proyecto_1_progra_4.logic.Horario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends CrudRepository<Horario, Integer> {
    List<Horario> findByDiaSemana(String diaSemana);

    List<Horario> findByMedicoId(Integer medicoId);
}
