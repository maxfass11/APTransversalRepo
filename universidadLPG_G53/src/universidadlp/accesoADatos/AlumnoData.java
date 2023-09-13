package universidadlp.accesoADatos;

import universidadlp.Entidades.alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;


public class AlumnoData {
    private Connection con=null;
    public AlumnoData(){
        con=conexion.getConexion();
    }
    
    //Metodo para guardado de nuevos alumnos
    public void guardarAlumno(alumno alumno) {
        //QuerySQL para insertar nuevos registros
        String sql = "INSERT INTO alumno(dni, apellido, nombre, fechaNacimiento, estado) VALUES(?, ?, ?, ?, ?)";
        try{   
            // Crear una PreparedStatement con la consulta SQL
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            // Establecer los valores de los parámetros
            ps.setInt(1, alumno.getDNI()); // dni
            ps.setString(2, alumno.getApellido()); // apellido
            ps.setString(3, alumno.getNombre()); // nombre
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento())); // problema entre LocalDate y Date REVISAR
            ps.setBoolean(5, alumno.isEstado()); // estado

            // Ejecutar la consulta
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            
            //Chequeo si se pudo agregar un nuevo registro e imprime mensaje.
            if(rs.next()){
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                JOptionPane.showMessageDialog(null,"Alumno agregado exitosamente");
            }
            ps.close();
    }catch(SQLException ex){
        JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno: "+ex.getMessage());
        }
}
    //Metodo de busqueda por ID
    public alumno buscarAlumno(int idAlumno) {
        alumno alumno=null;
        //Query SQL
        String sql= "SELECT dni, apellido, nombre, fechaNacimiento FROM alumno WHERE idAlumno=? AND estado=1";
        PreparedStatement ps=null;
        ResultSet rs = null; // Agrega esta línea para declarar el ResultSet fuera del try-catch
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,idAlumno);
            rs=ps.executeQuery();
            
            //Al encontrar una coincidencia crea un objeto alumno y lo llena con la informacion de la BD.
            if(rs.next()){
                alumno= new alumno();
                alumno.setIdAlumno(idAlumno);
                alumno.setDNI(rs.getInt("DNI"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);
            
            //En caso de no hallar coincidencias con ese ID muestra un mensaje 
            }else{
                JOptionPane.showMessageDialog(null,"No existe el alumno");
            ps.close();
        }}catch (SQLException ex){
        JOptionPane.showMessageDialog(null,"Error al acceder a la tabla Alumno: " +ex.getMessage());
        }
        return alumno;
    }

    public alumno buscarAlumnoPorDNI(int dni) {
        alumno alumno=null;
        String sql="SELECT idAlumno, dni, apellido, nombre, fechaNacimiento FROM alumno WHERE dni=? AND estado=1";
        PreparedStatement ps=null;
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()){
            alumno=new alumno();
            alumno.setIdAlumno(rs.getInt("idAlumno"));
            alumno.setDNI(rs.getInt("DNI"));
            alumno.setApellido(rs.getString("apellido"));
            alumno.setNombre(rs.getString("nombre"));
            alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
            alumno.setEstado(true);
            }else{
            JOptionPane.showMessageDialog(null,"No existe el alumno");
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla Alumno : "+ ex.getMessage());
        }
        return alumno;
    }

    public List<alumno> listarAlumnos(){
        List<alumno> alumnos=new ArrayList<>();
        try{
            String sql="SELECT * FROM alumno WHERE estado =1 ";
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                alumno alumno =new alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDNI(rs.getInt("DNI"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
                alumnos.add(alumno);
            }
            ps.close();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla Alumno "+ ex.getMessage());
        }
        return alumnos;
    }
    
    public void modificarAlumno(alumno alumno) {
        String sql = "UPDATE alumno SET dni = ?, apellido = ?, nombre = ?, fechaNacimiento= ? WHERE idAlumno = ?";
        PreparedStatement ps =null;
        
        try {
            ps= con.prepareStatement(sql);
            ps.setInt(1, alumno.getDNI());
            ps.setString(2, alumno.getApellido());
            ps.setString(3,alumno.getNombre());
            ps.setDate(4,Date.valueOf(alumno.getFechaNacimiento()));
            ps.setInt(5,alumno.getIdAlumno());
            int exito=ps.executeUpdate();
            
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Modificado Exitosamente.");
            }else{
                JOptionPane.showMessageDialog(null,"El alumno no existe");
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla Alumno : "+ex.getMessage());
        }
    }
    

    public void eliminarAlumno(int id) {
    try {
        String sql = "UPDATE alumno SET estado = 0 WHERE idAlumno = ? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        int fila=ps.executeUpdate();

        if(fila==1){
        JOptionPane.showMessageDialog(null, " Se eliminó el alumno.");
        }
        ps.close();
        } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Alumno"+ex.getMessage());
        }
    }
    
}
