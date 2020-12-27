<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
    <title>Accueil</title>
</head>
<body>
<%-- Inclusion d'une page avec l'action standard JSP. --%>
<jsp:include page="WEB-INF/menu.jsp" />

<h1>
<div>
    Bienvenue sur TousContreLACovid.fr
</div>
</h1>


<%-- Deconnecté --%>
<c:if test="${empty sessionScope.current_user }">
    <p>Vous êtes déconnecté !</p>
</c:if>

<%-- Connecté --%>
<c:if test="${ !empty sessionScope.current_user }">
    <p>Vous êtes ${ sessionScope.current_user.login } !</p>
</c:if>

</body>

</html>