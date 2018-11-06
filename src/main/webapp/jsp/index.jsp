<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
    <head>
        <title>WeatherAdminDataViewer</title>
        <%@ include file="/WEB-INF/jspf/templateHeader.jspf" %>
        <!-- OpenStreetMap -->
        <link href="vendors/openlayers/ol.css" rel="stylesheet">
    </head>

    <body class="nav-md">
    <div class="container body">
        <div class="main_container">
            <%@ include file="/WEB-INF/jspf/templateSidebarMenu.jspf" %>
            <%@ include file="/WEB-INF/jspf/templateTopNavigation.jspf" %>
            <!-- page content -->
            <div class="right_col" role="main">
                <!-- top tiles -->
                <div class="row tile_count">
                    <div class="col-sm-3 tile_stats_count">
                        <span class="count_top">Moyenne des températures :</span>
                        <div class="count">20,3 °C</div>
                        <span class="count_bottom"><i class="green"><i class="fa fa-sort-asc"></i>3% </i> depuis la semaine dernière</span>
                    </div>
                    <div class="col-sm-3 tile_stats_count">
                        <span class="count_top">Moyenne des pressions atmosphériques :</span>
                        <div class="count">1009,2 HPa</div>
                    </div>
                    <div class="col-sm-3 tile_stats_count">
                        <span class="count_top">Moyenne de la vitesse du vent :</span>
                        <div class="count">26,5 km/h</div>
                        <span class="count_bottom"><i class="red"><i class="fa fa-sort-desc"></i>10% </i> depuis la semaine dernière</span>
                    </div>
                    <div class="col-sm-3 tile_stats_count">
                        <span class="count_top">Moyenne de la direction du vent :</span>
                        <div class="count">167,5 °</div>
                    </div>
                </div>
                <!-- /top tiles -->

                <!-- map row -->
                <div class="row">
                    <!-- map container -->
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Localisation des capteurs
                                </h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <div id="sensorMap" style="height:620px"></div>
                                <div id="sensorPopup"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /map row -->

            </div>
            <!-- /page content -->

            <%@ include file="/WEB-INF/jspf/templateFooter.jspf" %>
        </div>
    </div>
    <%@ include file="/WEB-INF/jspf/includeTemplateScripts.jspf" %>
    <!-- OpenStreetMap -->
    <script src="vendors/openlayers/ol.js"></script>
    <!-- Index script -->
    <script src="js/index/index.js"></script>
    <script>
        initMap();
        loadSensors();
    </script>
    </body>
</html>
