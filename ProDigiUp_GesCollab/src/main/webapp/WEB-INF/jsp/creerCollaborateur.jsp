
<%-- 
    Document   : creerCollaborateur
    Created on : 12 juin 2024, 09:15:40
    Author     : cberge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>


<!DOCTYPE html>
<html>
    <head>
        <%-- Définir une variable pour indiquer que ce n'est pas la page d'accueil --%>
        <c:set var="notHome" value="true" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <link rel="shortcut icon" href="<c:url value="/assets/img/favicon.png"/>" type="image/x-icon"/>
        <title>Créer Collaborateur</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>

        <main>
            <form action="/ProDigiUp_GesCollab/creer_collaborateur" method="post">                  
                <div>${requestScope.message}</div>
                <div>${requestScope.errMsg}</div>
                <div class="error-message">${requestScope.errorMsg}</div>
                <fieldset>
                    <legend class="legend-texte">Nouveau Collaborateur</legend>
                    <div class="fieldset-texte">
                        <label for="matricule">*Matricule</label>
                        <!-- pour garder en memoire les champs deja remplis si erreur quelque part-->
                        <input type="text" id="matricule" name="matricule" 
                               pattern="0?[0-9]{1,5}" 
                               maxlength="5" 
                               required
                               title="Veuillez saisir uniquement des chiffres (0-9), 5 max" 
                               value="${requestScope.collaborateur != null ? requestScope.collaborateur.matricule : ''}" 
                               class="${not empty requestScope.errors.matricule ? 'error-input' : ''}"

                               >
                        <div class="error">${requestScope.errors.matricule}</div>
                    </div>

                    <div class="fieldset-texte">
                        <label for="nom">*Nom</label>
                        <input type="text" id="nom" name="nom"
                               required
                               title="Veuillez saisir uniquement des lettres (A-Z, a-z)"
                               value="${requestScope.collaborateur != null ? requestScope.collaborateur.nom : ''}"
                               class="${not empty requestScope.errors.nom ? 'error-input' : ''}"

                               >
                        <div class="error">${requestScope.errors.nom}</div>
                    </div>

                    <div>
                        <label for="prenom">*Prénom</label>
                        <input type="text" id="prenom" name="prenom"
                               required
                               title="Veuillez saisir uniquement des lettres (A-Z, a-z)"
                               value="${requestScope.collaborateur != null ? requestScope.collaborateur.prenom : ''}"
                               class="${not empty requestScope.errors.prenom ? 'error-input' : ''}"

                               >
                        <div class="error">${requestScope.errors.prenom}</div>
                    </div>

                    <div>
                        <label for="mail_1">*Mail Professionnel</label>
                        <input type="email" id="mail_1" name="mail_1"
                               required
                               pattern="(?=.*[a-zA-Z].*[a-zA-Z])[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}"

                               value="${requestScope.collaborateur != null ? requestScope.collaborateur.mail_1 : ''}"
                               class="${not empty requestScope.errors.mail_1 ? 'error-input' : ''}"

                               >
                        <div class="error">${requestScope.errors.mail_1}</div>
                    </div>
                    <div>
                        <label for="mail_2">Mail Personnel</label>
                        <input type="email" id="mail_2" name="mail_2"
                              
