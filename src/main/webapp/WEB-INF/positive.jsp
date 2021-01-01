<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Emanuel
  Date: 28/12/2020
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Se declarer positif</title>
</head>
<body>
<jsp:include page="menu.jsp" />

<div>
    <h1> Qu'est-ce que le coronavirus Covid-19 ?</h1>
</div>

<div> Les Coronavirus sont une grande famille de virus, qui provoquent des maladies allant d’un simple
    rhume (certains virus saisonniers sont des Coronavirus) à des pathologies plus sévères comme le
    MERS ou le SRAS.
</div>

<div>
    Le virus identifié en janvier 2020 en Chine est un nouveau Coronavirus. La maladie provoquée par ce
    Coronavirus a été nommée COVID-19 par l’Organisation mondiale de la Santé (OMS).
</div>

<div>
    <h1> Quels sont les symptômes de la maladie ?</h1>
</div>

<div>
    Fièvre ou sensation de fièvre, toux, difficultés à respirer :
    <ul>
        <li>après le retour d'une zone où circule le virus</li>
        <li>ou après un contact étroit avec une personne contaminée par le virus</li>
    </ul>
</div>

<div>
    <h1> Comment se transmet la maladie ?</h1>
</div>

<div>
    La maladie se transmet notamment par les postillons (éternuements, toux). On considère donc que les
    contacts étroits avec une personne malade est nécessaire pour transmettre la maladie : même lieu de
    vie, contact direct à moins d’un mètre lors d’une toux, d’un éternuement ou une discussion en
    l’absence de mesures de protection. Les symptômes peuvent apparaître jusque 14 jours après le
    contact.
</div>
<br>
<div>
    Plus d'infos
    <a href="https://www.gouvernement.fr/info-coronavirus"> ici </a>
</div>
<br>
<div>
    <c:choose>
        <c:when test="${sessionScope.current_user.coroned == '0'}">
            <h2> Vous êtes actuellement négatif à la covid-19 </h2>
            <br>
            <form action="positive" method="post">
                <div class="button">
                    <button type="submit">Je suis positif !</button>
                </div>
            </form>
        </c:when>
        <c:when test="${sessionScope.current_user.coroned == '1'}">
            <h2> Vous avez été testé positif à la covid-19 </h2>
            <br>
            <h1>ISOLEZ-VOUS !</h1>
        </c:when>
    </c:choose>
</div>


</body>
</html>
