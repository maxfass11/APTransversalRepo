package universidadlp.Vistas;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;
import universidadlp.Entidades.alumno;
import universidadlp.Entidades.materia;
import universidadlp.accesoADatos.AlumnoData;
import universidadlp.accesoADatos.MateriaData;
import universidadlp.accesoADatos.conexion;

public class UniversidadLP {

    public static void main(String[] args) {
        //Prueba de la clase conexion
        Connection con=conexion.getConexion(); 
        
        //Prueba del metodo agregar alumno
        /*
        alumno juan = new alumno(12312312, "Luna", "Pedro", LocalDate.of(1988,8, 10), true);
        AlumnoData alu=new AlumnoData();
        alu.guardarAlumno(juan);
        */
        
        //Prueba del metodo modificar alumno
        /*
        alumno juan = new alumno(1,12312312, "Luna", "Juan Pedro", LocalDate.of(1988,8, 10), true);
        AlumnoData alu=new AlumnoData();
        alu.modificarAlumno(juan);
        */
        
        //Prueba del metodo agregar materia FUNCIONA PERO TIRA ERROR REVISAR
        /*
        materia materia1 = new materia("Programacion", 1, true);
        MateriaData mat=new MateriaData();
        mat.guardarMateria(materia1);
        */
        
        //Prueba del metodo modificar materia 
        /*
        materia materia1 = new materia(2,"Programacion III", 2, true);
        MateriaData mat=new MateriaData();
        mat.modificarMateria(materia1);
        */
        
    }
}
