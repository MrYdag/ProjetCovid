<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
    <title>JSP - Hello World</title>

</head>
<body>
<%-- Inclusion d'une page avec l'action standard JSP. --%>
<jsp:include page="menu.jsp" />

<h1><%= "Creer un compte sur TousContreLACovid" %></h1>

<br/>

<form action="inscription" method="post">
    <div>
        <label for="firstname">Prenom :</label>
        <input type="text" id="firstname" name="user_firstname" value="<c:out value="${user.firstName}"/>" >
        <span class="erreur">${erreurs['firstName']}</span>
    </div>
    <div>
        <label for="lastname">Nom :</label>
        <input type="text" id="lastname" name="user_lastname" value="<c:out value="${user.lastName}"/>" >
        <span class="erreur">${erreurs['lastName']}</span>
    </div>
    <div>
        <label for="mail">Email:</label>
        <input type="email" id="mail" name="user_mail" value="<c:out value="${user.login}"/>" >
        <span class="erreur">${erreurs['email']}</span>
    </div>
    <div>
        <label for="password">mot de passe:</label>
        <input type="password" id="password" name="user_password" value="">
    </div>
    <div>
        <label for="password">Confirmation du mot de passe:</label>
        <input type="password" id="passwordConfirmation" name="user_passwordConfirmation" value="">
    </div>
    <div>
        <label for="dateBirthday">Date de naissance:</label>
        <input type="date" id="dateBirthday" name="user_birthday" value="<c:out value="${user.dateNaissance}"/>" >
        <span class="erreur">${erreurs['birthday']}</span>
    </div>
    <div class="button">
        <button type="submit">Confirmer l'inscription</button>
    </div>

    <p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>

</form>

<br/>

</body>

</html>
