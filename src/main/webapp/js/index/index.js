/**
 * Global var
 */
var sensorMap;
var markers = new ol.source.Vector();
var markerStyle = new ol.style.Style({
    image: new ol.style.Icon(({
        anchor: [0.5, 46],
        anchorXUnits: 'fraction',
        anchorYUnits: 'pixels',
        src: 'img/marker.png'
    }))
});

/**
 * Init sensors locations map (OpenStreetMap based)
 */
function initMap() {
    sensorMap = new ol.Map({
        layers: [
            new ol.layer.Tile({
                source: new ol.source.OSM()
            }),
            new ol.layer.Vector({
                source: markers,
                style: markerStyle
            })
        ],
        target: 'sensorMap',
        view: new ol.View({
            center: ol.proj.fromLonLat([-1.553621, 47.218371]),
            zoom: 12
        })
    });
    initSensorPopups();
}

/**
 * Init sensor popups on click
 */
function initSensorPopups() {
    // Add map overlay
    var popupElem = document.getElementById('sensorPopup');
    var popup = new ol.Overlay({
        element: popupElem,
        positioning: 'bottom-center',
        stopEvent: false,
        offset: [0, -50]
    });
    sensorMap.addOverlay(popup);

    // Display popup on click
    sensorMap.on('click', function(evt) {
        var feature = sensorMap.forEachFeatureAtPixel(evt.pixel,
            function(feature) {
                return feature;
            });
        if (feature) {
            var coordinates = feature.getGeometry().getCoordinates();
            popup.setPosition(coordinates);
            $(popupElem).popover({
                placement: 'top',
                html: true,
                content: feature.get('idSensor') + feature.get('nameSensor')
            });
            $(popupElem).popover('show');
        } else {
            $(popupElem).popover('destroy');
        }
    });
}

/**
 * Load sensors from servlet
 */
function loadSensors() {
    // GET request
    $.get("./getAllSensorForMapAction", function(sensors) {
        initSensorMarkers(sensors);
    });
}

/**
 * Add sensor markers on the map
 * @param sensors Sensors data
 */
function initSensorMarkers(sensors) {
    // Init marker for each sensor
    for (var i=0; i < sensors.length; i=i+1) {
        var marker = new ol.Feature({
            geometry: new ol.geom.Point(ol.proj.transform([sensors[i].longitude, sensors[i].latitude],
                'EPSG:4326',
                'EPSG:3857')),
            idSensor: sensors[i].id,
            nameSensor: sensors[i].name,
            typeSensor: sensors[i].type,
            statusSensor: sensors[i].status
        });
        markers.addFeature(marker);
    }
}
