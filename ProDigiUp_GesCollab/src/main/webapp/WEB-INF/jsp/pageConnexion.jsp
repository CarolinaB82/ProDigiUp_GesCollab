<%-- 
    Document   : pageConnexion
    Created on : 11 juil. 2024, 15:43:19
    Author     : asolanas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
        <link rel="shortcut icon" href="<c:url value="/assets/img/favicon.png"/>" type="image/x-icon"/>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <main>
            <h1>Authentification</h1>
            <nav class="centered-form">
                <div>
                    <label for="username">Identifiant :</label>
                    <input type="text" id="username" name="username">
                </div>
                <div>
                    <label for="password">Mot de passe :</label>
                    <input type="password" id="password" name="password">
                </div>

                <div class="buttons">
                    <button type="submit" class="btn-connexion" onclick="location.href = '<c:url value='/liste_collaborateurs'/>'">Connexion</button>
                </div>
            </nav>
        </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>
</html>