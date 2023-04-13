window.onload = () => {
    const boardNo = new URLSearchParams(window.location.search).get('boardNo');
    fetch('http://localhost:8080/blog/user/board/' + boardNo, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": localStorage.getItem("Authorization")
        }
    })
    .then((res) => res.json())
    .then(res => {
        console.log(res);
        if(res.success == true) {
            console.log("Select Detail Board Success");
            showBoard(res.response);
        }
    })
}

const showBoard = (board) => {
    console.log("showBoard called");
    let content = board.content;
    let userId = board.userId;
    let createdDate = board.createdDate.substr(0, 9);
    let count = board.count;
    let tagList = board.hashtagList;

    let tagElements = "";
    for(let tag of tagList) {
        tagElements += 
            `
            <div id = "tag-element">#${tag}</div>
            `;
    }

    document.getElementById('board-area').innerHTML =
        `
            <div id = "board-element">
                <div id = "hashtag-area">${tagElements}</div>
                <div id = "content-area">${content}</div>
                <div id = "user-area">${userId}</div>
            </div>
        `;
}