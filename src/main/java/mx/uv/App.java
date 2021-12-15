package mx.uv;

import static spark.Spark.*;
import spark.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.stream.Location;

import java.io.*;
import java.nio.file.*;
import com.google.gson.*;
import mx.uv.modelo.Tabla.TablaDAO_Imp;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import mx.uv.modelo.cuestionario.Cuestionario;
import mx.uv.modelo.cuestionario.CuestionarioDAO_Imp;

public class App {

    private static Gson gson = new Gson();

    public static void main(String[] args) {

        port(getHerokuAssignedPort());
        staticFiles.location("/");

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

        post("/", (req, res) -> {

            Path tempFile = Files.createTempFile(uploadDir.toPath(), "", ".mp4");
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            try (InputStream input = req.raw().getPart("videoGrabado").getInputStream()) { // getPart needs to use same "name" as input field in form
                Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }
            logInfo(req, tempFile);                         
            String  json = req.body();
            String nombreVideo = "/"+tempFile.getFileName();
            JsonObject respuesta = new JsonObject(); 
            respuesta.addProperty("nombreVideo", nombreVideo);
            return respuesta;
        });
        //CREAR TABLA 
        post("/crearCuestionario", (req, res) -> {
            String json = req.body();
            Cuestionario cuestionario = gson.fromJson(json, Cuestionario.class);
            CuestionarioDAO_Imp dao = new CuestionarioDAO_Imp();
            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("status", dao.createCuestionario(cuestionario));
            return respuesta;
        });
        //CREAR PREGUNTAS CON EL POP UP
        post("/crearPregunta", (req, res) -> {
            String json = req.body();
            Cuestionario cuestionario = gson.fromJson(json, Cuestionario.class);
            CuestionarioDAO_Imp dao = new CuestionarioDAO_Imp();
            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("status", dao.createPregunta(cuestionario));
            return respuesta;
        });
        //SE USAN PARA CONTESTAR EL EXAMEN
        post("/listaPreguntas", (req, res) -> {
            before((req2, res2) -> res.type("application/json"));
            String json = req.body();            
            Cuestionario cuestionario = gson.fromJson(json, Cuestionario.class);
            CuestionarioDAO_Imp dao = new CuestionarioDAO_Imp();
            return gson.toJson(dao.listaPreguntas(cuestionario));
        });
                
        post("/guardarRespuesta", (req, res) -> {
            String json = req.body();
            Cuestionario cuestionario = gson.fromJson(json, Cuestionario.class);
            CuestionarioDAO_Imp dao = new CuestionarioDAO_Imp();
            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("status", dao.modificarRespuesta(cuestionario));
            return respuesta;
        });        
        //MOSTRAR TABLAS
        get("/listaCuestionarios", (req, res) -> {
            before((req2, res2) -> res.type("application/json"));
            TablaDAO_Imp dao = new TablaDAO_Imp();
            return gson.toJson(dao.listaCuestionarios());
        });

        get("/listaCuestionariosProfesor", (req, res) -> {
            before((req2, res2) -> res.type("application/json"));
            TablaDAO_Imp dao = new TablaDAO_Imp();
            return gson.toJson(dao.listaCuestionarios());
        });

        //ELIMINAR CUESTIONARIO
        post("/eliminarCuestionario", (req, res) -> {
            String json = req.body();
            Cuestionario cuestionario = gson.fromJson(json, Cuestionario.class);
            CuestionarioDAO_Imp dao = new CuestionarioDAO_Imp();
            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("status", dao.eliminarCuestionario(cuestionario));
            return respuesta;
        });
        //CALIFICAR EXAMENES

        post("/guardarCalificacion", (req, res) -> {
            String json = req.body();
            Cuestionario cuestionario = gson.fromJson(json, Cuestionario.class);
            CuestionarioDAO_Imp dao = new CuestionarioDAO_Imp();
            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("status", dao.calificarPregunta(cuestionario));
            return respuesta;
        });

        //
        post("/listaPreguntasProfesor2", (req, res) -> {
            before((req2, res2) -> res.type("application/json"));
            String json = req.body();            
            Cuestionario cuestionario = gson.fromJson(json, Cuestionario.class);
            CuestionarioDAO_Imp dao = new CuestionarioDAO_Imp();
            return gson.toJson(dao.listaPreguntasParaCalificar(cuestionario));
        });
        //VER LOS EXAMENES
        post("/listaCuestionariosCalificacion", (req, res) -> {
            before((req2, res2) -> res.type("application/json"));
            String json = req.body();            
            Cuestionario cuestionario = gson.fromJson(json, Cuestionario.class);
            CuestionarioDAO_Imp dao = new CuestionarioDAO_Imp();
            return gson.toJson(dao.listaCuestionariosHechos(cuestionario));
        });      
        
        post("/listaCuestionariosCalificacion2", (req, res) -> {
            before((req2, res2) -> res.type("application/json"));
            String json = req.body();            
            Cuestionario cuestionario = gson.fromJson(json, Cuestionario.class);
            CuestionarioDAO_Imp dao = new CuestionarioDAO_Imp();
            return gson.toJson(dao.listaCuestionariosHechos(cuestionario));
        });

        //USO DE VELOCITY

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(new ModelAndView(model, "vista/index.html"));
        });

        get("/calificarCuestionario", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(new ModelAndView(model, "vista/calificarCuestionario.html"));
        });         

        get("/crearCuestionario", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(new ModelAndView(model, "vista/crearCuestionario.html"));
        });

        get("/responderCuestionario", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(new ModelAndView(model, "vista/responderCuestionario.html"));
        });        

        get("/seleccionarCuestionario", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(new ModelAndView(model, "vista/seleccionarCuestionario.html"));
        }); 

        get("/seleccionarCuestionarioCalificacion", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(new ModelAndView(model, "vista/seleccionarCuestionarioCalificacion.html"));
        }); 

        get("/seleccionarCuestionarioProfesor", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(new ModelAndView(model, "vista/seleccionarCuestionarioProfesor.html"));
        }); 


        get("/visualizarCalificaciones", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(new ModelAndView(model, "vista/visualizarCalificaciones.html"));
        });         


    }

    // methods used for logging
    private static void logInfo(Request req, Path tempFile) throws IOException, ServletException {
        System.out.println("Uploaded file '" + getFileName(req.raw().getPart("videoGrabado")) + "' saved as '" + tempFile.toAbsolutePath() + "'");
    }
    
    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
            
}