package mx.uv.modelo.usuario;

import java.util.List;

public interface Usuario_DAO{

	/*public String create(Usuario usuario) throws Exception;
	public List<Usuario> readAll() throws Exception;
	public String update (Usuario usuario) throws Exception;
	public String delete (Usuario usuario) throws Exception;*/
	public String create(Usuario usuario) ;
	public List<Usuario> readAll() ;
	public String update (Usuario usuario) ;
	public String delete (Usuario usuario) ;
	//INICIO DE SESION 
	//public Usuario read(String password) throws Exception;
    public Usuario read(Usuario u) throws Exception;

}