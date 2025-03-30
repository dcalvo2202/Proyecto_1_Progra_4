package com.example.proyecto_1_progra_4.data;

import com.example.proyecto_1_progra_4.logic.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    Optional<Usuario> findByNombre(String login);
}
