USE invex_db;

CREATE TABLE IF NOT EXISTS empleados (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    primer_nombre VARCHAR(100) NOT NULL,
    segundo_nombre VARCHAR(100),
    apellido_paterno VARCHAR(100) NOT NULL,
    apellido_materno VARCHAR(100),
    edad INT,
    sexo VARCHAR(10),
    fecha_nacimiento DATE,
    puesto VARCHAR(100)
);