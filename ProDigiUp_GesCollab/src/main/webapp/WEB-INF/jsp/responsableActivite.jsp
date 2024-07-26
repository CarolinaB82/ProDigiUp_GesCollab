<%-- 
    Document   : responsableActivite
    Created on : 24 juin 2024, 17:04:41
    Author     : asolanas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <c:set var="notHome" value="true"/>
        <link rel="shortcut icon" href="<c:url value="/assets/img/favicon.png"/>" type="image/x-icon"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <title>Rechercher responsable</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            $(document).ready(function () {
                $("#rechercherRaParMatricule").keyup(function () {
                    var recherche = $(this).val();
                    if (recherche.length > 0) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/rechercher_ra",
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
                $("#rechercherRaParNom").keyup(function () {
                    var recherche = $(this).val();
                    if (recherche.length > 0) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/rechercher_ra",
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

                $("#rechercherRaParPrenom").keyup(function () {
                    var recherche = $(this).val();
                    if (recherche.length > 0) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/rechercher_ra",
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
            });
        </script>
    </head>

    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <main>
            <fieldset>
                <legend>Rechercher un Responsable d'activité</legend>

                <form>
                    <div>
                        <label for="rechercherRaParNom">Par nom :</label>
                        <input type="text" id="rechercherRaParNom" name="rechercherRaParNom" autocomplete="off">
                    </div>
                    <div id="suggestionsNom"></div>
                </form>
                <br>
                <form>
                    <div>
                        <label for="rechercherRaParPrenom">Par prénom :</label>
                        <input type="text" id="rechercherRaParPrenom" name="rechercherRaParPrenom" autocomplete="off">
                    </div>
                    <div id="suggestionsPrenom"></div>
                </form>
                <br>
                <form>
                    <div>
                        <label for="rechercherRaParMatricule">Par matricule :</label>
                        <input type="text" id="rechercherRaParMatricule" name="rechercherRaParMatricule" autocomplete="off">
                    </div>
                    <div id="suggestionsMatricule"></div>
                </form>
            </fieldset>

            <fieldset>
                <legend>Résultats de recherche</legend>
                <div id="resultats" class="neutral-links">
                    <c:if test="${not empty erreur}">
                        <p style="color: red;"><c:out value="${erreur}"/></p>
                    </c:if>
                    <c:if test="${rechercheEffectuee}">
                        <c:choose>
                            <c:when test="${not empty resultats}">
                                <table class="custom-table">
                                    <thead>
                                        <tr>
                                            <th>🔗Matricule</th>
                                            <th>🔗Nom</th>
                                            <th>Prénom</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="ra" items="${resultats}">
                                            <tr>
                                                <td><a href="${pageContext.request.contextPath}/afficherResponsableActivite?id_ra=${ra.id}">${ra.matricule}</a></td>
                                                <td><a href="${pageContext.request.contextPath}/afficherResponsableActivite?id_ra=${ra.id}">${ra.nom}</a></td>
                                                <td>${ra.prenom}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <p>Aucun responsable trouvé pour la recherche : ${recherche}</p>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </div>
            </fieldset>
        </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>
</html>
