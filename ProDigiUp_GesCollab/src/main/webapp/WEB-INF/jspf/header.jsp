<%-- 
    Document   : header
    Created on : 13 juin 2024, 12:23:41
    Author     : asolanas
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 


<header>
    <div>
        <img src="<c:url value="/assets/img/logo.png" />" width="200px" height="100px"  alt="logo">
        <img src="<c:url value="${pageContext.request.contextPath}/assets/img/logo.png" />" width="200px" height="100px"  alt="logo">
        <h1>ProDigiUp Gestion des ressources</h1>
    </div>

        <nav>
            <ul>
                <li><a href="<c:url value="/accueil"/>">Accueil</a></li>
            </ul>

        </nav>
</header>
