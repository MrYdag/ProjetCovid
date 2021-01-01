<%--
  Created by IntelliJ IDEA.
  User: Emanuel
  Date: 31/12/2020
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="menu.jsp" />

<%-- Si je suis connecté--%>
<c:if test="${!empty sessionScope.current_user}">

    <h1><c:out value=" ${activity.lieu.nom} "/></h1>

    <table>
            <tr>
                <div>
                    <td> Debute le :  <c:out value=" ${activity.dateDebut} "/> </td>
                    <td> Fini le :  <c:out value=" ${activity.dateFin} "/> </td>
                    <td> Se situe : <c:out value=" ${activity.lieu.adresse} "/> </td>
                    <td> ( <c:out value=" ${activity.lieu.coordGPS} "/> ) </td>
                    <c:choose>

                        <%-- Si l'utilisateur ne participe pas encore à l'activité--%>
                        <c:when test="${requestScope.isParticipating == 'false'}">
                            <td> <input type="button" value="Je participe" onclick="window.location.href='joinActivity?activity=${requestScope.activity.idActivity}'" width="200" height="50"/></td>
                        </c:when>

                        <%-- Si l'utilisateur participe deja à l'activité--%>
                        <c:when test="${requestScope.isParticipating == 'true'}">
                            <td> <input type="button" value="Je participe plus" onclick="window.location.href='leaveActivity?activity=${requestScope.activity.idActivity}'" width="200" height="50"/></td>
                        </c:when>
                        <%-- Sinon --%>
                        <c:otherwise>
                            Probleme
                            <c:out value=" ${requestScope.isParticipating} "/>
                        </c:otherwise>
                    </c:choose>
                    </div>
            </tr>
    </table>
    <h1>Participants: </h1>

    <table>
        <c:forEach items="${requestScope.listParticipants}"  var="participant">
            <tr>
                <div>
                    <td> <a href="./profil?profil=${participant.login}"><c:out value=" ${participant.login} "/> </a> </td>
                </div>
            </tr>
        </c:forEach>
    </table>


</c:if>

</body>
</html>
