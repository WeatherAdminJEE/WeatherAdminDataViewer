<%--
  Created by IntelliJ IDEA.
  User: vivien
  Date: 12/10/18
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LOGIN</title>
</head>
<body>
<h1>login</h1>

<form action="login" method="post">
    <p>Nom d'utilisateur :</p>
    <input type="text" name="username">
    <p>Mot de passe : </p>
    <input type="password" name="password">
    <p></p>
    <input type="submit" value="login">
</form>
<c:if test="${not empty errorMessage}">
    <p>${errorMessage}</p>
</c:if>
</body>
</html>
