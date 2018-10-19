
var mymap;

function initMap(){
    mymap = L.map('mapid').setView([47.218371, -1.553621], 13);
    L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
        maxZoom: 18,
        id: 'mapbox.streets',
        accessToken: 'pk.eyJ1IjoiaGFtaWxjYXJlIiwiYSI6ImNqbmZuaWNzZzZtajgzcW4xMWh1ZzBsZGcifQ.Hfncp4B3KBgdGc_IVD70qA'
    }).addTo(mymap);
}


function addPinToMap(arrayOfCoordinate){
    console.log(arrayOfCoordinate);
    for (var i =0; i <arrayOfCoordinate.length-1;i=i+2){
        console.log(arrayOfCoordinate[i]);
        console.log(arrayOfCoordinate[i+1]);
        var marker = L.marker([arrayOfCoordinate[i], arrayOfCoordinate[i+1]]).addTo(mymap);
        marker.bindPopup('<img src="./img/imt.JPG" alt="Italian Trulli">');

    }

}