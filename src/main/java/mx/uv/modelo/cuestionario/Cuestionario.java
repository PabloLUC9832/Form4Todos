package mx.uv.modelo.cuestionario;

public class Cuestionario {

	int id;
    String nombreCuestionario;
    String nombreAlumno;
    String pregunta;
    String respuesta;

    public Cuestionario(){
    	this.id=0;
    	this.nombreAlumno="";
    	this.pregunta="";
    	this.respuesta="";
    }
    //CONSTRUCTOR NOMBRE DEL CUESTIONARI
    //CONSTRUCTOR VISTA MAESTRO
    public Cuestionario(int id,String pregunta){
    	this.id=0;
    	//this.nombreAlumno="";
    	this.pregunta="";
    	//this.respuesta="";
    }
    //CONSTRUCTOR VISTA ALUMNO
    public Cuestionario(String nombreAlumno,String respuesta){
    	this.nombreAlumno="";
    	this.respuesta="";
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombreAlumno() {
        return nombreAlumno;
    }
    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
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

    


    

    
}
