<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<div>
    <h4>You Login by: ${name}</h4>
    <h4> Your role: ${role} </h4>
</div>
<form method="get" action="LogoutUser">
    <button type="submit">Logout</button>
</form>
</body>
</html>
