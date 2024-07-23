<%-- 
    Document   : partenaire
    Created on : 22 juil. 2024, 16:17:33
    Author     : cberge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix= "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html>
    <head>
        <%-- Définir une variable pour indiquer que ce n'est pas la page d'accueil --%>
        <c:set var="notHome" value="true" />
        <link rel="shortcut icon" href="<c:url value="/assets/img/favicon.png"/>" type="image/x-icon"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <title>rechercher partenaire</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            $(document).ready(function () {
                $("#rechercherParNom").keyup(function () {
                    var recherche = $(this).val();
                    if (recherche.length > 0) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/rechercher_partenaire",
                            method: "GET",
                            data: {recherche: recherche, type: "nom"},
                            success: function (data) {
                                console.log("Réponse AJAX pour recherche par nom: ", data);
                                $("#resultats").html(data);
                            },
                            error: function (xhr, status, error) {
                                console.log("Erreur: " + error); // Log pour le débogage
                            }
                        });
                    } else {
                        console.log("Recherche par nom vide.");
                        $("#resultats").html("");
                    }
                });
                $("#rechercherParVille").keyup(function () {
                    var recherche = $(this).val();
                    if (recherche.length > 0) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/rechercher_partenaire",
                            method: "GET",
                            data: {recherche: recherche, type: "ville"},
                            success: function (data) {
                                console.log("Réponse AJAX pour recherche par ville: ", data);
                                $("#resultats").html(data);
                            },
                            error: function (xhr, status, error) {
                                console.log("Erreur: " + error); // Log pour le débogage
                            }
                        });
                    } else {
                        console.log("Recherche par ville vide.");
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
                <legend>Rechercher un Partenaire</legend>

                <form>
                    <div>
                        <label for="rechercherParNom">Par nom :</label>
                        <input type="text" id="rechercherParNom" name="rechercherParNom" autocomplete="off">
                    </div>
                    <div id="suggestionsNom"></div>
                </form>
                <br>
                <form>
                    <div>
                        <label for="rechercherParVille">Par ville :</label>
                        <input type="text" id="rechercherParVille" name="rechercherParVille" autocomplete="off">
                    </div>
                    <div id="suggestionsVille"></div>
                </form>
                
            </fieldset>


            <fieldset>
                <legend>Résultats de recherche</legend>
                <div id="resultats" class="neutral-links">
                    <c:if test="${not empty erreur}">
                        <p style="color: red;"><c:out value="${erreur}" /></p>
                    </c:if>
                    <c:if test="${not empty resultats}">
                        <table class="custom-table">
                            <thead>
                                <tr>
                                    <th>🔗Nom</th>
                                    <th>🔗Ville</th>
                                    
-
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="part" items="${resultats}">
                                    <tr>
                                        <td><a href="${pageContext.request.contextPath}/afficherPartenaire?id_partenaire=${partenaire.id}">${partenaire.nom}</a></td>
                                        <td><a href="${pageContext.request.contextPath}/afficherPartenaire?id_partenaire=${partenaire.id}">${partenaire.ville}</a></td>
                                        
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${empty resultats && empty suggestionsNom && empty suggestionsVille}">
                        <p>Aucun responsable trouvé pour la recherche : ${param.recherche}</p>
                    </c:if>
                </div>

            </fieldset>
        </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>

</html>
      