var btnCrearCuestionario = document.getElementById("btnCrearCuestionario");
var btnPreguntas = document.getElementById("btnPreguntas");
var nombreCuestionar = document.getElementById("nombreCuestionario").value
document.getElementById("nombreCuestionarioExistente").innerHTML = document.getElementById("nombreCuestionario").value

btnCrearCuestionario.addEventListener("click", () => {
    document.getElementById("nombreCuestionarioExistente").innerHTML = document.getElementById("nombreCuestionario").value
    axios.post("http://localhost:4567/crearCuestionario", {
        nombreCuestionario : document.getElementById("nombreCuestionario").value,
        id: 0,
        alummo:"",
        pregunta: document.getElementById("pregunta1").value,
        respuesta:"",
        calificacion:""
    })
    .then(function (res) {
        //alert("Status:" + res.data.status);
        document.getElementById("mensaje2").textContent = ""+res.data.status; 
        console.log("Status:" + res.data.status);        
    })
    .catch(function (error) {
        console.log(error)
    })
});

function habilitarBAP() {
    document.getElementById("btn-abrir-popup").disabled = false;
}
