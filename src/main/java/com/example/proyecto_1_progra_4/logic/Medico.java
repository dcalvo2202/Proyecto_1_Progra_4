package com.example.proyecto_1_progra_4.logic;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "medicos")
public class Medico {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    // ... campos existentes ...

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
    //Nuevo Código
    @NotNull(message = "El costo no puede ser nulo")
    @Column(name = "costo", nullable = false)
    private double costo;

    @NotBlank(message = "La localidad no puede estar vacía")
    @Size(max = 100, message = "La localidad debe tener como máximo 100 caracteres")
    @Column(name = "localidad", nullable = false, length = 100)
    private String localidad;

    @Lob  // para textos largos
    @Column(name = "horario_semanal")
    private String horarioSemanal;

    @Size(max = 255)
    @Column(name = "ruta_foto")
    private String rutaFoto;

    @Lob // para textos largos
    @Column(name = "resena")
    private String resena;

    @ManyToOne(fetch = FetchType.LAZY, optional =false)
    @JoinColumn (name ="id", insertable = false, updatable = false)
    private Usuario usuario;

    public Integer getId() {
        return id;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
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

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getHorarioSemanal() {
        return horarioSemanal;
    }

    public void setHorarioSemanal(String horarioSemanal) {
        this.horarioSemanal = horarioSemanal;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public String getResena() {
        return resena;
    }

    public void setResena(String resena) {
        this.resena = resena;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}