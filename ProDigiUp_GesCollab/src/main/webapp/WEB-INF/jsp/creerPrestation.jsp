<%-- 
    Document   : creerPrestation
    Created on : 27 juin 2024, 10:55:21
    Author     : cberge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/styles.css">
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <main>
            <form action="/ProDigiUp_GesCollab/creer_prestation" method="post">                  
                <div>${requestScope.message}</div>
                <div class="error-message">${requestScope.errorMsg}</div>
                <fieldset>
                    <legend>Nouvelle Prestation</legend>
                    <div>
                        <label for="siglum">Siglum Prestation</label>
                        <!-- pour garder en memoire les champs deja remplis si erreur quelque part-->

                        <input type="text" id="siglum" name="siglum"  
                               pattern="[0-9]*"
                               required
                               title="Veuillez saisir uniquement des chiffres (0-9)" 
                               value="${requestScope.prestation != null ? requestScope.prestation.siglum_presta : ''}" 
                               class="${not empty requestScope.errors.siglum_presta ? 'error-input' : ''}"

                               >
                        <div class="error-details-message">${requestScope.errors.siglum_presta}</div>
                    </div>

                    <div>
                        <label for="nom">Nom Prestation</label>
                        <input type="text" id="nom" name="nom"
                               pattern="[a-zA-ZÀ-ÿ' ]*"
                               required
                               title="Veuillez saisir uniquement des lettres (A-Z, a-z)"

                               value="${requestScope.prestation != null ? requestScope.prestation.nom_presta : ''}"
                               class="${not empty requestScope.errors.nom_presta ? 'error-input' : ''}"

                               >
                        <div class="error-details-message">${requestScope.errors.nom_presta}</div>
                    </div>
                    <div>
                        <label for="prenom">Référent facturation partenaire</label>
                        <input type="text" id="prenom" name="prenom"
                               pattern="[a-zA-ZÀ-ÿ' ]*"
                               required
                               title="Veuillez saisir uniquement des lettres (A-Z, a-z)"
                               value="${requestScope.prestation != null ? requestScope.prestation.ref_fact_partenaire : ''}"
                               class="${not empty requestScope.errors.ref_fact_partenaire ? 'error-input' : ''}"

                               >
                        <div class="error-details-message">${requestScope.errors.ref_fact_partenaire}</div>
                    </div>

                    <div>
                        <label for="telephone_personnel">Référent facturation Airbus</label>
                        <input type="text" id="telephone_personnel" name="telephone_personnel"
                               pattern="\d{10}" 
                               maxlength="10" 
                               required  
                               title="Veuillez saisir exactement 10 chiffres"

                               value="${requestScope.prestation != null ? requestScope.prestation.ref_fact_airbus : ''}"
                               class="${not empty requestScope.errors.ref_fact_airbus ? 'error-input' : ''}"

                               >
                        <div class="error-details-message">${requestScope.errors.ref_fact_airbus}</div>
                    </div>


                    <form action="/submit-form" method="POST">

                        <div class="combobox-container">
                            <div class="combobox">
                                <label for="multi-select">Choisissez son Responsable activité :</label>
                                
                                
                                <br>
                            <select id="multi-select-responsable_activite" name="responsable_activiteOptions" multiple>
                                    <option value="option1">Option 1</option>
                                    <option value="option2">Option 2</option>
                                    <option value="option3">Option 3</option>
                                    <option value="option4">Option 4</option>
                                </select>
                                <br><br>
                                <input type="submit" value="Soumettre Responsable activité"> 
                            </div>
                            <div class="combobox">
                                <form action="/submit-form" method="POST">
                                    
                                    <label for="multi-select">Choisissez son Collaborateur :</label>
                                    <br>
                                <select id="multi-select-collaborateur" name="collaborateurOptions" multiple>
                                        <option value="option1">Option 1</option>
                                        <option value="option2">Option 2</option>
                                        <option value="option3">Option 3</option>
                                        <option value="option4">Option 4</option>
                                    </select>
                                    <br><br>
                                    <input type="submit" value="Soumettre Collaborateur"> 
                                    </div>

                                    <div class="combobox">
                                        <form action="/submit-form" method="POST">
                                            <label for="multi-select">Choisissez son Partenaire :</label>
                                <select id="multi-select-partenaire" name="partenaireOptions" multiple>
                                                <option value="option1">Option 1</option>
                                                <option value="option2">Option 2</option>
                                                <option value="option3">Option 3</option>
                                                <option value="option4">Option 4</option>
                                            </select>
                                            <br><br>
                                            <input type="submit" value="Soumettre Partenaire"> 

                                            </div>


                                            <p>Merci de remplir tous les champs</p>
                                            </fieldset>
                                            <div>
                                                <input type="submit" value="Envoyer">
                                                <input type="reset" value="Annuler">
                                            </div>

                                            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

                                        </form>
                                        </main>
                                        </body>
                                        <%@include file="/WEB-INF/jspf/footer.jsp" %>
                                        </html>
