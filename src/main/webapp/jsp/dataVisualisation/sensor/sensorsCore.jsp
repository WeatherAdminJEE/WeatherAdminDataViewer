<%--
  Created by IntelliJ IDEA.
  User: valentin
  Date: 10/16/18
  Time: 3:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>

    <title>Sensors Visualtion</title>

      <style>
          html {
              font-family: sans-serif;
          }

          table {
              border-collapse: collapse;
              border: 2px solid rgb(200,200,200);
              letter-spacing: 1px;
              font-size: 0.8rem;
          }

          td, th {
              border: 1px solid rgb(190,190,190);
              padding: 10px 20px;
          }

          th {
              background-color: rgb(235,235,235);
          }

          td {
              text-align: center;
          }

          tr:nth-child(even) td {
              background-color: rgb(250,250,250);
          }

          tr:nth-child(odd) td {
              background-color: rgb(245,245,245);
          }

          caption {
              padding: 10px;
          }
      </style>

</head>

<body>
<h1>Tous les sensors</h1>
<table>
    <c:forEach items="${lstSensors}" var="sensor">
        <tr>
            <td>${sensor.id}</td>
            <td>${sensor.name}</td>
            <td><a href="http://localhost:9090/WeatherAdminDataViewer/VisualiseAllDataFromOneSensor?idSensor=${sensor.id}">Data</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
