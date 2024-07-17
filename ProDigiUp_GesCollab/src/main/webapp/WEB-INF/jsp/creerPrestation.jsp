<%-- 
    Document   : creerPrestation
    Created on : 27 juin 2024, 10:55:21
    Author     : cberge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>


<!DOCTYPE html>
<html>
    <head>
        <%-- Définir une variable pour indiquer que ce n'est pas la page d'accueil --%>
        <c:set var="notHome" value="true" />
        <link rel="shortcut icon" href="<c:url value="/assets/img/favicon.png"/>" type="image/x-icon"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <title>créer prestation</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        
        <main>
            <form action="/ProDigiUp_GesCollab/creer_prestation" method="post">                  
                <div>${requestScope.message}</div>
                <div class="error-message">${requestScope.errorMsg}</div>
                <fieldset>
                    <legend>Nouvelle Prestation</legend>
                    <div>
                        <label for="siglum_presta">Siglum Prestation</label>
                        <!-- pour garder en memoire les champs deja remplis si erreur quelque part-->

                        <input type="text" id="siglum_presta" name="siglum_presta"  

                               value="${requestScope.prestation != null ? requestScope.prestation.siglum_presta : ''}" 
                               class="${not empty requestScope.errors.siglum_presta ? 'error-input' : ''}"

                               >
                        <div class="error-details-message">${requestScope.errors.siglum_presta}</div>
                    </div>
                    <br>
                    <div>
                        <label for="num_affaire">Numéro d'affaire Prestation</label>
                        <!-- pour garder en memoire les champs deja remplis si erreur quelque part-->

                        <input type="text" id="num_affaire" name="num_affaire"  
                               required
                               value="${requestScope.prestation != null ? requestScope.prestation.num_affaire : ''}" 
                               class="${not empty requestScope.errors.num_affaire ? 'error-input' : ''}"

                               >
                        <div class="error-details-message">${requestScope.errors.num_affaire}</div>
                    </div>
                    <br>
                    <div>
                        <label for="nom">Nom Prestation</label>
                        <input type="text" id="nom_presta" name="nom_presta"
                               required
                               value="${requestScope.prestation != null ? requestScope.prestation.nom_presta : ''}"
                               class="${not empty requestScope.errors.nom_presta ? 'error-input' : ''}"

                               >
                        <div class="error-details-message">${requestScope.errors.nom_presta}</div>
                    </div>
                    <br><br>
                    <div>
                        <label for="ref_fact_partenaire">Référent facturation partenaire</label>
                        <input type="text" id="ref_fact_partenaire" name="ref_fact_partenaire"
                               pattern="[a-zA-ZÀ-ÿ' ]*"

                               title="Veuillez saisir uniquement des lettres (A-Z, a-z)"
                               value="${requestScope.prestation != null ? requestScope.prestation.ref_fact_partenaire : ''}"
                               class="${not empty requestScope.errors.ref_fact_partenaire ? 'error-input' : ''}"
                               >
                        <div class="error-details-message">${requestScope.errors.ref_fact_partenaire}</div>
                    </div>
                    <br>

                    <div>
                        <label for="mail_partenaire">Mail partenaire</label>
                        <input type="email" id="mail_partenaire" name="mail_partenaire"
                               pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}"

                               title="Veuillez saisir une adresse e-mail valide"
                               value="${requestScope.prestation != null ? requestScope.prestation.mail_partenaire : ''}"
                               class="${not empty requestScope.errors.mail_partenaire ? 'error-input' : ''}"

                               >
                        <div class="error-details-message">${requestScope.errors.mail_partenaire}</div>
                    </div>
                    <br><br>
                    <div>
                        <label for="ref_fact_airbus">Référent facturation Airbus</label>
                        <input type="text" id="ref_fact_airbus" name="ref_fact_airbus"
                               pattern="[a-zA-ZÀ-ÿ' ]*"

                               title="Veuillez saisir uniquement des lettres (A-Z, a-z)"value="${requestScope.prestation != null ? requestScope.prestation.ref_fact_airbus : ''}"
                               class="${not empty requestScope.errors.ref_fact_airbus ? 'error-input' : ''}"

                               >
                        <div class="error-details-message">${requestScope.errors.ref_fact_airbus}</div>
                    </div>
                    <br>
                    <div>
                        <label for="mail_airbus">Mail de contact Airbus</label>
                        <input type="email" id="mail_airbus" name="mail_airbus"
                               value="${requestScope.prestation != null ? requestScope.prestation.mail_airbus : ''}"
                               class="${not empty requestScope.errors.mail_airbus ? 'error-input' : ''}"

                               >
                        <div class="error">${requestScope.errors.mail_airbus}</div>
                    </div>
                    <br>

                    <div class="dropdown-container">
                        <label for="responsable">Responsable activité</label>
                        <div class="dropdown">
                            <button type="button" class="dropdown-toggle">Sélectionner Responsable(s)</button>
                            <div class="dropdown-menu">
                                <c:forEach var="responsable" items="${responsableActiviteList}">
                                    <label>
                                        <input type="checkbox" name="responsable" value="${responsable.id}">
                                        ${responsable.nom}
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="dropdown-container">
                        <label for="collaborateur">Son Collaborateur</label>
                        <div class="dropdown">
                            <button type="button" class="dropdown-toggle">Sélectionner Collaborateur(s)</button>
                            <div class="dropdown-menu">
                                 <label>
                                    <input type="checkbox" name="collaborateur" value="none" id="checkbox-none">
                                    Aucun
                                </label>
                                <c:forEach var="collaborateur" items="${collaborateurList}">
                                    <label>
                                        <input type="checkbox" name="collaborateur" value="${collaborateur.id}">
                                        ${collaborateur.nom} ${collaborateur.prenom}
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="dropdown-container">
                        <label for="partenaire">Son Partenaire</label>
                        <div class="dropdown">
                            <button type="button" class="dropdown-toggle">Sélectionner Partenaire(s)</button>
                            <div class="dropdown-menu">
                                <c:forEach var="partenaire" items="${partenaireList}">
                                    <label>
                                        <input type="checkbox" name="partenaire" value="${partenaire.id}">
                                        ${partenaire.nom}
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                    </div>


                    <div class="button-container">
                        <input  type="submit" value="Valider">
                        <input type="reset" value="Annuler">
                    </div>
                </fieldset>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            </form>
        </main>
    </body>
    <%@include file="/WEB-INF/jspf/footer.jsp" %>
     <script>
        document.addEventListener('DOMContentLoaded', function () {
            const dropdownToggles = document.querySelectorAll('.dropdown-toggle');
            const dropdownMenus = document.querySelectorAll('.dropdown-menu');
            const checkboxNone = document.getElementById('checkbox-none');

            dropdownToggles.forEach(toggle => {
                toggle.addEventListener('click', function (event) {
                    event.stopPropagation(); // Empêche la fermeture du menu quand on clique sur le bouton de sélection
                    this.classList.toggle('active');
                    const dropdownMenu = this.nextElementSibling;
                    if (dropdownMenu.style.display === 'block') {
                        dropdownMenu.style.display = 'none';
                    } else {
                        dropdownMenu.style.display = 'block';
                    }
                });
            });

            // Empêche la fermeture du menu lorsque l'on clique sur une option
            dropdownMenus.forEach(menu => {
                menu.addEventListener('click', function (event) {
                    event.stopPropagation();
                });
            });

            // Fermer le menu déroulant si on clique en dehors
            window.addEventListener('click', function () {
                dropdownToggles.forEach(toggle => {
                    toggle.classList.remove('active');
                    const dropdownMenu = toggle.nextElementSibling;
                    dropdownMenu.style.display = 'none';
                });
            });

            // Fermer le menu déroulant lorsque "Aucun" est coché
            checkboxNone.addEventListener('change', function () {
                if (this.checked) {
                    dropdownToggles.forEach(toggle => {
                        toggle.classList.remove('active');
                        const dropdownMenu = toggle.nextElementSibling;
                        dropdownMenu.style.display = 'none';
                    });
                }
            });
        });
    </script>
</html>
