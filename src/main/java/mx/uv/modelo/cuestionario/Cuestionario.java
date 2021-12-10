package mx.uv.modelo.cuestionario;


public class Cuestionario {

    private int id; 
    String nombreCuestionario; 
    private String alumno;
    private String pregunta;
    private String respuesta;
    private byte[] respuestaVideo;
    //VistaProfesor
    public Cuestionario(int id, String pregunta){
        this.id=id;
        this.pregunta=pregunta;
    }
    //VistaAlumno1
    public Cuestionario(String alumno, String respuesta){
        this.alumno=alumno;
        this.respuesta=respuesta;
    }
    //VistaAlumno2
    public Cuestionario(String alumno, String respuesta,byte[] respuestaVideo){
        this.alumno=alumno;
        this.respuesta=respuesta;
        this.respuestaVideo=respuestaVideo;
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
    
    public String getNombreCuestionario() {
        return nombreCuestionario;
    }
    public void setNombreCuestionario(String nombreCuestionario) {
        this.nombreCuestionario = nombreCuestionario;
    }

    public byte[] getRespuestaVideo() {
        return respuestaVideo;
    }
    public void setRespuestaVideo(byte[] respuestaVideo) {
        this.respuestaVideo = respuestaVideo;
    }
    @Override
    public String toString() {
        return "Cuestionario [alumno=" + alumno + ", id=" + id + ", pregunta=" + pregunta + ", respuesta=" + respuesta
                + "]";
    }

    
    
}
