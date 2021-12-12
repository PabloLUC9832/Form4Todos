package mx.uv;

import static spark.Spark.*;
import spark.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;

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
        
        File uploadDir = new File("upload");
        uploadDir.mkdir(); // create the upload directory if it doesn't exist

        staticFiles.externalLocation("upload");

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
        
        get("/", (req, res) ->
                  "<form method='post' enctype='multipart/form-data'>" // note the enctype
                + "    <input type='file' name='videoGrabado' accept='.png'>" // make sure to call getPart using the same "name" in the post
                + "    <button>Upload picture</button>"
                + "</form>"
        );       
               
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

        post("/", (req, res) -> {

            Path tempFile = Files.createTempFile(uploadDir.toPath(), "", ".mp4");

            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

            try (InputStream input = req.raw().getPart("videoGrabado").getInputStream()) { // getPart needs to use same "name" as input field in form
                Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }

            logInfo(req, tempFile);
            return "<h1>You uploaded this image:<h1><img src='" + tempFile.getFileName() + "'>";

        });

    }

    // methods used for logging
    private static void logInfo(Request req, Path tempFile) throws IOException, ServletException {
        System.out.println("Uploaded file '" + getFileName(req.raw().getPart("uploaded_file")) + "' saved as '" + tempFile.toAbsolutePath() + "'");
    }
    
    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
