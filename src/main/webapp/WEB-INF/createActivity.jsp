<%--
  Created by IntelliJ IDEA.
  User: Emanuel
  Date: 30/12/2020
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Creer une activité</title>
</head>
<body>
<jsp:include page="menu.jsp" />
<div>
    <h1> Créer une activité </h1>
</div>

<form action="createActivity" method="post">
    <div>
        <label for="nomLieu">Lieu:</label>
        <input type="text" id="nomLieu" name="nomLieu" />
    </div>
    <div>
        <label for="adresseLieu">Adresse:</label>
        <input type="text" id="adresseLieu" name="adresseLieu"/>
    </div>
    <div>
        <label for="coordLieu">Coordonnées GPS</label>
        <input type="text" id="coordLieu" name="coordLieu"/>
    </div>
    <div>
        <label for="dateDebut">Debut:</label>
        <input type="date" id="dateDebut" name="activitydatedebut" >
        <label for="heureDebut"> à </label>
        <input type="time" id="heureDebut" name="activityheuredebut">
        <%--<span class="erreur">${erreurs['birthday']}</span> --%>
    </div>
    <div>
        <label for="dateFin">Fin:</label>
        <input type="date" id="dateFin" name="activitydatefin" >
        <label for="heureFin"> à </label>
        <input type="time" id="heureFin" name="activityheurefin">
        <%--<span class="erreur">${erreurs['birthday']}</span> --%>
    </div>

    <div class="button">
        <button type="submit">Confirmer la création d'activité</button>
    </div>

    <p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>

</form>

</body>
</html>
