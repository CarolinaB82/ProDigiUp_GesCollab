
<%-- 
    Document   : afficherCollaborateur
    Created on : 20 juin 2024, 10:02:28
    Author     : asolanas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix= "c" %>


<!DOCTYPE html>
<html>
    <head>
        <%-- Définir une variable pour indiquer que ce n'est pas la page d'accueil --%>
        <c:set var="notHome" value="true" />

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="<c:url value="/assets/img/favicon.png"/>" type="image/x-icon"/>
        <title>Fiche collaborateur</title>

    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <main>
            <fieldset>
                <legend><strong>Fiche Collaborateur</legend>
                <div>${requestScope.message}</div>
                <div class='fiches'>
                    <p><span class="label-text">Matricule: </span><span class="value-text"><c:out value="${collaborateur.matricule}"/></span></p>
                    <p><span class="label-text">Genre: </span><span class="value-text"><c:out value="${collaborateur.genre}"/></span></p>
                    <p><span class="label-text">Nom: </span><span class="value-text"><c:out value="${collaborateur.nom}"/></span></p>
                    <p><span class="label-text">Prénom: </span><span class="value-text"><c:out value="${collaborateur.prenom}"/></span></p>
                    <p><span class="label-text">Mail 1: </span><span class="value-text"><c:out value="${collaborateur.mail_1}"/></span></p>
                    <p><span class="label-text">Mail 2: </span><span class="value-text"><c:out value="${collaborateur.mail_2}"/></span></p>
                    <p><span class="label-text">Téléphone personnel: </span><span class="value-text"><c:out value="${collaborateur.telephone_personnel}"/></span></p>
                    <p><span class="label-text">Statut: </span><span class="value-text"><c:out value="${collaborateur.statut}"/></span></p>
                    <p><span class="label-text">Catégorie: </span><span class="value-text"><c:out value="${collaborateur.categorie}"/></span></p>
                    <p><span class="label-text">RQTH: </span><span class="value-text"><c:out value="${collaborateur.rqth}"/></span></p>
                        <c:if test="${collaborateur.rqth == 'oui'}">
                        <p><span class="label-text">Date de renouvellement: </span><span class="value-text"><c:out value="${formattedDate}"/></span></p>
                        </c:if>
                    <p><span class="label-text">Métier: </span><span class="value-text"><c:out value="${collaborateur.metier}"/></span></p>
                    <p><span class="label-text">Responsable(s) activité: </span><span class="value-text"><c:out value="${responsablesActivite}"/></span></p>
                    <p><span class="label-text">Responsable(s) activité liés aux prestations: </span><span class="value-text"><c:out value="${responsablesActivitePrestation}"/></span></p>
                </div>

                <div class="button-container">
                    <form action="/ProDigiUp_GesCollab/modifier_collaborateur" method="get"style="display: inline;">
                        <input type="hidden" name="id" value="${collaborateur.id}" />
                        <input type="submit" value="Modifier">
                    </form>

               <!--<form action="<c:url value='/collaborateur'/>" method="post" style="display: inline;">
                    <input type="hidden" name="id" value="${collaborateur.id}" />
                    <input type="hidden" name="action" value="deactivate" />
                    <button type="submit">Désactiver</button>
                </form>-->

                    <form action="<c:url value='/supprimer_collaborateur'/>" method="post" style="display: inline;">
                        <input type="hidden" name="id" value="${collaborateur.id}" />
                        <input type="hidden" name="action" value="delete" />
                        <button type="submit" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce collaborateur ?');">Supprimer</button>
                    </form>
                </div>

            </fieldset>  
        </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>

</html>
