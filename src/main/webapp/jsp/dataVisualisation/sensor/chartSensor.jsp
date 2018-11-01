<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="fr">
<head>
    <title>Chart sensor</title>
    <%@ include file="/WEB-INF/jspf/templateHeader.jspf" %>
</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <%@ include file="/WEB-INF/jspf/templateSidebarMenu.jspf" %>
        <%@ include file="/WEB-INF/jspf/templateTopNavigation.jspf" %>

        <!-- page content -->
        <div class="right_col" role="main">

            <!-- map line -->
            <div class="row">
                <!-- map container -->
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="x_panel" style="height:620px">
                        <div class="x_title">
                            <h2>Choix du capteur</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <select name="sensorChoice" onchange="sensorSelectionChanged(this.value);">
                                <c:forEach items="${sensors}" var="sensor">
                                    <option value="${sensor.id}">
                                        ${sensor.name} 
                                    </option>
                                </c:forEach>
                            </select>
                            <p id="sensorName"></p>
                            <p id="sensorType"></p>
                            <p id="sensorStatus"></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="/WEB-INF/jspf/templateFooter.jspf" %>
    </div>
</div>
<script src="build/js/customchart.js" />
<%@ include file="/WEB-INF/jspf/includeTemplateScripts.jspf" %>


</body>
</html>