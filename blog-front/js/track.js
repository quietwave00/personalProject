
window.onload = function() {
    //Track Select
    const keyword = new URLSearchParams(window.location.search).get('keyword');

    fetch('http://localhost:8080/musics/' + keyword,{
        method:"GET",
        headers: {
            "Content-Type": "application/json"
        }
    })
    .then((res) => res.json())
    .then(res => {
        if(res.success == true) {
            console.log("track Select Success");
            showTrackList(res.response);
        }
    })
}

const showTrackList = (trackList) => {
    for(let track of trackList) {
        let title = track.title;
        let artist = track.artistName;
        let album = track.albumName;
        let imageUrl = track.imageUrl;

        document.getElementById('track-area').innerHTML += 
            `
            <div id = "title-area">
                <span id = "title">${title}</span>
            </div>
            <div id = "artist-area">
                <span id = "artist">${artist}</span>
            </div>
            <div id = "album-area">
                <span id = "album">${album}</span>
            </div>
            <div id = "image-area">
                <img id = "image" src = "${imageUrl}" style = "width: 40%;"></img>
            </div>      
            `;
        
        
    }
};
    
