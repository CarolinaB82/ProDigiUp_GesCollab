<%-- 
    Document   : CreerResponsableActivite
    Created on : 12 juin 2024, 15:07:26
    Author     : asolanas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Créer RA</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <form action="<c:url value="/creer_ra" />" method="post">
            <div>${requestScope.message}</div>
            <div>${requestScope.errMsg}</div>
            <fieldset>
                <legend>Créer un responsable d'activité</legend>
                <div>
                    <label for="matricule">Matricule</label>
                    <input type="text" id="matricule" name="matricule"
                           <div>${requestScope.errors.matricule}</div>
                </div>
                <div>
                    <label for="nom">Nom</label>
                    <input type="text" id="nom" name="nom" pattern="[a-zA-Z]*"
                           required
                           title="Veuillez saisir uniquement des lettres (A-Z, a-z)"
                           value="${requestScope.ra != null ? requestScope.ra.prenom : ''}"
                           class="${not empty requestScope.errors.prenom ? 'error-input' : ''}"

                           >
                    <div class="error">${requestScope.errors.nom}</div>
                </div>
                <div>
                    <label for="prenom">Prénom</label>
                    <input type="text" id="prenom" name="prenom" pattern="[a-zA-Z]*"
                           required
                           title="Veuillez saisir uniquement des lettres (A-Z, a-z)"
                           value="${requestScope.ra != null ? requestScope.ra.prenom : ''}"
                           class="${not empty requestScope.errors.prenom ? 'error-input' : ''}"

                           >
                    <div class="error">${requestScope.errors.prenom}</div>
                </div>
                <div>
                    <label for="tel_pro">Téléphone Professionnel</label>
                    <input type="text" id="telephone_professionnel" name="telephone_professionnel"
                           pattern="\d{10}" 
                               maxlength="10" 
                               required  
                               title="Veuillez saisir exactement 10 chiffres"
 
                               value="${requestScope.ra != null ? requestScope.ra.telephone_professionnel : ''}"
                               class="${not empty requestScope.errors.telephone_professionnel ? 'error-input' : ''}"
 
                               >
                    <div class="error">${requestScope.errors.telephone_professionnel}</div>
                </div>
                <div>
                    <label for="tel_perso">Téléphone Personnel</label>
                    <input type="text" id="telephone_personnel" name="telephone_personnel"
                           pattern="\d{10}" 
                               maxlength="10" 
                               required  
                               title="Veuillez saisir exactement 10 chiffres"
 
                               value="${requestScope.collaborateur != null ? requestScope.collaborateur.telephone_personnel : ''}"
                               class="${not empty requestScope.errors.telephone_personnel ? 'error-input' : ''}"
 
                               >
                    <div class="error">${requestScope.errors.telephone_personnel}</div>
                </div>
            </fieldset>
            <div>
                <input type="submit" value='Valider'>
                <input type="reset" value="Annuler">
            </div>

        </form>
    </body>
    <%@include file="/WEB-INF/jspf/footer.jsp" %>
</html>
