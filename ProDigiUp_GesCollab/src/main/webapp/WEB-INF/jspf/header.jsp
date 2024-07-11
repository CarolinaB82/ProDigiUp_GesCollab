<%-- 
    Document   : header
    Created on : 13 juin 2024, 12:23:41
    Author     : asolanas
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">



<header>
    <link rel="shortcut icon" href="<c:url value='/assets/img/logo.png'/>" type="image/png"/>
    <link rel="shortcut icon" href="<c:url value='/assets/img/proDigiUp.ico'/>" type="image/x-icon"/>


    <div>
        <a href="https://www.prodigiup.com/" target="_blank"><img src="<c:url value="/assets/img/logo.png" />" width="300px" height="100px" alt="logo"></a> 
    </div>
    <nav>
        <ul>
            <li>
                <a href="<c:url value="/"/>">Aller à l'accueil &ensp;</a>
            </li>
            <li>
                <a href="<c:url value="/connexion"/>">Déconnexion &ensp;</a>
            </li>

            <li>Ajouter &ensp;
                <ul class="sous">
                    <li><a href="<c:url value="/creer_ra"/>">Un Responsable d'Activité</a></li>
                    <li><a href="<c:url value="/creer_collaborateur"/>">Un collaborateur</a></li>
                    <li><a href="<c:url value="/creer_prestation"/>">Une prestation</a></li>
                    <li><a href="<c:url value="/creer_partenaire"/>">Un partenaire</a></li>
                </ul>
            </li>

            <li >Rechercher &ensp;
                <ul class="sous">
                    <li><a href="<c:url value="/rechercher_ra"/>">Un responsable d'activité</a></li>
                    <li><a href="<c:url value="/rechercher"/>">Un Collaborateur</a></li>
                </ul>
            </li>

            <li >Listings &ensp;
                <ul class="sous">
                    <li><a href="<c:url value="/liste_prestations"/>">Prestations</a></li>
                    <li><a href="<c:url value="/liste_collaborateurs"/>">Collaborateurs</a></li>
                </ul>
            </li>
        </ul>

    </nav>
    <div>
        <h1>ProDigiUp Gestion des ressources</h1>
    </div>
</header>
