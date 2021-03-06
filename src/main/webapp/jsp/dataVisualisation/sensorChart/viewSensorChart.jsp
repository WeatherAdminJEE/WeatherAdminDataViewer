<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="fr">
    <head>
        <title>Historique des mesures</title>
        <%@ include file="/WEB-INF/jspf/templateHeader.jspf" %>
        <!-- Select2 -->
        <link href="vendors/select2/dist/css/select2.min.css" rel="stylesheet">
        <!-- bootstrap-datetimepicker -->
        <link href="vendors/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

        <!-- Datatables -->
        <link href="vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
        <link href="vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
        <link href="vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
        <link href="vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
        <link href="vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">
        <link href="css/loader.css" rel="stylesheet">
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
                    <div class="col-md-6 col-sm-4 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Liste des capteurs</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content" style="height:60px">
                                <label for="sensorChoice" class="control-label">Capteur :</label>
                                    <select class="select2_single form-control" tabindex="-1" id="sensorChoice" onchange="sensorSelectionChanged(this.value);">
                                        <c:forEach items="${sensors}" var="sensor">
                                            <c:choose>
                                            <c:when test = "${sensorSelected == sensor.id}">
                                                <option value="${sensor.id}" selected="selected">
                                            </c:when>
                                                <c:otherwise>
                                                    <option value="${sensor.id}">
                                                </c:otherwise>
                                            </c:choose>
                                                    ${sensor.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                            </div>
                            <div class="x_content" style="height:75px">
                                <p>Type : <i id="sensorType"></i></p>
                                <p>Statut : <i id="sensorStatut"></i></p>
                            </div>
                        </div>
                    </div>
                    <!-- /sensors list container -->

                    <!-- datepicker container -->
                    <div class="col-md-6 col-sm-4 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Période des mesures</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content" style="height:140px">
                                <div class="form-group">
                                    <label for="startDateInput" class="control-label">Date de début :</label>
                                    <div class='input-group date' id='datetimepickerStart'>
                                        <input type='text' class="form-control" id="startDateInput"/>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="endDateInput" class="control-label">Date de fin :</label>
                                    <div class='input-group date' id='datetimepickerEnd'>
                                        <input type='text' class="form-control" id="endDateInput"/>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /cmd row -->

                <div class="row" id="custom-loader">
                    <div class="loader" style="margin: auto;"></div>
                </div>

                <!-- chart row -->
                <div class="row">
                    <!-- chart container -->
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Historique des mesures pour la période</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content" id="chart-container">
                                <canvas id="histo-chart"></canvas>
                            </div>
                        </div>
                    </div>
                    <!-- /chart container -->
                </div>
                <!-- /chart row -->

                <!-- tab line -->
                <div class="row">
                    <!-- tab container -->
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Liste des mesures pour la période</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <p class="text-muted font-13 m-b-30"></p>
                                <table id="datatableMesures" class="table table-striped table-bordered">
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- /tab container -->
                </div>
                <!-- /tab line -->

            </div>
            <!-- /page content -->


            <%@ include file="/WEB-INF/jspf/templateFooter.jspf" %>
        </div>
    </div>



    <%@ include file="/WEB-INF/jspf/includeTemplateScripts.jspf" %>

    <script src="vendors/select2/dist/js/select2.full.min.js"></script>
    <script src="vendors/moment/js/moment.min.js"></script>
    <script src="vendors/moment/locale/fr.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
    <script src="vendors/chartjs/chartjs-plugin-annotation.min.js"></script>
    <!-- bootstrap-datetimepicker -->
    <script src="vendors/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>

    <script src="js/sensorChart/customchart.js"></script>

    <!-- Datatables -->
    <script src="vendors/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
    <script src="vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
    <script src="vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
    <script src="vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
    <script src="vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
    <script src="vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
    <script src="vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
    <script src="vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
    <script src="vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
    <script src="vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
    <script src="vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>

    </body>

</html>
