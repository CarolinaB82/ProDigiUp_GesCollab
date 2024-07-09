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
            <form action="<c:url value="/modifierPrestation" />" method="post">                 
                <div>${requestScope.message}</div>
                <div class="error-message">${requestScope.errorMsg}</div>
                <fieldset>
                    <legend>Modifier la Prestation</legend>
                    <form action="ModifierPrestation" method="post">

                        <input type="hidden" name="id_prestation" value="${prestation.id_prestation}">
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
                                <label for="multi-select">Responsable d'activité mié à la prestation</label>
                                <br>
                                <select id="responsable" name="responsable" multiple>
                                    <c:forEach var="responsable" items="${responsableActiviteList}">
                                        <!--<option value="${responsable.id}">${responsable.nom}</option>-->
                                        <option value="${responsable.id}" <c:if test="${selectedResponsables.contains(responsable.id)}">selected</c:if>>${responsable.nom}</option>
                                    </c:forEach>
                                </select>
                                <br><br>

                            </div>
                            <br>
                            <div class="combobox-container">
                                <div class="combobox">
                                    <label for="multi-select">Collaborateur lié à la prestation</label>
                                    <br>
                                    <select id="collaborateur" name="collaborateur" multiple>
                                        <option value="">-- Aucun --</option>
                                        <c:forEach var="collaborateur" items="${collaborateurList}">
                                            <!--<option value="${collaborateur.id}">${collaborateur.nom}</option>-->
                                            <option value="${collaborateur.id}" <c:if test="${selectedCollaborateurs.contains(collaborateur.id)}">selected</c:if>>${collaborateur.nom}</option>
                                        </c:forEach>
                                    </select>
                                    <br><br>

                                </div>
                            </div>
                            <br>
                            <div class="combobox-container">
                                <div class="combobox">
                                    <label for="multi-select">Partenaire lié à la prestation</label>
                                    <br>
                                    <select id="partenaire" name="partenaire" multiple>
                                        <option value="">-- Aucun --</option>
                                        <c:forEach var="partenaire" items="${partnenaireList}">
                                            <!--<option value="${partenaire.id}">${partenaire.nom}</option>-->
                                            <option value="${partenaire.id}" <c:if test="${selectedPartenaires.contains(partenaire.id)}">selected</c:if>>${partenaire.nom}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <br>
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

