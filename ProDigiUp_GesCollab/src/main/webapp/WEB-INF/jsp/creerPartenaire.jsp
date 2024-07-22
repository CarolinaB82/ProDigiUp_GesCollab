<%-- 
    Document   : creerPartenaire
    Created on : 27 juin 2024, 10:55:08
    Author     : cberge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix= "c" %>


<!DOCTYPE html>
<html>
    <head>
        <%-- Définir une variable pour indiquer que ce n'est pas la page d'accueil --%>
        <c:set var="notHome" value="true" />

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <link rel="shortcut icon" href="<c:url value="/assets/img/favicon.png"/>" type="image/x-icon"/>
        <title>créer partenaire</title>
    </head>
    <body> 
        <%@include file="/WEB-INF/jspf/header.jsp" %>

        <main>
            <form action="/ProDigiUp_GesCollab/creer_partenaire" method="post">                  
                <div>${requestScope.message}</div>
                <div class="error-message">${requestScope.errorMsg}</div>
                <fieldset>
                    <legend>Nouveau Partenaire</legend>


                    <div>
                        <label for="nom">*Nom Partenaire</label>
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
                        <label for="numero_voie">*Numero de voie</label>
                        <input type="text" id="numero_voie" name="numero_voie"
                               pattern="\\+?[0-9\\(\\)\\- ]{1,30}" 
                               required
                               title="Veuillez saisir uniquement des chiffres (0-9)" 

                               value="${requestScope.partenaire != null ? requestScope.partenaire.numero_voie : ''}"
                               class="${not empty requestScope.errors.numero_voie ? 'error-input' : ''}"

                               >
                        <div class="error-details-message">${requestScope.errors.numero_voie}</div>
                    </div>
                    <br>
                    <div>
                        <label for="adresse">*Adresse</label>
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
                        <label for="code_postal">*Code Postal</label>
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
                        <label for="ville">*Ville</label>
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

                    <div class="combobox-container">
                        <div class="combobox">
                            <label for="responsableDropdown">Responsable(s) activité(s)</label>
                            <div class="dropdown-container">
                                <div class="dropdown">
                                    <button class="dropdown-toggle" type="button" id="responsableDropdown" aria-haspopup="true" aria-expanded="false">
                                        Sélectionner les responsables d'activité
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="responsableDropdown">
                                        <label>
                                            <input type="checkbox" name="responsable" value="" onclick="handleNoneOption(this, 'responsable')"/>
                                            Aucun
                                        </label><br>
                                        <c:forEach var="responsable" items="${responsableActiviteList}">
                                            <label>
                                                <input type="checkbox" name="responsable" value="${responsable.id}" />
                                                ${responsable.nom}
                                            </label><br>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <br><br>

                    </div>
                    <div class="button-container">
                        <input  type="submit" value="Valider">
                        <input type="reset" value="Annuler">
                    </div>                
                </fieldset>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

            </form>
        </main>
    </body>
    <%@include file="/WEB-INF/jspf/footer.jsp" %>
    <script>
                                                    document.addEventListener('DOMContentLoaded', function () {
                                                        const dropdownToggles = document.querySelectorAll('.dropdown-toggle');

                                                        dropdownToggles.forEach(toggle => {
                                                            toggle.addEventListener('click', function () {
                                                                this.classList.toggle('active');
                                                                const menu = this.nextElementSibling;
                                                                if (menu.style.display === 'block') {
                                                                    menu.style.display = 'none';
                                                                } else {
                                                                    menu.style.display = 'block';
                                                                }
                                                            });
                                                        });

                                                        // Function to handle the "Aucun" option
                                                        window.handleNoneOption = function (checkbox, name) {
                                                            const checkboxes = document.querySelectorAll(`input[name="${name}"]`);
                                                            checkboxes.forEach(cb => {
                                                                if (cb !== checkbox) {
                                                                    cb.checked = false;
                                                                }
                                                            });
                                                        };

                                                        // Prevent dropdown from closing on item click
                                                        const dropdownMenus = document.querySelectorAll('.dropdown-menu');
                                                        dropdownMenus.forEach(menu => {
                                                            menu.addEventListener('click', function (event) {
                                                                event.stopPropagation();
                                                            });
                                                        });

                                                        // Close the dropdown if the user clicks outside of it
                                                        window.addEventListener('click', function (event) {
                                                            dropdownToggles.forEach(toggle => {
                                                                const menu = toggle.nextElementSibling;
                                                                if (event.target !== toggle && !toggle.contains(event.target)) {
                                                                    if (menu.style.display === 'block') {
                                                                        menu.style.display = 'none';
                                                                        toggle.classList.remove('active');
                                                                    }
                                                                }
                                                            });
                                                        });
                                                    });
    </script>

</html>
