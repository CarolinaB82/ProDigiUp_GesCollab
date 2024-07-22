<%-- 
    Document   : modifierPartenaire
    Created on : 4 juil. 2024, 10:32:21
    Author     : cberge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix= "c" %>



<!DOCTYPE html>
<html>
    <head>
        <%-- Définir une variable pour indiquer que ce n'est pas la page d'accueil --%>
        <c:set var="notHome" value="true" />
        <link rel="shortcut icon" href="<c:url value="/assets/img/favicon.png"/>" type="image/x-icon"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <title>Modifier partenaire</title>
    </head>
    <body> 
        <%@include file="/WEB-INF/jspf/header.jsp" %>

        <main>
            <form action="/ProDigiUp_GesCollab/modifier_partenaire" method="post">                  
                <div>${requestScope.message}</div>
                <div class="error-message">${requestScope.errorMsg}</div>
                <fieldset>
                    <legend>Modifier Partenaire</legend>
                    <input type="hidden" name="id" value="${partenaire.id}" />


                    <div>
                        <label for="nom">Nom Partenaire</label>
                        <!-- pour garder en memoire les champs deja remplis si erreur quelque part-->

                        <input type="text" id="nom" name="nom"  
                               pattern="[a-zA-ZÀ-ÿ' ]*"
                               required
                               title="Veuillez saisir uniquement des lettres (A-Z, a-z)"
                               value="${requestScope.partenaire != null ? requestScope.partenaire.nom : ''}" 
                               class="${not empty requestScope.errors.nom ? 'error-input' : ''}"

                               >
                        <div class="error-details-message">${requestScope.errors.nom}</div>
                    </div>
                    <br>
                    <div>
                        <label for="numero_voie">Numero de voie</label>
                        <input type="text" id="numero_voie" name="numero_voie"
                               pattern="[0-9]*"
                               required
                               title="Veuillez saisir uniquement des chiffres (0-9)" 

                               value="${requestScope.partenaire != null ? requestScope.partenaire.numero_voie : ''}"
                               class="${not empty requestScope.errors.numero_voie ? 'error-input' : ''}"

                               >
                        <div class="error-details-message">${requestScope.errors.numero_voie}</div>
                    </div>
                    <br>
                    <div>
                        <label for="adresse">Adresse</label>
                        <input type="text" id="adresse" name="adresse"
                               pattern="[a-zA-ZÀ-ÿ' ]*"
                               required
                               title="Veuillez saisir uniquement des lettres (A-Z, a-z)"
                               value="${requestScope.partenaire != null ? requestScope.partenaire.adresse : ''}"
                               class="${not empty requestScope.errors.adresse ? 'error-input' : ''}"

                               >
                        <div class="error-details-message">${requestScope.errors.adresse}</div>
                    </div>
                    <br>
                    <div>
                        <label for="code_postal">Code Postal</label>
                        <input type="text" id="code_postal" name="code_postal"
                               pattern="\d{5}" 
                               maxlength="5" 
                               required  
                               title="Veuillez saisir exactement 5 chiffres"

                               value="${requestScope.partenaire != null ? requestScope.partenaire.code_postal : ''}"
                               class="${not empty requestScope.errors.code_postal ? 'error-input' : ''}"

                               >
                        <div class="error-details-message">${requestScope.errors.code_postal}</div>
                    </div>
                    <br>
                    <div>
                        <label for="ville">Ville</label>
                        <input type="text" id="ville" name="ville"
                               pattern="[a-zA-ZÀ-ÿ' ]*"
                               required
                               title="Veuillez saisir uniquement des lettres (A-Z, a-z)"
                               value="${requestScope.partenaire != null ? requestScope.partenaire.ville : ''}"
                               class="${not empty requestScope.errors.ville ? 'error-input' : ''}"

                               >
                        <div class="error-details-message">${requestScope.errors.ville}</div>
                    </div>
                    <br>

                    <label for="responsable">Responsable activité</label>
                    <div class="dropdown-container">
                        <div class="dropdown">
                            <button class="dropdown-toggle" type="button" id="responsableDropdown" aria-haspopup="true" aria-expanded="false">
                                Sélectionner les responsables
                            </button>
                            <div class="dropdown-menu" aria-labelledby="responsableDropdown">
                                <c:forEach var="responsable" items="${responsableActiviteList}">
                                    <label>
                                        <input type="checkbox" name="responsable" value="${responsable.id}" <c:if test="${selectedResponsables.contains(responsable.id)}">checked</c:if> />
                                        ${responsable.nom}
                                    </label><br>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <br><br>

                    <div class="button-container">
                        <input type="submit" value="Valider">
                        <button type="button" onclick="redirectToList()">Annuler</button>
                    </div>
                </fieldset>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

            </form>

        </main>
    </body>
    <%@include file="/WEB-INF/jspf/footer.jsp" %>
     <script>
                            function redirectToList() {
                                window.location.href = '<c:url value="/liste_collaborateurs"/>';
                            }

                            function validateForm() {
                                var matricule = document.getElementById('matricule').value;
                                var responsables = document.getElementById('responsable').options;
                                for (var i = 0; i < responsables.length; i++) {
                                    if (responsables[i].selected && responsables[i].value === matricule) {
                                        alert("L'ID du collaborateur ne peut pas être égal à l'ID du responsable d'activité.");
                                        return false;
                                    }
                                }
                                return true;
                            }

                            document.addEventListener('DOMContentLoaded', function () {
                                const dropdownToggles = document.querySelectorAll('.dropdown-toggle');

                                dropdownToggles.forEach(toggle => {
                                    toggle.addEventListener('click', function (event) {
                                        event.stopPropagation(); // Empêche la fermeture du menu quand on clique sur le bouton de sélection
                                        this.classList.toggle('active');
                                        const dropdownMenu = this.nextElementSibling;
                                        if (dropdownMenu.style.display === 'block') {
                                            dropdownMenu.style.display = 'none';
                                        } else {
                                            dropdownMenu.style.display = 'block';
                                        }
                                    });
                                });

                                // Empêche la fermeture du menu lorsque l'on clique sur une option
                                const dropdownMenus = document.querySelectorAll('.dropdown-menu');
                                dropdownMenus.forEach(menu => {
                                    menu.addEventListener('click', function (event) {
                                        event.stopPropagation();
                                    });
                                });

                                // Fermer le menu déroulant si on clique en dehors
                                window.addEventListener('click', function () {
                                    dropdownToggles.forEach(toggle => {
                                        toggle.classList.remove('active');
                                        const dropdownMenu = toggle.nextElementSibling;
                                        dropdownMenu.style.display = 'none';
                                    });
                                });
                            });

        </script>
</html>
