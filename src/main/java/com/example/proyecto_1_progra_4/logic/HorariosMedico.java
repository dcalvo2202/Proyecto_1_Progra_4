package com.example.proyecto_1_progra_4.logic;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

@Entity
@Table(name = "horarios_medicos")
public class HorariosMedico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "El médico no puede estar vacío")
    @Column(name = "medico_id")
    private Integer medicoId;


    @NotBlank(message = "El día no puede estar vacío")
    @Lob
    @Column(name = "dia", nullable = false)
    private String dia;

    @NotNull(message = "La hora de inicio no puede ser nula")
    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @NotNull(message = "La hora final no puede ser nula")
    @Column(name = "hora_final", nullable = false)
    private LocalTime horaFinal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

}