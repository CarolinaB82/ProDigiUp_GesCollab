<%-- 
    Document   : afficherPartenaire
    Created on : 18 juin 2024, 11:35:06
    Author     : asolanas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%-- Définir une variable pour indiquer que ce n'est pas la page d'accueil --%>
        <c:set var="notHome" value="true" />

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Afficher partenaire</title>

    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <main>
            <fieldset>
                <legend><strong>Fiche Partenaire</legend>
                <p><strong>Nom :  </strong><c:out value="${partenaire.nom}"/></p>
                <p><strong>Numéro de voie : </strong><c:out value="${partenaire.numero_voie}"/></p>
                <p><strong>Adresse : </strong><c:out value="${partenaire.adresse}"/></p>
                <p><strong>Code postal : </strong><c:out value="${partenaire.code_postal}"/></p>
                <p><strong>Ville : </strong><c:out value="${partenaire.ville}"/></p>

                <p><strong>Responsable(s) activité(s): </strong><c:out value="${responsablesActivite}"/></p>


                <div class="button-container">
                    <form action="/ProDigiUp_GesCollab/modifier_partenaire" method="get"style="display: inline;">
                        <input type="hidden" name="id" value="${partenaire.id}" />
                        <input type="submit" value="Modifier">
                    </form>
                        <form action="<c:url value='/supprimer_partenaire'/>" method="post" style="display: inline;">
                    <input type="hidden" name="id" value="${partenaire.id}" />
                    <input type="hidden" name="action" value="delete" />
                    <button type="submit" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce partenaire ?');">Supprimer</button>
                </form>
                </div>
            </fieldset>

            <!--<fieldset>
                <legend>Rechercher</legend>
                <form>
                    <label for="nom">Nom du partenaire</label>
                    <input type="text" id="nom" name="nom" pattern="[a-zA-Z -]*"
                           required
                           title="Veuillez saisir uniquement des lettres (A-Z, a-z)"
                           >
                    <div class="error">${requestScope.errors.nom}</div>

                    <input type="submit" value="Rechercher">
                    <input type="reset" value="Annuler">
                </form>
            </fieldset>-->
        </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>
</html>
