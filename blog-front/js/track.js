
window.onload = function() {
    //Track Select
    const keyword = new URLSearchParams(window.location.search).get('keyword');

    fetch(`${host}/musics/` + keyword,{
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
        let trackId = track.trackId;
        let title = track.title;
        let artist = track.artistName;
        let album = track.albumName;
        let imageUrl = track.imageUrl;

        document.getElementById('track-area').innerHTML += 
            `
                <div class = "row track">
                    <input class = "track-id" type = "hidden" value = ${trackId}>
                    <div class = "col-5">
                        <img src = "${imageUrl}" style = "width: 60%">
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
                        </div>
                    </div>        
                </div> 
            `;
    }
    moveDetails();
};

const moveDetails = () => {
    const trackElements = document.getElementsByClassName('track');

    for(let element of trackElements) {
        element.addEventListener('click', (e) => {
            const trackId = e.currentTarget.querySelector('.track-id').value;
            window.location.href = `detail.html?trackId=${trackId}`;
        })
    }
}