pattern="(?=.*[a-zA-Z].*[a-zA-Z])[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}"

                               title="Veuillez saisir une adresse e-mail valide"
                               value="${requestScope.collaborateur != null ? requestScope.collaborateur.mail_2 : ''}"
                               class="${not empty requestScope.errors.mail_2 ? 'error-input' : ''}">

                               
                        <div class="error">${requestScope.errors.mail_2}</div>
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
                        <div class="error">${requestScope.errors.telephone_personnel}</div>
                    </div>

                    <div>
                        <label for="metier">*Métier</label>
                        <input type="text" id="metier" name="metier"
                               pattern="[a-zA-ZÀ-ÿ' -]*"
                               required
                               title="Veuillez saisir uniquement des lettres (A-Z, a-z)"
                               value="${requestScope.collaborateur != null ? requestScope.collaborateur.metier : ''}"
                               class="${not empty requestScope.errors.matricule ? 'error-input' : ''}"
                    </div> 
                    <br><br>
                    <div>
                        <label for="materiel">Matériel</label>
                        <input type="text" id="materiel" name="materiel"
                               value="${requestScope.collaborateur != null ? requestScope.collaborateur.materiel : ''}"
                               class="${not empty requestScope.errors.matricule ? 'error-input' : ''}"
                    </div> 
                    <br><br>
                    <div>
                        <label for="statut">Statut</label>
                        <select id="statut" name="statut">
                            <option value="CDD" ${requestScope.collaborateur != null && requestScope.collaborateur.statut == 'CDD' ? 'selected' : ''}>CDD</option>
                            <option value="CDI" ${requestScope.collaborateur != null && requestScope.collaborateur.statut == 'CDI' ? 'selected' : ''}>CDI</option>
                            <option value="CDD Tremplin" ${requestScope.collaborateur != null && requestScope.collaborateur.statut == 'CDD Tremplin' ? 'selected' : ''}>CDD TREMPLIN</option>
                            <option value="Stage" ${requestScope.collaborateur != null && requestScope.collaborateur.statut == 'Stage' ? 'selected' : ''}>STAGE</option>
                        </select>
                        <div class="error-details-message">${requestScope.errors.statut}</div>
                    </div>
                    <br>
                    <div>
                        <label for="categorie">Catégorie</label>
                        <select id="categorie" name="categorie">
                            <option value="ETAM" ${requestScope.collaborateur != null && requestScope.collaborateur.categorie == 'ETAM' ? 'selected' : ''}>ETAM</option>
                            <option value="Cadre" ${requestScope.collaborateur != null && requestScope.collaborateur.categorie == 'Cadre' ? 'selected' : ''}>CADRE</option>
                        </select>
                        <div class="error-details-message">${requestScope.errors.categorie}</div>
                    </div>
                    <br>
                    <div>
                        <label for="genre">Genre</label>
                        <select id="genre" name="genre">
                            <option value="Madame" ${requestScope.collaborateur != null && requestScope.collaborateur.genre == 'Madame' ? 'selected' : ''}>Madame</option>
                            <option value="Monsieur" ${requestScope.collaborateur != null && requestScope.collaborateur.genre == 'Monsieur' ? 'selected' : ''}>Monsieur</option>
                        </select>
                        <div class="error-details-message">${requestScope.errors.genre}</div>
                    </div>
                    <br>
                    <div>
                        <label for="rqth">RQTH</label>
                        <select id="rqth" name="rqth" onchange="toggleFields()">
                            <option value="oui" ${isOuiSelected ? 'selected' : ''}>Oui</option>
                            <option value="non" ${!isOuiSelected && !isAVieSelected ? 'selected' : ''}>Non</option>
                            <option value="a vie" ${isAVieSelected ? 'selected' : ''}>A vie</option>
                        </select>
                        <div class="error-details-message">${requestScope.errors.rqth}</div>
                    </div>

                    <div id="date-renouvellement" style="display:none;">
                        <label for="date_de_renouvellement">Date de renouvellement</label>
                        <input type="date" id="date_de_renouvellement" name="date_de_renouvellement"
                               placeholder="jj-mm-aaaa"
                               value="${requestScope.collaborateur != null ? requestScope.collaborateur.date_de_renouvellement : ''}">
                        <div class="error-details-message">${requestScope.errors.date_de_renouvellement}</div>
                    </div>

                    <div id="type-rqth" style="display:none;">
                        <label for="type_rqth">Type de RQTH</label>
                        <select id="type_rqth" name="type_rqth">
                            <option value="">-- Aucun --</option>
                            <option value="TH" ${requestScope.collaborateur != null && requestScope.collaborateur.type_rqth == 'TH' ? 'selected' : ''}>TH</option>
                            <option value="THT" ${requestScope.collaborateur != null && requestScope.collaborateur.type_rqth == 'THT' ? 'selected' : ''}>THT</option>
                            <option value="THEA" ${requestScope.collaborateur != null && requestScope.collaborateur.type_rqth == 'THEA' ? 'selected' : ''}>THEA</option>
                        </select>
                        <div class="error-details-message">${requestScope.errors.type_rqth}</div>
                    </div>

                    <script>
                        function toggleFields() {
                            var rqthSelect = document.getElementById('rqth');
                            var dateField = document.getElementById('date-renouvellement');
                            var typeField = document.getElementById('type-rqth');
                            var dateInput = document.getElementById('date_de_renouvellement');

                            if (rqthSelect.value === 'oui') {
                                dateField.style.display = 'block';
                                typeField.style.display = 'block';
                                dateInput.disabled = false;
                            } else if (rqthSelect.value === 'a vie') {
                                dateField.style.display = 'none';
                                typeField.style.display = 'block';
                                dateInput.disabled = true;
                                dateInput.value = '';
                            } else {
                                dateField.style.display = 'none';
                                typeField.style.display = 'none';
                                dateInput.disabled = true;
                                dateInput.value = '';
                            }
                        }

                        document.addEventListener("DOMContentLoaded", function () {
                            toggleFields(); // Ensure the initial state is correct based on the current selection
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



                    <div class="combobox-container">
                        <div class="combobox">

                            <label for="responsable">Responsables d'activité</label>
                            <select id="responsable" name="responsable">
                                <c:forEach var="responsable" items="${responsableActiviteList}">
                                    <option value="${responsable.id}">${responsable.nom}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="button-container">
                            <input  type="submit" value="Valider">
                            <input type="reset" value="Annuler">
                        </div>
                    </div>
                    </div>
                </fieldset>

                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

            </form>
        </main>
    </body>
    <%@include file="/WEB-INF/jspf/footer.jsp" %>
</html>
