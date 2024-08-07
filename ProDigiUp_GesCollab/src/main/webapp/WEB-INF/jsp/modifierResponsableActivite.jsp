<%-- 
    Document   : modifierResponsableActivite
    Created on : 4 juil. 2024, 09:37:52
    Author     : cberge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html>
    <head>
        <%-- Définir une variable pour indiquer que ce n'est pas la page d'accueil --%>
        <c:set var="notHome" value="true" />
        <link rel="shortcut icon" href="<c:url value="/assets/img/favicon.png"/>" type="image/x-icon"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <title>Modifier responsable</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>

        <main>
            <form action="<c:url value="/modifier_ra" />" method="post">
                <div>${requestScope.message}</div>
                <div>${requestScope.errMsg}</div>
                <fieldset>
                    <legend>Modifier responsable d'activité</legend>
                    <input type="hidden" name="id" value="${ra.id}" />
                    <div>
                        <label for="matricule">Matricule</label>
                        <input type="text" id="matricule" name="matricule" 
                               pattern="\d{1,5}" 
                               maxlength="5" 
                               required
                               title="Veuillez saisir uniquement des chiffres (0-9)" 
                               value="${requestScope.ra != null ? requestScope.ra.matricule : ''}" 
                               class="${not empty requestScope.errors.matricule ? 'error-input' : ''}"
                               >
                        <div class="error-details-message">${requestScope.errors.matricule}</div>
                    </div>
                    <div>
                        <label for="nom">Nom</label>
                        <input type="text" id="nom" name="nom" pattern="[a-zA-ZÀ-ÿ' -]*"
                               required
                               title="Veuillez saisir uniquement des lettres (A-Z, a-z)"
                               value="${requestScope.ra != null ? requestScope.ra.nom : ''}"
                               class="${not empty requestScope.errors.nom ? 'error-input' : ''}"
                               >
                        <div class="error-details-message">${requestScope.errors.nom}</div>
                    </div>
                    <div>
                        <label for="prenom">Prénom</label>
                        <input type="text" id="prenom" name="prenom" pattern="[a-zA-ZÀ-ÿ' -]*"
                               required
                               title="Veuillez saisir uniquement des lettres (A-Z, a-z)"
                               value="${requestScope.ra != null ? requestScope.ra.prenom : ''}"
                               class="${not empty requestScope.errors.prenom ? 'error-input' : ''}"

                               >
                        <div class="error-details-message">${requestScope.errors.prenom}</div>
                    </div>

                    <div>
                        <label for="mail">Mail </label>
                        <input type="email" id="mail" name="mail"
pattern="(?=.*[a-zA-Z].*[a-zA-Z])[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}"
                               value="${requestScope.ra != null ? requestScope.ra.mail : ''}"
                               class="${not empty requestScope.errors.mail ? 'error-input' : ''}"

                               >
                        <div class="error-details-message">${requestScope.errors.mail}</div>
                    </div>
                   

                    <div>
                        <label for="tel_pro">Téléphone Professionnel</label>
                        <input type="text" id="telephone_professionnel" name="telephone_professionnel"
                               pattern="\\+?[0-9\\(\\)\\- ]{1,30}" 
                               maxlength="30" 
                               required  
                               title="Veuillez saisir 16 caractères maximum"

                               value="${requestScope.ra != null ? requestScope.ra.telephone_professionnel : ''}"
                               class="${not empty requestScope.errors.telephone_professionnel ? 'error-input' : ''}"

                               >
                        <div class="error-details-mesage">${requestScope.errors.telephone_professionnel}</div>
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
                        <div class="error-details-message">${requestScope.errors.telephone_personnel}</div>
                    </div>

                    <label for="partenaire">Partenaire</label>
                    <div class="dropdown-container">
                        <div class="dropdown">
                            <button class="dropdown-toggle" type="button" id="partenaireDropdown" aria-haspopup="true" aria-expanded="false">
                                Sélectionner les partenaires
                            </button>
                            <div class="dropdown-menu" aria-labelledby="partenaireDropdown">
                                <c:forEach var="partenaire" items="${partenaireList}">
                                    <label>
                                        <input type="checkbox" name="partenaire" value="${partenaire.id}" <c:if test="${selectedPartenaires.contains(partenaire.id)}">checked</c:if> />
                                        ${partenaire.nom}
                                    </label><br>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                    <label for="collaborateur">Collaborateur</label>
                    <div class="dropdown-container">
                        <div class="dropdown">
                            <button class="dropdown-toggle" type="button" id="collaborateurDropdown" aria-haspopup="true" aria-expanded="false">
                                Sélectionner les collaborateurs
                            </button>
                            <div class="dropdown-menu" aria-labelledby="collaborateurDropdown">
                                <c:forEach var="collaborateur" items="${collaborateurList}">
                                    <label>
                                        <input type="checkbox" name="collaborateur" value="${collaborateur.id}" <c:if test="${selectedCollaborateurs.contains(collaborateur.id)}">checked</c:if> />
                                        ${collaborateur.nom}
                                    </label><br>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <br><br>
                    <div class="button-container">
                        <input type="submit" value="Enregistrer">
                        <button type="button" onclick="window.location.href = '<c:url value="/liste_collaborateurs"/>'">Annuler</button>
                    </div>
                </fieldset>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

            </form>
        </main>
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
        <script>
    document.querySelector('form').addEventListener('submit', function(event) {
            try {
                var emailInput = document.getElementById('mail');
                var emailValue = emailInput.value;
                var localPart = emailValue.split('@')[0];

                // Validation en JavaScript
                if (!localPart.match(/(?=.*[a-zA-Z].*[a-zA-Z])/)) {
                    alert('L\'adresse e-mail doit contenir au moins deux lettres avant le "@".');
                    // N'annulez pas l'envoi du formulaire ici
                }
            } catch (e) {
                console.error('Erreur de validation:', e);
                // N'annulez pas l'envoi du formulaire en cas d'erreur de script
            }
</script>
    </body>
    <%@include file="/WEB-INF/jspf/footer.jsp" %>
</html>
