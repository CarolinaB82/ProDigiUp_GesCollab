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
                <h1>Recherche de Collaborateur</h1>
    <form action="rechercher" method="get">
        <label for="rechercher">Rechercher (Nom, Prénom, Matricule) :</label>
        <input type="text" name="recherche">
        <button type="submit">Rechercher</button>
    </form>
            </fieldset>
            <h1>Résultats de recherche</h1>
        
        <table border="1">
            <thead class="tableaux">
                <tr >
                   
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
                       
                        <td><c:out value="${collaborateur.matricule}" /></td>
                        <td><c:out value="${collaborateur.nom}" /></td>
                        <td><c:out value="${collaborateur.prenom}" /></td>
                        <td><c:out value="${collaborateur.statut}" /></td>
                        <td><c:out value="${collaborateur.metier}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
            
        </main>
    </body>
    <%@include file="/WEB-INF/jspf/footer.jsp" %>
</html>

