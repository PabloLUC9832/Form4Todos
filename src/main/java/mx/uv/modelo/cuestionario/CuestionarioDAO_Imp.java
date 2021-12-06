package mx.uv.modelo.cuestionario;

import mx.uv.modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CuestionarioDAO_Imp {
    
    private Conexion conexion = new Conexion();

    public String create(Cuestionario cuestionario){

        Connection conn = null;
        PreparedStatement prestm  = null;
        PreparedStatement prestm2 = null;
        String msj = "";
        conn = conexion.getConnection();        
        String nombreTabla = cuestionario.getNombreCuestionario();
        try {
            String sql1 = "CREATE TABLE `proyectosw`.`"+nombreTabla+"`"+"( `id` INT NOT NULL AUTO_INCREMENT , `alumno` VARCHAR(50) NULL , `pregunta` VARCHAR(255) NULL , `respuesta` VARCHAR(255) NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
//            String sql1 = "CREATE TABLE `proyectosw`.`"+nombreTabla+"`"+"( `id` INT NOT NULL AUTO_INCREMENT , `alumno` VARCHAR(50) NULL , `pregunta` VARCHAR(255) NULL , `respuesta` VARCHAR(255) NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
            //String sql2 = "INSERT INTO cuestionario (id,alumno,pregunta,respuesta) VALUES (?,?,?,?)";
            String sql2 = "INSERT INTO "+nombreTabla+" (id,alumno,pregunta,respuesta) VALUES (?,?,?,?)";
            prestm = conn.prepareStatement(sql1);
            prestm2 = conn.prepareStatement(sql2);
            prestm2.setInt(1, cuestionario.getId());
            prestm2.setString(2, cuestionario.getAlumno()); 
            prestm2.setString(3, cuestionario.getPregunta()); 
            prestm2.setString(4, cuestionario.getRespuesta()); 

            System.out.println(cuestionario.toString());

            if (prestm.execute()==true){ 
                msj = "Cuestionario creado.";
                if(prestm2.executeUpdate()>0){
                    msj += "Pregunta añadida";
                }else{
                    msj += "Error al añadir pregunta";
                }
            }else{
                msj = "Error al crear el cuestionario.";
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

    }


}
