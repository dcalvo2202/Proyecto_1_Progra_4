package com.example.proyecto_1_progra_4.logic;

import com.example.proyecto_1_progra_4.data.CitasRepository;
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
    private HorarioRepository horarioRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final UsuarioRepository usuarioRepository;


    public Service (CitasRepository citasRepository, HorarioRepository horarioRepository, MedicoRepository medicoRepository, PacienteRepository pacienteRepository, UsuarioRepository usuarioRepository) {
        this.citasRepository = citasRepository;
        this.horarioRepository = horarioRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.usuarioRepository = usuarioRepository;
    }
    // Citas
    public Optional<Cita> obtenerCitaPorId(Integer id) {
        return citasRepository.findById(id);
    }

    public Iterable<Cita> obtenerCitas() {
        return citasRepository.findAll();
    }

    public Cita guardarCita(Cita cita) {
        return citasRepository.save(cita);
    }

    public List<Cita> listarCitasPorMedico(Integer medicoId) {
        return citasRepository.findByMedicoIdOrderByFechaDescHoraDesc(medicoId);
    }

    public List<Cita> filtrarCitasPorEstado(Integer medicoId, String estado) {
        return citasRepository.findByMedicoIdAndEstadoOrderByFechaDescHoraDesc(medicoId, estado);
    }

    public List<Cita> filtrarCitasPorNombrePaciente(Integer medicoId, String nombrePaciente) {
        return citasRepository.findByMedicoIdAndPacienteNombreContainingIgnoreCaseOrderByFechaDescHoraDesc(medicoId, nombrePaciente);
    }

    public List<Cita> filtrarCitasPorEstadoYNombre(Integer medicoId, String estado, String nombrePaciente) {
        return citasRepository.findByMedicoIdAndEstadoAndPacienteNombreContainingIgnoreCaseOrderByFechaDescHoraDesc(medicoId, estado, nombrePaciente);
    }

    //Horarios

    public Iterable<HorariosMedico> listarHorarios() {
        return horarioRepository.findAll();
    }

    public Optional<HorariosMedico> obtenerHorarioPorId(Integer id) {
        return horarioRepository.findById(id);
    }

    public List<HorariosMedico> obtenerHorariosPorMedico(Integer medicoId) {
        return horarioRepository.findByMedicoId(medicoId);
    }

    public List<HorariosMedico> obtenerHorariosPorDiaSemana(String diaSemana) {
        return horarioRepository.findByDiaSemana(diaSemana);
    }

    public HorariosMedico guardarHorario(HorariosMedico horario) {
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

    @Transactional
    public Medico registrarMedico(Usuario usuario, Medico medico) {
        usuario.setEstado("PENDIENTE");
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        medico.setId(usuarioGuardado.getId());
        medico.setUsuario(usuarioGuardado);
        return medicoRepository.save(medico);
    }

    public Optional<Medico> aprobarMedico(Integer id) {
        Optional<Medico> medicoOptional = medicoRepository.findById(id);
        if (medicoOptional.isPresent()) {
            Medico medico = medicoOptional.get();

            Usuario usuario = usuarioRepository.findById(medico.getId()).orElse(null);
            if (usuario != null) {
                usuario.setEstado("APROBADO");
                usuarioRepository.save(usuario);
            }

            return Optional.of(medico);
        }
        return Optional.empty();
    }

    public boolean esPrimeraVezMedico(Integer medicoId) {
        return !citasRepository.existsByMedicoIdAndEstado(medicoId, "PENDIENTE");
    }

    // Pacientes
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

    // Login
    public Optional<Usuario> login(Integer id, String clave) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            if (usuario.getClave().equals(clave) && usuario.getEstado().equals("APROBADO")) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }
}
