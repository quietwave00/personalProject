
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
        if(res.success == true) {
            showBoard(res.response);
        }
    });

    fetch('http://localhost:8080/blog/user/comments/' + boardNo, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": localStorage.getItem("Authorization")
        }
    })
    .then((res) => res.json())
    .then (res => {
        if(res.success == true) {
            showComments(res.response);
        }
    })

}

const showBoard = (board) => {
    let content = board.content;
    let userId = board.userId;
    let createdDate = board.createdDate.substr(0, 10);
    let count = board.count;
    let tagList = board.hashtagList;

    let tagElements = "";
    for(let tag of tagList) {
        tagElements += 
            `
            <div class = "tag-elements">#${tag}</div>
            `;
    }

    document.getElementById('board-area').innerHTML =
        `
            <div id = "board-element">
                <div class = "row">
                    <div class = "col-9" id = "hashtag-area">${tagElements}</div>
                    <div class = "col-3" id = "created-date-area">${createdDate}</div>
                </div>
                
                <div id = "content-area">${content}</div>
                <div id = "user-area">${userId}</div>
            </div>
        `;
}

const showComments = (comments) => {
    console.log("showComments called");
    for(let comment of comments) {
        document.getElementById('comment-elements').innerHTML +=
    `
        <div class = "row comment-element">
            <div class = "col-2">${comment.userId}</div>
            <div class = "col-7">${comment.content}</div>
            <div class = "col-2 createdDate-area">${comment.createdDate.substr(0, 10)}</div>
        </div>
    `;
    }
    

}

//Write Comment
document.getElementById('comment-write-button').addEventListener('click', () => {
    fetch('http://localhost:8080/blog/user/comments', {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": localStorage.getItem("Authorization")
        },
        body: JSON.stringify({
            "boardNo": new URLSearchParams(window.location.search).get('boardNo'),
            "content": document.getElementById('comment-input').value
        })
    })
    .then((res) => res.json())
    .then(res => {
        if(res.success == true) {
            addComment(res.response);
        }
    })
});

const addComment = (comment) => {
    document.getElementById('comment-elements').innerHTML +=
    `
        <div class = "row comment-element">
            <div class = "col-2">${comment.userId}</div>
            <div class = "col-7">${comment.content}</div>
            <div class = "col-2 createdDate-area">${comment.createdDate.substr(0, 10)}</div>
        </div>
    `;

}

