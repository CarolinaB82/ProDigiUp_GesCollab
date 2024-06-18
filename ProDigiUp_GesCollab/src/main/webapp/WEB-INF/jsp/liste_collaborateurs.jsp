<%-- 
    Document   : liste_collaborateur
    Created on : 17 juin 2024, 20:13:00
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
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <main>
            <h2>Liste des collaborateurs</h2>

          <table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Matricule</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Statut</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="collaborateur" items="${requestScope.collaborateurs}">
            <tr>
                <td><c:out value="${collaborateur.id}"/></td>
                <td><a href="<c:url value='${collaborateur.matricule}'/>"><c:out value="${collaborateur.matricule}"/></a></td>
                <td><a href="<c:url value='${collaborateur.nom}'/>"><c:out value="${collaborateur.nom}"/></a></td>
                <td><c:out value="${collaborateur.prenom}"/></td>
                <td><c:out value="${collaborateur.statut}"/></td>
              
            </tr>
        </c:forEach>
    </tbody>
</table>

   
</html>
