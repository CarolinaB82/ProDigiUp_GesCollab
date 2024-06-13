<%-- 
    Document   : page_accueil
    Created on : 13 juin 2024, 12:16:35
    Author     : asolanas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil</title>
    </head>
    <body>
        <img src="<c:url value="/assets/img/logoproDigiUp.png" />" width="200px" height="100px"  alt="logoProDigiUp">
        <h1>Bienvenue sur l'application ProDigiUp</h1>
        <h1>pour gérer vos collaborateurs</h1>

        <nav>
            <ul>
                <li><a href="<c:url value="/creer_ra"/>">Créer un Responsable d'Activité</a></li>
                <li><a href="<c:url value="/creer_collaborateur"/>">Créer un collaborateur</a></li>
            </ul>

        </nav>
    </body>
</html>