<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title><fmt:message key="index_title"/> </title>
    <style type="text/css">
        <%@include file="/WEB-INF/resources/main.css" %>
    </style>
</head>
<body>
<%--<%--%>
<%--session.setAttribute("SessionUser", "userName))");--%>
<%--session.setAttribute("SessionPassword", "PaSSword!");--%>
<%--session.setMaxInactiveInterval(1);%>--%>

<h1>Прокат автомобилей</h1>
<jsp:useBean id="client" class="transactions.entity.Client" scope="session"/>
<% if (client.getId_client() != 0) {
%>
<p>Hello, ${client.first_name}!</p>
<a href="${pageContext.request.contextPath}/profile">Перейти к профилю</a>
<form action="${pageContext.request.contextPath}/logout" method="post">
    <input type="submit" value="Logout">
</form>
<%
} else {
%>
<p><a href="${pageContext.request.contextPath}/home">Войти</a></p>
<%
    }%>

<c:forEach var="car" items="${cars}">
<div class="view">
    <img src="${pageContext.request.contextPath}/image?autoID=${car.getImageIds().get(0)}" alt="">
    <c:if test="${car.isRented()}">
    <div class="inner">
        </c:if>
        <c:if test="${!car.isRented()}">
        <div class="inner leased">
            </c:if>
            <span>${car.getBrand()} ${car.getModel()}</span>
            <span>${car.getClassAuto()} - ${car.getClassDescription()}</span>
            <span>Цвет: ${car.getColor()}</span>
            <span>Объем двигателя: ${car.getConsumption()/1000} литра</span>
            <c:if test="${car.isRented()}"><span class="free">Свободна</span></c:if>
            <c:if test="${!car.isRented()}"><span class="nonfree">Арендована</span></c:if>
                <%--<span>VIN: ${car.getVin()}</span>--%>
                <%--<span>Номер: ${car.getRegisterNumber()}</span>--%>
            <a href="${pageContext.request.contextPath}/car?id=${car.getIdCar()}">Просмотреть</a>
        </div>
    </div>
    </c:forEach>
</body>
</html>
