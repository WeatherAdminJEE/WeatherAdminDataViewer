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
function dateTimeRangeChanged(value){

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
                backgroundColor: 'rgb(255, 99, 132)',
                borderColor: 'rgb(255, 99, 132)',
                data: valueArray
            }]
        },

        // Configuration options go here
        options: {
        }
    });
}




$(function() {
    /**
     * DateTimePicker initialization
     */
    var start = moment().subtract(29, 'days');
    var end = moment();

    function cb(start, end) {
        $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
    }

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
    }, cb);

    cb(start, end);

    //Pour forcer la premiere creation du graph
    var sensorSelected = $("#sensorChoice option:selected").val();
    sensorSelectionChanged(sensorSelected);

});