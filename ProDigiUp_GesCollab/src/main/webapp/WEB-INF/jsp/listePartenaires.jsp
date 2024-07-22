<%-- 
    Document   : listePartenaires
    Created on : 22 juil. 2024, 11:31:38
    Author     : cberge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix= "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html>
    <head>
        <%-- Définir une variable pour indiquer que ce n'est pas la page d'accueil --%>
        <c:set var="notHome" value="true" />
        <title>Liste des partenaires</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <link rel="shortcut icon" href="<c:url value="/assets/img/favicon.png"/>" type="image/x-icon"/>
        <style>
            .tooltip {
                position: relative;
                display: inline-block;
                cursor: pointer;
            }

            .tooltip .tooltiptext {
                visibility: hidden;
                width: 120px;
                background-color: #555;
                color: #fff;
                text-align: center;
                border-radius: 6px;
                padding: 5px 0;
                position: absolute;
                z-index: 1;
                bottom: 125%;
                left: 50%;
                margin-left: -60px;
                opacity: 0;
                transition: opacity 0.3s;
            }

            .tooltip:hover .tooltiptext {
                visibility: visible;
                opacity: 1;
                background-color: #A8B7DB;
                color: #17407B
            }
        </style>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const urlParams = new URLSearchParams(window.location.search);
                const deleteSuccess = urlParams.get('deleteSuccess');
                if (deleteSuccess === 'true') {
                    alert('La suppression a été effectuée avec succès.');
                }
            });
        </script>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <main>
            <h2>Liste des partenaires</h2>

            <br><br>
            <table border="1" class="custom-table neutral-links">
               <thead>
                    <tr>
                        <th>
                            <a href="?sort=nom&order=<c:out value='${param.order == "asc" ? "desc" : "asc"}'/>" 
                               class="tooltip">🔗Nom <c:if test="${param.sort == 'nom'}"><c:out value='${param.order == "asc" ? "↑" : "↓"}'/></c:if>
                                <span class="tooltiptext">Cliquez pour trier</span>
                            </a>
                        </th>
                        <th>Numéro de voie</th>
                        <th>Adresse</th>
                        <th>Code Postal</th>
                        <th>Ville</th> 
                        <th> Ra </th>
                    </tr>
                </thead>
                <tbody> 
                    <c:forEach var="partRa" items="${partenaires}">
                        <tr>
                            <td>
                                <a href="<c:url value='/partenaire?id=${partRa.partenaire.id}'/>">
                                    <c:out value="${partRa.partenaire.nom}"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${partRa.partenaire.numero_voie}"/>
                            </td>
                            <td>
                                <c:out value="${partRa.partenaire.adresse}"/>
                            </td>
                            <td>
                                <c:out value="${partRa.partenaire.code_postal}"/>
                            </td>
                            <td>
                                <c:out value="${partRa.partenaire.ville}"/>
                            </td>
                            <td>
                            <c:out value="${partRa.nomRa}"/> <!-- Afficher les noms des responsables d'activités -->
                        </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>
</html>
