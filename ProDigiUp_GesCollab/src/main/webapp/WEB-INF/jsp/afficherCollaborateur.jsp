<%-- 
    Document   : afficherCollaborateur
    Created on : 20 juin 2024, 10:02:28
    Author     : asolanas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix= "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <title>Fiche collaborateur</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <fieldset>
            <legend>Fiche Collaborateur</legend>

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
        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>

</html>
