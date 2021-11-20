var btnLogin = document.getElementById("btnLogin");
btnLogin.addEventListener("click", () => {
    axios.post("http://localhost:4567/login", {
        user: document.getElementById("user").value,
        pass: document.getElementById("pass").value
    })
        .then(function (res) {
            alert("Usuario:" + res.data.status + " user:" + res.data.user);
        })
        .catch(function (error) {
            console.log(error)
        })
});