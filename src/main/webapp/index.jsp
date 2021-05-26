<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Global Testing Program</title>
    <link href="${pageContext.request.contextPath}/index.css" rel="stylesheet">
</head>
<body>
<div class="index-page">
    <form align="center" action="login" method="post">
        <label>Введите ваш email:</label></br>
        <input name="email" type="email" placeholder="Email" required></br>
        <label>Введите пароль:</label></br>
        <input name="password" type="password" placeholder="Password" required></br>
        <button type="submit">Вход</button>
    </form>
    <form align="center" action="registerPage.jsp">
        <label>Регистрация</label></br></br>
        <input type="submit" value="Register"/>
    </form>
</div>
</body>
</html>
