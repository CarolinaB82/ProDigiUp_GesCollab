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

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Afficher prestation</title>

    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <main>
            <fieldset>
                <legend><strong>Fiche Prestation</strong></legend>
                <p><strong>Siglum prestation : </strong><c:out value="${prestation.siglum_presta}"/></p>
                <p><strong>Numéro d'affaire : </strong><c:out value="${prestation.num_affaire}"/></p>
                <p><strong>Nom prestation : </strong><c:out value="${prestation.nom_presta}"/></p>
                <p><strong>Référent facture partenaire : </strong><c:out value="${prestation.ref_fact_partenaire}"/></p>
                <p><strong>Mail partenaire : </strong><c:out value="${prestation.mail_partenaire}"/></p>
                <p><strong>Référent facture Airbus : </strong><c:out value="${prestation.ref_fact_airbus}"/></p>
                <p><strong>Mail airbus : </strong><c:out value="${prestation.mail_airbus}"/></p>
                
                 <p><strong>Partenaire : </strong><c:out value="${partenaire}"/></p>
                <p><strong>Collaborateur : </strong><c:out value="${collaborateur}"/></p>
                <p><strong>Responsable activité : </strong><c:out value="${responsablesActivite}"/></p>


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
