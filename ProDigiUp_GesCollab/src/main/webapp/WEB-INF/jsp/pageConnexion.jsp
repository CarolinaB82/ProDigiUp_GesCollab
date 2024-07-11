<%-- 
    Document   : pageConnexion
    Created on : 11 juil. 2024, 15:43:19
    Author     : asolanas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
    </head>
    <body>
        <main>
            <div class='en_tete_accueil'>
                <a href="https://www.prodigiup.com/" target="_blank"><img src="<c:url value="/assets/img/logo.png" />" width="300px" height="100px" alt="logo"></a> 

                <h1>ProDigiUp Gestion des ressources</h1>
            </div>
            <nav class="centered-form">
                <div >
                    <label for="login">Identifiant</label>
                    <input type="text" id="login" name="login" >
                </div>

                <div >     
                    <label for="login">Mot de passe</label>
                    <input type="password" id="password" name="password" >
                </div>
                <input type="submit" value="Connexion">
                <h2 class='neutral-links'><a href="<c:url value="/liste_collaborateurs"/>">Connexion</a></h2>
            </nav>
        </main>

        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>

</html>