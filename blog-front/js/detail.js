
window.onload = () => {
    //Track Select
    const trackId = new URLSearchParams(window.location.search).get('trackId');
    fetch('http://localhost:8080/musics/board/' + trackId, {
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
    })
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
                                <input type = "button" class="btn btn-dark" value = "write" id = "modal-button" data-bs-toggle="modal" data-bs-target="#exampleModal">
                            </div>
                        </div>
                    </div>        
                </div> 
            `;
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
            <div class = "hashtag-elements" style = "margin-right: 10px;">#${hashtag}</div>

        `;
    hashtagArray.push(hashtag);
    console.log(hashtagArray);
}

//Write Board
document.getElementById('write-button').addEventListener('click', function() {
    console.log(hashtagArray);
    fetch('http://localhost:8080/blog/user/boards',{
        method:"POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": localStorage.getItem('Authorization')
        },
        body: JSON.stringify({
            "content": document.getElementById('board-input').value,
            "hashtagList": hashtagArray
        })
    })
    .then((res) => res.json())
    .then(res => {
        if(res.success == true) {
            console.log("Write Board Success");
        }
    })


});
