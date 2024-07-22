<%-- 
    Document   : ResponsableActivite
    Created on : 12 juin 2024, 15:07:02
    Author     : asolanas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <%-- Définir une variable pour indiquer que ce n'est pas la page d'accueil --%>
        <c:set var="notHome" value="true" />
        <link rel="shortcut icon" href="<c:url value="/assets/img/favicon.png"/>" type="image/x-icon"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>voir un RA</title>

    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <main>
            <fieldset>
                <legend><strong>Fiche Responsable d'Activite</legend>
                <div class='fiches'>
                    <p><span class="label-text">Matricule : </span>
                        <span class="value-text"><c:out value="${ra.matricule}"/></span>                    </p>
                    <p><span class="label-text">Nom : </span>
                        <span class="value-text"><c:out value="${ra.nom}"/></span></p>
                    <p><span class="label-text">Prénom : </span>
                        <span class="value-text"><c:out value="${ra.prenom}"/></span></p>
                    <p><span class="label-text">Mail : </span>
                        <span class="value-text"><c:out value="${ra.mail}"/></span></p>
                    <p><span class="label-text">Téléphone Pro : </span>
                        <span class="value-text"><c:out value="${ra.telephone_professionnel}"/></span></p>
                    <p><span class="label-text">Téléphone Perso : </span>
                        <span class="value-text"><c:out value="${ra.telephone_personnel}"/></span></p>
                    <p><span class="label-text">Partenaire(s) : </span>
                        <span class="value-text"><c:out value="${partenaire}"/></span></p>
                    <p><span class="label-text">Partenaire(s) liés aux prestations : </span>
                        <span class="value-text"><c:out value="${partenairesPrestation}"/></span></p>
                    <p><span class="label-text">Collaborateur(s) : </span>
                        <span class="value-text"><c:out value="${collaborateur}"/></span></p>
                    <p><span class="label-text">Collaborateur(s) liés aux prestations : </span>
                        <span class="value-text"><c:out value="${collaborateursPrestation}"/></span></p>

                </div>
                <div class="button-container">
                    <form action="/ProDigiUp_GesCollab/modifier_ra" method="get"style="display: inline;">
                        <input type="hidden" name="id" value="${ra.id}" />
                        <input type="submit" value="Modifier">
                    </form>
                    <form action="<c:url value='/supprimer_ra'/>" method="post" style="display: inline;">
                        <input type="hidden" name="id" value="${ra.id}" />
                        <input type="hidden" name="action" value="delete" />
                        <button type="submit" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce responsable d activité ?');">Supprimer</button>
                    </form>
                </div> 
                </div>

            </fieldset>
        </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>

</html>
