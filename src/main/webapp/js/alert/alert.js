/**
 * When user choose a sensor in the dropdown list
 * @param value sensor ID
 */
function sensorSelectionChanged(value) {
    /*
    Affiche les détails du capteur sélectionné
     */
    $.getJSON("./getSensorById?sensorId=" + value, function (sensor) {
        var sensorName = sensor["name"];
        var sensorType = sensor["type"];
        var sensorStatus = sensor["status"];

        $("#sensorName").text(sensorName);
        $("#sensorType").text(sensorType);
        $("#sensorStatut").text(sensorStatus);

        /*
        Recupere les alertes
         */
        console.log("value : " + value);
        $.getJSON("./getAlertBySensorId?sensorId=" + value , function (alerts) {
            console.log(JSON.stringify(alerts));
            // $.each(records, function (key, val) {
            //     datasetArray.push({t: moment(val["date"]), y: val["value"]});
            //     dateArray.push(moment(val["date"]).format("DD/MM/YYYY HH:mm:ss"));
            //     valueArray.push(val["value"]);
            // });
            var dataSet = [];
            $.each(alerts, function (key,val) {
                dataSet.push([val["sensorName"], val["param"]["value"], val["startDate"], val["endDate"]]);
            });
        });
    });
}



/**
 * Print new graph for sensor selected and datetime range
 */
function printTable(dataSet){
    //Reset le tableau avec le nouveau dataset
    $('#datatableAlertes').DataTable().clear().rows.add(dataSet).draw();
}


$(function() {

    //Premiere initialisation du tableau
    $('#datatableAlertes').DataTable( {
        // data: dataSet,
        columns: [
            { title: "Nom du capteur" },
            { title: "Valeur Seuil" },
            { title: "Debut" },
            { title: "Fin" }
        ],
        "order": [[ 2, "desc" ]]
    });

    //Pour forcer la premiere creation du graph
    var sensorSelected = $("#sensorChoice option:selected").val();
    sensorSelectionChanged(sensorSelected);
});
