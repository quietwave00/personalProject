
//로그인
document.getElementById("submit").addEventListener("click", function() {
    fetch('http://localhost:8080/login',{
        method:"POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            "userId": document.getElementById("userId").value,
            "password": document.getElementById("password").value
        })
    })
    .then(res => {
        if(res.status == 200) {
            console.log("Login Success");
            localStorage.setItem("Authorization", res.headers.get("Authorization"));
            console.log(localStorage.getItem("Authorization"));
            location.href = "main.html";
        }
    })
})

