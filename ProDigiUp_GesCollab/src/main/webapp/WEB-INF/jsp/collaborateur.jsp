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
        <title>rechercher collaborateur</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <main>
            <fieldset>
                <legend>Recherche de Collaborateur</legend>
                <form action="rechercher" method="get">
                    <div>
                        <label for="nom">Entrez un nom :</label>
                        <input type="text" id="nom" name="nom">
                    </div>
                    <div>
                        <label for="prenom">ou un prénom :</label>
                        <input type="text" id="prenom" name="prenom">
                    </div>
                    <div>
                        <label for="matricule">ou un matricule :</label>
                        <input type="text" id="matricule" name="matricule">
                    </div>
                    <div>
                        <button type="submit">Rechercher</button>
                    </div>
                </form>
            </fieldset>


            <fieldset>
                <legend>Resultats de recherche</legend>

                <c:if test="${not empty erreur}">
                    <p style="color: red;"><c:out value="${erreur}" /></p>
                </c:if>
                <c:if test="${not empty resultats}">
                    <table class="custom-table">
                        <thead>
                            <tr>
                                <th>Matricule</th>
                                <th>Nom</th>
                                <th>Prénom</th>
                                <th>Métier</th>
                                <th>Statut</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="collaborateur" items="${resultats}">
                                <tr>
                                    <td><a href="${pageContext.request.contextPath}/collaborateur?id=${collaborateur.id}">${collaborateur.matricule}</a></td>
                                    <td><a href="${pageContext.request.contextPath}/collaborateur?id=${collaborateur.id}">${collaborateur.nom}</a></td>
                                    <td>${collaborateur.prenom}</td>
                                    <td>${collaborateur.metier}</td>
                                    <td>${collaborateur.statut}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${empty resultats && empty erreur}">
                    <p>Aucun collaborateur trouvé pour la recherche : <c:out value="${param.recherche}" /></p>
                </c:if>
            </fieldset>
        </main>
    </body>
    <%@include file="/WEB-INF/jspf/footer.jsp" %>
</html>