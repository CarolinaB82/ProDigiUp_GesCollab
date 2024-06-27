<%-- 
    Document   : header
    Created on : 13 juin 2024, 12:23:41
    Author     : asolanas
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
<link rel="shortcut icon" href="<c:url value="/assets/img/logo.png"/>" type="logo"/>


<header>
    <div>
        <img src="<c:url value="/assets/img/logo.png" />" width="300px" height="100px" alt="logo">
    </div>
    <div>
        <h1>ProDigiUp Gestion des ressources</h1>
    </div>
    <nav>
        <ul >
            <p><a href="<c:url value="/accueil"/>">Retour à l'accueil</a></p>
        </ul>
    </nav>



</header>
