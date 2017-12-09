<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Все машины</title>
<style><%@include file="/WEB-INF/resources/main.css"%></style>
<body>
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
