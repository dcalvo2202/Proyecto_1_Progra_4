package com.example.proyecto_1_progra_4.logic;

import com.example.proyecto_1_progra_4.Especialidad;
import com.example.proyecto_1_progra_4.data.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadService {
    @Autowired
    private EspecialidadRepository especialidadRepository;

    public List<Especialidad> listarEspecialidades() {
        return especialidadRepository.findAll();
    }

    public Optional<Especialidad> obtenerEspecialidadPorId(Integer id) {
        return especialidadRepository.findById(id);
    }

    public Optional<Especialidad> obtenerEspecialidadPorNombre(String nombre) {
        return especialidadRepository.findByNombre(nombre);
    }

    public Especialidad guardarEspecialidad(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    public void eliminarEspecialidad(Integer id) {
        especialidadRepository.deleteById(id);
    }
}
