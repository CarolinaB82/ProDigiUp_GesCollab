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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <title>listing</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <main>
            <fieldset>
                <legend>Liste des collaborateurs, de leurs responsables et de leurs partenaires associés</legend>

                <!--<table border="1" style="border: 3px solid purple; border-collapse: collapse;">-->
                <table border="1" class="custom-table">
                    <thead>
                        <tr>
                            <th>Matricule</th>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Statut</th>
                            <th>Nom Responsable Activité</th>
                            <th>Nom Partenaire</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="collaborateur" items="${requestScope.collaborateurs}">
                            <c:set var="raPartenairesSize" value="${collaborateur.raPartenaires.size()}" />
                            <c:forEach var="raPartenaire" items="${collaborateur.raPartenaires}" varStatus="status">
                                <tr class="${status.index == 0 ? 'collaborateur-row' : ''} ${status.index == raPartenairesSize - 1 ? 'collaborator-end-row' : ''} '/>">
                                    <c:if test="${status.index == 0}">

                                        <td rowspan="${raPartenairesSize}">

                                            <c:out value="${collaborateur.collaborateur.matricule}"/>
                                            </a>
                                        </td>
                                        <td rowspan="${raPartenairesSize}">
                                           <!-- <a href="<c:url value='${collaborateur.collaborateur.nom}'/>">-->
                                            <a href="<c:url value='/collaborateur?id=${collaborateur.collaborateur.id}'/>">
                                                <c:out value="${collaborateur.collaborateur.nom}"/>
                                            </a>

                                        </td>
                                        <td rowspan="${raPartenairesSize}">
                                            <c:out value="${collaborateur.collaborateur.prenom}"/>
                                        </td>
                                        <td rowspan="${raPartenairesSize}">
                                            <c:out value="${collaborateur.collaborateur.statut}"/>
                                        </td>
                                    </c:if>
                                    <td>
                                        <c:out value="${raPartenaire.ra.nom}"/>
                                    </td>
                                    <td>
                                        <c:forEach var="partenaire" items="${raPartenaire.partenaires}">
                                            <c:out value="${partenaire.nom}"/><br/>
                                        </c:forEach>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:forEach>

                    </tbody>
                </table>
            </fieldset>
            <%@include file="/WEB-INF/jspf/footer.jsp" %>
        </main>
</html>