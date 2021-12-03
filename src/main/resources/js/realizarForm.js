var btnMas = document.getElementById("btnMas");
var seccion = document.getElementsByTagName("div");
var contador=0;
/*btnMas.addEventListener("click",()=>{
    console.log("aqui1");
    //var seccion = document.getElementsByTagName("div");
    //for(i=0;i<seccion.length;i++){
    for(i=0;i<btnMas.onclick;i++){        
        console.log("aqui2");
        document.write("<br>"+i+"sss"+seccion[i].innerHTML+"<hr>");
    }
    console.log("aqui3");

});*/
btnMas.onclick = function(){
    contador++;
    console.log("vees: "+contador);
//    for(i=0;i<contador;i++){        
    for(i=0;i<1;i++){        

        console.log("aqui2");
        //document.write("<br>"+seccion[i].innerHTML+"<hr>");
        //document.insertBefore("<br>"+seccion[i].innerHTML+"<hr>");
        const div = document.createElement("input");
        //const div2 = document.createElement("button");
        //const div = document.createElement("seccion");
        //div2.textContent = "HOLA."; 
        /*div2.onclick = function(){
            const div = document.createElement("input");
            const div2 = document.createElement("button");   
            div2.textContent = "HOLA2"; 
            const app = document.createElement("div");
            app.id="app";
            document.body.appendChild(div);
            document.body.appendChild(div2);                     
            console.log("aqui2");
        }*/
        //div2.addEventListener(hola());
        //div2.onclick(hola());
       // div.textContent = "Esto es un div insertado con JS.";        


        const app = document.createElement("div");
        app.id="app";
        document.body.appendChild(div);
        //document.body.appendChild(div2);





    }

}

var btnCrear = document.getElementById("btnCrear");
btnCrear.addEventListener("click",()=>{
    axios.post("http://localhost:4567/crearFormulario",{
        id: 0,
        nombreForm: document.getElementById("nombreForm").value,
        descripcion:document.getElementById("descripcion").value
    })
        .then(function (res){
            alert("Cuestionario: "+res.data.status+" id:"+res.data.id);
        })
        .catch(function (error) {
            console.log(error)
        })
});