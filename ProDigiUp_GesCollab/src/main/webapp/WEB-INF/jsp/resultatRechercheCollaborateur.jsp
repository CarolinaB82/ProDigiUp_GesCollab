<%-- 
    Document   : afficherRechercheCollaborateur
    Created on : 18 juin 2024, 15:22:10
    Author     : asolanas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Résultats de recherche</title>
    </head>
    <body>
         <%@include file="/WEB-INF/jspf/header.jsp" %>
        <h1>Résultats de recherche</h1>
        
        <table border="1">
            <thead class="tableaux">
                <tr >
                    <th>ID</th>
                    <th>Matricule</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Statut</th>
                    <th>Métier</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="collaborateur" items="${collaborateurs}">
                    <tr>
                        <td><c:out value="${collaborateur.id}" /></td>
                        <td><c:out value="${collaborateur.matricule}" /></td>
                        <td><c:out value="${collaborateur.nom}" /></td>
                        <td><c:out value="${collaborateur.prenom}" /></td>
                        <td><c:out value="${collaborateur.statut}" /></td>
                        <td><c:out value="${collaborateur.metier}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
    <%@include file="/WEB-INF/jspf/footer.jsp" %>
</html>


