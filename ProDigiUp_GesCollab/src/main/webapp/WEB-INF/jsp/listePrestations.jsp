<%-- 
    Document   : listePrestations
    Created on : 8 juil. 2024, 13:39:00
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
        <title>Liste des prestations</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <link rel="shortcut icon" href="<c:url value="/assets/img/favicon.png"/>" type="image/x-icon"/>

        <style>
            .tooltip {
                position: relative;
                display: inline-block;
                cursor: pointer;
                z-index: 1;
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
                z-index: 50;
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
            <div class="table-responsive">
                
                <h2>Liste des prestations</h2>
                <br><br><br>
                <table border="1" class="custom-table neutral-links">
                    <thead>
                        <tr>
                            <th>🔗Siglum prestation</th>
                            <th>Numéro d'affaire</th>
                            <th>
                                <a href="?sort=nom&order=<c:out value='${param.order == "asc" ? "desc" : "asc"}'/>" class="tooltip">
                                    Nom prestation <c:if test="${param.sort == 'nom'}"><c:out value='${param.order == "asc" ? "↑" : "↓"}'/></c:if>
                                        <span class="tooltiptext">Cliquez pour trier</span>
                                    </a>
                                </th>
                                <th>Référent facture partenaire</th>
                                <th>Mail partenaire</th>
                                <th>Réf fact Airbus</th>
                                <th>Mail Airbus</th>     
                                <th>Partenaire</th>
                                <th>Responsable activité</th>
                                <th>Collaborateur</th>
                            </tr>
                        </thead>

                        <tbody>
                        <c:set var="prestationSize" value="${prestations}" />

                        <c:forEach var="prestation" items="${requestScope.prestations}">
                            <tr>
                                <td rowspan="${prestationSize}">
                                    <a href="<c:url value='/prestation?id=${prestation.prestation.id}'/>">
                                        <c:out value="${prestation.prestation.siglum_presta}"/>
                                    </a>
                                </td>
                                <td rowspan="${prestationSize}">
                                    <c:out value="${prestation.prestation.num_affaire}"/>
                                </td>
                                <td rowspan="${prestationSize}">
                                    <c:out value="${prestation.prestation.nom_presta}"/>
                                </td>
                                <td rowspan="${prestationSize}">
                                    <c:out value="${prestation.prestation.ref_fact_partenaire}"/>
                                </td>
                                <td rowspan="${prestationSize}">
                                    <c:out value="${prestation.prestation.mail_partenaire}"/>
                                </td>
                                <td rowspan="${prestationSize}">
                                    <c:out value="${prestation.prestation.ref_fact_airbus}"/>
                                </td>
                                <td rowspan="${prestationSize}">
                                    <c:out value="${prestation.prestation.mail_airbus}"/>
                                </td>
                                <td rowspan="${prestationSize}">
                                    <c:out value="${prestation.nomPartenaire}"/>
                                </td>
                                <td rowspan="${prestationSize}">
                                    <c:out value="${prestation.nomRa}"/>
                                </td>
                                <td rowspan="${prestationSize}">
                                    <c:out value="${prestation.nomCollab}"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>
</html>


