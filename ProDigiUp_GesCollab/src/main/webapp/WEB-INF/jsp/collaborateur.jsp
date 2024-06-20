<%-- 
    Document   : collaborateur
    Created on : 11 juin 2024, 13:40:04
    Author     : cberge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix= "c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <main>
            <fieldset>
                <legend>Fiche Collaborateur</legend>

                <p><strong>ID: </strong><c:out value="${collaborateur.id}"/></p>
                <p><strong>Matricule: </strong><c:out value="${collaborateur.matricule}"/></p>
                <p><strong>Genre: </strong><c:out value="${collaborateur.genre}"/></p>
                <p><strong>Nom: </strong><c:out value="${collaborateur.nom}"/></p>
                <p><strong>Prénom: </strong><c:out value="${collaborateur.prenom}"/></p>
                <p><strong>Téléphone personnel: </strong><c:out value="${collaborateur.telephone_personnel}"/></p>
                <p><strong>Statut: </strong><c:out value="${collaborateur.statut}"/></p>
                <p><strong>Catégorie: </strong><c:out value="${collaborateur.categorie}"/></p>
                <p><strong>RQTH: </strong><c:out value="${collaborateur.rqth}"/></p>
                <p><strong>Date de renouvellement: </strong><c:out value="${collaborateur.date_de_renouvellement}"/></p>
                <p><strong>Métier: </strong><c:out value="${collaborateur.metier}"/></p>

            </fieldset>  

            
            <fieldset>
                <h1>Recherche de Collaborateur</h1>
    <form action="rechercher" method="get">
        <label for="rechercher">Rechercher (Nom, Prénom, Matricule) :</label>
        <input type="text" name="recherche">
        <button type="submit">Rechercher</button>
    </form>
            </fieldset>
        </main>
    </body>
    <%@include file="/WEB-INF/jspf/footer.jsp" %>
</html>

