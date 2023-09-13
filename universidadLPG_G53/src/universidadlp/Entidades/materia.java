package universidadlp.Entidades;

//Clase entidad correspondiente a la tabla materia de la BD
public class materia {
    private int idMateria;
    private String nombre;
    private int anio;
    private boolean estado;

    //Constructor vacio
    public materia() {
    }
    
    //Constructor con todos los atributos salvo el id autogenerado
    public materia(String nombre, int anio, boolean estado) {
        this.nombre = nombre;
        this.anio = anio;
        this.estado = estado;
    }
    
    //Constructor con todos los atributos incluido el id autogenerado
    public materia(int idMateria, String nombre, int anio, boolean estado) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.anio = anio;
        this.estado = estado;
    }
    
    
    //Get y set para los atributos de la clase
    public int getIdMateria() {
        return idMateria;
    }
    public void setIdMateria(int idMateria){
        this.idMateria=idMateria;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre=nombre;
    }

    public int getAnio() {
        return anio;
    }
    public void setAnio(int anio) {
        this.anio=anio;
    }

    public boolean isEstado() {
        return estado;
    }
    public void setEstado(Boolean estado) {
        this.estado=estado;
    }
    
}
