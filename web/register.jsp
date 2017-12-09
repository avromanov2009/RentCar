<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Регистрация аккаунта</title>
    <style type="text/css"><%@include file="/WEB-INF/resources/register.css" %></style>
</head>
<body>
<%@ include file="/head.jsp"%>
<form action="${pageContext.request.contextPath}/register" method="post" accept-charset="UTF-8">
    <fieldset class="account-info">
        <legend>Регистрация аккаунта</legend>
        <label>Логин<input type="text" id="login" name="login" placeholder="Логин" oninput="checkLogin()" required></label>
        <p id="displayed-data"></p>
        <label>Имя<input type="text" name="firstName" placeholder="Имя пользователя" required></label>
        <label>Фамилия<input type="text" name="lastName" placeholder="Фамилия пользователя" required></label>
        <label>Паспорт<input type="text" name="passport" placeholder="Паспортные данные пользователя" required></label>
        <label>Телефон<input type="text" name="phone" placeholder="Телефон пользователя" required></label>
        <label>Адрес<input type="text" name="address" placeholder="Адрес пользователя" required></label>
        <label>E-mail<input type="email" name="email" placeholder="Почтовый адрес" required></label>
        <label>Пароль<input type="password" name="password" placeholder="Введите пароль" required>
        <label>Пароль второй раз<input type="password" name="password2" placeholder="Введите пароль" required>
        </label>

        <input type="submit" class="btn" value="Зарегистрироваться">
    </fieldset>
</form>
<script type="text/javascript"><%@include file="/WEB-INF/resources/jquery-3.2.1.min.js" %></script>

<script>
    function checkLogin() {
        inputValue = $("#login").val();
        $.ajax({
            url: '/checkLogin',
            method: 'POST',
            data: JSON.stringify({
                loginname: inputValue
            })
        }).done(function(data) {
            $("#displayed-data").text(data.username);
        });
    }
</script>
</body>
</html>