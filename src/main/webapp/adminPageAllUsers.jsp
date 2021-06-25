<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="usertable" class="com.service.serviceImpl.UserServiceImpl" scope="page"/>


<html>
<head>
    <title>All Users</title>
</head>
<body>
<table border="2">
    <tr>
        <td>Name</td>
        <td>Delete</td>
    </tr>
    <c:forEach items="${usertable.getAllUsers()}" var="user">
        <tr>
            <td>${user.userName}</td>
            <td>
                <form method="post" action="deleteUser"><button name="deleteUser" value="${user.userId}" type="submit">Delete</button> </form>
            </td>
        </tr>
    </c:forEach>
</table>

<form action="adminPage.jsp">
    <p><input type="submit" value="Admin Page"></p>
</form>

</body>
</html>
