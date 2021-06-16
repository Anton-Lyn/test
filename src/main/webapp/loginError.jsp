<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Global Testing Program</title>
    <link href="${pageContext.request.contextPath}/index.css" rel="stylesheet">
</head>
<body>
<div class="index-page">
    <%
        if (session.getAttribute("login") == null) {
            out.println("<p style=\"color:red\">Error. Your account has been found. Check the correctness of the login and password</p>");
        } else if (session.getAttribute("status").equals(false)) {
            out.println("<p style=\"color:red\">Error. Your account has been found. Check the correctness of the login and password</p>");
        } else if (session.getAttribute("status").equals(true)) {
            out.println("<p style=\"color:red\">The user is blocked. Contact your administrator to find out how to restore your account.</p>");
        }
    %>
    <form align="center" action="login" method="post">
        <label>Enter your email:</label></br>
        <input name="email" type="email" placeholder="Email" required></br>
        <label>Enter your password:</label></br>
        <input name="password" type="password" placeholder="Password" required></br>
        <button type="submit">Log in</button>
    </form>
    <form align="center" action="registerPage.jsp">
        <label>Registration</label></br></br>
        <input type="submit" value="Register"/>
    </form>
</div>
</body>
</html>
