<%-- 
    Document   : page_accueil
    Created on : 13 juin 2024, 12:16:35
    Author     : asolanas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <h1 class='bienvenue'>Bienvenue cher surmulot </h1>

        <div class="listeAccueil">
            <ul >
                <li><a href="<c:url value="/creer_ra"/>">Créer un Responsable d'Activité</a></li>
                <li><a href="<c:url value="/afficher_ra"/>">Voir un Responsable d'Activité</a></li>
                <li><a href="<c:url value="/creer_collaborateur"/>">Créer un collaborateur</a></li>
                <li><a href="<c:url value="/collaborateur"/>">Voir un collaborateur</a></li>
                <li><a href="<c:url value="/partenaire"/>">Voir un partenaire</a></li>
                <li><a href="<c:url value="/liste_collaborateurs"/>">Liste collaborateurs</a></li>


            </ul>

        </div>


    </body>
    <%@include file="/WEB-INF/jspf/footer.jsp" %>
</html>
