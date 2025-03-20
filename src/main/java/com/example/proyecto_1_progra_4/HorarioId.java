package com.example.proyecto_1_progra_4;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Embeddable
public class HorarioId implements java.io.Serializable {
    private static final long serialVersionUID = 7341831694700662257L;
    @NotNull
    @Column(name = "id_medico", nullable = false)
    private Integer idMedico;

    @NotNull
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @NotNull
    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HorarioId entity = (HorarioId) o;
        return Objects.equals(this.fecha, entity.fecha) &&
                Objects.equals(this.idMedico, entity.idMedico) &&
                Objects.equals(this.horaInicio, entity.horaInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, idMedico, horaInicio);
    }

}