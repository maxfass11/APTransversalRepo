package universidadlp.accesoADatos;

import universidadlp.Entidades.inscripcion;
import universidadlp.Entidades.materia;
import universidadlp.Entidades.alumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InscripcionData {
    private Connection con;
    private AlumnoData aluData;
    private MateriaData matData;

    public InscripcionData() {
    }

    public void guardarInscripcion(inscripcion insc) {
        // Query SQL para insertar una nueva inscripción
        String sql = "INSERT INTO inscripcion(idAlumno, idMateria, nota) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, insc.getIdAlumno().getIdAlumno());
            ps.setInt(2, insc.getIdMateria().getIdMateria());
            ps.setDouble(3, insc.getNota());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al guardar inscripción: " + ex.getMessage());
        }
    }

    
    public List<inscripcion> obtenerInscripciones() {
        List<inscripcion> inscripciones = new ArrayList<>();
        String sql = "SELECT * FROM inscripcion";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idInscripcion = rs.getInt("idInscripcion");
                int idAlumno = rs.getInt("idAlumno");
                int idMateria = rs.getInt("idMateria");
                double nota = rs.getDouble("nota");

                alumno alumno = aluData.buscarAlumno(idAlumno);
                materia materia = (materia) matData.buscarMateria(idMateria);

                inscripcion insc = new inscripcion(idInscripcion, alumno, materia, nota);
                inscripciones.add(insc);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener inscripciones: " + ex.getMessage());
        }
        return inscripciones;
    }

    public List<inscripcion> obtenerInscripcionesPorAlumno(int idAlumno) {
        List<inscripcion> inscripciones = new ArrayList<>();
        String sql = "SELECT * FROM inscripcion WHERE idAlumno = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idInscripcion = rs.getInt("idInscripcion");
                int idMateria = rs.getInt("idMateria");
                double nota = rs.getDouble("nota");

                alumno alumno = aluData.buscarAlumno(idAlumno);
                materia materia = (materia) matData.buscarMateria(idMateria);//RAAAARO

                inscripcion insc = new inscripcion(idInscripcion, alumno, materia, nota);
                inscripciones.add(insc);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener inscripciones por alumno: " + ex.getMessage());
        }
        return inscripciones;
    }

    public List<materia> obtenerMateriasCursadas(int idAlumno) {
        List<materia> materiasCursadas = new ArrayList<>();
        String sql = "SELECT m.* FROM materia m INNER JOIN inscripcion i ON m.idMateria = i.idMateria WHERE i.idAlumno = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idMateria = rs.getInt("idMateria");
                String nombre = rs.getString("nombre");
                int anio = rs.getInt("anio");
                boolean estado = rs.getBoolean("estado");

                materia materia = new materia(idMateria, nombre, anio, estado);
                materiasCursadas.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener materias cursadas: " + ex.getMessage());
        }
        return materiasCursadas;
    }

    public List<materia> obtenerMateriasNoCursadas(int idAlumno) {
        List<materia> materiasNoCursadas = new ArrayList<>();
        String sql = "SELECT * FROM materia WHERE idMateria NOT IN (SELECT idMateria FROM inscripcion WHERE idAlumno = ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idMateria = rs.getInt("idMateria");
                String nombre = rs.getString("nombre");
                int anio = rs.getInt("anio");
                boolean estado = rs.getBoolean("estado");

                materia materia = new materia(idMateria, nombre, anio, estado);
                materiasNoCursadas.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener materias no cursadas: " + ex.getMessage());
        }
        return materiasNoCursadas;
    }

    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria) {
        String sql = "DELETE FROM inscripcion WHERE idAlumno = ? AND idMateria = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al borrar inscripción de materia y alumno: " + ex.getMessage());
        }
    }

    public void actualizarNota(int idAlumno, int idMateria, double nota) {
        String sql = "UPDATE inscripcion SET nota = ? WHERE idAlumno = ? AND idMateria = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar nota de inscripción: " + ex.getMessage());
        }
    }

    public List<alumno> obtenerAlumnosPorMateria(int idMateria) {
        List<alumno> alumnos = new ArrayList<>();
        String sql = "SELECT a.* FROM alumno a INNER JOIN inscripcion i ON a.idAlumno = i.idAlumno WHERE i.idMateria = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idAlumno = rs.getInt("idAlumno");
                int dni = rs.getInt("dni");
                String apellido = rs.getString("apellido");
                String nombre = rs.getString("nombre");
                boolean estado = rs.getBoolean("estado");

                alumno alumno = new alumno(idAlumno, dni, apellido, nombre, null, estado);
                alumnos.add(alumno);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener alumnos por materia: " + ex.getMessage());
        }
        return alumnos;
    }
}



    

    