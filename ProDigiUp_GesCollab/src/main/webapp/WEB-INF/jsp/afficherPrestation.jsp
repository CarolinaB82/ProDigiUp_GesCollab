
<%-- 
    Document   : afficherPrestation
    Created on : 21 juin 2024, 11:10:59
    Author     : cberge
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
        <title>Afficher prestation</title>

    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <main>
            <fieldset>
                <legend><strong>Fiche Prestation</strong></legend>
                <div class="fiches">
                    <p><span class="label-text">Siglum prestation : </span><span class="value-text"><c:out value="${prestation.siglum_presta}"/></span></p>
                    <p><span class="label-text">Numéro d'affaire : </span><span class="value-text"><c:out value="${prestation.num_affaire}"/></span></p>
                    <p><span class="label-text">Nom prestation : </span><span class="value-text"><c:out value="${prestation.nom_presta}"/></span></p>
                    <p><span class="label-text">Référent facture partenaire : </span><span class="value-text"><c:out value="${prestation.ref_fact_partenaire}"/></span></p>
                    <p><span class="label-text">Mail partenaire : </span><span class="value-text"><c:out value="${prestation.mail_partenaire}"/></span></p>
                    <p><span class="label-text">Référent facture Airbus : </span><span class="value-text"><c:out value="${prestation.ref_fact_airbus}"/></span></p>
                    <p><span class="label-text">Mail Airbus : </span><span class="value-text"><c:out value="${prestation.mail_airbus}"/></span></p>
                    <p><span class="label-text">Partenaire : </span><span class="value-text"><c:out value="${partenaire}"/></span></p>
                    <p><span class="label-text">Collaborateur : </span><span class="value-text"><c:out value="${collaborateur}"/></span></p>
                    <p><span class="label-text">Responsable activité : </span><span class="value-text"><c:out value="${responsablesActivite}"/></span></p>
                </div>

                <div class="button-container">
                    <form action="/ProDigiUp_GesCollab/modifierPrestation" method="get"style="display: inline;">
                        <input type="hidden" name="id" value="${prestation.id}" />
                        <input type="submit" value="Modifier">
                    </form>
                    <form action="<c:url value='/supprimerPrestation'/>" method="post" style="display: inline;">
                        <input type="hidden" name="id" value="${prestation.id}" />
                        <input type="hidden" name="action" value="delete" />
                        <button type="submit" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette prestation ?');">Supprimer</button>
                    </form>
                </div>
            </fieldset>
        </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>  
    </body>

</html>