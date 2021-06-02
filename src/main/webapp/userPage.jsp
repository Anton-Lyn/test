<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Page</title>
</head>
<body>
<div>
    <h2>You Login by: ${name}</h2>
    <p> Your role: ${role} </p>
</div>

<form method="get" action="LogoutUser">
    <button type="submit">Logout</button>
</form>

</body>
</html>
