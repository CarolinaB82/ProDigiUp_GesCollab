<%-- 
    Document   : afficherPartenaire
    Created on : 18 juin 2024, 11:35:06
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
        <link rel="shortcut icon" href="<c:url value="/assets/img/favicon.png"/>" type="image/x-icon"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Afficher partenaire</title>

    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <main>
            <fieldset>
                <legend><strong>Fiche Partenaire</legend>
                <div class='fiches'>
                    <p><span class="label-text">Nom : </span><span class="value-text"><c:out value="${partenaire.nom}"/></span></p>
                    <p><span class="label-text">Numéro de voie : </span><span class="value-text"><c:out value="${partenaire.numero_voie}"/></span></p>
                    <p><span class="label-text">Adresse : </span><span class="value-text"><c:out value="${partenaire.adresse}"/></span></p>
                    <p><span class="label-text">Code postal : </span><span class="value-text"><c:out value="${partenaire.code_postal}"/></span></p>
                    <p><span class="label-text">Ville : </span><span class="value-text"><c:out value="${partenaire.ville}"/></span></p>
                    <p><span class="label-text">Responsable(s) d'activité(s) : </span><span class="value-text"><c:out value="${responsablesActivite}"/></span></p>
                    <p><span class="label-text">Responsable(s) d'activité liés aux prestations : </span><span class="value-text"><c:out value="${responsablesActivitePrestation}"/></span></p>
                </div>

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
                </div>

            </fieldset>
        </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>
</html>
