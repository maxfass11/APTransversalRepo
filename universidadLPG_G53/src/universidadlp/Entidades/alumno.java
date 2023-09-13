package universidadlp.Entidades;
import java.time.LocalDate;

//Clase entidad correspondiente a la tabla alumno de la BD
public class alumno {
    private int idAlumno;
    private int DNI;
    private String apellido;
    private String nombre;
    private LocalDate fechaNacimiento;
    private boolean estado;

    //Constructor vacio
    public alumno() {
    }
    
    //Constructor con todos los atributos salvo el id autogenerado
    public alumno(int DNI, String apellido, String nombre, LocalDate fechaNacimiento, boolean estado) {
        this.DNI=DNI;
        this.apellido = apellido;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
    }
    
    //Constructor con todos los atributos incluido el id autogenerado
    public alumno(int idAlumno, int DNI, String apellido, String nombre, LocalDate fechaNacimiento, boolean estado) {
        this.idAlumno = idAlumno;
        this.DNI=DNI;
        this.apellido = apellido;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
    }

    //Get y set para los atributos de la clase
    public int getIdAlumno() {
        return idAlumno;
    }
    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getDNI() {
        return DNI;
    }
    public void setDNI(int DNI) {
        this.DNI = DNI;
    }
    
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento=fechaNacimiento;
    }

    public boolean isEstado() {
        return estado;
    }
    public void setEstado(Boolean estado) {
        this.estado=estado;
    }
}
