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
        <title>voir un RA</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <fieldset>
            <legend><strong>Rechercher un Responsable d'Activite</legend>
            <p><strong>Matricule : </strong><c:out value="${ra.matricule}"/></p>
            <p><strong>Nom : </strong><c:out value="${ra.nom}"/></p>
            <p><strong>Prénom: </strong><c:out value="${ra.prenom}"/></p>
            <p><strong>Téléphone Pro: </strong><c:out value="${ra.telephone_professionnel}"/></p>
            <p><strong>Téléphone Perso: </strong><c:out value="${ra.telephone_personnel}"/></p>
        </fieldset>
    </body>
    <%@include file="/WEB-INF/jspf/footer.jsp" %>
</html>
