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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Afficher prestation</title>
       
    </head>
   <body>
    <%@include file="/WEB-INF/jspf/header.jsp" %>
    <fieldset>
        <legend><strong>Fiche Prestation</strong></legend>
        <p><strong>Siglum prestation : </strong><c:out value="${prestation.siglum_presta}"/></p>
        <p><strong>Nom prestation : </strong><c:out value="${prestation.nom_presta}"/></p>
        <p><strong>Référent facture partenaire : </strong><c:out value="${prestation.ref_fact_partenaire}"/></p>
        <p><strong>Référent facture Airbus : </strong><c:out value="${prestation.ref_fact_airbus}"/></p>
        
    </fieldset>
</body>
   
    
    <!--<fieldset>
        <legend>Rechercher</legend>
        <form>
            <label for="nom">Nom de la prestation</label>
            <input type="text" id="nom" name="nom" pattern="[a-zA-Z]*"
                   required
                   title="Veuillez saisir uniquement des lettres (A-Z, a-z)"
                   >
            <div class="error">${requestScope.errors.nom}</div>
            </div>
            <div>
                <input type="submit" value="Rechercher">
                <input type="reset" value="Annuler">
                </form>-->
                </fieldset>
                </body>
                <%@include file="/WEB-INF/jspf/footer.jsp" %>
                </html>
