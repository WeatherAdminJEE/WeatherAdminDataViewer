
var sensorMap;
var markers;

initSensorMap();
loadSensors();

/**
 * Init sensors locations map (OpenStreetMap based)
 */
function initSensorMap() {

    sensorMap = new OpenLayers.Map("sensorMap");
    sensorMap.addLayer(new OpenLayers.Layer.OSM());
    sensorMap.zoomToMaxExtent();
    sensorMap.setCenter(new OpenLayers.LonLat(-1.553621,47.218371) // Centre de la carte
        .transform(
            new OpenLayers.Projection("EPSG:4326"), // Transformation de WGS 1984
            new OpenLayers.Projection("EPSG:900913") // Projection Mercator sphérique
        ), 12 // Zoom level
    );
    markers = new OpenLayers.Layer.Markers("Markers");
    sensorMap.addLayer(markers);
}

/**
 * Add sensors markers on the map
 * @param mapMarkersCoordinates Sensors GPS coordinates
 */
function initMapSensorMarkers(sensors) {

    for (var i=0; i < sensors.length; i=i+1) {
        var longLat = new OpenLayers.LonLat(sensors[i].longitude, sensors[i].latitude)
            .transform(
                new OpenLayers.Projection("EPSG:4326"),
                sensorMap.getProjectionObject()
            );
        markers.addMarker(new OpenLayers.Marker(longLat));
    }
}

/**
 * Load sensors from servlet
 */
function loadSensors() {
    $.get("./getAllSensorForMapAction", function(sensorsResponse) {
        initMapSensorMarkers(sensorsResponse);
    });
}
