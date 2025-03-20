package com.example.proyecto_1_progra_4.data;

import com.example.proyecto_1_progra_4.Citas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CitasRepository extends JpaRepository<Citas, Integer> {
   Optional<Citas> findById(Integer id);
}
