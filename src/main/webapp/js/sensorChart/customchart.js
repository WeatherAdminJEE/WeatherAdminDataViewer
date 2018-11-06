var startDate = moment().subtract(2, 'hours').format('YYYY-MM-DD HH:mm:ss');
var endDate = moment().format('YYYY-MM-DD HH:mm:ss');
var sensorSelected = $("#sensorChoice option:selected").val();

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
        Affiche le graph
         */
        var datasetArray = [];
        var dateArray = [];
        var valueArray = [];
        $.getJSON("./getSensorDataById?sensorId=" + value + "&beginDate=" + startDate + "&endDate=" + endDate, function (records) {
            $.each(records, function (key, val) {
                datasetArray.push({t: moment(val["date"]), y: val["value"]});
                dateArray.push(moment(val["date"]).format("DD/MM/YYYY HH:mm:ss"));
                valueArray.push(val["value"]);
            });

            printGraph(dateArray, valueArray, sensorType, value);
        });
    });
}

/**
 * When user change datetime range
 */
function dateTimeRangeChanged(start, end) {
    console.log("La sélection de la période a changée :")
    console.log("start : " + start);
    console.log("end : " + end);

    //On update le graph
    //Sensor selected
    var selectedSensor = $('#sensorChoice option:selected').val();
    console.log("selectedSensor : " + selectedSensor);

    var sensorType = $("#sensorType").text();
    console.log("sensortype : " + sensorType);
    /*
    Affiche le graph
     */
    var datasetArray = [];
    var dateArray = [];
    var valueArray = [];
    $.getJSON("./getSensorDataById?sensorId=" + selectedSensor + "&beginDate=" + start + "&endDate=" + end, function (records) {
        $.each(records, function (key, val) {
            datasetArray.push({t: moment(val["date"]), y: val["value"]});
            dateArray.push(moment(val["date"]).format("DD/MM/YYYY HH:mm:ss"));
            valueArray.push(val["value"]);
        });

        printGraph(dateArray, valueArray, sensorType, selectedSensor);
    });

}

/**
 * Print new graph for sensor selected and datetime range
 */
function printGraph(dateArray, valueArray, sensorType, sensorId){
    $('#histo-chart').empty();
    $('#chart-container').html('&nbsp;');
    $('#chart-container').html('<canvas id="histo-chart"></canvas>');


    $.getJSON("./getSensorAlertParamBySensorId?sensorId=" + sensorId, function (alertParam) {

        console.log("alertparam : " + JSON.stringify(alertParam));
        var alertValue = alertParam["value"];
        var ctx = document.getElementById('histo-chart').getContext('2d');
        var chart = new Chart(ctx, {
            // The type of chart we want to create
            type: 'line',

            // The data for our dataset
            data: {
                labels: dateArray,
                datasets: [{
                    label: sensorType,
                    fill: true,
                    backgroundColor: 'rgba(32, 162, 219, 0.3)',
                    borderColor: 'rgba(32, 162, 219, 0.8)',
                    data: valueArray
                }]
            },

            // Configuration options go here
            options: {
                scales: {
                    yAxes: [{
                        // display: true,
                        ticks: {
                            // min: 1000,
                            // max : 1050
                            beginAtZero: true   // minimum value will be 0.
                        }
                    }]
                },
                annotation: {
                    annotations: [{
                        type: 'line',
                        mode: 'horizontal',
                        scaleID: 'y-axis-0',
                        value: alertValue,
                        borderColor: 'red',
                        borderWidth: 3,
                        label: {
                            backgroundColor: 'rgba(0,0,0,0.8)',
                            fontFamily: "sans-serif",
                            fontSize: 12,
                            fontStyle: "bold",
                            fontColor: "#fff",
                            xPadding: 6,
                            yPadding: 6,
                            cornerRadius: 6,
                            position: "center",
                            xAdjust: 0,
                            yAdjust: 0,
                            enabled: true,
                            content: "Seuil d'alerte"
                        }
                    }]
                }
            }
        });
    });

}
$(function() {
    /**
     * DateTimePicker initialization
     */

    $('#datetimepickerStart').datetimepicker({
        locale: 'fr',
        date: startDate
    });
    $('#datetimepickerEnd').datetimepicker({
        locale: 'fr',
        useCurrent: false,
        date: endDate
    });

    $("#datetimepickerStart").on("dp.change", function (e) {
        startDate = moment(e.date).format('YYYY-MM-DD HH:mm:ss');
        dateTimeRangeChanged(startDate, endDate);
        refreshMesure(startDate, endDate);
    });
    $("#datetimepickerEnd").on("dp.change", function (e) {
        endDate = moment(e.date).format('YYYY-MM-DD HH:mm:ss');
        dateTimeRangeChanged(startDate, endDate);
        refreshMesure(startDate, endDate);
    });

    //Pour forcer la premiere creation du graph
    sensorSelected = $("#sensorChoice option:selected").val();
    sensorSelectionChanged(sensorSelected);
});



function refreshMesure(start, end) {
    console.log("refreshMesure start");
    var selectedSensor = $('#sensorChoice option:selected').val();
    console.log("refreshMesure start");
    console.log(selectedSensor);
    var dataSet = [];
    $.getJSON("./getSensorDataById?sensorId=" + selectedSensor + "&beginDate=" + start + "&endDate=" + end, function (records) {
        $.each(records, function (key, val) {
            dataSet.push([val["id"], val["value"], moment(val["date"]).format("DD/MM/YYYY HH:mm:ss")]);
        });
        $('#datatableMesures').DataTable( {
            data: dataSet,
            columns: [
                { title: "id" },
                { title: "Valeur" },
                { title: "Date" }
            ],
            "order": [[ 2, "desc" ]]
        } );
    });
}
