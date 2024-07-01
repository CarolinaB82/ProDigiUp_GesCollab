<%-- 
    Document   : afficherPrestation
    Created on : 21 juin 2024, 11:10:59
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
        <title>Afficher prestation</title>

    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <main>
            <fieldset>
                <legend><strong>Fiche Prestation</strong></legend>
                <p><strong>Siglum prestation : </strong><c:out value="${prestation.siglum_presta}"/></p>
                <p><strong>Nom prestation : </strong><c:out value="${prestation.nom_presta}"/></p>
                <p><strong>Référent facture partenaire : </strong><c:out value="${prestation.ref_fact_partenaire}"/></p>
                <p><strong>Référent facture Airbus : </strong><c:out value="${prestation.ref_fact_airbus}"/></p>
            </fieldset>
        </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>  
    </body>

</html>
