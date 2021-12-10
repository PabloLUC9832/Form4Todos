package mx.uv;

import static spark.Spark.*;

import com.google.gson.*;

import mx.uv.modelo.cuestionario.Cuestionario;
import mx.uv.modelo.cuestionario.CuestionarioDAO_Imp;

/*
 * Hello world!
 *
 */
public class App {
    private static Gson gson = new Gson();

    public static void main( String[] args ){
        System.out.println( "Hello World!" );

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
               
        post("/crearCuestionario", (req, res) -> {
            String json = req.body();
            Cuestionario cuestionario = gson.fromJson(json, Cuestionario.class);
            CuestionarioDAO_Imp dao = new CuestionarioDAO_Imp();
            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("status", dao.createCuestionario(cuestionario));
            return respuesta;
        });
        
        post("/crearPregunta", (req, res) -> {
            String json = req.body();
            Cuestionario cuestionario = gson.fromJson(json, Cuestionario.class);
            CuestionarioDAO_Imp dao = new CuestionarioDAO_Imp();
            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("status", dao.createPregunta(cuestionario));
            return respuesta;
        });

        get("/listaPreguntas", (req, res) -> {
            before((req2, res2) -> res.type("application/json"));
            CuestionarioDAO_Imp dao = new CuestionarioDAO_Imp();
            return gson.toJson(dao.listaPreguntas());
        });

        post("/guardarRespuesta", (req, res) -> {
            // Insertamos un nuevo usuario
            String json = req.body();
            Cuestionario cuestionario = gson.fromJson(json, Cuestionario.class);
            //String id = UUID.randomUUID().toString();
            //u.setId(id);
            //usuarios.put(id, u);

            CuestionarioDAO_Imp dao = new CuestionarioDAO_Imp();
            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("status", dao.modificarRespuesta(cuestionario));
            //respuesta.addProperty("id", id);
            return respuesta;
        });

    }
}
