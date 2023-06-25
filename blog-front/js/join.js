document.getElementById('join-button').addEventListener('click', () => {
    fetch(`${host}/blog/join`, {
        method: 'POST',
        headers: {
            "content-type": "application/json",
        },
        body: JSON.stringify({
            "userId": document.getElementById('userId').value,
            "password": document.getElementById('password').value,
            "nickname": document.getElementById('nickname').value
        })
    })
    .then((res) => res.json())
    .then(res => {
        if(res.success == true) {
            console.log("Join Success");
            alert(`${res.response.nickname}님 가입을 축하합니다.`);
            
        }
    })
})