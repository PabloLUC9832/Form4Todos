package mx.uv.modelo.usuario;

public class Usuario {
    String nombre;
    String user;
    String password;

    public Usuario() {
        this.nombre = "";
        this.user = "";
        this.password = "";
    }

    public Usuario (String nombre, String user, String password) {
        this.nombre = nombre;
        this.user = user;
        this.password = password;
    }

    public String getNombre() {
        return nombre;

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", user=" + user + ", password= " + password + '}';
    }
 
}