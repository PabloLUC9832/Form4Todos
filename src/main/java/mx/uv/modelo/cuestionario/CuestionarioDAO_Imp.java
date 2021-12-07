package mx.uv.modelo.cuestionario;

import mx.uv.modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CuestionarioDAO_Imp {
    
    private Conexion conexion = new Conexion();

    //String nombreCuestionario;

    public String createCuestionario(Cuestionario cuestionario){

        Connection conn = null;
        PreparedStatement prestm  = null;
        String msj = "";
        conn = conexion.getConnection();        
        String nombreCuestionario = cuestionario.getNombreCuestionario();      
        try {
            String sql1 = "CREATE TABLE `proyectosw`.`"+nombreCuestionario+"`"+"( `id` INT NOT NULL , `alumno` VARCHAR(50) NULL , `pregunta` VARCHAR(255) NULL , `respuesta` VARCHAR(255) NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
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

    public String createPregunta(Cuestionario cuestionario){

        Connection conn = null;
        PreparedStatement prestm0  = null;
        PreparedStatement prestm2 = null;
        String msj = "";
        conn = conexion.getConnection();        
        String nombreCuestionario = cuestionario.getNombreCuestionario();      
        try {

            String sql0 = "SELECT 1 FROM " +nombreCuestionario+" LIMIT 1";
            String sql2 = "INSERT INTO "+nombreCuestionario+" (id,alumno,pregunta,respuesta) VALUES (?,?,?,?)";

            prestm0 = conn.prepareStatement(sql0);
            prestm2 = conn.prepareStatement(sql2);

            if (prestm0.execute() == true){
                prestm2.setInt(1, cuestionario.getId());
                prestm2.setString(2, cuestionario.getAlumno()); 
                prestm2.setString(3, cuestionario.getPregunta()); 
                prestm2.setString(4, cuestionario.getRespuesta());
                prestm2.executeUpdate();
                System.out.println("Solo guardar");
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

    /*public String createCuestionario(Cuestionario cuestionario){

        Connection conn = null;
        PreparedStatement prestm0  = null;
        PreparedStatement prestm  = null;
        PreparedStatement prestm2 = null;
        String msj = "";
        conn = conexion.getConnection();        
        String nombreCuestionario = cuestionario.getNombreCuestionario();      
        try {

            String sql0 = "SELECT 1 FROM " +nombreCuestionario+" LIMIT 1";
            String sql1 = "CREATE TABLE `proyectosw`.`"+nombreCuestionario+"`"+"( `id` INT NOT NULL , `alumno` VARCHAR(50) NULL , `pregunta` VARCHAR(255) NULL , `respuesta` VARCHAR(255) NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
            String sql2 = "INSERT INTO "+nombreCuestionario+" (id,alumno,pregunta,respuesta) VALUES (?,?,?,?)";

            prestm0 = conn.prepareStatement(sql0);
            prestm = conn.prepareStatement(sql1);
            prestm2 = conn.prepareStatement(sql2);

            if (prestm0.execute() == true){
                //prestm.execute();
                prestm2.setInt(1, cuestionario.getId());
                prestm2.setString(2, cuestionario.getAlumno()); 
                prestm2.setString(3, cuestionario.getPregunta()); 
                prestm2.setString(4, cuestionario.getRespuesta());
                prestm2.executeUpdate();
                System.out.println("Solo guardar");
            }else{
                prestm2.setInt(1, cuestionario.getId());
                prestm2.setString(2, cuestionario.getAlumno()); 
                prestm2.setString(3, cuestionario.getPregunta()); 
                prestm2.setString(4, cuestionario.getRespuesta());

                prestm.execute();
                prestm2.executeUpdate();
                System.out.println("Valio kk");
            }

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

    }*/

}
