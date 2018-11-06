<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Connexion</title>
        <%@ include file="/WEB-INF/jspf/includeTemplateCSS.jspf" %>
    </head>

    <body class="login">
        <div>
            <div class="login_wrapper">
                <div class="animate form login_form">
                    <section class="login_content">
                        <form method="post">
                            <h1><i class="fa fa-sun-o"></i> La météo !</h1>
                            <div>
                                <h5 align="left">Nom d'utilisateur :</h5>
                                <input type="text" class="form-control" name="username" required="" />
                            </div>
                            <div>
                                <h5 align="left">Mot de passe :</h5>
                                <input type="password" class="form-control" name="password" required="" />
                            </div>
                            <input type="hidden" name="requestedURI" value="${requestedURI}">
                            <div>
                                <input class="btn btn-default submit" type="submit" value="Connexion">
                                <a class="reset_pass" href="#">Mot de passe oublié ?</a>
                            </div>
                        </form>
                    </section>
                </div>

            </div>
        </div>
    <%@ include file="/WEB-INF/jspf/includeTemplateScripts.jspf" %>
    </body>
        <c:if test="${not empty errorMessage}">
            <p>${errorMessage}</p>
        </c:if>
</html>
