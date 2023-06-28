
const trackId = new URLSearchParams(window.location.search).get('trackId');
window.onload = () => {
    //Select Track
    fetch(`${host}/musics/board/` + trackId, {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    })
    .then((res) => res.json())
    .then(res => {
        if(res.success == true) {
            console.log("moved to track Success");
            showTrack(res.response);
        }
    });

    //Select Board
    fetch(`${host}/blog/user/boards/` + trackId + "/byOrder", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": localStorage.getItem('Authorization')
        }
    })
    .then((res) => res.json())
    .then(res => {
        if(res.success == true) {
            console.log("select board Success");
            showBoards(res.response);
        }
    });
}

//Move To The Board
const moveDetails = () => {
    console.log("move to board Called");
    const boardElements = document.getElementsByClassName('board-element');

    for(let element of boardElements) {
        element.addEventListener('click', (e) => {
            const boardNo = e.currentTarget.querySelector('.board-no').value;
            console.log("select boardNo: " + boardNo);
            window.location.href = `board.html?boardNo=${boardNo}`;
        })
    }
}

const showTrack = (track) => {
    let title = track.title;
    let artist = track.artistName;
    let album = track.albumName;
    let imageUrl = track.imageUrl;

    document.getElementById('track-area').innerHTML += 
            `
                <div class = "row track">
                    <div class = "col-5">
                        <img src = "${imageUrl}" style = "width: 60%; margin-left: 100px;">
                    </div>
                    <div class = "col-7">
                        <div class = "row">
                            <div class = "col-7">
                                <span class = "title">${title}</span>
                            </div>
                            <div class = "col-7">
                                <span style = "color: gray; font-weight: bold;">Artist</span> <span class = "artist">${artist}</span>
                            </div>
                            <div class = "col-7">
                                <span style = "color: gray; font-weight: bold;">Album</span> <span class = "album">${album}</span>
                            </div>
                            <div class = "col-7">
                                <input type = "button" class="btn btn-dark" value = "write" id = "modal-button" data-bs-toggle="modal" data-bs-target="#boardModal">
                            </div>
                        </div>
                    </div>        
                </div> 
            `;
}

const showBoards = (boardList) => {
    for(let board of boardList) {
        let boardNo = board.boardNo;
        let tagList = board.tagList;
        let content = board.content;

        let tagElements = "";
        for(let tag of tagList) {
            tagElements += 
                `
                <div class = "tag-element">#${tag}</div>
                `;
        }
        document.getElementById('board-element-area').innerHTML += 
            `
            <div class = "col-5 board-element">
                <div class = "row">
                    <div>
                        <input type = "hidden" class = "board-no" value = "${boardNo}">
                        <div class = "tag-area">${tagElements}</div>
                        <div class = "content-area">${content}</div>
                    </div>
                </div>
            </div>
            `;
    }
    moveDetails();
}

const showBoard = (board) => {
    let boardNo = board.boardNo;
    let tagList = board.tagList;
    let content = board.content;

    let tagElements = "";
    for(let tag of tagList) {
        tagElements += 
                `
                <div class = "tag-element">#${tag}</div>
                `;
    }
    const boardElementArea = document.getElementById('board-element-area');
    boardElementArea.insertAdjacentHTML('afterbegin', `
        <div class="col-5 board-element">
            <div class="row">
                <div>
                    <input type="hidden" class="board-no" value="${boardNo}">
                    <div class="tag-area">${tagElements}</div>
                    <div class="content-area">${content}</div>
                </div>
            </div>
        </div>
        `);
        document.getElementById('hashtag-container').innerHTML = "";
        document.getElementById('board-input').value = "";
        moveDetails();
    }

//Hashtag Event
const hashtagArray = [];
let enterCount = 0;
document.getElementById('tag-input').addEventListener('keypress', function(e) {
    if(e.key === "Enter") {
        enterCount++;
        if(enterCount > 5) {
            alert("you can make only 5 hashtags");
            this.value= "";
            e.preventDefault();
        } else {
            e.stopPropagation();
            e.preventDefault();
            const hashtag = this.value.trim();
            makeHashtag(hashtag);
            this.value = "";
        }
        
    }
});

