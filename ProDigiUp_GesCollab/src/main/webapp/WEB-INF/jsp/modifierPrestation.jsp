<%-- 
    Document   : modifierPrestation
    Created on : 3 juil. 2024, 14:22:03
    Author     : asolanas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <c:set var="notHome" value="true" />
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifier Prestation</title>
        <link rel="shortcut icon" href="<c:url value="/assets/img/favicon.png"/>" type="image/x-icon"/>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>

        <main>
            <form action="<c:url value="/modifierPrestation"/>" method="post">                 
                <div>${requestScope.message}</div>
                <div class="error-message">${requestScope.errorMsg}</div>
                <fieldset>
                    <legend>Modifier Prestation</legend>
                    <input type="hidden" name="id" value="${prestation.id}">

                    <!-- Siglum Presta -->
                    <div>
                        <label for="siglum_presta">Siglum Presta:</label>
                        <input type="text" id="siglum_presta" name="siglum_presta" value="${prestation.siglum_presta}">
                    </div>

                    <!-- Num Affaire -->
                    <div>
                        <label for="num_affaire">Num Affaire:</label>
                        <input type="text" id="num_affaire" name="num_affaire" value="${prestation.num_affaire}">
                    </div>

                    <!-- Nom Presta -->
                    <div>
                        <label for="nom_presta">Nom Presta:</label>
                        <input type="text" id="nom_presta" name="nom_presta" value="${prestation.nom_presta}">
                    </div>

                    <!-- Ref Fact Partenaire -->
                    <div>
                        <label for="ref_fact_partenaire">Ref Fact Partenaire:</label>
                        <input type="text" id="ref_fact_partenaire" name="ref_fact_partenaire" value="${prestation.ref_fact_partenaire}">
                    </div>

                    <!-- Mail Partenaire -->
                    <div>
                        <label for="mail_partenaire">Mail Partenaire:</label>
                        <input type="email" id="mail_partenaire" name="mail_partenaire" value="${prestation.mail_partenaire}">
                    </div>

                    <!-- Ref Fact Airbus -->
                    <div>
                        <label for="ref_fact_airbus">Ref Fact Airbus:</label>
                        <input type="text" id="ref_fact_airbus" name="ref_fact_airbus" value="${prestation.ref_fact_airbus}">
                    </div>

                    <!-- Mail Airbus -->
                    <div>
                        <label for="mail_airbus">Mail Airbus:</label>
                        <input type="email" id="mail_airbus" name="mail_airbus" value="${prestation.mail_airbus}">
                    </div>

                    <!-- Responsable Activité -->
                    <div class="dropdown-container">
                        <label for="responsable">Responsable activité</label>
                        <div class="dropdown">
                            <button type="button" class="dropdown-toggle">Sélectionner Responsable(s)</button>
                            <div class="dropdown-menu">
                                <c:forEach var="responsable" items="${responsablesActivite}">
                                    <label>
                                        <input type="checkbox" name="responsableIds" value="${responsable.id}" <c:if test="${fn:contains(prestation.responsableIds, responsable.id)}">checked</c:if>>
                                        ${responsable.nom}
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                    <!-- Collaborateur -->
                    <div class="dropdown-container">
                        <label for="collaborateur">Son Collaborateur</label>
                        <div class="dropdown">
                            <button type="button" class="dropdown-toggle">Sélectionner Collaborateur(s)</button>
                            <div class="dropdown-menu">
                                <c:forEach var="collaborateur" items="${collaborateurs}">
                                    <label>
                                        <input type="checkbox" name="collaborateurIds" value="${collaborateur.id}" <c:if test="${fn:contains(prestation.collaborateurIds, collaborateur.id)}">checked</c:if>>
                                        ${collaborateur.nom} ${collaborateur.prenom}
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                    <!-- Partenaire -->
                    <div class="dropdown-container">
                        <label for="partenaire">Son Partenaire</label>
                        <div class="dropdown">
                            <button type="button" class="dropdown-toggle">Sélectionner Partenaire(s)</button>
                            <div class="dropdown-menu">
                                <c:forEach var="partenaire" items="${partenaires}">
                                    <label>
                                        <input type="checkbox" name="partenaireIds" value="${partenaire.id}" <c:if test="${fn:contains(prestation.partenaireIds, partenaire.id)}">checked</c:if>>
                                        ${partenaire.nom}
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                    <div class="button-container">
                        <input type="submit" value="Enregistrer">
                        <button type="button" onclick="window.location.href='<c:url value="/liste_collaborateurs"/>'">Annuler</button>
                    </div>

                </fieldset>
            </form>
        </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const dropdownToggles = document.querySelectorAll('.dropdown-toggle');

                dropdownToggles.forEach(toggle => {
                    toggle.addEventListener('click', function (event) {
                        event.stopPropagation();
                        this.classList.toggle('active');
                        const dropdownMenu = this.nextElementSibling;
                        if (dropdownMenu.style.display === 'block') {
                            dropdownMenu.style.display = 'none';
                        } else {
                            dropdownMenu.style.display = 'block';
                        }
                    });
                });

                const dropdownMenus = document.querySelectorAll('.dropdown-menu');
                dropdownMenus.forEach(menu => {
                    menu.addEventListener('click', function (event) {
                        event.stopPropagation();
                    });
                });

                window.addEventListener('click', function () {
                    dropdownToggles.forEach(toggle => {
                        toggle.classList.remove('active');
                        const dropdownMenu = toggle.nextElementSibling;
                        dropdownMenu.style.display = 'none';
                    });
                });
            });
        </script>
    </body>
</html>
