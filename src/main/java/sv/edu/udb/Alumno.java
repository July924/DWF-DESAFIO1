package sv.edu.udb;
public class Alumno {
    private int id;
    private String nombre;
    private String apellido;
    private Materia materia;

    // Constructor cuando aún no tiene ID
    public Alumno(String nombre, String apellido, Materia materia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.materia = materia;
    }

    public Alumno(int id, String nombre, String apellido, Materia materia) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.materia = materia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Materia getMateria() {
        return materia;
    }
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

}