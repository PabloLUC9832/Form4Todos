package mx.uv.modelo.cuestionario;

import mx.uv.modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CuestionarioDAO_Imp {
    
    private Conexion conexion = new Conexion();
    //CREAR NUESTRAS TABLAS / CUESTIONARIOS
    public String createCuestionario(Cuestionario cuestionario){

        Connection conn = null;
        PreparedStatement prestm  = null;
        String msj = "Cuestionario creado con éxito.";
        conn = conexion.getConnection();        
        String nombreCuestionario = cuestionario.getNombreCuestionario();      
        try {
            String sql1 = "CREATE TABLE `proyectosw_e4`.`"+nombreCuestionario+"`"+"( `id` INT(11) NOT NULL AUTO_INCREMENT, `alumno` VARCHAR(50) NULL , `pregunta` VARCHAR(255) NULL , `respuesta` VARCHAR(255) NULL,`calificacion` VARCHAR(255) NULL  , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
            prestm = conn.prepareStatement(sql1);
            prestm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (prestm != null){
                try {
                    prestm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return msj;        
    }
    //CREAR PREGUNTAS CON POP UP (/crearPregunta)
    public String createPregunta(Cuestionario cuestionario){

        Connection conn = null;
        PreparedStatement prestm0  = null;
        PreparedStatement prestm2 = null;
        String msj = "Se creo con éxto la pregunta";
        conn = conexion.getConnection();        
        String nombreCuestionario = cuestionario.getNombreCuestionario();      
        try {
            String sql0 = "SELECT 1 FROM `" +nombreCuestionario+"` LIMIT 1";
            String sql2 = "INSERT INTO `"+nombreCuestionario+"` (id,alumno,pregunta,respuesta,calificacion) VALUES (?,?,?,?,?)";
            prestm0 = conn.prepareStatement(sql0);
            prestm2 = conn.prepareStatement(sql2);
            if (prestm0.execute() == true){
                prestm2.setInt(1, cuestionario.getId());
                prestm2.setString(2, cuestionario.getAlumno()); 
                prestm2.setString(3, cuestionario.getPregunta()); 
                prestm2.setString(4, cuestionario.getRespuesta());
                prestm2.setString(5, cuestionario.getCalificacion());
                prestm2.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (prestm2 != null){
                try {
                    prestm2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return msj;        
    }
    
    //USAMOS PARA RESPONDER LOS EXAMENES ()
    public List<Cuestionario> listaPreguntas(Cuestionario cuestionario) {

        Statement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Cuestionario> resultado = new ArrayList<>(); 
        String nombreCuestionario = cuestionario.getNombreCuestionario();
        String msj = "";      
        conn = conexion.getConnection();

        try {
            String sql = "SELECT * FROM `"+nombreCuestionario+"`";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()){
                Cuestionario u = new Cuestionario(rs.getInt("id"), rs.getString("pregunta"));
                resultado.add(u);
            }
            if (stm.executeUpdate(sql) >0) 
                msj = "Éxito al mostrar el cuestionario";
            else
                msj = "Error al mostrar el cuestionrio"; 
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException e) {
                    stm = null;
                    e.printStackTrace();
                }
            }
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    rs = null;
                    e.printStackTrace();
                }
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }

    //ELIMINAR CUESTIONARIO
    public String eliminarCuestionario(Cuestionario cuestionario){

        Connection conn = null;
        PreparedStatement prestm0  = null;
        String msj = "";
        conn = conexion.getConnection();        
        String nombreCuestionario = cuestionario.getNombreCuestionario();      
        try {
            String sql0 = "DROP TABLE `" +nombreCuestionario+"`";
            prestm0 = conn.prepareStatement(sql0);            
            if (prestm0.execute()){
                msj="Cuestionario eliminado";
            }else{
                msj="Error al eliminar el cuestionario";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (prestm0 != null){
                try {
                    prestm0.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return msj;        
    }

    //USAMOS PARA CALIFICAR LOS EXAMENES
    public List<Cuestionario> listaPreguntasParaCalificar(Cuestionario cuestionario) {

        Statement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Cuestionario> resultado = new ArrayList<>(); 
        String nombreCuestionario = cuestionario.getNombreCuestionario();
        String msj = "";      
        conn = conexion.getConnection();

        try {
            String sql = "SELECT * FROM `"+nombreCuestionario+"`";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()){
                Cuestionario u = new Cuestionario(rs.getInt("id"),rs.getString("alumno"), rs.getString("pregunta"), rs.getString("respuesta"));
                resultado.add(u);
            }
            if (stm.executeUpdate(sql) >0) 
                msj = "Éxito al mostrar el cuestionario";
            else
                msj = "Error al mostrar el cuestionrio"; 
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException e) {
                    stm = null;
                    e.printStackTrace();
                }
            }
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    rs = null;
                    e.printStackTrace();
                }
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }
    
    //PARA CONTESTAR LOS EXAMENES, MANDAR RESPUESTA EL VIDEO
    public String modificarRespuesta(Cuestionario cuestionario){

        Connection conn = null;
        PreparedStatement prestm = null;
        String msj = "";

        conn = conexion.getConnection();
        String nombreCuestionario = cuestionario.getNombreCuestionario();      
        try {
            String sql = "UPDATE `"+nombreCuestionario+"` SET alumno = ? , respuesta = ? WHERE id = ?;";
            prestm = conn.prepareStatement(sql);            
            prestm.setString(1, cuestionario.getAlumno());            
            prestm.setString(2, cuestionario.getRespuesta());                        
            prestm.setInt(3,cuestionario.getId());
            if (prestm.executeUpdate() >0) 
                msj = "Respuesta guardada";
            else
                msj = "Error al guardar respuesta";         
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (prestm != null){
                try {
                    prestm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return msj;        
        
    }

    //PARA PONERLE CALIFACACIÓN A LOS EXAMENES
    public String calificarPregunta(Cuestionario cuestionario){

        Connection conn = null;
        PreparedStatement prestm = null;
        String msj = "";

        conn = conexion.getConnection();
        String nombreCuestionario = cuestionario.getNombreCuestionario();      
        try {
            String sql = "UPDATE `"+nombreCuestionario+"` SET calificacion = ? WHERE id = ?;";
            prestm = conn.prepareStatement(sql);                                
            prestm.setString(1, cuestionario.getCalificacion());                        
            prestm.setInt(2,cuestionario.getId());
            if (prestm.executeUpdate() >0) 
                msj = "Calificaciones guardadas";
            else
                msj = "Error al guardar la calificación";         
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (prestm != null){
                try {
                    prestm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return msj;        
        
    }
    
    //VER TODOS LOS DATOS DE LOS EXAMENES, VISUALIZAR TODO EL CUESTIONARIO
    public List<Cuestionario> listaCuestionariosHechos(Cuestionario cuestionario) {

        Statement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Cuestionario> resultado = new ArrayList<>(); 
        String nombreCuestionario = cuestionario.getNombreCuestionario();
        String msj = "";      
        conn = conexion.getConnection();

        try {
            String sql = "SELECT * FROM `"+nombreCuestionario+"`";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()){
                Cuestionario u = new Cuestionario(rs.getInt("id"),rs.getString("alumno"), rs.getString("pregunta"), rs.getString("respuesta"), rs.getString("calificacion"));
                resultado.add(u);
            }
            if (stm.executeUpdate(sql) >0) 
                msj = "Éxito al mostrar el cuestionario";
            else
                msj = "Error al mostrar el cuestionrio"; 
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException e) {
                    stm = null;
                    e.printStackTrace();
                }
            }
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    rs = null;
                    e.printStackTrace();
                }
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }



}