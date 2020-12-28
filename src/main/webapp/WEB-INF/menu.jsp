<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Emanuel
  Date: 27/12/2020
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="../css/bootstrap2/css/bootstrap.css">
<script src="../css/bootstrap2/js/bootstrap.js"></script>

<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<ul id="navigation">
    <li><a href="accueil" title="Retourner à l'accueil">Accueil</a></li>
    <li><a href="#" title="WIP">WIP</a></li>


    <%-- Deconnecté --%>
    <c:if test="${empty sessionScope.current_user }">
        <li><a href="inscription" title="Se créer un compte">Inscription</a></li>
        <li><a href="connexion" title="Se connecter">Connexion</a></li>
    </c:if>

    <%-- Connecté --%>
    <c:if test="${ !empty sessionScope.current_user }">
        <li><a href="positive" title="Se declarer positif à la covid-19">Se declarer positif</a></li>
        <li><a href="information" title="Voir votre compte">Parametres</a></li>
        <li><a href="deconnexion" title="Se deconnecter">Deconnexion</a></li>
    </c:if>

</ul>



</body>
</html>