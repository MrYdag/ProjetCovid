<%--
  Created by IntelliJ IDEA.
  User: Emanuel
  Date: 27/12/2020
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Compte</title>
</head>
<body>
<jsp:include page="menu.jsp" />
<div> Changer vos informations </div>
<br/>

<form action="information" method="post">
    <div>
        <label for="firstname">Prenom :</label>
        <input type="text" id="firstname" name="user_firstname" value="<c:out value="${sessionScope.current_user.firstName}"/>" >
        <span class="erreur">${erreurs['firstName']}</span>
    </div>
    <div>
        <label for="lastname">Nom :</label>
        <input type="text" id="lastname" name="user_lastname" value="<c:out value="${sessionScope.current_user.lastName}"/>" >
        <span class="erreur">${erreurs['lastName']}</span>
    </div>
    <div>
        <label for="mail">Email:</label>
        <input type="email" id="mail" name="user_mail" value="<c:out value="${sessionScope.current_user.login}"/>" >
        <span class="erreur">${erreurs['email']}</span>
    </div>
    <div>
        <label for="password">mot de passe actuel:</label>
        <input type="password" id="password" name="user_password" value="">
    </div>
    <div>
        <label for="password">Nouveau mot de passe:</label>
        <input type="password" id="newpassword" name="new_user_password" value="">
    </div>
    <div>
        <label for="password">Confirmation nouveau mot de passe:</label>
        <input type="password" id="newpasswordConfirmation" name="new_user_passwordConfirmation" value="">
    </div>
    <div>
        <label for="dateBirthday">Date de naissance:</label>
        <input type="date" id="dateBirthday" name="user_birthday" value="<c:out value="${sessionScope.current_user.dateNaissance}"/>" >
        <span class="erreur">${erreurs['birthday']}</span>
    </div>
    <div class="button">
        <button type="submit">Confirmer</button>
    </div>

    <p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>

</form>


</body>
</html>

