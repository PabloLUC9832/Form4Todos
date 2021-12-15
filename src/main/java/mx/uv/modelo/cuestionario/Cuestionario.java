package mx.uv.modelo.cuestionario;


public class Cuestionario {

    private int id; 
    String nombreCuestionario; 
    private String alumno;
    private String pregunta;
    private String respuesta;
    private String calificacion;
    
    //VistaAlumno
    public Cuestionario(int id, String pregunta){
        this.id=id;
        this.pregunta=pregunta;
    }

    //VistaProfesor
    public Cuestionario(int id, String alumno, String pregunta, String respuesta){
        this.id=id;
        this.alumno=alumno;
        this.pregunta=pregunta;
        this.respuesta=respuesta;
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

    @Override
    public String toString() {
        return "Cuestionario [alumno=" + alumno + ", id=" + id + ", pregunta=" + pregunta + ", respuesta=" + respuesta +
        ", calificacion=" + calificacion + "]";
    }   
}