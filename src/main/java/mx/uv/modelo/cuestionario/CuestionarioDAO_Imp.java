package mx.uv.modelo.cuestionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mx.uv.modelo.Conexion;

public class CuestionarioDAO_Imp implements Cuestionario_DAO {

    private Conexion conexion = new Conexion();

    @Override
    //public String createVP(String nombreTabla,Cuestionario cuestionario) {
    public String createVP(Cuestionario cuestionario) {

        Connection conn = null;
        PreparedStatement prestm = null;
        PreparedStatement prestm2 = null;
        String msj = "";

        conn = conexion.getConnection();

        try {
            //String sql1 = "CREATE TABLE `proyectosw`."+"`"+nombreTabla+"`"+" ( `id` INT(10) NOT NULL AUTO_INCREMENT , `nombreAlumno` TEXT NOT NULL , `pregunta` LONGTEXT NOT NULL , `respuesta` LONGTEXT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";            
            //String sql2 = "INSERT INTO "+nombreTabla+" (id,pregunta) VALUES (?, ?)";
            //String sql1 = "CREATE TABLE `proyectosw`."+"`"+cuestionario.getNombreCuestionario()+"`"+" ( `id` INT(10) NOT NULL AUTO_INCREMENT , `nombreAlumno` TEXT NOT NULL , `pregunta` LONGTEXT NOT NULL , `respuesta` LONGTEXT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";            
            //String sql1 = "CREATE TABLE '"+cuestionario.getNombreCuestionario()+"' ( `id` INT(10) NOT NULL AUTO_INCREMENT , `nombreAlumno` TEXT NOT NULL , `pregunta` LONGTEXT NOT NULL , `respuesta` LONGTEXT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";            
            String sql1 = "CREATE TABLE `proyectosw`."+"`"+cuestionario.getNombreCuestionario()+"`"+" ( `id` INT(10) , `nombreAlumno` TEXT , `pregunta` LONGTEXT , `respuesta` LONGTEXT) ENGINE = InnoDB;";            

            String sql2 = "INSERT INTO "+cuestionario.getNombreCuestionario()+" (id,pregunta) VALUES (?, ?)";            
            //String sql = "INSERT INTO  usuario (nombre, user, password) VALUES (?, ?, ?)";
            System.out.println("NOMBREEEEEEEEEEEEEEEEEE: "+cuestionario.getNombreCuestionario());
            prestm = conn.prepareStatement(sql1);
            prestm2 = conn.prepareStatement(sql2);
            prestm2.setInt(1, cuestionario.getId());
            //prestm2.setInt(1, 0);
            prestm2.setString(2, cuestionario.getPregunta());
            //prestm2.setString(3, usuario.getPassword());
            if (prestm.executeUpdate() >0 && prestm2.executeUpdate()>0)
                msj = "Cuestionario creado";
            else
                msj = "Ha ocurrido un error al crear el cuestionario, intentalo nuevamente.";
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
    
}
