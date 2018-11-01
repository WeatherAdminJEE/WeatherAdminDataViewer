<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
    <head>
        <title>Historique des mesures</title>
        <%@ include file="/WEB-INF/jspf/templateHeader.jspf" %>
        <!-- Select2 -->
        <link href="vendors/select2/dist/css/select2.min.css" rel="stylesheet">
        <!-- bootstrap-daterangepicker -->
        <link href="vendors/bootstrap-daterangepicker/css/daterangepicker.css" rel="stylesheet">
    </head>

    <body class="nav-md">
    <div class="container body">
        <div class="main_container">
            <%@ include file="/WEB-INF/jspf/templateSidebarMenu.jspf" %>
            <%@ include file="/WEB-INF/jspf/templateTopNavigation.jspf" %>
            <!-- page content -->
            <div class="right_col" role="main">

                <!-- cmd row -->
                <div class="row">
                    <!-- sensors list container -->
                    <div class="col-md-4 col-sm-4 col-xs-4">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Liste des capteurs</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <div class="row">
                                    <!-- TODO: Alimenter le selecteur Select2 avec les capteurs -->
                                    <select class="select2_single form-control" tabindex="-1">
                                        <option value="1">Sensor1</option>
                                        <option value="2">Sensor2</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /sensors list container -->

                    <!-- datepicker container -->
                    <div class="col-md-4 col-sm-4 col-xs-4">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Période des mesures</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <div id="reportrange" style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc">
                                    <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                                    <span></span> <b class="caret"></b>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /datepicker container -->
                </div>
                <!-- /cmd row -->

                <!-- chart row -->
                <div class="row">
                    <!-- chart container -->
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Historique des mesures pour la période</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <!-- TODO: Rajouter ici la div du graphique -->
                            </div>
                        </div>
                    </div>
                    <!-- /chart container -->
                </div>
                <!-- /chart row -->

            </div>
            <!-- /page content -->

            <%@ include file="/WEB-INF/jspf/templateFooter.jspf" %>
        </div>
    </div>
    <%@ include file="/WEB-INF/jspf/includeTemplateScripts.jspf" %>
    <!-- Select2 -->
    <script src="vendors/select2/dist/js/select2.full.min.js"></script>
    <!-- bootstrap-daterangepicker -->
    <script src="vendors/moment/js/moment.min.js"></script>
    <script src="vendors/bootstrap-daterangepicker/js/daterangepicker.js"></script>
    </body>
</html>
