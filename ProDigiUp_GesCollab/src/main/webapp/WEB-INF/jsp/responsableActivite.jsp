<%-- 
    Document   : responsableActivite
    Created on : 24 juin 2024, 17:04:41
    Author     : asolanas
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
        <title>Rechercher responsable</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
                                console.log("Réponse AJAX pour recherche par matricule: ", data);
                                $("#resultats").html(data);
                            },
                            error: function (xhr, status, error) {
                                console.log("Erreur: " + error); // Log pour le débogage
                            }
                        });
                    } else {
                        console.log("Recherche par matricule vide.");
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

                $("#rechercherRaParPrenom").keyup(function () {
                    var recherche = $(this).val();
                    if (recherche.length > 0) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/rechercher_ra",
                            method: "GET",
                            data: {recherche: recherche, type: "prenom"},
                            success: function (data) {
                                console.log("Réponse AJAX pour recherche par prénom: ", data);
                                $("#resultats").html(data);
                            },
                            error: function (xhr, status, error) {
                                console.log("Erreur: " + error); // Log pour le débogage
                            }
                        });
                    } else {
                        console.log("Recherche par prénom vide.");
                        $("#resultats").html("");
                    }
                });


            });
        </script>
       
      
       
       
       
        <script>
            function checkFields() {

                var rechercherRaParNom = document.getElementById("rechercherRaParNom").value;
                var rechercherRaParPrenom = document.getElementById("rechercherRaParPrenom").value;
                var rechercherRaParMatricule = document.getElementById("rechercherRaParMatricule").value;
                var resultFieldset = document.getElementById("resultFieldset");

                if (rechercherRaParNom.trim() !== "" || rechercherRaParPrenom.trim() !== "" || rechercherRaParMatricule.trim() !== "" || ${not empty erreur}) {
                    resultFieldset.style.display = "block";
                } else {
                    resultFieldset.style.display = "none";
                }
            }

            document.addEventListener("DOMContentLoaded", function () {
                // Initialize the resultFieldset as hidden
                checkFields();
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
                        <input type="text" id="rechercherRaParNom" name="rechercherRaParNom" autocomplete="off" oninput="checkFields()">
                    </div>
                    <div id="suggestionsNom"></div>
                </form>
                <br>
                <form>
                    <div>
                        <label for="rechercherRaParPrenom">Par prénom :</label>
                        <input type="text" id="rechercherRaParPrenom" name="rechercherRaParPrenom" autocomplete="off" oninput="checkFields()">
                    </div>
                    <div id="suggestionsPrenom"></div>
                </form>
                <br>
                <form>
                    <div>
                        <label for="rechercherRaParMatricule">Par matricule :</label>
                        <input type="text" id="rechercherRaParMatricule" name="rechercherRaParMatricule" autocomplete="off" oninput="checkFields()">
                    </div>
                    <div id="suggestionsMatricule"></div>
                </form>

            </fieldset>


            <fieldset id="resultFieldset" style="display:none;">
                <legend>Résultats de recherche</legend>
                <div id="resultats" class="neutral-links">
                    
                    
                 <c:if test="${not empty erreur}">
                        <p style="color: red;"><c:out value="${erreur}" /></p>
                    </c:if>
                   
                    
                    <c:if test="${not empty resultats}">
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
                                        <!--<td>{ra.matricule}</td>
                                        <td>{ra.nom}</td>-->
                                        <td>${ra.prenom}</td>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${empty resultats && empty suggestionsNom && empty suggestionsPrenom && empty suggestionsMatricule}">
                        <p>Aucun responsable trouvé pour la recherche  ${param.recherche}</p>
                    </c:if>
                </div>

            </fieldset>
        </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>

</html>
