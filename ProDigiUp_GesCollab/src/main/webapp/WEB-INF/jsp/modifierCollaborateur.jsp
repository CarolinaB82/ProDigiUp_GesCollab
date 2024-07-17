<%-- 
    Document   : modifierCollaborateur
    Created on : 3 juil. 2024, 10:07:22
    Author     : cberge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix= "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%-- Définir une variable pour indiquer que ce n'est pas la page d'accueil --%>
<c:set var="notHome" value="true" />
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
    <title>Modifier Collaborateur</title>
    <link rel="shortcut icon" href="<c:url value="/assets/img/favicon.png"/>" type="image/x-icon"/>
</head>
<body>
    <%@include file="/WEB-INF/jspf/header.jsp" %>
    <main>
        <form action="/ProDigiUp_GesCollab/modifier_collaborateur" method="post">
            <div>${requestScope.message}</div>
            <div>${requestScope.errMsg}</div>
            <div class="error-message">${requestScope.errorMsg}</div>
            <fieldset>

                <legend>Modification Collaborateur</legend>
                <input type="hidden" name="id" value="${collaborateur.id}" />
                <div>
                    <label for="matricule">Matricule</label>
                    <!-- pour garder en memoire les champs deja remplis si erreur quelque part-->
                    <input type="text" id="matricule" name="matricule" 
                           pattern="\d{1,5}" 
                           maxlength="5" 
                           required
                           title="Veuillez saisir uniquement des chiffres (0-9)" 
                           value="${requestScope.collaborateur != null ? requestScope.collaborateur.matricule : ''}" 
                           class="${not empty requestScope.errors.matricule ? 'error-input' : ''}"

                           >
                    <div class="error-details-message">${requestScope.errors.matricule}</div>
                </div>
                <br>
                <div>
                    <label for="nom">Nom</label>
                    <input type="text" id="nom" name="nom"
                           pattern="[a-zA-ZÀ-ÿ' -]*"
                           required
                           title="Veuillez saisir uniquement des lettres (A-Z, a-z)"
                           value="${requestScope.collaborateur != null ? requestScope.collaborateur.nom : ''}"
                           class="${not empty requestScope.errors.nom ? 'error-input' : ''}"

                           >
                    <div class="error-details-message">${requestScope.errors.nom}</div>
                </div>
                <br>
                <div>
                    <label for="prenom">Prénom</label>
                    <input type="text" id="prenom" name="prenom"
                           pattern="[a-zA-ZÀ-ÿ' -]*"
                           required
                           title="Veuillez saisir uniquement des lettres (A-Z, a-z)"
                           value="${requestScope.collaborateur != null ? requestScope.collaborateur.prenom : ''}"
                           class="${not empty requestScope.errors.prenom ? 'error-input' : ''}"

                           >
                    <div class="error-details-message">${requestScope.errors.prenom}</div>
                </div>
                <br>
                <div>
                    <label for="mail_1">Mail 1</label>
                    <input type="email" id="mail_1" name="mail_1"
                           value="${requestScope.collaborateur != null ? requestScope.collaborateur.mail_1 : ''}"
                           class="${not empty requestScope.errors.mail_1 ? 'error-input' : ''}"

                           >
                    <div class="error-details-message">${requestScope.errors.mail_1}</div>
                </div>
                <div>
                    <label for="mail_2">Mail 2</label>
                    <input type="email" id="mail_2" name="mail_2"
                           value="${requestScope.collaborateur != null ? requestScope.collaborateur.mail_2 : ''}"
                           class="${not empty requestScope.errors.mail_2 ? 'error-input' : ''}"

                           >
                    <div class="error-details-message">${requestScope.errors.mail_2}</div>
                </div>

                <div>
                    <label for="telephone_personnel">Téléphone personnel</label>
                    <input type="text" id="telephone_personnel" name="telephone_personnel"
                           pattern="\\+?[0-9\\(\\)\\- ]{1,30}" 
                           maxlength="30" 
                           title="Veuillez saisir 16 caractères maximum"
                           value="${requestScope.collaborateur != null ? requestScope.collaborateur.telephone_personnel : ''}"
                           class="${not empty requestScope.errors.telephone_personnel ? 'error-input' : ''}"
                           >
                    <div class="error-details-message">${requestScope.errors.telephone_personnel}</div>
                </div>
                <br>
                <div>
                    <label for="metier">Métier</label>
                    <input type="text" id="metier" name="metier"
                           pattern="[a-zA-ZÀ-ÿ' -]*"
                           required
                           title="Veuillez saisir uniquement des lettres (A-Z, a-z)"
                           value="${requestScope.collaborateur != null ? requestScope.collaborateur.metier : ''}"
                           class="${not empty requestScope.errors.metier ? 'error-input' : ''}"
                           >
                    <div class="error-details-message">${requestScope.errors.metier}</div>
                </div>  
                <br>
                <div class="dropdown-container">
                    <label for="statut">Statut</label>
                    <div class="dropdown">
                        <button type="button" class="dropdown-toggle">Sélectionner Statut</button>
                        <div class="dropdown-menu">
                            <label>
                                <input type="checkbox" name="statut" value="CDD" ${requestScope.collaborateur != null && requestScope.collaborateur.statut == 'CDD' ? 'checked' : ''}>
                                CDD
                            </label>
                            <label>
                                <input type="checkbox" name="statut" value="CDI" ${requestScope.collaborateur != null && requestScope.collaborateur.statut == 'CDI' ? 'checked' : ''}>
                                CDI
                            </label>
                            <label>
                                <input type="checkbox" name="statut" value="CDD Tremplin" ${requestScope.collaborateur != null && requestScope.collaborateur.statut == 'CDD Tremplin' ? 'checked' : ''}>
                                CDD Tremplin
                            </label>
                            <label>
                                <input type="checkbox" name="statut" value="Stage" ${requestScope.collaborateur != null && requestScope.collaborateur.statut == 'Stage' ? 'checked' : ''}>
                                Stage
                            </label>
                        </div>
                    </div>
                </div>

                <div class="dropdown-container">
                    <label for="categorie">Catégorie</label>
                    <div class="dropdown">
                        <button type="button" class="dropdown-toggle">Sélectionner Catégorie</button>
                        <div class="dropdown-menu">
                            <label>
                                <input type="checkbox" name="categorie" value="ETAM" ${requestScope.collaborateur != null && requestScope.collaborateur.categorie == 'ETAM' ? 'checked' : ''}>
                                ETAM
                            </label>
                            <label>
                                <input type="checkbox" name="categorie" value="Cadre" ${requestScope.collaborateur != null && requestScope.collaborateur.categorie == 'Cadre' ? 'checked' : ''}>
                                Cadre
                            </label>
                        </div>
                    </div>
                </div>

                <div class="dropdown-container">
                    <label for="genre">Genre</label>
                    <div class="dropdown">
                        <button type="button" class="dropdown-toggle">Sélectionner Genre</button>
                        <div class="dropdown-menu">
                            <label>
                                <input type="checkbox" name="genre" value="Madame" ${requestScope.collaborateur != null && requestScope.collaborateur.genre == 'Madame' ? 'checked' : ''}>
                                Madame
                            </label>
                            <label>
                                <input type="checkbox" name="genre" value="Monsieur" ${requestScope.collaborateur != null && requestScope.collaborateur.genre == 'Monsieur' ? 'checked' : ''}>
                                Monsieur
                            </label>
                        </div>
                    </div>
                </div>

                <div class="dropdown-container">
                    <label for="rqth">RQTH</label>
                    <div class="dropdown">
                        <button type="button" class="dropdown-toggle">Sélectionner RQTH</button>
                        <div class="dropdown-menu">
                            <label>
                                <input type="checkbox" name="rqth" value="oui" ${isOuiSelected ? 'checked' : ''} onclick="toggleDateField('oui')">
                                oui
                            </label>
                            <label>
                                <input type="checkbox" name="rqth" value="non" ${!isOuiSelected ? 'checked' : ''} onclick="toggleDateField('non')">
                                non
                            </label>
                            <label>
                                <input type="checkbox" name="rqth" value="a vie" ${isAVieSelected ? 'checked' : ''} onclick="toggleDateField('a vie')">
                                à vie
                            </label>
                        </div>
                    </div>
                </div>

                <div class="fieldset-texte" id="date-renouvellement" style="display:none;">
                    <label for="date_de_renouvellement">Date de renouvellement</label>
                    <input type="date" id="date_de_renouvellement" name="date_de_renouvellement"
                           placeholder="jj-mm-aaaa"
                           required
                           value="${requestScope.collaborateur != null ? requestScope.collaborateur.date_de_renouvellement : ''}">
                    <div class="error">${requestScope.errors.date_de_renouvellement}</div>
                </div>
                <br>

                <div class="dropdown-container">
                    <label for="responsable">Responsable activité</label>
                    <div class="dropdown">
                        <button type="button" class="dropdown-toggle">Sélectionner Responsable(s)</button>
                        <div class="dropdown-menu">
                            <c:forEach var="responsable" items="${responsableActiviteList}">
                                <label>
                                    <input type="checkbox" name="responsable" value="${responsable.id}">
                                    ${responsable.nom}
                                </label>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="button-container">
                    <input type="submit" value="Valider">
                    <button type="button" onclick="redirectToList()">Annuler</button>
                </div>
            </fieldset>


            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        </form>
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

                                toggleDateField(); // Ensure the initial state is correct based on the current selection
                            });

                            function toggleDateField(value) {
                                var dateField = document.getElementById('date-renouvellement');
                                var dateInput = document.getElementById('date_de_renouvellement');

                                if (value === 'oui') {
                                    dateField.style.display = 'block';
                                    dateInput.disabled = false;
                                } else {
                                    dateField.style.display = 'none';
                                    dateInput.disabled = true;
                                    dateInput.value = ''; // Clear the value to avoid submission
                                }
                            }
        </script>
    </main>
    <%@include file="/WEB-INF/jspf/footer.jsp" %>
</body>
</html>





