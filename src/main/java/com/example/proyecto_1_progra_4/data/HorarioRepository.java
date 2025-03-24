package com.example.proyecto_1_progra_4.data;


import com.example.proyecto_1_progra_4.logic.HorariosMedico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends CrudRepository<HorariosMedico, Integer> {
    List<HorariosMedico> findByDiaSemana(String diaSemana);

    List<HorariosMedico> findByMedicoId(Integer medicoId);
}
