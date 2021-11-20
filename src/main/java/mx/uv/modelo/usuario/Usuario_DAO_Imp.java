package mx.uv.modelo.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mx.uv.modelo.Conexion;

public class Usuario_DAO_Imp implements Usuario_DAO {

    private Conexion conexion = new Conexion();

	@Override
	public String create(Usuario usuario) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement prestm = null;
		String msj = "";

		conn = conexion.getConnection();
		try {
			String sql = "INSERT INTO  usuario (nombre, user, password) VALUES (?, ?, ?)";
			prestm = conn.prepareStatement(sql);
			prestm.setString(1, usuario.getNombre());
			prestm.setString(2, usuario.getUser());
			prestm.setString(3, usuario.getPassword());
			if (prestm.executeUpdate() >0)
				msj = "Usuario Agregado";
			else
				msj = "No se agregó el Usuario";
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

	@Override
	public List<Usuario> readAll() {

		Statement stm = null;
		ResultSet rs = null;
		Connection conn = null;		
		List<Usuario> resultado = new ArrayList<>();
		conn = conexion.getConnection();

		try{
            String sql = "SELECT * FROM usuario";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()){
                Usuario u = new Usuario(rs.getString("nombre"), rs.getString("user"), rs.getString("pass"));
                resultado.add(u);
            }						
		} catch(Exception e) {
            e.printStackTrace();
        }finally{

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
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}				
            }

		}
		return resultado;
	}

	@Override
	public String update(Usuario usuario) {

		Connection conn = null;
        PreparedStatement prestm = null;
        String msj = "";
        conn = conexion.getConnection();

        try {
            String sql = "UPDATE usuario SET nombre = ?, pass = ? WHERE user = ?";
            prestm = conn.prepareStatement(sql);
            prestm.setString(1, usuario.getNombre());
            prestm.setString(2, usuario.getUser());
            prestm.setString(3, usuario.getPassword());
            if (prestm.executeUpdate() >0) 
                msj = "Usuario modificado";
            else
                msj = "No se modificó el usuario";
            
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

	@Override
	public String delete(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario read(Usuario u) throws Exception {
		
        Statement stm;
        ResultSet rs;
        String password = u.getPassword();
        String sql = "SELECT * FROM usuario WHERE password = '"+password+"'";		
		//Usuario usuario = new Usuario();
		Conexion cc = new Conexion();

        try(Connection con = cc.getConnection();){
            System.out.println("AQUII");
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                u.setNombre(rs.getString(1));
                u.setUser(rs.getString(2));                
                u.setPassword(rs.getString(3));                             
            }
            System.out.println("EXITOOOOOOOOO");
            stm.close();
            rs.close();
            con.close();
        }catch(SQLException e){
            throw new Exception("ERROR EN READ, SQLEXCEPTION "+e.getMessage());
        }catch(NullPointerException e){
            throw new Exception("ERROR EN READ, NULLPointerEXCEPTION "+e.getMessage());
        }catch(Exception e){
            throw new Exception("ERROR EN READ, EXCEPTION "+e.getMessage());
        }		


		return u;
	}


	








}
