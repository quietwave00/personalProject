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
        console.log("like check: " + response);
        document.getElementById("like-button-area").innerHTML = `<input type = "hidden" id = "checkLiked" value = ${response}>`;
        if (response == true) {
            console.log("꽉찬하트");
            document.getElementById("like-button-area").innerHTML += '<span id = "like-button">♥︎</span>';
        } else {
            console.log("빈하트");
            document.getElementById("like-button-area").innerHTML += '<span id = "like-button">♡</span>';
        }
    });
    onloadData(`http://localhost:8080/blog/user/boards/like/${boardNo}`, (response) => {
        document.getElementById('like-alert-area').innerHTML = `<span id = "like-alert">${response} Like</span>`;
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
    for(let comment of comments) {
        document.getElementById('comment-elements').innerHTML +=
    `
        <div class = "row comment-element">
            <input type = "hidden" value = "${comment.commentNo}">
            <div class = "col-2">${comment.userId}</div>
            <div class = "col-7">${comment.content}
                <span class = "replies-button" style = "float: right; color: gray; visibility: hidden;" onclick = "showRepliesForm(${comment.commentNo})">↳</span>
            </div>
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
            document.getElementById('comment-input').value = "";
        }
    })
});

const addComment = (comment) => {
    document.getElementById('comment-elements').innerHTML +=
    `
        <div class = "row comment-element">
            <input type = "hidden" value = "${comment.commentNo}">
            <div class = "col-2">${comment.userId}</div>
            <div class = "col-7">${comment.content}
                <span class = "replies-button" style = "float: right; color: gray; onclick = "showRepliesForm(${comment.commentNo})">↳</span>
            </div>
            <div class = "col-2 createdDate-area">${comment.createdDate.substr(0, 10)}</div>
        </div>
    `;

}

//Like Event
document.getElementById('like-button-area').addEventListener('click', function() {
    console.log("클릭했을 때 value값: " + document.getElementById('checkLiked').value);
    if(document.getElementById('checkLiked').value == 'true') { //좋아요했음
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
                document.getElementById('checkLiked').value = false;
                document.getElementById("like-button").innerHTML = "♡";
                onloadData(`http://localhost:8080/blog/user/boards/like/${boardNo}`, (response) => {
                document.getElementById('like-alert-area').innerHTML = `<span id = "like-alert">${response} Like</span>`;
                });
            }
        });
    } else { //좋아요안했음
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
            onloadData(`http://localhost:8080/blog/user/boards/like/count/${boardNo}`, (response) => {
            if (response === true) {
                document.getElementById('checkLiked').value = true;
                document.getElementById("like-button").innerHTML = "♥︎";
                onloadData(`http://localhost:8080/blog/user/boards/like/${boardNo}`, (response) => {
                document.getElementById('like-alert-area').innerHTML = `<span id = "like-alert">${response} Like</span>`;
                });
            }
        });
        }
    })
    }
});

//Hashtag Event
const elements = document.getElementsByClassName('tag-elements');
for (let i = 0; i < elements.length; i++) {
    elements[i].addEventListener('click', (e) => {
        console.log("hashtag click");
    });
}

//Replies Event
const showRepliesForm = (commentNo) => {
    console.log("reply commentNo:" + commentNo);
    const parentElement = document.querySelector(`.comment-element input[value="${commentNo}"]`).parentElement;

    const childDiv = document.createElement('div');
    childDiv.classList.add('child-div');
    childDiv.innerHTML = `<input type = "text" class = "replies-input" placeholder = "Write Reply"><button class = "btn btn-sm btn-dark replies-button">write</button>`;

    parentElement.appendChild(childDiv);

    const btn = childDiv.querySelector('.replies-button');
    btn.addEventListener('click', (e) => {
        const parentNode = e.target.parentNode;
        const replyInput = parentNode.querySelector('input');
        addReplies(commentNo, replyInput);
    });

    
}


const addReplies = (parentNo, replyInput) => {
    fetch('http://localhost:8080/blog/user/comments/replies', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
            "Authorization": localStorage.getItem("Authorization")
        },
        body: JSON.stringify({
            "boardNo": boardNo,
            "parentNo": parentNo,
            "content": replyInput.value
        })
    })
    .then((res) => res.json())
    .then(res => {
        if(res.success == true) {
            console.log("add reply success");
        }
    });
}