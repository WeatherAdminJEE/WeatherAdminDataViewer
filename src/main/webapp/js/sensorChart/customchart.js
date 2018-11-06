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

            printGraph(dateArray, valueArray, sensorType);
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

        printGraph(dateArray, valueArray, sensorType);
    });

}

/**
 * Print new graph for sensor selected and datetime range
 */
function printGraph(dateArray, valueArray, sensorType){
    $('#histo-chart').empty();
    $('#chart-container').html('&nbsp;');
    $('#chart-container').html('<canvas id="histo-chart"></canvas>');


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
                    display: true,
                    ticks: {
                        // suggestedMin: 0,    // minimum will be 0, unless there is a lower value.
                        // OR //
                        beginAtZero: true   // minimum value will be 0.
                    }
                }]
            }
        }
    });
}




$(function() {
    /**
     * DateTimePicker initialization
     */
    var start = moment().subtract(29, 'days');
    var end = moment();



    $('#reportrange').daterangepicker({
        startDate: start,
        endDate: end,
        ranges: {
            'Today': [moment(), moment()],
            'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
            'Last 7 Days': [moment().subtract(6, 'days'), moment()],
            'Last 30 Days': [moment().subtract(29, 'days'), moment()],
            'This Month': [moment().startOf('month'), moment().endOf('month')],
            'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        }
    }, dateTimeRangeChanged);

    //dateTimeRangeChanged(start, end);

    //Pour forcer la premiere creation du graph
    var sensorSelected = $("#sensorChoice option:selected").val();
    sensorSelectionChanged(sensorSelected);

});