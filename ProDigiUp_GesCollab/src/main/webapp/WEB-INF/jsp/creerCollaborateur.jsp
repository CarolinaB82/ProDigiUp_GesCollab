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
                        <input type="text" id="genre" name="genre">
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
                        <label for="date_de_naissance">Date de naissance</label>
                        <input type="text" id="date_de_naissance" name="date_de_naissance">
                        <div class="error">${requestScope.errors.date_de_naissance}</div>
                    </div>
                    <div>
                        <label for="numero_voie">Numéro de voie</label>
                        <input type="text" id="numero_voie" name="numero_voie">
                        <div class="error">${requestScope.errors.numero_voie}</div>
                    </div>
                    <div>
                        <label for="adresse">Adresse</label>
                        <input type="text" id="adresse" name="adresse">
                        <div class="error">${requestScope.errors.adresse}</div>
                    </div>
                    <div>
                        <label for="code_postal">Code Postal</label>
                        <input type="text" id="code_postal" name="code_postal"pattern="\d{1,10}" maxlength="5" required>
                        <div class="error">${requestScope.errors.code_postal}</div>
                    </div>
                    <div>
                        <label for="ville">Ville</label>
                        <input type="text" id="ville" name="ville">
                        <div class="error">${requestScope.ville}</div>
                    </div>
                    <div>
                        <label for="telephone_personnel">Téléphone personnel</label>
                        <input type="text" id="telephone_personnel" name="telephone_personnel"pattern="\d{1,10}" maxlength="10" required>
                        <div class="error">${requestScope.errors.telephone_personnel}</div>
                    </div>
                    <div>
                        <label for="statut">Statut (CDD, CDI, CDD Tremplin, Stage)</label>
                        <input type="text" id="statut" name="statut">
                        <div class="error">${requestScope.errors.statut}</div>
                    </div>
                    <div>
                        <label for="categorie">Catégorie (ETAM, Cadre)</label>
                        <input type="text" id="categorie" name="categorie">
                        <div class="error">${requestScope.errors.categorie}</div>
                    </div>
                    <div>
                        <label for="rqth">RQTH (oui, non, en cours)</label>
                        <input type="text" id="rqth" name="rqth">
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
</html>
