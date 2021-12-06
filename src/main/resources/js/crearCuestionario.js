var btnCrearCuestionario = document.getElementById("btnCrearCuestionario");
var btnPreguntas = document.getElementById("btnPreguntas");


btnCrearCuestionario.addEventListener("click", () => {
    axios.post("http://localhost:4567/crearCuestionario", {
        nombreCuestionario : document.getElementById("nombreCuestionario").value,
        id: 1,
        alummo:"",
        pregunta: document.getElementById("pregunta1").value,
        respuesta:"",
    })
        .then(function (res) {
            alert("Status:" + res.data.status);
        })
        .catch(function (error) {
            console.log(error)
        })
});

/*btnPreguntas.addEventListener("click", () => {
    axios.post("http://localhost:4567/crearCuestionario2", {
        id : 0 ,
        pregunta: document.getElementById("pregunta1").value
    })
        .then(function (res) {
            alert("Status:" + res.data.status);
        })
        .catch(function (error) {
            console.log(error)
        })
});*/
