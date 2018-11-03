/**
 * When user choose a sensor in the dropdown list
 * @param value
 */
function sensorSelectionChanged(value) {
    console.log(value);
    var datasetArray = [];
    var dateArray = [];
    var valueArray = [];
    $.getJSON("./getSensorDataById?sensorId=" + value, function (records) {
        $.each(records, function (key, val) {
            console.log("moment vs date : ");
            console.log(moment(val["date"]).toLocaleString());
            console.log((new Date(val["date"])).toLocaleString());
            datasetArray.push({t: moment(val["date"]), y: val["value"]});
            dateArray.push(moment(val["date"]).format("DD/MM/YYYY HH:mm:ss"));
            valueArray.push(val["value"]);
       });
        console.log("datasetArray (count=" + datasetArray.length + ") = " + JSON.stringify(datasetArray));
        console.log("dateArray (count=" + dateArray.length +") = " + JSON.stringify(dateArray));
        console.log("valueArray (count=" + valueArray.length + ") = " + JSON.stringify(valueArray));


        var a = ["24/10/2018 20:04:57","24/10/2018 20:05:07","24/10/2018 20:05:17"];//,"24/10/2018 20:05:27","24/10/2018 20:05:37","24/10/2018 20:05:47","24/10/2018 20:05:57","24/10/2018 20:06:07","24/10/2018 20:06:17","24/10/2018 20:06:27","24/10/2018 20:06:37","24/10/2018 20:06:47","24/10/2018 20:06:57","24/10/2018 20:07:07","24/10/2018 20:07:17","24/10/2018 20:07:27","24/10/2018 20:07:37","24/10/2018 20:07:47","24/10/2018 20:07:57","24/10/2018 20:08:07","24/10/2018 20:08:17","24/10/2018 20:08:27","24/10/2018 20:08:37","24/10/2018 20:08:47","24/10/2018 20:08:57","24/10/2018 20:09:07","24/10/2018 20:09:17","24/10/2018 20:09:27","24/10/2018 20:09:37","24/10/2018 20:09:47","24/10/2018 20:09:57"];
        var b = ["1.25", "2.32", "3.12"];

        console.log("a vs dateArray : ");
        console.log(JSON.stringify(a));
        console.log(JSON.stringify(dateArray.slice(0,3)));

        var ctx = document.getElementById('myChart').getContext('2d');
        var chart = new Chart(ctx, {
            // The type of chart we want to create
            type: 'line',

            // The data for our dataset
            data: {
                labels: dateArray,
                datasets: [{
                    label: "Temperature",
                    backgroundColor: 'rgb(255, 99, 132)',
                    borderColor: 'rgb(255, 99, 132)',
                    data: valueArray
                }]
            },

            // Configuration options go here
            options: {

            }
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
function printGraph(){

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


    /**
     * Chart initialization
     */
        var ctx = document.getElementById('myChart').getContext('2d');
        console.log("ctx : " + ctx)
        var chart = new Chart(ctx, {
            // The type of chart we want to create
            type: 'line',

            // The data for our dataset
            /*
            data: {
                labels: ["January", "February", "March", "April", "May", "June", "July"],
                datasets: [{
                    label: "My First dataset",
                    backgroundColor: 'rgb(255, 99, 132)',
                    borderColor: 'rgb(255, 99, 132)',
                    data: [0, 10, 5, 2, 20, 30, 45],
                }]
            },
            */
            data: [{
                x: 1,
                y:1
            }, {
                x: 2,
                y: 2
            }],

            // Configuration options go here
            options: {}
        });
});