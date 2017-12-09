<%@ page import="transactions.entity.Client" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Подробней о машине </title>
    <style>
        body {
            background-color: rgb(231, 231, 231);
        }

        .free-rented {
            color: rgb(0, 243, 22);
            font-weight: bold;
        }

        .leased {
            color: rgb(255, 0, 7);
            font-weight: bold;
        }

        .information > span {
            display: block;
            text-align: center;
            padding: 10px;
        }

        .images {
            padding: 0% 25%;
        }

        img {
            display: block;
            max-width: 600px;
            padding: 5px;
        }

        a.rentButton {
            display: block;
            margin: 10px auto;
            padding: 5px;
            border-radius: 5px;
            background: rgba(200, 130, 0, 1);
            width: 10em;
            text-align: center;
        }

        a {
            text-decoration-line: none;
            color: rgb(255, 255, 255);
        }

        a.rentButton:hover {
            background: rgb(255, 167, 0)
        }

        .button24 {
            display: inline-block;
            color: white;
            text-decoration: none;
            padding: .5em 2em;
            outline: none;
            border-width: 2px 0;
            border-style: solid none;
            border-color: #FDBE33 #000 #D77206;
            border-radius: 6px;
            background: linear-gradient(#F3AE0F, #E38916) #E38916;
            transition: 0.2s;
        }

        .button24:hover {
            background: linear-gradient(#f5ae00, #f59500) #f5ae00;
        }

        .button24:active {
            background: linear-gradient(#f59500, #f5ae00) #f59500;
        }

        .buttonReset {
            display: inline-block;
            color: white;
            text-decoration: none;
            padding: .5em 2em;
            outline: none;
            border-width: 2px 0;
            border-style: solid none;
            border-color: #f91014 #000 #bc1013;
            border-radius: 6px;
            background: linear-gradient(#f91014, #bc1013) #e41113;
            transition: 0.2s;
        }

        .buttonReset:hover {
            background: linear-gradient(#f91014, #f11417) #e41113;
        }

        .buttonReset:active {
            background: linear-gradient(#f91014, #bc1013) #e41113;
        }

        .orderForm{
            display: flex;
            align-items: center;
            justify-content: center
        }
    </style>
</head>
<body>
<jsp:useBean id="client" class="transactions.entity.Client" scope="session"/>
<%--<%client = (Client) session.getAttribute("client");%>--%>
<%--<p><c:out value="${client.id_client}"/></p>--%>
<%--<% Client client = (Client)session.getAttribute("client");--%>
<%--out.println(client.getId_client());%>--%>
<p><a href="${pageContext.request.contextPath}/" style="color:black">Назад</a> </p>
<div class="information">
    <span>${car.getBrand()} ${car.getModel()}</span>
    <span>Класс авто: ${car.getClassAuto()} - ${car.getClassDescription()}</span>
    <span>Цвет: ${car.getColor()}</span>
    <span>Объем двигателя: ${car.getConsumption()/1000}</span>
    <span>Свободна:
        <c:if test="${car.isRented()}">
            <span class="free-rented">СВОБОДНА</span>
        </c:if>
        <c:if test="${!car.isRented()}">
            <span class="leased">АРЕНДОВАНА</span>
        </c:if>
    </span>
    <span>VIN: ${car.getVin()}</span>
    <span>Номер: ${car.getRegisterNumber()}</span>
    <span>Номер: ${car.getIdCar()}</span>
    <form class="orderForm" action="/rent" method="get">
        <c:if test="${car.isRented()}">
            <label>Начало</label>
            <input type="datetime-local" path="startDate" name="start_datetime">
            <%--<input type="date" name="start_date">--%>
            <%--<input type="time" name="start_time">--%>
            по
            <input type="datetime-local" path="endDate" name="end_datetime">
            <input value="${car.getIdCar()}" name="carId" hidden>
            <input value="${client.id_client}" name="clientId" type="hidden">
            <%--<input type="date" name="end_date">--%>
            <%--<input type="time" name="end_time">--%>
            <input type="submit" class="button24" href="#" value="Арендовать" />
            <input type="reset" class="buttonReset" value="Сброс"/>
        </c:if>
    </form>
    <div class="images">
        <c:forEach var="imageId" items="${car.getImageIds()}">
            <img src="${pageContext.request.contextPath}/image?imageCarId=${imageId}" alt="">
        </c:forEach>
    </div>
</div>

</body>
</html>
