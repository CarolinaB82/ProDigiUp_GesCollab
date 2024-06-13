<%-- 
    Document   : ResponsableActivite
    Created on : 12 juin 2024, 15:07:02
    Author     : asolanas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Afficher RA</title>
    </head>
    <body>
        <fieldset>
            <legend><strong>Fiche Responsable d'Activité</legend>
            <p><strong>ID: </strong><c:out value="${ra.id}"/></p>
            <p><strong>Matricule : </strong><c:out value="${ra.matricule}"/></p>
            <p><strong>Nom : </strong><c:out value="${ra.nom}"/></p>
            <p><strong>Prénom: </strong><c:out value="${ra.prenom}"/></p>
            <p><strong>Date de Naissance : </strong><c:out value="${ra.date_de_naissance}"/></p>
            <p><strong>Numéro de voie : </strong><c:out value="${ra.numero_voie}"/></p>
            <p><strong>Adresse : </strong><c:out value="${ra.adresse}"/></p>
            <p><strong>Code Postal : </strong><c:out value="${ra.code_postal}"/></p>
            <p><strong>Ville : </strong><c:out value="${ra.ville}"/></p>
            <p><strong>Téléphone Pro: </strong><c:out value="${ra.telephone_professionnel}"/></p>
            <p><strong>Téléphone Perso: </strong><c:out value="${ra.telephone_personnel}"/></p>
        </fieldset>
    </body>
</html>