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
        <main>
            <div class='en_tete_accueil'>
                <a href="https://www.prodigiup.com/" target="_blank"><img src="<c:url value="/assets/img/logo.png" />" width="300px" height="100px" alt="logo"></a> 

                <h1>ProDigiUp Gestion des ressources</h1>
            </div>
                
            <h1 class='bienvenue'>Bienvenue</h1>
            <div class='accueil'>
                <a href="https://fr.wikipedia.org/wiki/Rattus_norvegicus" target="_blank"><img src="<c:url value="/assets/img/surmulotJo.png" />" width="500px" height="500px" alt="mulot"style='margin-left: 70px'></a>
            </div>
            <div >
                <h1 class='bienvenue neutral-links'><a href="<c:url value="/connexion"/>" >entrer dans l'application</a></h1>
            </div>
        </main>

        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>

</html>
