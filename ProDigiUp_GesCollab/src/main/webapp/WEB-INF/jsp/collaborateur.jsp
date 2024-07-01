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
        <%-- Définir une variable pour indiquer que ce n'est pas la page d'accueil --%>
        <c:set var="notHome" value="true" />

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <title>rechercher collaborateur</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            $(document).ready(function () {
                $("#rechercherParNom").keyup(function () {
                    var recherche = $(this).val();
                    if (recherche.length > 0) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/rechercher",
                            method: "GET",
                            data: {recherche: recherche, type: "nom"},
                            success: function (data) {
                                $("#resultats").html(data);
                            }
                        });
                    } else {
                        $("#resultats").html("");
                    }
                });

                $("#rechercherParPrenom").keyup(function () {
                    var recherche = $(this).val();
                    if (recherche.length > 0) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/rechercher",
                            method: "GET",
                            data: {recherche: recherche, type: "prenom"},
                            success: function (data) {
                                $("#resultats").html(data);
                            }
                        });
                    } else {
                        $("#resultats").html("");
                    }
                });

                $("#rechercherParMatricule").keyup(function () {
                    var recherche = $(this).val();
                    if (recherche.length > 0) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/rechercher",
                            method: "GET",
                            data: {recherche: recherche, type: "matricule"},
                            success: function (data) {
                                $("#resultats").html(data);
                            }
                        });
                    } else {
                        $("#resultats").html("");
                    }
                });
            });
        </script>

    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <main>
            <fieldset>
                <legend>Recherche de Collaborateur</legend>
                <form>
                    <div>
                        <label for="rechercherParNom">Par nom :</label>
                        <input type="text" id="rechercherParNom" name="rechercherParNom" autocomplete="off">
                    </div>
                    <div id="suggestionsNom"></div>
                </form>

                <form>
                    <div>
                        <label for="rechercherParPrenom">Par prénom :</label>
                        <input type="text" id="rechercherParPrenom" name="rechercherParPrenom" autocomplete="off">
                    </div>
                    <div id="suggestionsPrenom"></div>
                </form>

                <form>
                    <div>
                        <label for="rechercherParMatricule">Par matricule :</label>
                        <input type="text" id="rechercherParMatricule" name="rechercherParMatricule" autocomplete="off">
                    </div>
                    <div id="suggestionsMatricule"></div>
                </form>
            </fieldset>
            <fieldset>
                <legend>Résultats de recherche</legend>
                <%-- Code pour afficher les résultats de recherche --%>
                <div id="resultats">
                    <c:if test="${not empty resultats}">
                        <table class="custom-table">
                            <thead>
                                <tr>
                                    <th>Matricule</th>
                                    <th>Nom</th>
                                    <th>Prénom</th>
                                    <th>Statut</th>
                                    <th>Métier</th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="collaborateur" items="${resultats}">
                                    <tr>
                                        <td><a href="${pageContext.request.contextPath}/afficherCollaborateur?id=${collaborateur.id}">${collaborateur.matricule}</a></td>
                                        <td><a href="${pageContext.request.contextPath}/afficherCollaborateur?id=${collaborateur.id}">${collaborateur.nom}</a></td>
                                        <td>${collaborateur.prenom}</td>
                                        <td>${collaborateur.statut}</td>
                                        <td>${collaborateur.metier}</td>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${empty resultats && empty suggestionsNom && empty suggestionsPrenom && empty suggestionsMatricule}">
                        <p>Aucun collaborateur trouvé pour la recherche : ${param.recherche}</p>
                    </c:if>
                </div>
            </fieldset>
        </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>
</html>
