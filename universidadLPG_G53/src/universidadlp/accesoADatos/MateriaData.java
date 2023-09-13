package universidadlp.accesoADatos;
import java.sql.Connection;
import java.sql.Date;
import universidadlp.Entidades.materia;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MateriaData {
    private Connection con=null;
    public MateriaData() {
        con=conexion.getConexion();
    }
    
//Metodo para guardado de nuevas materias
public void guardarMateria(materia materia) {
        //Query SQL para insertar nuevos registros
        String sql="Insert INTO materia(nombre, anio, estado) VALUES(?,?,?)";
        
        try {
            // Crear una PreparedStatement con la consulta SQL
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            
            // Establecer los valores de los parámetros
            ps.setString(1,materia.getNombre()); //Nombre materia
            ps.setInt(2,materia.getAnio()); //Anio de la carrera a la que corresponde
            ps.setBoolean(3,materia.isEstado()); //Estado de la materia
            
            //Ejecutar consulta
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            
            //Chequeo si se pudo agregar un nuevo registro e imprime mensaje.
            if(rs.next()){
                materia.setIdMateria(rs.getInt("idMateria"));
                JOptionPane.showMessageDialog(null,"Materia agregada exitosamente");
            }
            ps.cancel();            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla Materia");
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //Metodo para buscar materias por su ID
    public materia buscarMateria(int idMateria) {
       materia materia=null;
       
       //Query SQL
       String sql="SELECT nombre, anio FROM materia WHERE idMateria=? AND estado=1";
       PreparedStatement ps=null;
       ResultSet rs=null;
       
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            rs=ps.executeQuery();
            
            //Al encontrar una coincidencia crea un objeto materia y lo llena con la informacion de la BD.
                if(rs.next()){
                materia= new materia();
                materia.setIdMateria(idMateria);
                materia.setNombre(rs.getString("nombre"));
                materia.setEstado(true);
                
            //En caso de no hallar coincidencias con ese ID muestra un mensaje    
            }else{
                JOptionPane.showMessageDialog(null,"No existe el alumno");
            ps.close();
        }} catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla Materia: " +ex.getMessage());
        }
        return materia;
    }

public void modificarMateria(materia materia) {
        String sql = "UPDATE materia SET nombre = ?, anio = ? WHERE idMateria= ?";
        PreparedStatement ps =null;
        try {
            ps= con.prepareStatement(sql);
            ps.setString(1,materia.getNombre());
            ps.setInt(2,materia.getAnio());
            ps.setInt(3,materia.getIdMateria());
            int exito=ps.executeUpdate();
            
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Modificado Exitosamente.");
            }else{
                JOptionPane.showMessageDialog(null,"La materia no existe");
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla Materia: "+ex.getMessage());
        }
    }

    public void eliminarMateria(int id) {
        try {
            String sql = "UPDATE alumno SET estado = 0 WHERE idMateria = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int fila=ps.executeUpdate();

            if(fila==1){
            JOptionPane.showMessageDialog(null, " Se eliminó la materia.");
            }
            ps.close();
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Alumno"+ex.getMessage());
            }
    }
    
    public List<materia> listarMaterias(){
        List<materia> materias=new ArrayList<>();
        try{
            String sql="SELECT * FROM materia WHERE estado =1";
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                materia materia =new materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setEstado(rs.getBoolean("estado"));
                materias.add(materia);
            }
            ps.close();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla Materia "+ ex.getMessage());
        }
        return materias;
    }
}
