<%-- 
    Document   : CreerResponsableActivite
    Created on : 12 juin 2024, 15:07:26
    Author     : asolanas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <%-- Définir une variable pour indiquer que ce n'est pas la page d'accueil --%>
        <c:set var="notHome" value="true" />

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <link rel="shortcut icon" href="<c:url value="/assets/img/favicon.png"/>" type="image/x-icon"/>
        <title>Créer RA</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>

        <main>
            <form action="<c:url value="/creer_ra" />" method="post">
                <div>${requestScope.message}</div>
                <div>${requestScope.errMsg}</div>
                <fieldset>
                    <legend>Nouveau responsable d'activité</legend>
                    <div>
                        <label for="matricule">*Matricule</label>
                        <input type="text" id="matricule" name="matricule" 
                               pattern="0?[0-9]{1,5}"
                               maxlength="5" 
                               required
                               title="Veuillez saisir uniquement des chiffres (0-9), 5 maximum" 
                               value="${requestScope.ra != null ? requestScope.ra.matricule : ''}" 
                               class="${not empty requestScope.errors.matricule ? 'error-input' : ''}"
                               >
                        <div class="error">${requestScope.errors.matricule}</div>
                    </div>
                    <div>
                        <label for="nom">*Nom</label>
                        <input type="text" id="nom" name="nom" pattern="[a-zA-ZÀ-ÿ' -]*"
                               required
                               title="Veuillez saisir uniquement des lettres (A-Z, a-z)"
                               value="${requestScope.ra != null ? requestScope.ra.prenom : ''}"
                               class="${not empty requestScope.errors.prenom ? 'error-input' : ''}"
                               >
                        <div class="error">${requestScope.errors.nom}</div>
                    </div>
                    <div>
                        <label for="prenom">*Prénom</label>
                        <input type="text" id="prenom" name="prenom" pattern="[a-zA-ZÀ-ÿ' -]*"
                               required
                               title="Veuillez saisir uniquement des lettres (A-Z, a-z)"
                               value="${requestScope.ra != null ? requestScope.ra.prenom : ''}"
                               class="${not empty requestScope.errors.prenom ? 'error-input' : ''}"

                               >
                        <div class="error">${requestScope.errors.prenom}</div>
                    </div>

                    <div>
                        <label for="mail">Mail </label>
                        <input type="email" id="mail" name="mail"
                               value="${requestScope.ra != null ? requestScope.ra.mail : ''}"
                               class="${not empty requestScope.errors.mail ? 'error-input' : ''}"

                               >
                        <div class="error">${requestScope.errors.mail}</div>
                    </div>

                    <div>
                        <label for="tel_pro">*Téléphone Professionnel</label>
                        <input type="text" id="telephone_professionnel" name="telephone_professionnel"
                               pattern="\\+?[0-9\\(\\)\\- ]{1,30}" 
                               maxlength="30" 
                               required  
                               title="Veuillez saisir 30 caractères maximum"

                               value="${requestScope.ra != null ? requestScope.ra.telephone_professionnel : ''}"
                               class="${not empty requestScope.errors.telephone_professionnel ? 'error-input' : ''}"

                               >
                        <div class="error">${requestScope.errors.telephone_professionnel}</div>
                    </div>
                    <div>
                        <label for="tel_perso">Téléphone Personnel</label>
                        <input type="text" id="telephone_personnel" name="telephone_personnel"
                               pattern="\\+?[0-9\\(\\)\\- ]{1,30}" 
                               maxlength="30" 
                               title="Veuillez saisir 30 caractères maximum"

                               value="${requestScope.ra != null ? requestScope.ra.telephone_personnel : ''}"
                               class="${not empty requestScope.errors.telephone_personnel ? 'error-input' : ''}"
                               >
                        <div class="error">${requestScope.errors.telephone_personnel}</div>
                    </div>
                    <div class="combobox-container">
                        <div class="combobox">

                            <label for="multi-select">Partenaire(s)</label>
                            
                            <select id="partenaire" name="partenaire" multiple>
                                <c:forEach var="partenaire" items="${partenaireList}">
                                    <option value="${partenaire.id}">${partenaire.nom}</option>
                                </c:forEach>
                            </select>
                            <br><br>
                            
                        </div>
                        
                            <div class="combobox">
                                <label for="multi-select">Collaborateur(s)</label>
                               
                                <select id="collaborateur" name="collaborateur" multiple>
                                   <c:forEach var="collaborateur" items="${collaborateurList}">
                                    <option value="${collaborateur.id}">${collaborateur.nom} ${collaborateur.prenom}</option>
                                </c:forEach>
                            </select>
                                <br><br>
                                
                                </form>
                            </div>
                    </div>
                        
                   
                    <div class="button-container">
                        <input  type="submit" value="Valider">
                        <input type="reset" value="Annuler">
                    </div>
                </fieldset>

            </form>
        </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const dropdownToggles = document.querySelectorAll('.dropdown-toggle');
            const dropdownMenus = document.querySelectorAll('.dropdown-menu');
            const checkboxNone = document.getElementById('checkbox-none');

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

            // Fermer le menu déroulant lorsque "Aucun" est coché
            checkboxNone.addEventListener('change', function () {
                if (this.checked) {
                    dropdownToggles.forEach(toggle => {
                        toggle.classList.remove('active');
                        const dropdownMenu = toggle.nextElementSibling;
                        dropdownMenu.style.display = 'none';
                    });
                }
            });
        });
    </script>
</html>
