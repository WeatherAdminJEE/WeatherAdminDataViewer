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
        $.getJSON("./getSensorDataById?sensorId=" + value, function (records) {
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
function dateTimeRangeChanged(start, end){
    $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
    console.log("La sélection de la période a changée :")
    console.log("start : " + start.format('DD/MM/YYYY HH:mm:ss'));
    console.log("end : " + end.format('DD/MM/YYYY HH:mm:ss'));

    var dateFormat = 'DD/MM/YYYY HH:mm:ss';
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
    $.getJSON("./getSensorDataById?sensorId=" + selectedSensor + "&beginDate=" + start.format(dateFormat) + "&endDate=" + end.format(dateFormat), function (records) {
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

    console.log("helloworld");
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
                            // suggestedMin: 0,    // minimum will be 0, unless there is a lower value.
                            // OR //
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
                            // Background color of label, default below
                            backgroundColor: 'rgba(0,0,0,0.8)',

                            // Font family of text, inherits from global
                            fontFamily: "sans-serif",

                            // Font size of text, inherits from global
                            fontSize: 12,

                            // Font style of text, default below
                            fontStyle: "bold",

                            // Font color of text, default below
                            fontColor: "#fff",

                            // Padding of label to add left/right, default below
                            xPadding: 6,

                            // Padding of label to add top/bottom, default below
                            yPadding: 6,

                            // Radius of label rectangle, default below
                            cornerRadius: 6,

                            // Anchor position of label on line, can be one of: top, bottom, left, right, center. Default below.
                            position: "center",

                            // Adjustment along x-axis (left-right) of label relative to above number (can be negative)
                            // For horizontal lines positioned left or right, negative values move
                            // the label toward the edge, and positive values toward the center.
                            xAdjust: 0,

                            // Adjustment along y-axis (top-bottom) of label relative to above number (can be negative)
                            // For vertical lines positioned top or bottom, negative values move
                            // the label toward the edge, and positive values toward the center.
                            yAdjust: 0,

                            // Whether the label is enabled and should be displayed
                            enabled: true,

                            // Text to display in label - default is null
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
    var start = moment().subtract(29, 'days');
    var end = moment();


    //
    // $('#reportrange').daterangepicker({
    //     startDate: start,
    //     endDate: end,
    //     ranges: {
    //         'Today': [moment(), moment()],
    //         'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
    //         'Last 7 Days': [moment().subtract(6, 'days'), moment()],
    //         'Last 30 Days': [moment().subtract(29, 'days'), moment()],
    //         'This Month': [moment().startOf('month'), moment().endOf('month')],
    //         'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
    //     }
    // }, dateTimeRangeChanged);

    //dateTimeRangeChanged(start, end);

    //Pour forcer la premiere creation du graph
    var sensorSelected = $("#sensorChoice option:selected").val();
    sensorSelectionChanged(sensorSelected);

});