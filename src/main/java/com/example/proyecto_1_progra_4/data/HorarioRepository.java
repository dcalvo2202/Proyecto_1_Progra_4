package com.example.proyecto_1_progra_4.data;


import com.example.proyecto_1_progra_4.logic.HorariosMedico;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//@Repository
//@Transactional
//public interface HorarioRepository extends JpaRepository<HorariosMedico, Integer> {
//
//}


@Repository
public interface HorarioRepository extends CrudRepository<HorariosMedico, Integer> {
    List<HorariosMedico> findByDia(String diaSemana);

    List<HorariosMedico> findByMedicoId(Integer medicoId);
}
