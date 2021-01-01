<%--
  Created by IntelliJ IDEA.
  User: Emanuel
  Date: 30/12/2020
  Time: 15:22
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
    <h1>Voici toutes les activités</h1>

    <table>
        <c:forEach items="${requestScope.listActivities}"  var="activity">
            <tr>
                <div>
                    <td> A : <a href="./showActivity?activity=${activity.idActivity}"><c:out value=" ${activity.lieu.nom} "/> </a> </td>
                </div>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