const makeHashtag = (hashtag) => {
    document.getElementById('hashtag-container').innerHTML +=
        `
            <div class = "hashtag-elements" id = "tag-${enterCount}" style = "margin-right: 10px;" onclick = "deleteHashtag('tag-${enterCount}')">#${hashtag}</div>

        `;
    hashtagArray.push(hashtag);
}

const deleteHashtag = (hashtagId) => {
    document.getElementById(hashtagId).remove();
}

//Write Board
document.getElementById('write-button').addEventListener('click', function() {
    writeBoard();
});

const writeBoard = () => {
    fetch(`${host}/blog/user/boards`,{
        method:"POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": localStorage.getItem('Authorization')
        },
        body: JSON.stringify({
            "content": document.getElementById('board-input').value,
            "hashtagList": hashtagArray,
            "trackId": trackId
        })
    })
    .then((res) => res.json())
    .then(res => {
        if(res.success == true) {
            document.getElementById('alert-area').innerHTML =
            `
                <div class = "alert alert-dark hide" role = "alert">
                    Your board has been created successfully.
                </div>
            `;
            let alertDiv = document.querySelector('.alert');
            let opacity = 1;
            let timer = setInterval(function() {
                if (opacity > 0) {
                    opacity -= 0.005;
                    alertDiv.style.opacity = opacity;
                } else {
                    clearInterval(timer);
                    alertDiv.style.display = 'none';
                }
            }, 10);
            showBoard(res.response);
            addFile(res.response.boardNo);
        }
    });
}

const addFile = (boardNo) => {
    const formData = new FormData();
        for (const value of beforeFormData.values()) {
            formData.append('multipartFileList', value);
    }

    fetch(`${host}/blog/user/files/${boardNo}`, {
        method:"POST",
        headers: {
            "Authorization": localStorage.getItem("Authorization")
        },
        body: formData
    })
    .then((res) => res.json())
    .then(res => {
        if(res.success === true) {
            console.log("Add File Success");
        }
    })
}




//File Event
const btnUpload = document.querySelector("#upload_file");
const btnOuter = document.querySelector(".button_outer");
const errorMsg = document.querySelector(".error_msg");
const uploadedView = document.querySelector("#uploaded_view");
const beforeFormData = new FormData();
let imgCount = 1;

btnUpload.addEventListener("change", function(e) {    
    let ext = btnUpload.value.split('.').pop().toLowerCase();
    if (!['png', 'jpg', 'jpeg'].includes(ext)) {
        errorMsg.textContent = "이미지 파일을 선택해 주세요";
    } else {
        //Image Preview
        btnOuter.classList.add("file_uploaded");
        let uploadedFile = URL.createObjectURL(e.target.files[0]);
        let imgDiv = document.createElement("div");
        imgDiv.className = "img_div"
        imgDiv.id = `img${imgCount++}`;
        let img = document.createElement("img");
        img.src = uploadedFile;
        imgDiv.appendChild(img);
        uploadedView.appendChild(imgDiv);
        uploadedView.classList.add("show");

        //Add file to formData
        const fileInput = e.target.files[0];
        beforeFormData.append(`imgDiv${imgDiv.id}`, fileInput);

        deleteImg();
    }
});

const deleteImg = () => {
    const parentImgDiv = document.querySelector('#uploaded_view');
    const imgDivList = parentImgDiv.querySelectorAll('.img_div');

    parentImgDiv.addEventListener('click', (event) => {
      event.stopPropagation(); // Stop event propagation
    });

    imgDivList.forEach((imgDiv) => {
        imgDiv.addEventListener('click', (event) => {
            //Delete from Preview
            event.currentTarget.remove();
            btnOuter.classList.remove("file_uploading");
            btnOuter.classList.remove("file_uploaded");
            
            //Delete from formData
            const fileKey = imgDiv.id;
            console.log("fileKey: " + fileKey);
            beforeFormData.delete(fileKey);
        });
    });
}






