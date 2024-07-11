<%-- 
    Document   : liste_collaborateur
    Created on : 17 juin 2024, 20:13:00
    Author     : cberge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix= "c" %>

<!DOCTYPE html>
<html>
    <head>
        <%-- Définir une variable pour indiquer que ce n'est pas la page d'accueil --%>
        <c:set var="notHome" value="true" />
        <title>Liste collaborateurs</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <main>


            <br><br>
            <h2>Liste des collaborateurs, de leurs responsables et de leurs partenaires associés</h2>
            <br><br><br>
            <!--<table border="1" style="border: 3px solid purple; border-collapse: collapse;">-->
            <table border="1" class="custom-table neutral-links" style='text-decoration: none'>
                <thead>
                    <tr>
                        <th>Matricule</th>
                        <th>🔗Nom</th>
                        <th>Prénom</th>
                        <th>Statut</th>
                        <th>En activé</th>
                        <th>🔗Prestation</th>
                        <th>🔗Responsable d'Activité</th>
                        <th>🔗Partenaire</th>

                    </tr>

                </thead>
                <tbody>

                    <c:forEach var="collaborateur" items="${requestScope.collaborateurs}">
                        <c:set var="prestationSize" value="${collaborateur.prestRaPart != null ? collaborateur.prestRaPart.size() : 0}" />
                        <c:if test="${prestationSize == 0}">
                            <tr class="collaborateur-row collaborator-end-row">
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
                                    <a href="<c:url value='/prestation?id=${preRaPar.prestation.id}'/>">
                                        <c:out value="${preRaPar.prestation.nom_presta}"/>                                        </a>

                                </td>
                                <td>
                                    <a href="<c:url value='/afficher_ra?id=${preRaPar.resp.id}'/>">
                                        <c:out value="${preRaPar.resp.nom}"/>
                                </td>
                                <td>
                                    <a href="<c:url value='/partenaire?id=${preRaPar.part.id}'/>">
                                        <c:out value="${preRaPar.part.nom}"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </tbody>
            </table>
        </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>
    </body>
</html>
