<%-- 
    Document   : CreerResponsableActivite
    Created on : 12 juin 2024, 15:07:26
    Author     : asolanas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Créer RA</title>
    </head>
    <body>
        <form action="<c:url value="/ProDigiUp_GesCollab/creer_ra" />" method="post">
            <div>${requestScope.message}</div>
            <div>${requestScope.errMsg}</div>
            <fieldset>
                <legend>Créer un responsable d'activité</legend>
                <div>
                    <label for="matricule">Matricule</label>
                    <input type="number" id="matricule" name="matricule"
                    <div class="error">${requestScope.errors.matricule}</div>
                </div>
                <div>
                    <label for="nom">Nom</label>
                    <input type="text" id="nom" name="nom"
                    <div class="error">${requestScope.errors.nom}</div>
                </div>
                <div>
                    <label for="prenom">Prénom</label>
                    <input type="text" id="prenom" name="prenom"
                    <div class="error">${requestScope.errors.prenom}</div>
                </div>
                <div>
                    <label for="date_de_naissance">Date de Naissance</label>
                    <input type="date" id="date_de_naissance" name="date_de_naissance"
                    <div class="error">${requestScope.errors.date_de_naissance}</div>
                </div>
                <div>
                    <label for="num_voie">Numéro de voie</label>
                    <input type="text" id="num_voie" name="num_voie"
                    <div class="error">${requestScope.errors.num_voie}</div>
                </div>
                <div>
                    <label for="adresse">Adresse</label>
                    <input type="text" id="adresse" name="adresse"
                    <div class="error">${requestScope.errors.adresse}</div>
                </div>
                <div>
                    <label for="code_postal">Code Postal</label>
                    <input type="number" id="code_postal" name="code_postal"
                    <div class="error">${requestScope.errors.code_postal}</div>
                </div>
                <div>
                    <label for="ville">Ville</label>
                    <input type="text" id="ville" name="ville"
                    <div class="error">${requestScope.errors.ville}</div>
                </div>
                <div>
                    <label for="tel_pro">Téléphone Professionnel</label>
                    <input type="text" id="tel_pro" name="tel_pro"
                    <div class="error">${requestScope.errors.tel_pro}</div>
                </div>
                <div>
                    <label for="tel_perso">Téléphone Personnel</label>
                    <input type="text" id="tel_perso" name="tel_perso"
                    <div class="error">${requestScope.errors.tel_perso}</div>
                </div>
            </fieldset>
                <div>
                    <input type="submit" value='Valider'>
                    <input type="reset" value="Annuler">
                </div>

        </form>
    </body>
</html>
