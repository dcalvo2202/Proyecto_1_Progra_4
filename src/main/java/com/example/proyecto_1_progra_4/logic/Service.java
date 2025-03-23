package com.example.proyecto_1_progra_4.logic;

import com.example.proyecto_1_progra_4.data.CitasRepository;
import com.example.proyecto_1_progra_4.data.EspecialidadRepository;
import com.example.proyecto_1_progra_4.data.HorarioRepository;
import com.example.proyecto_1_progra_4.data.MedicoRepository;
import com.example.proyecto_1_progra_4.data.PacienteRepository;
import com.example.proyecto_1_progra_4.data.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service("service")
public class Service {

    @Autowired
    private final CitasRepository citasRepository;
    private EspecialidadRepository especialidadRepository;
    private HorarioRepository horarioRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final UsuarioRepository usuarioRepository;


    public Service (CitasRepository citasRepository, EspecialidadRepository especialidadRepository, HorarioRepository horarioRepository, MedicoRepository medicoRepository, PacienteRepository pacienteRepository, UsuarioRepository usuarioRepository) {
        this.citasRepository = citasRepository;
        this.especialidadRepository = especialidadRepository;
        this.horarioRepository = horarioRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.usuarioRepository = usuarioRepository;
    }
    // Citas
    public Optional<Citas> obtenerCitaPorId(Integer id) {
        return citasRepository.findById(id);
    }

    public Iterable<Citas> obtenerCitas() {
        return citasRepository.findAll();
    }

    public Citas guardarCita(Citas citas) {
        return citasRepository.save(citas);
    }

    //Especialidades
    public Iterable<Especialidad> listarEspecialidades() {
        return especialidadRepository.findAll();
    }

    public Optional<Especialidad> obtenerEspecialidadPorId(Integer id) {
        return especialidadRepository.findById(id);
    }

    public Optional<Especialidad> obtenerEspecialidadPorNombre(Integer id) {
        return especialidadRepository.findById(id);
    }

    public Especialidad guardarEspecialidad(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    public void eliminarEspecialidad(Integer id) {
        especialidadRepository.deleteById(id);
    }

    //Horarios

    public Iterable<Horario> listarHorarios() {
        return horarioRepository.findAll();
    }

    public Optional<Horario> obtenerHorarioPorId(Integer id) {
        return horarioRepository.findById(id);
    }

    public List<Horario> obtenerHorariosPorMedico(Integer medicoId) {
        return horarioRepository.findByMedicoId(medicoId);
    }

    public List<Horario> obtenerHorariosPorDiaSemana(String diaSemana) {
        return horarioRepository.findByDiaSemana(diaSemana);
    }

    public Horario guardarHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    public void eliminarHorario(Integer id) {
        horarioRepository.deleteById(id);
    }

    //Medicos
    public Iterable<Medico> obtenerMedicos() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> obtenerMedicoPorId(Integer id) {
        return medicoRepository.findById(id);
    }

    public Medico guardarMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    // Registro de Medico
    @Transactional
    public Medico registrarMedico(Usuario usuario, Medico medico) {
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        medico.setId(usuarioGuardado.getId());
        medico.setUsuarios(usuarioGuardado);
        medico.setEstado("PENDIENTE");
        return medicoRepository.save(medico);
    }

    //Pacientes
    public Iterable<Paciente> obtenerPacientes() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> obtenerPacientePorId(Integer id) {
        return pacienteRepository.findById(id);
    }

    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    //Registro de paciente
    @Transactional
    public Paciente registrarPaciente(Usuario usuario, Paciente paciente) {
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        paciente.setId(usuarioGuardado.getId());
        paciente.setUsuarios(usuarioGuardado);
        return pacienteRepository.save(paciente);
    }
    //Usuarios
    public Iterable<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Aprobación de Medico
    public Optional<Medico> aprobarMedico(Integer id) {
        Optional<Medico> medicoOptional = medicoRepository.findById(id);
        if (medicoOptional.isPresent()) {
            Medico medico = medicoOptional.get();
            medico.setEstado("APROBADO");
            return Optional.of(medicoRepository.save(medico));
        }
        return Optional.empty();
    }
}
