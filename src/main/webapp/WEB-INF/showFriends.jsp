<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Emanuel
  Date: 29/12/2020
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Amis</title>
</head>
<body>
<jsp:include page="menu.jsp" />

<%-- Si je suis connecté--%>
<c:if test="${!empty sessionScope.current_user}">
    <h1>Voici vos amis</h1>

    <table>
    <c:forEach items="${requestScope.listFriends}"  var="friend">
        <tr>
        <div>
            <td>  <a href="./profil?profil=${friend.login}"> <c:out value="${friend.login}"/> </a> </td>
            <td> <c:out value="${friend.firstName}"/> </td>
            <td> <c:out value="${friend.lastName}"/> </td>
            <td> <c:out value="${friend.dateNaissance}"/> </td>
            <td> <c:out value="${friend.coroned}"/> </td>
            <td> <c:out value="${friend.admin}"/> </td>

        </div>
        </tr>
    </c:forEach>
    </table>

</c:if>

</body>
</html>
