var btnAbrirPopup = document.getElementById('btn-abrir-popup'),
	overlay = document.getElementById('overlay'),
	popup = document.getElementById('popup'),
	btnCerrarPopup = document.getElementById('btn-cerrar-popup');

btnAbrirPopup.addEventListener('click', function(){
	overlay.classList.add('active');
	popup.classList.add('active');
});

btnCerrarPopup.addEventListener('click', function(e){
	e.preventDefault();
	overlay.classList.remove('active');
	popup.classList.remove('active');
});

btnPreguntas.addEventListener("click", () => {
    axios.post("http://localhost:4567/crearPregunta", {
        nombreCuestionario : document.getElementById("nombreCuestionarioExistente").value,
        id : 11,
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