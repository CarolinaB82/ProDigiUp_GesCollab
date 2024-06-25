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
        <title>fiche collaborateur</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <fieldset>
            <legend>Fiche Collaborateur</legend>

            <p><strong>Matricule: </strong><c:out value="${collaborateur.collab.matricule}"/></p>
            <p><strong>Genre: </strong><c:out value="${collaborateur.collab.genre}"/></p>
            <p><strong>Nom: </strong><c:out value="${collaborateur.collab.nom}"/></p>
            <p><strong>Prénom: </strong><c:out value="${collaborateur.collab.prenom}"/></p>
            <p><strong>Téléphone personnel: </strong><c:out value="${collaborateur.collab.telephone_personnel}"/></p>
            <p><strong>Statut: </strong><c:out value="${collaborateur.collab.statut}"/></p>
            <p><strong>Catégorie: </strong><c:out value="${collaborateur.collab.categorie}"/></p>
            <p><strong>RQTH: </strong><c:out value="${collaborateur.collab.rqth}"/></p>
            <c:if test="${collaborateur.collab.rqth == 'oui'}">
                <p><strong>Date de renouvellement: </strong><c:out value="${collaborateur.collab.date_de_renouvellement}"/></p>
            </c:if>
            <p><strong>Métier: </strong><c:out value="${collaborateur.collab.metier}"/></p>
            
            <p><strong>Prestation: </strong>
            <a href="<c:url value='/prestation?id=${collaborateur.collab.id_prestation}'/>">
                <c:out value="${collaborateur.nomPrestation}"/>
            </a>
        </p>

        </fieldset>  
        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>

</html>
