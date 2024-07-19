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
        <link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jsp" %>
        <main>
            <h2>Liste des prestations</h2>

            <!--<table border="1" style="border: 3px solid purple; border-collapse: collapse;">-->
            <table border="1" class="custom-table">
<thead>

                    <tr>
                        <th>Siglum prestation</th>
                        <th>Numéro d'affaire</th>
                        <th>Nom prestation</th>
                        <th>Réf fact partenaire</th>
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
    </main>
        <%@include file="/WEB-INF/jspf/footer.jsp" %>
</body>
</html>
              

