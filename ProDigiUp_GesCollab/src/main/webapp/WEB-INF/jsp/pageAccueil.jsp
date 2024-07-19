<%-- 
    Document   : page_accueil
    Created on : 13 juin 2024, 12:16:35
    Author     : asolanas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil</title>
    </head>
    <body>
        <main>
            <%@include file="/WEB-INF/jspf/header.jsp" %>
            <h1 class='bienvenue'>Bienvenue</h1>

            <div class='accueil'>
                <p><a href="<c:url value="/liste_collaborateurs"/>"style='margin-left: -200px'class="custom-link">Connexion</a></p>

                <!-- <ul>
                    <p><a href="<c:url value="/creer_ra"/>">Créer un Responsable d'Activité</a></p>
                    <p><a href="<c:url value="/rechercher_ra"/>">Chercher un Responsable d'Activité</a></p>
                      <p><a href="<c:url value="/creer_collaborateur"/>">Créer un collaborateur</a></p>
                    <p><a href="<c:url value="/rechercher"/>">Chercher un collaborateur</a></p>
                    <p><a href="<c:url value="/creer_prestation"/>">Créer une prestation</a></p>
                    <p><a href="<c:url value="/creer_partenaire"/>">Créer un partenaire</a></p>
                    
                    <p><a href="<c:url value="/liste_collaborateurs"/>">Liste des collaborateurs</a></p>
                    <p><a href="<c:url value="/liste_prestations"/>">Liste des prestations</a></p>
                   
                </ul>-->
                <img src="<c:url value="/assets/img/surmulotJo.png" />" width="500px" height="500px" alt="mulot"style='margin-left: 70px' >
            </div>
        </main>

        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>

</html>
