<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String message = pageContext.getException().getMessage();
    String exception = pageContext.getException().getClass().toString();
%>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
<h1 style="color: red">Oops! Something went wrong. Try again later</h1>
<p style="color: red">Type: <%= exception%>
</p>
<p style="color: red">Message: <%= message %>
</p>
<%
    System.out.println(exception);
    System.out.println(message);
%>
<a href="userPage.jsp">На главную страницу</a>
</body>
</html>
