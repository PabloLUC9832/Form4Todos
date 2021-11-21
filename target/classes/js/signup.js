var btnRegistrar = document.getElementById("btnRegistrar");

btnRegistrar.addEventListener("click",()=>{
    axios.post("http://localhost:4567/registro", {
        nombre:document.getElementById("nombre").value,
        user: document.getElementById("user").value,
        password: document.getElementById("password").value
    })
        .then(function (res) {
            alert("Nombre:" + res.data.status + " user:" + res.data.user+" password: "+res.data.password);
        })
        .catch(function (error) {
            console.log(error)
        })
});