package sv.edu.udb.repository;

import sv.edu.udb.Materia;

import java.sql.*;

public class MateriaRepository {

    private Connection connection;

    public MateriaRepository(Connection connection) {
        this.connection = connection;
    }

    public void agregarMateria(Materia materia) throws SQLException {
        String sql = "INSERT INTO materia (NOMBRE) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, materia.getNombre());
            stmt.executeUpdate();

            // Obtener el ID generado
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                materia.setId(generatedKeys.getInt(1));
            }
        }
    }


    public Materia obtenerMateriaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM materia WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Materia(rs.getInt("id"));
            }
        }
        return null;
    }

}
