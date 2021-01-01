<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Emanuel
  Date: 27/12/2020
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
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
        <li><a href="createActivity" title="Permet de créer une activity">Créer activité</a></li>
        <li><a href="showActivities" title="Permet voir toutes les activités">Activité</a></li>
        <li><a href="showFriends" title="Voir ses amis">Amis</a></li>
        <li><a href="positive" title="Se declarer positif à la covid-19">Se declarer positif</a></li>
        <li><a href="showNotifications" title="Voir ses notifications">Notifications</a></li>
        <li><a href="information" title="Voir votre compte">Parametres</a></li>
        <li><a href="deconnexion" title="Se deconnecter">Deconnexion</a></li>
    </c:if>

    <form role="search" action="profil" method="get">
        <div>
            <input type="search" id="recherche" name="profil"
                   placeholder="Rechercher sur le site…"
                   aria-label="Rechercher parmi le contenu du site">
            <button type="submit">Rechercher</button>
        </div>
    </form>

</ul>



</body>
</html>