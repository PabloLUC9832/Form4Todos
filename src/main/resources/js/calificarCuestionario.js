var btnVer = document.getElementById("btnVer");
var tituloCuestionario = document.getElementById("tituloCuestionario");
tituloCuestionario.textContent = localStorage.getItem("nombreCuest")

btnVer.addEventListener("click",()=>{
    axios.post("http://localhost:4567/listaPreguntasProfesor2",{
      nombreCuestionario: tituloCuestionario.textContent = localStorage.getItem("nombreCuest")
    })
    .then(function (res) {
        let json = res.data;
        let listaTareas = document.getElementById("preguntas");
        let btnEnviar = document.createElement("button");
        btnEnviar.setAttribute("type","button")
        for (var clave in json) {
            if (json.hasOwnProperty(clave)) {
                let id = document.createElement("label");
                let Pr = document.createElement("label");
                let resp = document.createElement("label");
                let calif = document.createElement("input")

                id.setAttribute("class", "labelId");
                Pr.setAttribute("class", "labelRepuesta");
                resp.setAttribute("class", "labelRepuesta");
                calif.setAttribute("placeholder","Ingresa la calificación");
                calif.setAttribute("class","inputRespuesta");
                btnEnviar.setAttribute("class", "btn-lightEnviarRespuesta");
       
                var a =  "calificacion"+clave
                calif.setAttribute("id",a)
                let salto = document.createElement("br");
                id.textContent = json[clave].id;
                Pr.textContent = json[clave].pregunta;
                resp.textContent = json[clave].respuesta;
                btnEnviar.textContent = "Enviar respuesta";
                
                btnEnviar.addEventListener("click",() => {
                    console.log(resp.innerText)
                    axios.post("http://localhost:4567/guardarCalificacion",{
                        nombreCuestionario : tituloCuestionario.textContent = localStorage.getItem("nombreCuest"),
                        id: id.innerText,
                        calificacion : calif.value
                    })
                    .then(function(res){                        
                        alert(""+ res.data.status);
                        window.location.href="index.html"
                    })
                    .catch(function (error) {
                        console.log(error)
                    })
                })
                
                listaTareas.appendChild(id);
                listaTareas.appendChild(Pr);
                listaTareas.appendChild(resp);
                listaTareas.appendChild(calif);
                listaTareas.appendChild(salto);
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

