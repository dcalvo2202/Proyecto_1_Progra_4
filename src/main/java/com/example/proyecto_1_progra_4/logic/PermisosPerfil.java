package com.example.proyecto_1_progra_4.logic;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "permisos_perfil")
public class PermisosPerfil {
    @EmbeddedId
    private PermisosPerfilId id;

    @MapsId("perfilId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfile perfil;

    @MapsId("permisoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "permiso_id", nullable = false)
    private Permiso permiso;

    public PermisosPerfilId getId() {
        return id;
    }

    public void setId(PermisosPerfilId id) {
        this.id = id;
    }

    public Perfile getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfile perfil) {
        this.perfil = perfil;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

}
