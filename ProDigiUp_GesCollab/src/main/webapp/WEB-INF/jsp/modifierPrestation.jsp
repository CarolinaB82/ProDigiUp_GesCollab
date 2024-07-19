<%-- 
    Document   : modifierPrestation
    Created on : 3 juil. 2024, 14:22:03
    Author     : asolanas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>


<!DOCTYPE html>
<html>
    <head>
        <%-- Définir une variable pour indiquer que ce n'est pas la page d'accueil --%>
        <c:set var="notHome" value="true" />
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/styles.css">
        <title>modifier prestation</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>

        <main>
            <form action="<c:url value="/modifierPrestation"/>" method="post">                 
                <div>${requestScope.message}</div>
                <div class="error-message">${requestScope.errorMsg}</div>
                <fieldset>
                    <legend>Modifier la Prestation</legend>
                    <form action="ModifierPrestation" method="post">

                        <input type="hidden" name="id" value="${prestation.id}">
                        <div>
                            <label for="siglum_presta">Siglum Presta:</label>
                            <input type="text" id="siglum_presta" name="siglum_presta" value="${prestation.siglum_presta}">
                        </div>
                        <br>
                        <div>
                            <label for="num_affaire">Num Affaire:</label>
                            <input type="text" id="num_affaire" name="num_affaire" value="${prestation.num_affaire}">
                        </div>
                        <br>
                        <div>
                            <label for="nom_presta">Nom Presta:</label>
                            <input type="text" id="nom_presta" name="nom_presta" value="${prestation.nom_presta}">
                        </div>
                        <br>
                        <div>
                            <label for="ref_fact_partenaire">Ref Fact Partenaire:</label>
                            <input type="text" id="ref_fact_partenaire" name="ref_fact_partenaire" value="${prestation.ref_fact_partenaire}">
                        </div>
                        <br>
                        <div>
                            <label for="mail_partenaire">Mail Partenaire:</label>
                            <input type="email" id="mail_partenaire" name="mail_partenaire" value="${prestation.mail_partenaire}">
                        </div>
                        <br>
                        <div>
                            <label for="ref_fact_airbus">Ref Fact Airbus:</label>
                            <input type="text" id="ref_fact_airbus" name="ref_fact_airbus" value="${prestation.ref_fact_airbus}">
                        </div>
                        <br>
                        <div>
                            <label for="mail_airbus">Mail Airbus:</label>
                            <input type="email" id="mail_airbus" name="mail_airbus" value="${prestation.mail_airbus}">
                        </div>
                        <br>
                        <div class="combobox-container">
                            <div class="combobox">
                                <label for="multi-select-responsable_activite">Responsable activité</label>
                                <br>
                                <!--<label for="id_ra">Responsable d'Activité:</label>-->
                                <select id="id_ra" name="id_ra" >
                                    <c:forEach var="ra" items="${responsablesActivite}">
                                        <option value="${ra.id}" <c:if test="${ra.id == prestation.id_ra}">selected</c:if>>${ra.nom}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="combobox">

                                <label for="multi-select">Collaborateur</label>
                                <br>
                                <!--<label for="id_collaborateur">Collaborateur:</label>-->
                                <select id="id_collaborateur" name="id_collaborateur">
                                    <c:forEach var="collaborateur" items="${collaborateurs}">
                                        <option value="${collaborateur.id}" <c:if test="${collaborateur.id == prestation.id_collaborateur}">selected</c:if>>${collaborateur.nom}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="combobox">
                                <label for="multi-select">Partenaire</label>
                                <br>
                                <!--<label for="id_partenaire">Partenaire:</label>-->

                                <select id="id_partenaire" name="id_partenaire">
                                    <c:forEach var="partenaire" items="${partenaires}">
                                        <option value="${partenaire.id}" <c:if test="${partenaire.id == prestation.id_partenaire}">selected</c:if>>${partenaire.nom}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div>
                            <input type="submit" value="Modifier">
                            <input type="reset" value="Annuler">
                        </div>
                    </form>
                </fieldset>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>

</html>