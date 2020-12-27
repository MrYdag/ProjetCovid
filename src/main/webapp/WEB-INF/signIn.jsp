<%--
  Created by IntelliJ IDEA.
  User: Emanuel
  Date: 27/12/2020
  Time: 10:46
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
<div>Connexion </div>
<br/>

<form action="connexion" method="post">

    <div>
        <label for="mail">Email:</label>
        <input type="email" id="mail" name="user_mail" value="" >
        <span class="erreur">${erreurs['email']}</span>
    </div>
    <div>
        <label for="password">mot de passe:</label>
        <input type="password" id="password" name="user_password" value="">
    </div>
    <div class="button">
        <button type="submit">Connexion</button>
    </div>

    <p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>

</form>

<br/>

</body>
</html>
