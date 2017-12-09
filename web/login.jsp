<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Вход в систему</title>
    <style type="text/css"><%@include file="/WEB-INF/resources/main.css" %></style>
</head>
<body>
<%@ include file="head.jsp"%>
<form action="/login" method="post">
    <fieldset class="account-info">
        <legend>Вход в систему</legend>
        <label>Логин<input type="text" id="uname" name="uname" placeholder="Имя пользователя" required ></label>
        <label>Пароль<input type="password" id="pass" name="pass" placeholder="Введите пароль" required>
        </label>
        <p id="displayed-data"></p>
        <input type="submit" class="btn" value="Войти">
    </fieldset>
</form>

<a href="register">Зарегистрироваться</a>

<%--<script type="text/javascript"><%@include file="/WEB-INF/resources/jquery-3.2.1.min.js" %></script>--%>

<%--<script>--%>
    <%--function enter() {--%>
        <%--uname = $("#uname").val();--%>
        <%--console.log(uname);--%>
        <%--$.ajax({--%>
            <%--url: '/Login',--%>
            <%--method: 'POST',--%>
            <%--data: JSON.stringify({--%>
                <%--uname: uname--%>
            <%--})--%>
        <%--}).done(function(data) {--%>
            <%--$("#displayed-data").text(data.uname);--%>
        <%--});--%>
    <%--}--%>
<%--</script>--%>
</body>
</html>