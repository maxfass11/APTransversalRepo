package universidadlp.Entidades;

public class inscripcion {
    private int idInscripcion;
    private alumno idAlumno;
    private materia idMateria;
    private double nota;

    //Constructor vacio
    public inscripcion() {
    }
    
    //Constructor unicamente para el atributo nota.
    public inscripcion(double nota) {
        this.nota = nota;
    }
    
    //Constructor con todos los atributos excepto el id autogenerado
    public inscripcion(alumno idAlumno, materia idMateria, double nota) {
        this.idAlumno = idAlumno;
        this.idMateria = idMateria;
        this.nota = nota;
    }
    
    //Constructor con todos los atributos incluido el id autogenerado
    public inscripcion(int idInscripcion, alumno idAlumno, materia idMateria, double nota) {
        this.idInscripcion=idInscripcion;
        this.idAlumno=idAlumno;
        this.idMateria=idMateria;
        this.nota=nota;
    }
    
    
    //Get y set para los atributos de la clase
    public int getIdInscripcion() {
        return idInscripcion;
    }
    public void setIdInscripcion(int idInscripcion) {
     this.idInscripcion=idInscripcion;
    }
    
    
    public alumno getIdAlumno() {
        return idAlumno;
    }
    public void setAlumno(alumno idAlumno) {
        this.idAlumno=idAlumno;}
    
    
    public materia getIdMateria() {
        return idMateria;
    }
    
    
    public double getNota() {
        return nota;
    }
    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        String insc=idInscripcion+""+ idAlumno.getApellido()+", "+idAlumno.getNombre()+" "+idMateria.getNombre()+" "+nota;
        return insc;
    }
    

    
    }
    
    



