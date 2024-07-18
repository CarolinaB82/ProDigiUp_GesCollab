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
                        <label for="num_affaire">*Numéro d'affaire Prestation</label>
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
                        <label for="nom">*Nom Prestation</label>
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

                    <div class="combobox-container">
                        <div class="combobox">
                            <label for="multi-select-responsable_activite">Son Responsable activité</label>

                            <select id="id_ra" name="id_ra">
                                <option value="">-- Aucun --</option>
                                <c:forEach var="responsable" items="${responsableActiviteList}">
                                    <option value="${responsable.id}">${responsable.nom}</option>
                                </c:forEach>
                            </select>
                            <br><br>
                        </div>
                        <div class="combobox">

                            <label for="multi-select">Son Collaborateur</label>

                            <select id="id_collaborateur" name="id_collaborateur">
                                <option value="">-- Aucun --</option>
                                <c:forEach var="collaborateur" items="${collaborateurList}">
                                    <option value="${collaborateur.id}">${collaborateur.nom} ${collaborateur.prenom}</option>
                                </c:forEach>
                            </select>
                            <br><br>
                        </div>

                        <div class="combobox">
                            <label for="multi-select">Son Partenaire</label>

                            <select id="id_partenaire" name="id_partenaire">
                                <c:forEach var="partenaire" items="${partenaireList}">
                                    <option value="${partenaire.id}">${partenaire.nom}</option>
                                </c:forEach>
                            </select>
                            <br><br><br>

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

</html>
