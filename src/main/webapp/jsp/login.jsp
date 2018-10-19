<!DOCTYPE html>
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

        <form action="login" method="POST">
            <p>Nom d'utilisateur :</p>
                <input type="text" name="username">
            <p>Mot de passe : </p>
                <input type="password" name="password">
            <p></p>
            <input type="submit" value="login">
            <input type="hidden" name="requestedURI" value="${requestedURI}">
        </form>
        <c:if test="${not empty errorMessage}">
            <p>${errorMessage}</p>
        </c:if>
    </body>
</html>
