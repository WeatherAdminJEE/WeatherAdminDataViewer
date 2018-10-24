<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>LOGIN</title>
        <%@ include file="/WEB-INF/jspf/includeTemplateCSS.jspf" %>
    </head>

    <body class="login">
    <div>
        <a class="hiddenanchor" id="signup"></a>
        <a class="hiddenanchor" id="signin"></a>

        <div class="login_wrapper">
            <div class="animate form login_form">
                <section class="login_content">
                    <form method="post">
                        <h1>Connexion</h1>
                        <div>
                            <input type="text" class="form-control" placeholder="username" required="" />
                        </div>
                        <div>
                            <input type="password" class="form-control" placeholder="Password" required="" />
                        </div>
                        <div>
                            <input class="btn btn-default submit" type="submit" value="login">
                            <a class="reset_pass" href="#">Mot de passe oublié?</a>
                        </div>

                        <div class="clearfix"></div>

                        <div class="separator">

                            <div class="clearfix"></div>
                            <br />

                            <div>
                                <h1><i class="fa fa-paw"></i> Gentelella Alela!</h1>
                                <p>©2016 All Rights Reserved. Gentelella Alela! is a Bootstrap 3 template. Privacy and Terms</p>
                            </div>
                        </div>
                    </form>
                </section>
            </div>


        </div>
    </div>
    <%@ include file="/WEB-INF/jspf/includeTemplateScripts.jspf" %>
    </body>

    <!--
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
    -->
</html>
