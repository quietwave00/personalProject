
//페이지 이동
document.getElementById("search-button").addEventListener("click", function() {
    const keyword = document.getElementById("search-input").value;
    window.location.href = `track.html?keyword=${keyword}`;
});