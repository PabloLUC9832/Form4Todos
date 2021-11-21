package mx.uv;

import static spark.Spark.*;

import com.google.gson.*;

//import mx.uv.db.DAO;
import mx.uv.modelo.usuario.*;



/**
 * Hello world!
 *
 */
public class App {
    private static Gson gson = new Gson();

    public static void main( String[] args ){
        System.out.println( "Hello World!" );
        //Conexion c = new Conexion();
        //c.getConnection();

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        get("/", (req, res) -> {
            return null;
        });

        post("/login",(req,res)->{
            String json = req.body();
            Usuario u = gson.fromJson(json, Usuario.class);
            //String paass = u.getPassword();
            //String pass =
            Usuario_DAO_Imp usuario_DAO = new Usuario_DAO_Imp();
            JsonObject respuesta = new JsonObject();
            //String  pass="";
            respuesta.addProperty("status", usuario_DAO.read(u));
            //respuesta.addProperty(, );
            return respuesta;
        });
        
        post("/registro", (req, res) -> {
            // Insertamos un nuevo usuario
            String json = req.body();
            Usuario u = gson.fromJson(json, Usuario.class);
            //String id = UUID.randomUUID().toString();
            //u.setId(id);
            //usuarios.put(id, u);

            Usuario_DAO_Imp usuario_DAO_Imp = new Usuario_DAO_Imp();
            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("status", usuario_DAO_Imp.create(u));
            //respuesta.addProperty("id", id);
            return respuesta;
        });                

    }
}
