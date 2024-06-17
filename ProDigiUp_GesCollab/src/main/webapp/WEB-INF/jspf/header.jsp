<%-- 
    Document   : header
    Created on : 13 juin 2024, 12:23:41
    Author     : asolanas
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
<!DOCTYPE html>



<header>
    <div>
        <img src="<c:url value='/assets/img/logoproDigiUp.png' />" width="200px" height="100px"  alt="logo">
        <h1>ProDigiUp Gestion Collaborateurs</h1>
    </div>

        <nav>
            <ul>
                <li><a href="<c:url value="/accueil"/>">Accueil</a></li>
            </ul>

        </nav>
</header>
