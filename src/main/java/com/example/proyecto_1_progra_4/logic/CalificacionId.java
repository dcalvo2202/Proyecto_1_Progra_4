package com.example.proyecto_1_progra_4.logic;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class CalificacionId implements java.io.Serializable {
    private static final long serialVersionUID = 3560102192722410848L;
    @NotNull
    @Column(name = "id_medico", nullable = false)
    private Integer idMedico;

    @NotNull
    @Column(name = "id_paciente", nullable = false)
    private Integer idPaciente;

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CalificacionId entity = (CalificacionId) o;
        return Objects.equals(this.idPaciente, entity.idPaciente) &&
                Objects.equals(this.idMedico, entity.idMedico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPaciente, idMedico);
    }

}