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
        <link rel="shortcut icon" href="<c:url value="/assets/img/favicon.png"/>" type="image/x-icon"/>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <main>
            <div class='bienvenue centered-flex'>
                <h1>Bienvenue</h1>
                <a href="https://fr.wikipedia.org/wiki/Rattus_norvegicus" target="_blank"><img src="<c:url value="/assets/img/imageaccueil.png" />" height="250" width="500"  alt="prodiup"></a>
                <h1 class='bienvenue neutral-links'><a href="<c:url value="/connexion"/>" >Entrer dans l'application</a></h1>
            </div>
        </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>
</html>
