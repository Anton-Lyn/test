<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<div align="center">
    <form action="login" method="post">
        <label>Введите ваш email:</label></br>
        <input name="email" type="email" placeholder="Email" required></br>
        <label>Введите пароль:</label></br>
        <input name="password" type="password" placeholder="Password" required></br>
        <button type="submit">Вход</button>
    </form>
    <form action="register.jsp">
        <label>Регистрация</label></br>
        <input type="submit" value="Register"/>
    </form>
</div>
</body>
</html>
