var btnVer = document.getElementById("btnVer");
var tituloCuestionario = document.getElementById("tituloCuestionario");
tituloCuestionario.textContent = localStorage.getItem("nombreCuest")

btnVer.addEventListener("click",()=>{
    axios.post("https://forms4todos.herokuapp.com/listaCuestionariosCalificacion2",{
      nombreCuestionario: tituloCuestionario.textContent = localStorage.getItem("nombreCuest")
    })
    .then(function (res) {
        let json = res.data;
        let listaTareas = document.getElementById("preguntas");

        let al = document.createElement("h6");
        al.setAttribute("class", "h6")
        
        for (var clave in json) {
            if (json.hasOwnProperty(clave)) {
                let id = document.createElement("label");
                let Pr = document.createElement("label");
                let resp = document.createElement("label");
                let video = document.createElement("video");
                video.setAttribute("controls",'');
                video.width="150";
                
                let calif = document.createElement("label")
                let msj = document.createElement("label");

                let salto = document.createElement("br");
                id.textContent = json[clave].id;
                Pr.textContent = json[clave].pregunta;
                resp.textContent = json[clave].respuesta;
                resp.textContent = json[clave].respuesta;
                video.setAttribute("src", json[clave].rutaVideo);
                calif.textContent = json[clave].calificacion;
                al.textContent = json[1].alumno;
                msj.textContent = "Tu puntaje:";
                listaTareas.appendChild(id);                           
                listaTareas.appendChild(Pr);
                listaTareas.appendChild(resp);
                listaTareas.appendChild(video);
                listaTareas.appendChild(salto);
                listaTareas.appendChild(msj);                
                listaTareas.appendChild(calif);
                listaTareas.appendChild(salto);
                listaTareas.appendChild(salto);
            }
        } 
        let salto2 = document.createElement("br");
        listaTareas.appendChild(salto2);
        let nn = document.createElement("h6");
        nn.textContent = "Nombre del alumno";
        listaTareas.appendChild(nn); 
        listaTareas.appendChild(al);  
    })
    .catch(function (error) {
        console.log(error)
    })
})


