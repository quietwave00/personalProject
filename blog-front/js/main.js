
//페이지 이동
document.getElementById("search-button").addEventListener("click", function() {
    const keyword = document.getElementById("search-input").value;
    window.location.href = `track.html?keyword=${keyword}`;
});

window.onload = () => {
    //Alert Test
    // fetch('http://localhost:8080/blog/user/alert', {
    //     method: "GET",
    //     headers: {
    //         "Content-Type": "application/json",
    //         "Authorization": localStorage.getItem("Authorization")
    //     }
    // })
    // .then((res) => res.json())
    // .then((res) => {
    //     console.log("Alert Test: " + res);
    // });
}