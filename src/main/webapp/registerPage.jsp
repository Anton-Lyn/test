<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Global Testing Program</title>
</head>
<body>
<form action="register" method="post">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p></br>
        <label><b>Name</b></label>
        <input type="text" placeholder="Enter Name" name="name" required></br>
        <label><b>Email</b></label>
        <input type="text" placeholder="Enter Email" name="login" required></br>
        <label><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" required></br>
        <label><b>Language</b></label>
        <select size="1" name="lang"></br>
            <option value="1">Русский</option>
            <option value="2">English</option>
        </select>
    <button type="submit">Вход</button>
</form>
</body>
</html>
