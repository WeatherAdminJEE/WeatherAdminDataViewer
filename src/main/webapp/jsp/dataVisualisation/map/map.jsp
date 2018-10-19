<%--
  Created by IntelliJ IDEA.
  User: valentin
  Date: 10/19/18
  Time: 9:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.4/dist/leaflet.css"
          integrity="sha512-puBpdR0798OZvTTbP4A8Ix/l+A4dHDD0DGqYW6RQ+9jxkRFclaxxQb/SJAWZfWAkuyeQUytO7+7N4QKrDh+drA=="
          crossorigin=""/>
    <link rel="stylesheet" href="/WeatherAdminDataViewer/css/dataVisualisation/map/mapStyle.css"/>

    <script src="https://unpkg.com/leaflet@1.3.4/dist/leaflet.js"
            integrity="sha512-nMMmRyTVoLYqjP9hrbed9S+FzjZHW5gY1TWCHA5ckwXZBadntCNs8kEqAWdrb9O7rxbCaA4lKTIWjDXZxflOcA=="
            crossorigin=""></script>

    <script src="./js/dataVisualisation/map/map.js"></script>

</head>
<body>
    <div id="mapid"></div>
    <script>initMap()</script>

    <c:forEach items="${lstSensorCoordinate}" var="coord">
        <tr>
            <td>${coord}</td>
        </tr>
    </c:forEach>
    <script type="text/javascript">
        addPinToMap(<%= request.getAttribute("lstSensorCoordinate")%>);
    </script>

</body>
</html>
