var btnEnviarCuestionario = document.getElementById("btnEnviarCuestionario");
var btnVer = document.getElementById("btnVer");

let resultados ='';
const contenedor = document.querySelector('tbody');
const pregunta = document.getElementById("pregunta");
const respuesta = document.getElementById("respuesta");
const nombreCuestionario = document.getElementById("nombreCuestionario");
const alumno = document.getElementById("alumno");

//mostrar
function mostrarTabla(){
    const mostrar = (preguntas) => {
        preguntas.forEach(pregunt => {
            resultados += `<tr>
                                 <td>${pregunt.id}</td>
                                 <td>${pregunt.alumno}</td>
                                <td>${pregunt.pregunta}</td>
                                <td>${pregunt.respuesta}</td>
                            </tr>
                        `
        })
        contenedor.innerHTML = resultados
    }
}


btnVer.addEventListener("click", function () {
    axios.get("http://localhost:4567/listaPreguntas")
    .then(function (res) {
        let json = res.data;
        let listaTareas = document.getElementById("preguntas");
        for (var clave in json) {
            // Controlando que json realmente tenga esa propiedad
            if (json.hasOwnProperty(clave)) {
                // Mostrando en pantalla la clave junto a su valor
                // alert("La clave es " + clave + " y el valor es " + json[clave]);
                //let tarea = document.createElement("button");
                //idyPr = ID de la pregunta y la descripció de la pregunta
                let idyPr = document.createElement("label");
                let resp = document.createElement("input");
                let salto = document.createElement("br");
                let btnEnviar = document.createElement("button");
                //let pregunta = document.createElement("label")
                ///tarea.textContent = clave + " " + json[clave].id;
                idyPr.textContent = ""+ json[clave].id+"\n"+json[clave].pregunta;
                res.textContent = ""+ json[clave].respuesta;
                btnEnviar.textContent = "Enviar respuesta";
                listaTareas.appendChild(idyPr);
                listaTareas.appendChild(resp);
                listaTareas.appendChild(btnEnviar);
                listaTareas.appendChild(salto);
                
            }
        }
        //mostrarTabla()     
    })
    .catch(function (error) {
        console.log(error)
    })
})
