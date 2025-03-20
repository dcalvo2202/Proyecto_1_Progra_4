package com.example.proyecto_1_progra_4;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalTime;

@Entity
@Table(name = "horarios")
public class Horario {
    @EmbeddedId
    private HorarioId id;

    @MapsId("idMedico")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico idMedico;

    @NotNull
    @Column(name = "hora_final", nullable = false)
    private LocalTime horaFinal;

    @NotNull
    @Column(name = "frecuencia", nullable = false)
    private LocalTime frecuencia;

    public HorarioId getId() {
        return id;
    }

    public void setId(HorarioId id) {
        this.id = id;
    }

    public Medico getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Medico idMedico) {
        this.idMedico = idMedico;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    public LocalTime getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(LocalTime frecuencia) {
        this.frecuencia = frecuencia;
    }

}