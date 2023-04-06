
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
            const token = res.headers.get("Authorization").split(" ")[1];
            localStorage.setItem("Authorization", token);
            console.log(token);
            location.href = "main.html";
        }
    })
})

