<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="fr">
<head>
    <title>Toutes les alertes</title>
    <%@ include file="/WEB-INF/jspf/templateHeader.jspf" %>
    <!-- Datatables -->
    <link href="vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
    <link href="vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
    <link href="vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
    <link href="vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">
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
                        <div class="x_content">
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
                        <div class="x_content">
                            <p>Type : <i id="sensorType"></i></p>
                            <p>Statut : <i id="sensorStatut"></i></p>
                        </div>
                    </div>
                </div>
                <!-- /sensors list container -->


            </div>
            <!-- /cmd row -->

            <!-- tab line -->
            <div class="row">
                <!-- tab container -->
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>Liste des alertes</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <p class="text-muted font-13 m-b-30">
                            </p>
                            <table id="datatableAlertes" class="table table-striped table-bordered"></table>
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

<script src="js/alert/alert.js"></script>

<script>

</script>
</body>
</html>
