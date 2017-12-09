<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%--
  Created by IntelliJ IDEA.
  User: andrei
  Date: 08.12.17
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Профиль пользователя</title>
    <style type="text/css">
        <%@include file="/WEB-INF/resources/main.css" %>
    </style>
</head>
<body>
<a href="${pageContext.request.contextPath}/">На главную страницу</a>
<section>
    <jsp:useBean id="client" class="transactions.entity.Client" scope="session"/>
    <p>Логин: ${client.username}</p>
    <p>Имя: ${client.first_name}</p>
    <p>Фамилия: ${client.last_name}</p>
    <p>Паспорт: ${client.passport}</p>
    <p>Телефон: ${client.phone_number}</p>
</section>
<c:if test="${client.role_id == 1}">
    <h3>Подтвердите заказы клиентов: </h3>
</c:if>
<c:if test="${client.role_id != 1}">
    <h3>Ждут подтверждения администратора: </h3>
</c:if>

<section>
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
                <%--<c:if test="${client.role_id == 1}">--%>
                    <%--<input type="submit"  formmethod="get" value="Подтвердить">--%>
                <%--</c:if>--%>
                    <%--<span>VIN: ${car.getVin()}</span>--%>
                    <%--<span>Номер: ${car.getRegisterNumber()}</span>--%>
                <a href="${pageContext.request.contextPath}/car?id=${car.getIdCar()}">Просмотреть</a>
            </div>
        </div>
        </c:forEach>
</section>
</body>
</html>
