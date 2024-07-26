<%-- 
    Document   : header
    Created on : 13 juin 2024, 12:23:41
    Author     : asolanas
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<head>
    <link rel="shortcut icon" href="<c:url value='/assets/img/favicon.png'/>" type="image/x-icon">
    <link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
   
</head>
<header>
    <div>
        <a href="https://www.prodigiup.com/" target="_blank"><img src="<c:url value="/assets/img/logo.png" />" width="300px" height="100px" alt="logo"></a> 
    </div>
    <c:set var="currentURI" value="${pageContext.request.requestURI}" />
    <c:set var="isHomePage" value="${fn:contains(currentURI, '/pageAccueil.jsp')}" />
    <c:set var="isConnexionPage" value="${fn:contains(currentURI, '/pageConnexion.jsp')}" />
    <nav>
        <ul>
            <c:if test="${!isHomePage && !isConnexionPage}">
                <li>
                    <a href="<c:url value="/"/>">Aller à l'accueil &ensp;</a>
                </li>
                <li>
                    <a href="<c:url value="/connexion"/>">Déconnexion &ensp;</a>
                </li>
                <li>Ajouter &ensp;
                    <ul class="sous">
                        <li><a href="<c:url value="/creer_ra"/>">Un Responsable d'Activité</a></li>
                        <li><a href="<c:url value="/creer_collaborateur"/>">Un Collaborateur</a></li>
                        <li><a href="<c:url value="/creer_prestation"/>">Une Prestation</a></li>
                        <li><a href="<c:url value="/creer_partenaire"/>">Un Partenaire</a></li>
                    </ul>
                </li>

                <li >Rechercher &ensp;
                    <ul class="sous">
                        <li><a href="<c:url value="/rechercher_ra"/>">Un Responsable d'Activité</a></li>
                        <li><a href="<c:url value="/rechercher"/>">Un Collaborateur</a></li>
                        <li><a href="<c:url value="/rechercher_partenaire"/>">Un Partenaire</a></li>

                        
                    </ul>
                </li>

                <li >Listings &ensp;
                    <ul class="sous">
                        <li><a href="<c:url value="/liste_partenaires"/>">Partenaires</a></li>
                        <li><a href="<c:url value="/liste_prestations"/>">Prestations</a></li>
                        <li><a href="<c:url value="/liste_collaborateurs"/>">Collaborateurs</a></li
                    </ul>
                </li>
            </c:if>
        </ul>
    </nav>
    <div class="nom_appli">
        Gestion des ressources
    </div>
</header>




