var btnMostrar = document.getElementById("btnMostrar");
var btnResponder =  document.getElementById("btnResponder");
var cuestionarioRealizar =  document.getElementById("cuestionarioRealizar").value;

localStorage.setItem("nombreCuest",document.getElementById("cuestionarioRealizar").value)

btnMostrar.addEventListener("click", function () {
    axios.get("https://forms4todos.herokuapp.com/listaCuestionariosProfesor")
    .then(function (res) {
        let json = res.data;
        let listaTareas = document.getElementById("cuestionarios");
        for (var clave in json) {

            // Controlando que json realmente tenga esa propiedad
            if (json.hasOwnProperty(clave)) {

                // Mostrando en pantalla la clave junto a su valor
                let tarea = document.createElement("li");
                tarea.textContent = json[clave].nombre;
                listaTareas.appendChild(tarea);
            }
        }
    })
    .catch()
})

btnResponder.addEventListener("click",()=>{
    console.log(cuestionarioRealizar.value)
    localStorage.setItem("nombreCuest",document.getElementById("cuestionarioRealizar").value)
    window.location.href='/visualizarCalificaciones'
    axios.post("https://forms4todos.herokuapp.com/listaCuestionariosCalificacion",{
        nombreCuestionario: document.getElementById("cuestionarioRealizar").value
    })
    .then(function(res){
    })
    .catch(function(error){
        consoloe.log(error);
    })
})