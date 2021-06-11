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
<%
  if (session.getAttribute("NotFoundUser").equals(false)){
    out.println("<p style=\"color:red\">Error. User not found.</p>");
  }
%>
<form method="post" action="UserEditing">
  <label>Введите логин пользователя которого вы хотите отредактировать:</label></br>
  <input name="email" type="email" placeholder="Email" required>
  <button type="submit">Поиск пользователя</button>
</form>
<form method="get" action="LogoutUser">
  <button type="submit">Logout</button>
</form>
</body>
</html>
