--
-- Tablas de la base de datos: `desafio1`
--

-- ----------------------------------------------------------------
--  TABLE MATERIA
-- ----------------------------------------------------------------
CREATE TABLE  materia(
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nombre VARCHAR(100) NOT NULL
);


-- ----------------------------------------------------------------
--  TABLE ALUMNO
-- ----------------------------------------------------------------
CREATE TABLE alumno (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    id_materia INT NOT NULL,
    FOREIGN KEY(id_materia) REFERENCES materia(id)
);

