var btnEnviarCuestionario = document.getElementById("btnEnviarCuestionario");
var btnVer = document.getElementById("btnVer");

/*
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
*/
btnVer.addEventListener("click", function () {
    axios.get("http://localhost:4567/listaPreguntas")
    .then(function (res) {
        let json = res.data;
        let listaTareas = document.getElementById("preguntas");
        let btnEnviar = document.createElement("button");
        for (var clave in json) {
            // Controlando que json realmente tenga esa propiedad
            if (json.hasOwnProperty(clave)) {
                // Mostrando en pantalla la clave junto a su valor
                // alert("La clave es " + clave + " y el valor es " + json[clave]);
                //let tarea = document.createElement("button");
                //idyPr = ID de la pregunta y la descripció de la pregunta
                //let idyPr = document.createElement("label");
                let id = document.createElement("label");
                let Pr = document.createElement("label");
                let resp = document.createElement("input");
                var a =  "respuesta"+clave
                resp.setAttribute("id",a)
                let salto = document.createElement("br");
                
                //let pregunta = document.createElement("label")
                ///tarea.textContent = clave + " " + json[clave].id;
                //idyPr.textContent = ""+ json[clave].id+"\n"+json[clave].pregunta;
                id.textContent = json[clave].id;
                Pr.textContent = json[clave].pregunta;

                //var a = res.textContent = ""+ json[clave].respuesta;
                res.textContent = ""+ json[clave].respuesta;
                btnEnviar.textContent = "Enviar respuesta";
                btnEnviar.addEventListener("click",() => {
                    console.log(id.innerText)
                    axios.post("http://localhost:4567/guardarRespuesta",{
                        id: id.innerText,
                        alumno : document.getElementById("alumno").value ,
                        respuesta : resp.value 
                        //respuesta : document.getElementById(a1).value,
                        //respuesta : document.getElementById(a2).value, 
                    })
                    .then(function(res){
                        //alert("Usuario:" + res.data.status + " id:" + res.data.id);
                        alert("Status: "+ res.data.status);
                    })
                    .catch(function (error) {
                        console.log(error)
                    })
                })
                //btnEnviar.onclick = function(){
                    //console.log("hola")
                    //console.log(resp.value)
                    //UPDATE ¿?
                    /*axios.post("http://localhost:4567/guardarRespuesta",{
                        alumno : document.getElementById("alumno").value ,
                        respuesta : resp 
                    })
                    .then(function(res){
                        //alert("Usuario:" + res.data.status + " id:" + res.data.id);
                        alert("Status: "+ res.data.status);
                    })
                    .catch(function (error) {
                        console.log(error)
                    })*/
                    //enviarResp(a)
                //}
                listaTareas.appendChild(id);
                listaTareas.appendChild(Pr);
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
/*
btnEnviar.addEventListener("click",(alum,respuest)=>{
    axios.post("http://localhost:4567/guardarRespuesta",{
        alumno : document.getElementById(alum).value ,
        respuesta: document.getElementById(respuest).value        
    })
    .then(function(res){
        //alert("Usuario:" + res.data.status + " id:" + res.data.id);
        alert("Status: "+ res.data.status);
    })
    .catch(function (error) {
        console.log(error)
    })
})
*/

/*function enviarResp(respu){
    axios.post("http://localhost:4567/guardarRespuesta",{
                        alumno : document.getElementById("alumno").value ,
                        respuesta : respu 
                    })
                    .then(function(res){
                        //alert("Usuario:" + res.data.status + " id:" + res.data.id);
                        alert("Status: "+ res.data.status);
                    })
                    .catch(function (error) {
                        console.log(error)
                    })
}*/