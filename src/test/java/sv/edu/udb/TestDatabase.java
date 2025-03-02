package sv.edu.udb;

import org.junit.jupiter.api.*;
import sv.edu.udb.repository.AlumnoRepository;
import sv.edu.udb.repository.MateriaRepository;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestDatabase {

    private static Connection connection;
    private static MateriaRepository materiaRepository;
    private static AlumnoRepository alumnoRepository;

    @BeforeAll
    public static void setup() throws SQLException {
        // Establecer la conexión a la base de datos H2 en memoria
        connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;", "sa", "");
        // Ejecutar los scripts de creación de las tablas
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("RUNSCRIPT FROM 'classpath:schema.sql'");
            stmt.execute("RUNSCRIPT FROM 'classpath:data.sql'");
        }
        materiaRepository = new MateriaRepository(connection);
        alumnoRepository = new AlumnoRepository(connection);
    }

    @Test
    public void testAgregarMateriaYAlumno() throws SQLException {
        // Crear una materia
        Materia materia = new Materia("DESARROLLO DE SOFTWARE");
        materiaRepository.agregarMateria(materia);

// Recuperar la materia insertada (su ID se asigna en agregarMateria)
//        Materia materiaGuardada = materiaRepository.obtenerMateriaPorId(materia.getId());
//        assertNotNull(materiaGuardada);
//        connection.commit();
//
//        assertTrue(materiaGuardada.getId() > 0, "El ID de la materia debería ser mayor que 0");

        // Crear un alumno y asignarle la materia
        ResultSet rs = null;
//        Alumno alumno = new Alumno((1), "MARIELA JULISSA", "VENTURA IRAHETA", materiaGuardada);
//        alumnoRepository.agregarAlumno(alumno);
        connection.commit();


        //Verificar que el alumno se haya agregado correctamente
        Alumno alumnoGuardado = alumnoRepository.obtenerAlumnoPorId(1);
        Statement stmt = connection.createStatement();
        rs = stmt.executeQuery("SELECT * FROM alumno");
        stmt = connection.createStatement();
        rs = stmt.executeQuery("SELECT a.id, a.nombre, a.apellido, m.nombre AS materia_nombre " +
                "FROM alumno a " +
                "JOIN materia m ON a.id_materia = m.id");

        while (rs.next()) {
            System.out.println("Alumno ID: " + rs.getInt("id") +
                    ", Nombre: " + rs.getString("nombre") +
                    ", Apellido: " + rs.getString("apellido") +
                    ", Materia: " + rs.getString("materia_nombre"));
        }
        assertNotNull(alumnoGuardado);
    }

}
