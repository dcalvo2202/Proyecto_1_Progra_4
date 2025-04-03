package com.example.proyecto_1_progra_4.logic;

import jakarta.persistence.*;
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

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id", nullable = false)
    private Usuario usuario;

    @Size(max = 50)
    @NotNull
    @Column(name = "especialidad", nullable = false, length = 50)
    private String especialidad;

    @Size(max = 20)
    @NotNull
    @Column(name = "ciudad", nullable = false, length = 20)
    private String ciudad;

    @Size(max = 50)
    @NotNull
    @Column(name = "clinica", nullable = false, length = 50)
    private String clinica;

    @NotNull
    @ColumnDefault("30")
    @Column(name = "frecuencia", nullable = false)
    private Integer frecuencia;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_horario", nullable = false)
    private HorariosMedico idHorario;

    @NotNull
    @ColumnDefault("'PENDIENTE'")
    @Lob
    @Column(name = "estado", nullable = false)
    private String estado;

    @NotNull
    @Column(name = "costo", nullable = false)
    private Double costo;

    @Size(max = 100)
    @Column(name = "resena", length = 100)
    private String resena;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
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