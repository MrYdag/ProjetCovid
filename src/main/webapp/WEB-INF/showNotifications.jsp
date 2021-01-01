<%--
  Created by IntelliJ IDEA.
  User: Emanuel
  Date: 01/01/2021
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Notifications</title>
</head>
<body>
<jsp:include page="menu.jsp" />

<%-- Si je suis connecté--%>
<c:if test="${!empty sessionScope.current_user}">
    <h1>Nouvelles notifications</h1>

    <table>
        <c:forEach items="${requestScope.listNewNotifs}"  var="notif">
            <tr>
                <div>
                    <td> ID:  <c:out value="${notif.idNotif}"/> </td>
                    <td> Message : <c:out value="${notif.about}"/> </td>
                    <td> Date : <c:out value="${notif.dateSending}"/> </td>
                    <td> Lu :  <c:out value="${notif.isReading}"/> </td>
                </div>
            </tr>
        </c:forEach>
    </table>

    <h1>Historique de notifications</h1>

    <table>
        <c:forEach items="${requestScope.listNotifs}"  var="notif">
            <tr>
                <div>
                    <td> ID:  <c:out value="${notif.idNotif}"/> </td>
                    <td> Message : <c:out value="${notif.about}"/> </td>
                    <td> Date : <c:out value="${notif.dateSending}"/> </td>
                    <td> Lu :  <c:out value="${notif.isReading}"/> </td>
                </div>
            </tr>
        </c:forEach>
    </table>

</c:if>

</body>
</html>
