package sv.edu.udb.repository;

import sv.edu.udb.Alumno;
import sv.edu.udb.Materia;

import java.sql.*;

public class AlumnoRepository {

    private Connection connection;

    public AlumnoRepository(Connection connection) {
        this.connection = connection;
    }

    public void agregarAlumno(Alumno alumno) throws SQLException {
        String sql = "INSERT INTO alumno (nombre, apellido, id_materia) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, alumno.getNombre());
            stmt.setString(2, alumno.getApellido());
            stmt.setInt(3, alumno.getMateria().getId());
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo insertar el alumno.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    alumno.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("No se pudo obtener el ID generado.");
                }
            }
        }
    }


    public Alumno obtenerAlumnoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM alumno WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Materia materia = new Materia(rs.getInt("id_materia"));
                    return new Alumno(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), materia);
                }
                return null;
            }
        }
    }


}
