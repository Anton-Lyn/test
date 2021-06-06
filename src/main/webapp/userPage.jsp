<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="sub" class="com.service.serviceImpl.SubjectServiceImpl" scope="page"/>

<html>
<head>
    <title>User Page</title>
</head>
<body>
<br>
<div>
    <h4>You Login by: ${name}</h4>
    <h4> Your role: ${role} </h4>
</div>
<table border="2">
    <tr>
        <td>Предмет</td>
        <td>Сложность</td>
        <td>Действие</td>
        <td>Результат</td>
    </tr>
    <c:forEach items="${sub.subjectList}" var="subject">
        <tr>
            <td>${subject.nameSubject}</td>
            <td>${subject.complexitySubject}</td>
            <td>Переход на тест</td>
            <td>Результат</td>
        </tr>
    </c:forEach>
</table></br>
<form method="get" action="LogoutUser">
    <button type="submit">Logout</button>
</form>
</body>
</html>
