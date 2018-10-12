<%--
  Created by IntelliJ IDEA.
  User: vivien
  Date: 12/10/18
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ERROR</title>
</head>
<body>
<h1>Error</h1>
    <p>
    The username and password you supplied are not valid.
    </p>
    Click <a href='<%= response.encodeURL("login.jsp") %>'>here</a>
    to retry login
</body>
</html>
