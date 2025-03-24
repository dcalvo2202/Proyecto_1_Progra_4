CREATE DATABASE proyecto1;
USE proyecto1;

CREATE TABLE perfiles (
    id INT PRIMARY KEY,
    nombre VARCHAR(20) UNIQUE NOT NULL -- 'PACIENTE', 'MEDICO', 'ADMIN'
);

CREATE TABLE permisos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE NOT NULL -- Ej: 'Crear Cita', 'Ver Citas', 'Finalizar Cita'
);

CREATE TABLE permisos_perfil (
    perfil_id INT NOT NULL,
    permiso_id INT NOT NULL,
    PRIMARY KEY (perfil_id, permiso_id),
    FOREIGN KEY (perfil_id) REFERENCES perfiles(id) ON DELETE CASCADE,
    FOREIGN KEY (permiso_id) REFERENCES permisos(id) ON DELETE CASCADE
);

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    clave VARCHAR(255) NOT NULL,
    perfil_id INT NOT NULL, -- Relación con la tabla de perfiles
	estado ENUM('PENDIENTE', 'APROBADO', 'RECHAZADO') NOT NULL DEFAULT 'PENDIENTE',
    FOREIGN KEY (perfil_id) REFERENCES perfiles(id) ON DELETE CASCADE
);

CREATE TABLE pacientes (
	id INT PRIMARY KEY,
    nombre VARCHAR (50) NOT NULL,
	apellido VARCHAR(50) NOT NULL,
    FOREIGN KEY (id) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE medicos (
    id INT PRIMARY KEY, -- ID coincide con el de usuarios
    nombre VARCHAR (50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    especialidad VARCHAR(50) NOT NULL,
    ciudad VARCHAR(20) NOT NULL,
    clinica VARCHAR(50) NOT NULL,
	frecuencia INT NOT NULL DEFAULT 30,
    id_horario INT NOT NULL, 
    FOREIGN KEY (id) REFERENCES usuarios(id) ON DELETE CASCADE,
	FOREIGN KEY (id_horario) REFERENCES horarios_medicos(id) ON DELETE CASCADE
);

CREATE TABLE horarios_medicos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    medico_id INT NOT NULL,
    dia ENUM('L', 'M', 'X', 'J', 'V', 'S', 'D') NOT NULL, -- Días de la semana
    hora_inicio TIME NOT NULL,
    hora_final TIME NOT NULL,
    FOREIGN KEY (medico_id) REFERENCES medicos(id) ON DELETE CASCADE
);

CREATE TABLE citas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    medico_id INT NOT NULL,
    paciente_id INT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    estado ENUM('PENDIENTE', 'ATENDIDA', 'CANCELADA') NOT NULL,
    anotaciones TEXT NULL,
    FOREIGN KEY (medico_id) REFERENCES medicos(id) ON DELETE CASCADE,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id) ON DELETE CASCADE
);

INSERT INTO perfiles (id, nombre) VALUES 
(0, 'ANONIMO'), 
(1, 'ADMIN'),
(2, 'MEDICO'),
(3, 'PACIENTE');

INSERT INTO permisos (id, nombre) VALUES 
(1, 'Login'), 
(2, 'Aprobar'), 
(3, 'Registro'), 
(4, 'Crear cita'),
(5, 'Buscar cita'),
(6, 'Horario medico'),
(7, 'Historico citas'),
(8, 'Gestion citas');

INSERT INTO permisos_perfil (perfil_id, permiso_id) VALUES
(0,7), -- ANONIMO puede Ver todas las citas, nada más
(1,1), -- ADMIN puede hacer login
(1,2), -- ADMIN puede aprobar usuarios
(2,1), -- MEDICO puede hacer login
(2,3), -- MEDICO puede registrarse
(2,8), -- MEDICO puede gestionar sus citas (historico de citas + attender o cancelarlas)
(3,1), -- PACIENTE puede hacer login
(3,3), -- PACIENTE puede registrarse
(3,4), -- PACIENTE puede crear cita
(3,5), -- PACIENTE puede buscar cita
(3,6), -- PACIENTE puede ver el horario de los medicos
(3,7); -- PACIENTE puede ver su historico de citas