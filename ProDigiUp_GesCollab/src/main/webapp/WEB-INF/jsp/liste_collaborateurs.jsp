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
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
       <main>
    <h2>Liste des collaborateurs, de leurs responsables et de leurs partenaires associés</h2>

    <!--<table border="1" style="border: 3px solid purple; border-collapse: collapse;">-->
     <table border="1" class="custom-table">
        
        
        <thead>
        
            <tr>
                <th>Matricule</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Statut</th>
                <th>En activé</th>
                <th>Nom Responsable Activité</th>
                <th>Nom Partenaire</th>
            </tr>
        
        </thead>
        <tbody>
             
            <c:forEach var="collaborateur" items="${requestScope.collaborateurs}">
    <c:set var="raPartenairesSize" value="${collaborateur.raPartenaires != null ? collaborateur.raPartenaires.size() : 0}" />
    <c:if test="${raPartenairesSize == 0}">
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
    <c:forEach var="raPartenaire" items="${collaborateur.raPartenaires}" varStatus="status">
        <tr class="${status.index == 0 ? 'collaborateur-row' : ''} ${status.index == raPartenairesSize - 1 ? 'collaborator-end-row' : ''}">
            <c:if test="${status.index == 0}">
                <td rowspan="${raPartenairesSize}">
                    <c:out value="${collaborateur.collaborateur.matricule}"/>
                </td>
               
                <td rowspan="${raPartenairesSize}">
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
                <td rowspan="${raPartenairesSize}">
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
</main>
</html>
