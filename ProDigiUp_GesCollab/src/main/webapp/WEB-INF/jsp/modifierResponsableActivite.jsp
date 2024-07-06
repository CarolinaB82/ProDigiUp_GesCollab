<%-- 
    Document   : modifierResponsableActivite
    Created on : 4 juil. 2024, 09:37:52
    Author     : cberge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <%-- Définir une variable pour indiquer que ce n'est pas la page d'accueil --%>
        <c:set var="notHome" value="true" />

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/styles.css">
        <title>Créer RA</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <main>
            <form action="<c:url value="/modifier_ra" />" method="post">
                <div>${requestScope.message}</div>
                <div>${requestScope.errMsg}</div>
                <fieldset>
                    <legend>Nouveau responsable d'activite</legend>
                      <input type="hidden" name="id" value="${ra.id}" />
                    <div>
                        <label for="matricule">Matricule</label>
                        <input type="text" id="matricule" name="matricule" 
                               pattern="\d{5}" 
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
                    <div class="combobox-container">
                        <div class="combobox">

                            <label for="multi-select">Son partenaire</label>
                            <br>
                            <select id="partenaire" name="partenaire" multiple>
                                <c:forEach var="partenaire" items="${partenaireList}">
                                    <!--<option value="${partenaire.id}">${partenaire.nom}</option>-->
                                    <option value="${partenaire.id}" <c:if test="${selectedPartenaires.contains(partenaire.id)}">selected</c:if>>${partenaire.nom}</option>
                                </c:forEach>
                            </select>
                            <br><br>

                        </div>

                        <div class="combobox">
                            <label for="multi-select">Son collaborateur</label>
                            <br>
                            <select id="collaborateur" name="collaborateur" multiple>
                                <c:forEach var="collaborateur" items="${collaborateurList}">
                                 <!--<option value="${collaborateur.id}">${collaborateur.nom}</option>-->
                                    <option value="${collaborateur.id}" <c:if test="${selectedCollaborateurs.contains(collaborateur.id)}">selected</c:if>>${collaborateur.nom}</option>
                                </c:forEach>
                            </select>
                            <br><br>


                            <p>Merci de remplir tous les champs</p>
                            </fieldset>
                            <input type="submit" value="Enregistrer">

                            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

                            </form>
                            </main>

                            </body>
                            <%@include file="/WEB-INF/jspf/footer.jsp" %>
                            </html>
