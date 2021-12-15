package mx.uv.modelo.cuestionario;


public class Cuestionario {

    private int id; 
    String nombreCuestionario; 
    private String alumno;
    private String pregunta;
    private String respuesta;
    private String calificacion;
    private String rutaVideo;
    
    //VistaAlumno
    public Cuestionario(int id, String pregunta){
        this.id=id;
        this.pregunta=pregunta;
    }

    //VistaCalificacion
    public Cuestionario(int id, String alumno, String pregunta, String respuesta, String rutaVideo, String calificacion){
        this.id=id;
        this.alumno=alumno;
        this.pregunta=pregunta;
        this.respuesta=respuesta;
        this.rutaVideo=rutaVideo;
        this.calificacion=calificacion;
    }

    //VistaProfesor
    public Cuestionario(int id, String alumno, String pregunta, String respuesta, String rutaVideo){
        this.id=id;
        this.alumno=alumno;
        this.pregunta=pregunta;
        this.respuesta=respuesta;
        this.rutaVideo=rutaVideo;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getAlumno() {
        return alumno;
    }
    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getPregunta() {
        return pregunta;
    }
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getCalificacion() {
        return calificacion;
    }
    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }
    
    public String getNombreCuestionario() {
        return nombreCuestionario;
    }
    public void setNombreCuestionario(String nombreCuestionario) {
        this.nombreCuestionario = nombreCuestionario;
    }

    public String getRutaVideo() {
        return rutaVideo;
    }
    public void setRutaVideo(String rutaVideo) {
        this.rutaVideo = rutaVideo;
    }

    @Override
    public String toString() {
        return "Cuestionario [alumno=" + alumno + ", id=" + id + ", pregunta=" + pregunta + ", respuesta=" + respuesta +
        ", calificacion=" + calificacion + "]";
    }   
}