package mx.uv.modelo.Tabla;

import mx.uv.modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TablaDAO_Imp {
    
    private Conexion conexion = new Conexion();
    
    public List<Tabla> listaCuestionarios() {

        
        
        Statement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Tabla> resultado = new ArrayList<>(); 

        conn = conexion.getConnection();
        //String nombreCuestionario = cuestionario.getNombreCuestionario();
        try {
            
            java.sql.DatabaseMetaData dbm = conn.getMetaData();
            String[] types = {"TABLE"};
            rs = dbm.getTables(null,null,"%",types);
            while (rs.next()){
            System.out.println(rs.getString("TABLE_NAME"));
            String nombre = rs.getString("TABLE_NAME");
            String sql = "SHOW TABLES";
            Tabla u = new Tabla(rs.getString("TABLE_NAME"));
            resultado.add(u);
            }
            //return types;
            /*stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()){
                //Cuestionario u = new Cuestionario(rs.getInt("id"), rs.getString("alumno"), rs.getString("pregunta"),rs.getString("respuesta"));
                Cuestionario u = new Cuestionario(rs.getString("nombreCuestionario"));
                resultado.add(u);
            } */
            
           
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                try {
                    stm.close();
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
        return resultado;
    }
}
