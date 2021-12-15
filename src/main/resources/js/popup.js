var btnAbrirPopup = document.getElementById('btn-abrir-popup'),
	overlay = document.getElementById('overlay'),
	popup = document.getElementById('popup'),
	btnCerrarPopup = document.getElementById('btn-cerrar-popup');

btnAbrirPopup.addEventListener('click', function(){
    document.getElementById("nombreCuestionarioExistente").innerHTML = document.getElementById("nombreCuestionario").value
	overlay.classList.add('active');
	popup.classList.add('active');
});

btnCerrarPopup.addEventListener('click', function(e){
	e.preventDefault();
	overlay.classList.remove('active');
	popup.classList.remove('active');
});

document.getElementById("nombreCuestionarioExistente").innerHTML = document.getElementById("nombreCuestionario").value

btnPreguntas.addEventListener("click", () => {
    axios.post("http://localhost:4567/crearPregunta", {
        nombreCuestionario : document.getElementById("nombreCuestionario").value,
        id : 0,
        alummo:"",
        pregunta: document.getElementById("pregunta1").value,
        respuesta:"",
        calificacion:"",
    })
    .then(function (res) {
        document.getElementById("pregunta1").value = "";
        document.getElementById("mensaje").textContent = ""+res.data.status; 
        console.log("Status:" + res.data.status);
    })
    .catch(function (error) {        
        console.log(error)
    })
});