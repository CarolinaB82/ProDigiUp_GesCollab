
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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/styles.css">
        <title>Créer Collaborateur</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <main>
            <form action="/ProDigiUp_GesCollab/creer_collaborateur" method="post">                  
                <div>${requestScope.message}</div>
                <div>${requestScope.errMsg}</div>
                <div class="error-message">${requestScope.errorMsg}</div>
                <fieldset>
                    <legend>Nouveau Collaborateur</legend>
                    <div>
                        <label for="matricule">Matricule</label>
                        <!-- pour garder en memoire les champs deja remplis si erreur quelque part-->
                        <input type="text" id="matricule" name="matricule" 
                               pattern="0?[0-9]{1,5}" 
                               maxlength="5" 
                               required
                               title="Veuillez saisir uniquement des chiffres (0-9)" 
                               value="${requestScope.collaborateur != null ? requestScope.collaborateur.matricule : ''}" 
                               class="${not empty requestScope.errors.matricule ? 'error-input' : ''}"

                               >
                        <div class="error">${requestScope.errors.matricule}</div>
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
                        <div class="error">${requestScope.errors.nom}</div>
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
                        <div class="error">${requestScope.errors.prenom}</div>
                    </div>
                    <br>
                    <div>
                        <label for="mail_1">Mail 1</label>
                        <input type="email" id="mail_1" name="mail_1"
                               required
                               value="${requestScope.collaborateur != null ? requestScope.collaborateur.mail_1 : ''}"
                               class="${not empty requestScope.errors.mail_1 ? 'error-input' : ''}"

                               >
                        <div class="error">${requestScope.errors.mail_1}</div>
                    </div>
                    <div>
                        <label for="mail_2">Mail 2</label>
                        <input type="email" id="mail_2" name="mail_2"
                               pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}"

                               title="Veuillez saisir une adresse e-mail valide"
                               value="${requestScope.collaborateur != null ? requestScope.collaborateur.mail_2 : ''}"
                               class="${not empty requestScope.errors.mail_2 ? 'error-input' : ''}"

                               >
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
                    <br>
                    <div>
                        <label for="statut">Statut</label>
                        <select id="statut" name="statut">
                            <option value="CDD" ${requestScope.collaborateur != null && requestScope.collaborateur.statut == 'CDD' ? 'selected' : ''}>CDD</option>
                            <option value="CDI" ${requestScope.collaborateur != null && requestScope.collaborateur.statut == 'CDI' ? 'selected' : ''}>CDI</option>
                            <option value="CDD Tremplin" ${requestScope.collaborateur != null && requestScope.collaborateur.statut == 'CDD Tremplin' ? 'selected' : ''}>CDD Tremplin</option>
                            <option value="Stage" ${requestScope.collaborateur != null && requestScope.collaborateur.statut == 'Stage' ? 'selected' : ''}>Stage</option>


                        </select>
                        <div class="error">${requestScope.errors.statut}</div>
                    </div>
                    <br>
                    <div>
                        <label for="categorie">Catégorie</label>
                        <select id="categorie" name="categorie">
                            <option value="ETAM" ${requestScope.collaborateur != null && requestScope.collaborateur.categorie == 'ETAM' ? 'selected' : ''}>ETAM</option>
                            <option value="Cadre" ${requestScope.collaborateur != null && requestScope.collaborateur.categorie == 'Cadre' ? 'selected' : ''}>Cadre</option>
                        </select>
                        <div class="error">${requestScope.errors.categorie}</div>
                    </div>
                    <br>
                    <div>
                        <label for="genre">Genre</label>
                        <select id="genre" name="genre">
                            <option value="Madame" ${requestScope.collaborateur != null && requestScope.collaborateur.genre == 'Madame' ? 'selected' : ''}>Madame</option>
                            <option value="Monsieur" ${requestScope.collaborateur != null && requestScope.collaborateur.genre == 'Monsieur' ? 'selected' : ''}>Monsieur</option>
                        </select>
                        <div class="error">${requestScope.errors.genre}</div>
                    </div>
                    <br>
                    <div>


                        <div>
                            <label for="rqth">RQTH</label>
                            <select id="rqth" name="rqth" onchange="toggleDateField()">
                                <option value="oui" ${isOuiSelected ? 'selected' : ''}>oui</option>
                                <option value="non" ${!isOuiSelected ? 'selected' : ''}>non</option>
                                <option value="a vie" ${isAVieSelected ? 'selected' : ''}>à vie</option>
                            </select>
                            <div class="error">${requestScope.errors.rqth}</div>
                        </div>

                        <c:if test="${isOuiSelected}">
                            <div id="date-renouvellement" style="display:none;">
                                <label for="date_de_renouvellement">Date de renouvellement</label>
                                <input type="date" id="date_de_renouvellement" name="date_de_renouvellement" *
                                       placeholder="jj-mm-aaaa"
                                       required
                                       value="${requestScope.collaborateur != null ? requestScope.collaborateur.date_de_renouvellement : ''}"
                                       >
                                <div class="error">${requestScope.errors.date_de_renouvellement}</div>
                            </div>
                        </c:if>

                        <c:if test="${!isOuiSelected}">
                            <div id="date-renouvellement" style="display:none;">
                                <label for="date_de_renouvellement">Date de renouvellement</label>
                                <input type="date" id="date_de_renouvellement" name="date_de_renouvellement"
                                       placeholder="jj-mm-aaaa" 
                                       value="${requestScope.collaborateur != null ? requestScope.collaborateur.date_de_renouvellement : ''}">
                                <div class="error">${requestScope.errors.date_de_renouvellement}</div>
                            </div>
                        </c:if>

                        <script>
                            function toggleDateField() {
                                var rqthSelect = document.getElementById('rqth');
                                var dateField = document.getElementById('date-renouvellement');
                                var dateInput = document.getElementById('date_de_renouvellement');

                                if (rqthSelect.value === 'oui') {
                                    dateField.style.display = 'block';
                                    dateInput.disabled = false;
                                } else if (rqthSelect.value === 'a vie') {
                                    dateField.style.display = 'none';
                                    dateInput.disabled = true;
                                    dateInput.value = '';
                                } else {
                                    dateField.style.display = 'non';
                                    dateInput.disabled = true;
                                    dateInput.value = ''; // Clear the value to avoid submission
                                }
                            }

                            document.addEventListener("DOMContentLoaded", function () {
                                toggleDateField(); // Ensure the initial state is correct based on the current selection
                            });
                        </script>
                    </div>
                    <br>
                    <div>
                        <label for="metier">Métier</label>
                        <input type="text" id="metier" name="metier"
                               pattern="[a-zA-ZÀ-ÿ' -]*"
                               required
                               title="Veuillez saisir uniquement des lettres (A-Z, a-z)"
                               value="${requestScope.collaborateur != null ? requestScope.collaborateur.metier : ''}"
                               class="${not empty requestScope.errors.matricule ? 'error-input' : ''}"

                    </div>  
                    <div class="error">${requestScope.errors.metier}</div>
                    <br>

                    <div class="combobox-container">
                        <div class="combobox">

                            <label for="responsable">Responsable activité</label>
                            <select id="responsable" name="responsable" multiple>
                                <c:forEach var="responsable" items="${responsableActiviteList}">
                                    <option value="${responsable.id}">${responsable.nom}</option>
                                </c:forEach>
                            </select>
                            <br><br>

                        </div>

                    </div>
                    <p>Merci de remplir tous les champs</p>
                </fieldset>
                <div>
                    <input type="submit" value="Envoyer">
                    <input type="reset" value="Annuler">
                </div>

                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

            </form>
            <script>
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
            </script>
        </main>
    </body>
    <%@include file="/WEB-INF/jspf/footer.jsp" %>
</html>
