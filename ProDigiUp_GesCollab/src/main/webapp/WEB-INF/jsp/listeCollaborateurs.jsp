<%-- 
    Document   : liste_collaborateur
    Created on : 17 juin 2024, 20:13:00
    Author     : cberge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <c:set var="notHome" value="true" />
        <title>Liste des collaborateurs</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <link rel="shortcut icon" href="<c:url value="/assets/img/favicon.png"/>" type="image/x-icon"/>
        <style>
            .tooltip {
                position: relative;
                display: inline-block;
                cursor: pointer;
                z-index: 10;
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
        <main >
            <div class="table-responsive">
                <br>
                <h2>Liste des collaborateurs</h2>
                <br><br><br>
                <table border="1" class="custom-table neutral-links ">
                    <thead>
                        <tr>
                            <th>
                                <a href="?sort=matricule&order=<c:out value="${param.order == 'asc' ? 'desc' : 'asc'}"/>"
                                   class="tooltip">Matricule <c:if test="${param.sort == 'matricule'}"><c:out value="${param.order == 'asc' ? '↑' : '↓'}"/></c:if>
                                       <span class="tooltiptext">Cliquez pour trier</span>
                                   </a>
                                </th>
                                <th>
                                    <a href="?sort=nom&order=<c:out value="${param.order == 'asc' ? 'desc' : 'asc'}"/>"
                                   class="tooltip">🔗Nom <c:if test="${param.sort == 'nom'}"><c:out value="${param.order == 'asc' ? '↑' : '↓'}"/></c:if>
                                       <span class="tooltiptext">Cliquez pour trier</span>
                                   </a>
                                </th>
                                <th>Prénom</th>
                                <th>Statut</th>
                                <th>
                                    <a href="?sort=activite&order=<c:out value="${param.order == 'asc' ? 'desc' : 'asc'}"/>"
                                   class="tooltip">En activité <c:if test="${param.sort == 'activite'}"><c:out value="${param.order == 'asc' ? '↑' : '↓'}"/></c:if>
                                       <span class="tooltiptext">Cliquez pour trier</span>
                                   </a>
                                </th>
                                <th>🔗Prestation</th>
                                <th>
                                    <a href="?sort=responsable&order=<c:out value="${param.order == 'asc' ? 'desc' : 'asc'}"/>"
                                   class="tooltip">Responsable d'Activité <c:if test="${param.sort == 'responsable'}"><c:out value="${param.order == 'asc' ? '↑' : '↓'}"/></c:if>
                                       <span class="tooltiptext">Cliquez pour trier</span>
                                   </a>
                                </th>
                                <th>
                                    <a href="?sort=partenaire&order=<c:out value="${param.order == 'asc' ? 'desc' : 'asc'}"/>"
                                   class="tooltip">🔗Partenaire <c:if test="${param.sort == 'partenaire'}"><c:out value="${param.order == 'asc' ? '↑' : '↓'}"/></c:if>
                                       <span class="tooltiptext">Cliquez pour trier</span>
                                   </a>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="collaborateur" items="${requestScope.collaborateurs}">
                            <c:set var="prestationSize" value="${collaborateur.prestRaPart != null ? collaborateur.prestRaPart.size() : 0}" />
                            <c:if test="${prestationSize == 0}">
                                <tr class="collaborateur-row collaborator-end-row neutral-links">
                                    <td>
                                        <c:out value="${collaborateur.collaborateur.matricule}"/>
                                    </td>
                                    <td>
                                        <a href="<c:url value='/collaborateur?id=${collaborateur.collaborateur.id}'/>">
                                            <c:out value="${collaborateur.collaborateur.nom}"/>
                                        </a>
                                    </td>
                                    <td>
                                        <c:out value="${collaborateur.collaborateur.prenom}"/>
                                    </td>
                                    <td>
                                        <c:out value="${collaborateur.collaborateur.statut}"/>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${collaborateur.prestationActive}">
                                                Oui
                                            </c:when>
                                            <c:otherwise>
                                                Non
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>-</td> <!-- Ajouter une cellule avec contenu par défaut -->
                                    <td>-</td> <!-- Ajouter une cellule avec contenu par défaut -->
                                    <td>-</td> <!-- Ajouter une cellule avec contenu par défaut -->
                                </tr>
                            </c:if>
                            <c:forEach var="preRaPar" items="${collaborateur.prestRaPart}" varStatus="status">
                                <tr class="${status.index == 0 ? 'collaborateur-row' : ''} ${status.index == prestationSize - 1 ? 'collaborator-end-row' : ''}">
                                    <c:if test="${status.index == 0}">
                                        <td rowspan="${prestationSize}">
                                            <c:out value="${collaborateur.collaborateur.matricule}"/>
                                        </td>
                                        <td rowspan="${prestationSize}">
                                            <a href="<c:url value='/collaborateur?id=${collaborateur.collaborateur.id}'/>">
                                                <c:out value="${collaborateur.collaborateur.nom}"/>
                                            </a>
                                        </td>
                                        <td rowspan="${prestationSize}">
                                            <c:out value="${collaborateur.collaborateur.prenom}"/>
                                        </td>
                                        <td rowspan="${prestationSize}">
                                            <c:out value="${collaborateur.collaborateur.statut}"/>
                                        </td>
                                        <td rowspan="${prestationSize}">
                                            <c:choose>
                                                <c:when test="${collaborateur.prestationActive}">
                                                    Oui
                                                </c:when>
                                                <c:otherwise>
                                                    Non
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </c:if>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty preRaPar.prestation.nom_presta}">
                                                <a href="<c:url value='/prestation?id=${preRaPar.prestation.id}'/>">
                                                    <c:out value="${preRaPar.prestation.nom_presta}"/>
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                -
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty preRaPar.resp.nom}">
                                                <a href="<c:url value='/afficher_ra?id=${preRaPar.resp.id}'/>">
                                                    <c:out value="${preRaPar.resp.nom}"/>
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                -
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty preRaPar.part.nom}">
                                                <a href="<c:url value='/partenaire?id=${preRaPar.part.id}'/>">
                                                    <c:out value="${preRaPar.part.nom}"/>
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                -
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                    </tbody>
                </table>
            </div >


        </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>
</html>

