package com.example.proyecto_1_progra_4.logic;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @NotNull(message = "El nombre no puede estar vacío")
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Size(min = 3, max = 50, message = "El apellido debe tener entre 3 y 50 caraacteres")
    @NotNull(message = "El apellido no puede estar vacio")
    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Email(message = "El email debe ser válido")
    @NotNull(message = "El email no puede estar vacío")
    @Size(max = 100, message = "El email no puede tener más de 100 caracteres")
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(min = 8, message = "La clave debe tener al menos 8 caracteres")
    @NotNull(message = "La clave no puede estar vacía")
    @Column(name = "clave", nullable = false)
    private String clave;

    @NotNull(message = "El perfil no puede ser nulo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfile perfil;

    @NotBlank(message = "El estado no puede estar vacío")
    @ColumnDefault("'PENDIENTE'")
    @Lob
    @Column(name = "estado", nullable = false)
    private String estado;




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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Perfile getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfile perfil) {
        this.perfil = perfil;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}