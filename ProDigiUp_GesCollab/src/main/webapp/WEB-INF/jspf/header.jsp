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
    <div>
        <h1>ProDigiUp Gestion des ressources</h1>
    </div>
    <nav >
        <ul class="liensAccueil">
             <c:if test="${notHome}">
                <p><a href="<c:url value="/accueil"/>">Retour à l'accueil</a></p>
            </c:if>
            <c:if test="${notHome && currentPage != 'rechercher_ra'}">
                <p><a href="<c:url value="/rechercher_ra"/>">Chercher un RA</a></p>
            </c:if>
            <c:if test="${notHome && currentPage != 'rechercher'}">
                <p><a href="<c:url value="/rechercher"/>">Chercher un Collaborateur</a></p>
            </c:if>
            
        </ul>
    </nav>

</header>
