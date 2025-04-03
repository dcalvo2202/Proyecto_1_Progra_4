package com.example.proyecto_1_progra_4.service;

import com.example.proyecto_1_progra_4.data.CitasRepository;
import com.example.proyecto_1_progra_4.data.HorarioRepository;
import com.example.proyecto_1_progra_4.data.MedicoRepository;
import com.example.proyecto_1_progra_4.data.UsuarioRepository;
import com.example.proyecto_1_progra_4.logic.Cita;
import com.example.proyecto_1_progra_4.logic.HorariosMedico;
import com.example.proyecto_1_progra_4.logic.Medico;
import com.example.proyecto_1_progra_4.logic.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;


@org.springframework.stereotype.Service("service")
public class Service {

    @Autowired
    private final CitasRepository citasRepository;
    private final HorarioRepository horarioRepository;
    private final MedicoRepository medicoRepository;
    private final UsuarioRepository usuarioRepository;


    public Service(CitasRepository citasRepository, HorarioRepository horarioRepository, MedicoRepository medicoRepository, UsuarioRepository usuarioRepository) {
        this.citasRepository = citasRepository;
        this.horarioRepository = horarioRepository;
        this.medicoRepository = medicoRepository;
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
        return horarioRepository.findByDia(diaSemana);
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
        medico.setEstado("PENDIENTE");
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
                medico.setEstado("APROBADO");
                usuarioRepository.save(usuario);
            }

            return Optional.of(medico);
        }
        return Optional.empty();
    }

    public boolean esPrimeraVezMedico(Integer medicoId) {
        return !citasRepository.existsByMedicoIdAndEstado(medicoId, "PENDIENTE");
    }

    public Optional<Medico> rechazarMedico(Integer id) {
        Optional<Medico> medicoOptional = medicoRepository.findById(id);
        if (medicoOptional.isPresent()) {
            Medico medico = medicoOptional.get();

            Usuario usuario = usuarioRepository.findById(medico.getId()).orElse(null);
            if (usuario != null) {
                medico.setEstado("RECHAZADO");
                usuarioRepository.save(usuario);
            }
            return medicoOptional;
        }
        return Optional.empty();
    }

    public Map<LocalDate, List<LocalTime>> obtenerHorarioExtendidoMedico(Integer medicoId, int dias) {
        Map<LocalDate, List<LocalTime>> agenda = new LinkedHashMap<>();
        LocalDate hoy = LocalDate.now();

        for (int i = 0; i < dias; i++) {
            LocalDate fecha = hoy.plusDays(i);
            List<LocalTime> disponibles = obtenerEspaciosDisponibles(medicoId, fecha);
            if (!disponibles.isEmpty()) {
                agenda.put(fecha, disponibles);
            }
        }

        return agenda;
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
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(clave, usuario.getClave())) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    public List<Medico> buscarMedicosPorEspecialidadYCiudad(String especialidad, String ciudad) {
        return medicoRepository.findAllByEspecialidadContainingIgnoreCaseAndCiudadContainingIgnoreCase(especialidad, ciudad);
    }

    public List<LocalTime> obtenerEspaciosDisponibles(Integer medicoId, LocalDate fecha) {
        Optional<Medico> medicoOptional = medicoRepository.findById(medicoId);
        if (!medicoOptional.isPresent()) {
            return new ArrayList<>();
        }
        Medico medico = medicoOptional.get();
        // Obtener los horarios del medico
        List<HorariosMedico> horarios = horarioRepository.findByMedicoId(medicoId);

        if(horarios.isEmpty()){
            return new ArrayList<>();
        }

        // Determinar el día de la semana para la fecha dada
        String diaSemana = fecha.getDayOfWeek().toString(); 
        LocalTime horaInicio = null;
        LocalTime horaFin = null;
        //Obtener horas de inicio y fin
        for (HorariosMedico horario : horarios){
            if (horario.getDia().equals(diaSemana)){
                horaInicio = horario.getHoraInicio();
                horaFin = horario.getHoraFinal();
                break;
            }
        }

        if(horaInicio == null || horaFin == null){
            return new ArrayList<>();
        }

        //Calcular los espacios disponibles
        int frecuencia = medico.getFrecuencia();
        List<LocalTime> espaciosDisponibles = new ArrayList<>();
        LocalTime tiempoActual = horaInicio;
        while(tiempoActual.isBefore(horaFin)){
            // Verificar si existe una cita para este espacio
            if(!citasRepository.existsByMedicoIdAndFechaAndHora(medicoId, fecha, tiempoActual)){
                espaciosDisponibles.add(tiempoActual);
            }

            tiempoActual = tiempoActual.plusMinutes(frecuencia);

        }
        return espaciosDisponibles;
    }

    public List<Cita> obtenerCitasDisponiblesPorMedicoYFecha(Integer medicoId, LocalDate fecha) {
        return citasRepository.findByMedicoIdAndFechaAndEstado(medicoId, fecha, "DISPONIBLE");
    }

    public List<Cita> obtenerCitasPorPaciente(Integer pacienteId) {
        return citasRepository.findByPacienteIdOrderByFechaDescHoraDesc(pacienteId);
    }

    public boolean validarDisponibilidad(Integer medicoId, LocalDate fecha, LocalTime hora) {
        return !citasRepository.existsByMedicoIdAndFechaAndHora(medicoId, fecha, hora);
    }
}
