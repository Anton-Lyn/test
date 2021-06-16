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
<form method="post" action="UserEditing">
    <label>Enter the username of the user you want to edit:</label></br>
    <input name="email" type="email" placeholder="Email" required>
    <button type="submit">Search for a user</button>
</form>
<form action="createTest.jsp">
<p><input type="submit" value="Create test"></p>
</form>
<form action="workWithTest.jsp">
    <p><input type="submit" value="Edit test"></p>
</form>
<form method="get" action="LogoutUser">
    <button type="submit">Logout</button>
</form>
</body>
</html>
