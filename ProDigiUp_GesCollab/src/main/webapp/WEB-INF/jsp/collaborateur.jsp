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
        </head>
        
    </head>
    <body>
        <main>
            <fieldset>
            <legend>Fiche Collaborateur</legend>

            <p><strong>ID: </strong><c:out value="${collaborateur.id}"/></p>
            <p><strong>Matricule: </strong><c:out value="${collaborateur.matricule}"/></p>
            <p><strong>Genre: </strong><c:out value="${collaborateur.genre}"/></p>
            <p><strong>Nom: </strong><c:out value="${collaborateur.nom}"/></p>
            <p><strong>Prénom: </strong><c:out value="${collaborateur.prenom}"/></p>
            <p><strong>Date de naissance: </strong><c:out value="${collaborateur.date_de_naissance}"/></p>
            <p><strong>Numéro de voie: </strong><c:out value="${collaborateur.numero_voie}"/></p>
            <p><strong>Adresse: </strong><c:out value="${collaborateur.adresse}"/></p>
            <p><strong>Code postal: </strong><c:out value="${collaborateur.code_postal}"/></p>
            <p><strong>Ville: </strong><c:out value="${collaborateur.ville}"/></p>
            <p><strong>Téléphone personnel: </strong><c:out value="${collaborateur.telephone_personnel}"/></p>
            <p><strong>Statut: </strong><c:out value="${collaborateur.statut}"/></p>
            <p><strong>Catégorie: </strong><c:out value="${collaborateur.categorie}"/></p>
            <p><strong>RQTH: </strong><c:out value="${collaborateur.rqth}"/></p>
            <p><strong>Métier: </strong><c:out value="${collaborateur.metier}"/></p>
        
            </fieldset>       
        </main>
    </body>
</html>

        