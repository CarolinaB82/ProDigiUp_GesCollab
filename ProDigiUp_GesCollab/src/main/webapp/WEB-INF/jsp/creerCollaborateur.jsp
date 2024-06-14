<%-- 
    Document   : creerCollaborateur
    Created on : 12 juin 2024, 09:15:40
    Author     : cberge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>

        <main>
            <form action="/ProDigiUp_GesCollab/creer_collaborateur" method="post">                  
                <div>${requestScope.message}</div>
                <div>${requestScope.errorMsg}</div>
                <fieldset>
                    <legend>Nouveau Collaborateur</legend>
                    <div>
                        <label for="matricule">Matricule</label>
                        <input type="text" id="matricule" name="matricule">
                        <div class="error">${requestScope.errors.matricule}</div>
                    </div>
                    <div>
                        <label for="genre">Genre</label>
                        <select id="genre" name="genre">
                            <option value="madame">Madame</option>
                            <option value="monsieur">Monsieur</option>
                        </select>
                        <div class="error">${requestScope.errors.genre}</div>
                    </div>
                    <div>
                        <label for="nom">Nom</label>
                        <input type="text" id="nom" name="nom">
                        <div class="error">${requestScope.errors.nom}</div>
                    </div>
                    <div>
                        <label for="prenom">Prénom</label>
                        <input type="text" id="prenom" name="prenom">
                        <div class="error">${requestScope.errors.prenom}</div>
                    </div>

                    <div>
                        <label for="telephone_personnel">Téléphone personnel</label>
                        <input type="text" id="telephone_personnel" name="telephone_personnel"pattern="\d{1,10}" maxlength="10" required>
                        <div class="error">${requestScope.errors.telephone_personnel}</div>
                    </div>
                    <div>
                        <label for="statut">Statut</label>
                        <select id="statut" name="statut">
                            <option value="cdd">CDD</option>
                            <option value="cdi">CDI</option>
                            <option value="cdd_tremplin">CDD Tremplin</option>
                            <option value="stage">Stage</option>
                        </select>
                        <div class="error">${requestScope.errors.statut}</div>
                    </div>
                    <div>
                        <label for="categorie">Catégorie</label>
                        <select id="categorie" name="categorie">
                            <option value="etam">ETAM</option>
                            <option value="cadre">Cadre</option>
                        </select>
                        <div class="error">${requestScope.errors.categorie}</div>
                    </div>
                    <div>
                        <label for="rqth">RQTH</label>
                        <select id="rqth" name="rqth">
                            <option value="etam">oui</option>
                            <option value="cadre">non</option>
                        </select>
                        <div class="error">${requestScope.errors.rqth}</div>
                    </div>
                    <div>
                        <label for="metier">Métier</label>
                        <input type="text" id="metier" name="metier">
                        <div class="error">${requestScope.errors.metier}</div>
                    </div>

                    </div>
                    <p>Merci de remplir tous les champs</p>
                </fieldset>
                <div>
                    <input type="submit" value="Envoyer">
                    <input type="reset" value="Annuler">
                </div>
            </form>
        </main>
    </body>
    <%@include file="/WEB-INF/jspf/footer.jsp" %>
</html>
