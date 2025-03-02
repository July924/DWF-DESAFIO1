package sv.edu.udb;
public class Materia {
    private int id;
    private String nombre;

    public Materia(String nombre) {
        this.nombre = nombre;
    }
    public Materia(int id) {
        this.id = id;
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
}