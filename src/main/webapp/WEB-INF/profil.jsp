<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Emanuel
  Date: 28/12/2020
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Profil </title>
</head>
<body>
<jsp:include page="menu.jsp" />

<h1>Profil</h1>
<br>

<%-- Trouvé --%>
<c:if test="${!empty requestScope.profil.firstName }">
    <div> Login : <c:out value="${requestScope.profil.login} "> </c:out></div>
    <div> Prenom : <c:out value="${requestScope.profil.firstName} "> </c:out></div>
    <div> Nom : <c:out value="${requestScope.profil.lastName} "> </c:out></div>
    <div> Date de naissance : <c:out value="${requestScope.profil.dateNaissance} "> </c:out></div>
    <div> Coroned : <c:out value="${requestScope.profil.coroned} "> </c:out></div>
    <div> Admin : <c:out value="${requestScope.profil.admin} "> </c:out></div>

    <br>

    <%-- Si je suis connecté--%>
    <c:if test="${!empty sessionScope.current_user}">
        <c:choose>
            <%-- Je consulte mon propre profil--%>
            <c:when test="${sessionScope.current_user.id == requestScope.profil.id}">
                <div>Je consulte mon propre profil</div>
            </c:when>
            <%-- Je consulte un autre profil que le mien--%>
            <c:when test="${sessionScope.current_user.id != requestScope.profil.id}">
                <%-- Si je suis deja ami avec lui--%>
                <c:choose>
                    <c:when test="${requestScope.friend == 'true'}">
                        <input type="button" value="Retirer de la liste d'ami" onclick="window.location.href='removeFriend?profil=${requestScope.profil.login}'" width="200" height="50"/>
                    </c:when>
                    <c:when test="${requestScope.friend == 'false'}">
                        <input type="button" value="Demander en ami" onclick="window.location.href='askFriend?profil=${requestScope.profil.login}'" width="200" height="50"/>
                    </c:when>
                    <c:otherwise>
                        BUG !
                    </c:otherwise>
                </c:choose>
            </c:when>



        </c:choose>

    </c:if>

</c:if>

<%-- Pas trouvé --%>
<c:if test="${empty requestScope.profil.firstName }">
    <div> Aucun compte trouvé</div>
</c:if>

</body>
</html>
