package mx.uv;

import static spark.Spark.*;

import com.google.gson.*;

import mx.uv.modelo.Conexion;
//import mx.uv.db.DAO;
import mx.uv.modelo.usuario.*;



/**
 * Hello world!
 *
 */
public class App 
{
    private static Gson gson = new Gson();

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
         
        Conexion c = new Conexion();
        c.getConnection();

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

        post("/login", (req, res) -> {
            String json = req.body();
            Usuario u = gson.fromJson(json,Usuario.class);
            //String user = "12345";

            Usuario_DAO_Imp dao = new Usuario_DAO_Imp();
            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("user", dao.read(u));
            System.out.println("asdad");
            return respuesta;

        });

         post("/usuario", (req, res) -> {
            // Insertamos un nuevo usuario
            String json = req.body();
            Usuario u = gson.fromJson(json, Usuario.class);

            Usuario_DAO_Imp dao = new Usuario_DAO_Imp();
            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("status", dao.read(u));
            return respuesta;
        });
        
        

    }
}
