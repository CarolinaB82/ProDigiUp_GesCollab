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
        <%-- Définir une variable pour indiquer que ce n'est pas la page d'accueil --%>
        <c:set var="notHome" value="true" />

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>voir un RA</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <main>
            <fieldset>
                <legend><strong>Fiche Responsable d'Activite</legend>
                <p><strong>Matricule : </strong><c:out value="${ra.matricule}"/></p>
                <p><strong>Nom : </strong><c:out value="${ra.nom}"/></p>
                <p><strong>Prénom: </strong><c:out value="${ra.prenom}"/></p>
                <p><strong>Mail : </strong><c:out value="${ra.mail}"/></p>
                <p><strong>Téléphone Pro: </strong><c:out value="${ra.telephone_professionnel}"/></p>
                <p><strong>Téléphone Perso: </strong><c:out value="${ra.telephone_personnel}"/></p>
            </fieldset>
        </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>

</html>
