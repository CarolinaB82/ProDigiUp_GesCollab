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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/styles.css">
        <title>créer partenaire</title>
    </head>
    <body> 
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <main>
            <form action="/ProDigiUp_GesCollab/creer_partenaire" method="post">                  
                <div>${requestScope.message}</div>
                <div class="error-message">${requestScope.errorMsg}</div>
                <fieldset>
                    <legend>Nouveau Partenaire</legend>


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

                    <div class="combobox-container">
                        <div class="combobox">
                            <label for="multi-select">Responsable(s) activité</label>
                            <br>
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
        </main>
    </body>
    <%@include file="/WEB-INF/jspf/footer.jsp" %>
</html>
