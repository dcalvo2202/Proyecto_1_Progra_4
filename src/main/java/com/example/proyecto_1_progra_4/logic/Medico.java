package com.example.proyecto_1_progra_4.logic;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "medicos")
public class Medico {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;


    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre debe tener como máximo 50 caracteres")
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 50, message = "El apellido debe tener como máximo 50 caracteres")
    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @NotBlank(message = "La especialidad no puede estar vacía")
    @Size(max = 50, message = "La especialidad debe tener como máximo 50 caracteres")
    @Column(name = "especialidad", nullable = false, length = 50)
    private String especialidad;

    @NotBlank(message = "La ciudad no puede estar vacía")
    @Size(max = 20, message = "La ciudad debe tener como máximo 20 caracteres")
    @Column(name = "ciudad", nullable = false, length = 20)
    private String ciudad;

    @NotBlank(message = "La clínica no puede estar vacía")
    @Size(max = 50, message = "La clínica debe tener como máximo 50 caracteres")
    @Column(name = "clinica", nullable = false, length = 50)
    private String clinica;

    @NotNull(message = "La frecuencia no puede ser nula")
    @Min(value = 5, message = "La frecuencia debe ser al menos 5")
    @ColumnDefault("30")
    @Column(name = "frecuencia", nullable = false)
    private Integer frecuencia;

    @Size(max = 255)
    private String rutaFoto;

    @NotNull(message = "El horario no puede ser nulo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_horario", nullable = false)
    private HorariosMedico idHorario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Usuario usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getClinica() {
        return clinica;
    }

    public void setClinica(String clinica) {
        this.clinica = clinica;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public HorariosMedico getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(HorariosMedico idHorario) {
        this.idHorario = idHorario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

}