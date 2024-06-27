<%-- 
    Document   : CreerResponsableActivite
    Created on : 12 juin 2024, 15:07:26
    Author     : asolanas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/styles.css">
        <title>Créer RA</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <main>
            <form action="<c:url value="/creer_ra" />" method="post">
            <div>${requestScope.message}</div>
            <div>${requestScope.errMsg}</div>
            <fieldset>
                <legend>Nouveau responsable d'activite</legend>
                <div>
                    <label for="matricule">Matricule</label>
                    <input type="text" id="matricule" name="matricule" 
                           pattern="\d{5}" 
                           maxlength="5" 
                           required
                           title="Veuillez saisir uniquement des chiffres (0-9)" 
                           value="${requestScope.collaborateur != null ? requestScope.collaborateur.matricule : ''}" 
                           class="${not empty requestScope.errors.matricule ? 'error-input' : ''}"
                           >
                    <div class="error-details-message">${requestScope.errors.matricule}</div>
                </div>
                <div>
                    <label for="nom">Nom</label>
                    <input type="text" id="nom" name="nom" pattern="[a-zA-ZÀ-ÿ' -]*"
                           required
                           title="Veuillez saisir uniquement des lettres (A-Z, a-z)"
                           value="${requestScope.ra != null ? requestScope.ra.prenom : ''}"
                           class="${not empty requestScope.errors.prenom ? 'error-input' : ''}"
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
                    <label for="tel_pro">Téléphone Professionnel</label>
                    <input type="text" id="telephone_professionnel" name="telephone_professionnel"
                           pattern="\+?[0-9]{1,15}" 
                           maxlength="16" 
                           required  
                           title="Veuillez saisir exactement 10 chiffres"

                           value="${requestScope.collaborateur != null ? requestScope.collaborateur.telephone_professionnel : ''}"
                           class="${not empty requestScope.errors.telephone_professionnel ? 'error-input' : ''}"

                           >
                    <div class="error-details-mesage">${requestScope.errors.telephone_professionnel}</div>
                </div>
                <div>
                    <label for="tel_perso">Téléphone Personnel</label>
                    <input type="text" id="telephone_personnel" name="telephone_personnel"
                           pattern="\+?[0-9]{1,15}" 
                           maxlength="16" 
                           required  
                           title="Veuillez saisir exactement 10 chiffres"

                           value="${requestScope.collaborateur != null ? requestScope.collaborateur.telephone_personnel : ''}"
                           class="${not empty requestScope.errors.telephone_personnel ? 'error-input' : ''}"

                           >
                    <div class="error-details-message">${requestScope.errors.telephone_personnel}</div>
                </div>
                <form action="/submit-form" method="POST">
                    <div class="combobox-container">
                        <div class="combobox">

                            <label for="multi-select-partenaire">Choisissez son partenaire :</label>
                            <br>
                            <select id="multi-select-partenaire" name="partenaireOptions" multiple>
                                <option value="option1">Option 1</option>
                                <option value="option2">Option 2</option>
                                <option value="option3">Option 3</option>
                                <option value="option4">Option 4</option>
                            </select>
                            <br><br>
                            <input type="submit" value="Soumettre Partenaire"> 
                            </form>
                        </div>

                        <div class="combobox">
                            <form action="/submit-form" method="POST">
                                <label for="multi-select-collaborateur">Choisissez son collaborateur :</label>
                                <br>
                                <select id="multi-select-collaborateur" name="collaborateurOptions" multiple>
                                    <option value="option1">Option 1</option>
                                    <option value="option2">Option 2</option>
                                    <option value="option3">Option 3</option>
                                    <option value="option4">Option 4</option>
                                </select>
                                <br><br>
                                <input type="submit" value="Soumettre Collaborateur"> 

                                </div>

                                </div>
                                <p>Merci de remplir tous les champs</p>
                                </fieldset>


                                <div>
                                    <input type="submit" value='Valider'>
                                    <input type="reset" value="Annuler">
                                </div>
                        </div>

                </form>
                </body>
                <%@include file="/WEB-INF/jspf/footer.jsp" %>
                </html>
