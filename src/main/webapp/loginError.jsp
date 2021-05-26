<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Global Testing Program</title>
</head>
<body>
<div align="center">
    <p style="color:red">Email or password is incorrect!</p>
    <form action="login" method="post">
        <label>Введите ваш email:</label></br>
        <input name="email" type="email" placeholder="Email" required></br>
        <label>Введите пароль:</label></br>
        <input name="password" type="password" placeholder="Password" required></br>
        <button type="submit">Вход</button>
    </form>
    <form action="registerPage.jsp">
        <label>Регистрация</label></br>
        <input type="submit" value="Register"/>
    </form>
</div>
</body>
</html>
