const boardNo = new URLSearchParams(window.location.search).get('boardNo');
function onloadData(url, successCallback) {
    fetch(url, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": localStorage.getItem("Authorization")
        }
    })
    .then((res) => res.json())
    .then((res) => {
        if (res.success === true) {
            successCallback(res.response);
        }
    });
}

window.onload = () => {
    onloadData(`http://localhost:8080/blog/user/board/${boardNo}`, showBoard);
    onloadData(`http://localhost:8080/blog/user/comments/${boardNo}`, showComments);
    onloadData(`http://localhost:8080/blog/user/boards/like/count/${boardNo}`, (response) => {
        document.getElementById("like-button-area").innerHTML = `<input type = "hidden" id = "checkLiked" value = ${response}>`;
        if (response === true) {
            document.getElementById("like-button-area").innerHTML += '<span id = "like-button">♥︎</span>';
        }
    });
    onloadData(`http://localhost:8080/blog/user/boards/like/${boardNo}`, (response) => {
        document.getElementById('like-alert-area').innerHTML = `<input type = "hidden" id = "checkLiked" value = ${response}><span id = "like-alert">${response} Like</span>`;
    });
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

//Like Event
document.getElementById('like-button-area').addEventListener('click', function() {
    if(document.getElementById('checkLiked').value == 'true') {
        console.log('Liked True');
        fetch('http://localhost:8080/blog/user/boards/like/' + boardNo, {
            method: 'DELETE',
            headers: {
                "Content-Type": "application/json",
                "Authorization": localStorage.getItem("Authorization")
            }
        })
        .then((res) => res.json())
        .then(res => {
            if(res.success == true) {
                console.log("UnLike Success");
                document.getElementById("like-button-area").innerHTML = '<span id = "like-button">♡</span>';
            }
        });
    } else {
        fetch('http://localhost:8080/blog/user/boards/like', {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": localStorage.getItem("Authorization")
        },
        body: JSON.stringify({
            "boardNo": new URLSearchParams(window.location.search).get('boardNo')
        })
    })
    .then((res) => res.json())
    .then(res => {
        if(res.success == true) {
            console.log("Like post Success");
            document.getElementById("like-button-area").innerHTML = '<span id = "like-button">♥︎</span>';
            onloadData(`http://localhost:8080/blog/user/boards/like/count/${boardNo}`, (response) => {
            if (response === true) {
                document.getElementById("like-button-area").innerHTML = '<span id = "like-button">♥︎</span>';
            }
    });
        }
    })
    }
    
});


