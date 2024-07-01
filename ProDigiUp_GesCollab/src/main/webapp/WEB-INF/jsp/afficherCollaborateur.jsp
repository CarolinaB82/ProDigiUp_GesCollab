
<%-- 
    Document   : afficherCollaborateur
    Created on : 20 juin 2024, 10:02:28
    Author     : asolanas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix= "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <%-- Définir une variable pour indiquer que ce n'est pas la page d'accueil --%>
        <c:set var="notHome" value="true" />

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <title>Fiche collaborateur</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <main>
            <fieldset>
                <legend>Fiche Collaborateur</legend>

                <p><strong>Matricule: </strong><c:out value="${collaborateur.matricule}"/></p>
                <p><strong>Genre: </strong><c:out value="${collaborateur.genre}"/></p>
                <p><strong>Nom: </strong><c:out value="${collaborateur.nom}"/></p>
                <p><strong>Prénom: </strong><c:out value="${collaborateur.prenom}"/></p>
                <p><strong>Mail 1: </strong><c:out value="${collaborateur.mail_1}"/></p>
                <p><strong>Mail 2: </strong><c:out value="${collaborateur.mail_2}"/></p>
                <p><strong>Téléphone personnel: </strong><c:out value="${collaborateur.telephone_personnel}"/></p>
                <p><strong>Statut: </strong><c:out value="${collaborateur.statut}"/></p>
                <p><strong>Catégorie: </strong><c:out value="${collaborateur.categorie}"/></p>
                <p><strong>RQTH: </strong><c:out value="${collaborateur.rqth}"/></p>
                <c:if test="${collaborateur.rqth == 'oui'}">
                    <p><strong>Date de renouvellement: </strong><c:out value="${formattedDate}"/></p>
                </c:if>
                <p><strong>Métier: </strong><c:out value="${collaborateur.metier}"/></p>
            </fieldset>  
        </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>

</html>
