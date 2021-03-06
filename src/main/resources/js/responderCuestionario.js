var btnVer = document.getElementById("btnVer");
var blob ;
var rutaVideo="";
var tituloCuestionario = document.getElementById("tituloCuestionario");
tituloCuestionario.textContent = localStorage.getItem("nombreCuest")

var chunks = [];
var mediaRecorder;

navigator.mediaDevices.getUserMedia({
  audio: false, video: true
}).then(function (x) {
   //usar el flujo de datos
  console.log(x)
  mediaRecorder = new MediaRecorder(x);

  var camara = document.getElementById("camara")
  camara.srcObject = x
  camara.onloadedmetadata = function (e) {
    // Do something with the video here.
    camara.play()
  };
  console.log(camara)

  mediaRecorder.onstop = function (e) {
    console.log("data available after MediaRecorder.stop() called.");

    var clipName = prompt('Enter a name for your sound clip');

    var clipContainer = document.createElement('article');
    var clipLabel = document.createElement('p');
    var audio = document.createElement('video');
    audio.width="150"
    var deleteButton = document.createElement('button');
    // generar una liga
    var a = document.createElement('a')
    var texto = document.createTextNode("descarga")
    a.appendChild(texto)

    clipContainer.classList.add('clip');
    audio.setAttribute('controls', '');
    deleteButton.innerHTML = "Delete";
    clipLabel.innerHTML = clipName;

    var soundClips = document.getElementById("xxx")
    clipContainer.appendChild(audio);
    clipContainer.appendChild(clipLabel);
    clipContainer.appendChild(deleteButton);
    clipContainer.appendChild(a)
    soundClips.appendChild(clipContainer);

    audio.controls = true;
    var blob = new Blob(chunks, { 'type': 'video/webm; codecs=vp8' });
    chunks = [];
    var audioURL = URL.createObjectURL(blob);
    audio.src = audioURL;
    console.log("recorder stopped");
    a.href = audioURL
    a.download = "video.mp4"

    deleteButton.onclick = function(e) {
      evtTgt = e.target;
      evtTgt.parentNode.parentNode.removeChild(evtTgt.parentNode);
    }
  }

  mediaRecorder.ondataavailable = function(e) {
    chunks.push(e.data);
    enviar(e.data)
  }

  }).catch(function (err) {
    // manejar el error 
    console.log(err)
  });

  function GRABAR(params) {
    mediaRecorder.start();
    console.log(mediaRecorder.state);
    console.log("recorder started");
  }
  
  function DETENER(params) {
    mediaRecorder.stop();
    console.log(mediaRecorder.state);
    console.log("recorder stopped");
  }
  
  function enviar(stream) {
    var formData = new FormData();
    formData.append("videoGrabado", stream)
    axios.post("http://localhost:4567/", formData, {
      headers : {
         "Content-Type" : "multipart/form-data"
      }
    })
    .then(function (res){
      ruta =  res.data.nobreVi;
      alert("Ruta: "+res.data.nombreVideo);
    })
    .catch(function (error) {
      console.log(error)
    })
  }

  btnVer.addEventListener("click",()=>{
    axios.post("http://localhost:4567/listaPreguntas",{
      nombreCuestionario: tituloCuestionario.textContent = localStorage.getItem("nombreCuest")
    })
    .then(function (res) {
        let json = res.data;
        let listaTareas = document.getElementById("preguntas");
        let btnEnviar = document.createElement("button");
        btnEnviar.setAttribute("type","button")
        for (var clave in json) {
            var contadorDetener = "detener"+clave;
            var contadorGrabar = "grabar"+clave;
            if (json.hasOwnProperty(clave)) {
                let id = document.createElement("label");
                let Pr = document.createElement("label");
                let resp = document.createElement("input");
                let btnGrabar = document.createElement("button");
                btnGrabar.setAttribute("type","button");
                btnGrabar.addEventListener("click",GRABAR)
                btnGrabar.setAttribute("class","btn-lightGrabarRespuesta");
                let btnDetener = document.createElement("button");
                btnDetener.setAttribute("type","button");
                btnDetener.addEventListener("click",DETENER)
                btnDetener.setAttribute("class", "btn-lightDetenerGrabacion");

                id.setAttribute("class", "labelId");
                Pr.setAttribute("class", "labelRepuesta");
                resp.setAttribute("class", "inputRespuesta");
                resp.setAttribute("placeholder","Ingresa la respuesta");
                btnEnviar.setAttribute("class", "btn-lightEnviarRespuesta");
       
                var a =  "respuesta"+clave
                resp.setAttribute("id",a)
                let salto = document.createElement("br");
                id.textContent = json[clave].id;
                Pr.textContent = json[clave].pregunta;
                btnGrabar.textContent = "Grabar respuesta";
                btnDetener.textContent = "Detener grabaci??n";
                btnEnviar.textContent = "Enviar respuesta";

                btnEnviar.addEventListener("click",() => {
                    console.log(id.innerText)
                    //enviar(blob)
                    console.log(rutaVideo)
                    resp.textContent = rutaVideo
                    axios.post("http://localhost:4567/guardarRespuesta",{
                        nombreCuestionario : tituloCuestionario.textContent = localStorage.getItem("nombreCuest"),
                        id: id.innerText,
                        alumno : document.getElementById("alumno").value ,
                        respuesta : resp.value
                    })
                    .then(function(res){
                        alert("Status: "+ res.data.status);
                        //enviar(blob)
                        window.location.href='index.html'
                    })
                    .catch(function (error) {
                        console.log(error)
                    })
                })
                
                listaTareas.appendChild(id);
                listaTareas.appendChild(Pr);
                listaTareas.appendChild(resp);
                listaTareas.appendChild(btnGrabar);
                listaTareas.appendChild(btnDetener);
                listaTareas.appendChild(salto);                
                listaTareas.appendChild(btnEnviar);
                listaTareas.appendChild(salto);
            }
        }   
    })
    .catch(function (error) {
        console.log(error)
    })
})