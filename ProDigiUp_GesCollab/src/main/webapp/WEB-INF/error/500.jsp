<%-- 
    Document   : 404
    Created on : 27 juin 2024, 15:53:07
    Author     : asolanas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Problème de connexion</title>
        <style>
            :root {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <main>
            <h1>Oups! Erreur de connexion!</h1>
            <div>
                <img src="<c:url value="/assets/img/500.jpg" />" width="500px" height="500px" alt="erreurConnexion"/>
                <img src="<c:url value="/assets/img/500F.jpg" />" width="500px" height="500px" alt="erreurConnexion"/>
            </div>
            <p><a href="<c:url value="/accueil"/>">Retour à l'accueil</a></p>
            <%@include file="/WEB-INF/jspf/footer.jsp" %>
        </main>
    </body>
</html>