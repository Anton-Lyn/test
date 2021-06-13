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
        <td>Subject</td>
        <td>Complexity</td>
        <td>Test run</td>
        <td>Time to complete the test</td>
    </tr>
    <c:forEach items="${sub.subjectList}" var="subject">
        <tr>
            <td>${subject.nameSubject}</td>
            <td>${subject.complexitySubject}</td>
            <td>
                <form method="get" action="userTest">
                    <button name="topic" value="${subject.idSubject}" type="submit">Пройти тест</button>
                </form>
            </td>
            <td>${subject.timeTest}</td>
        </tr>
    </c:forEach>
</table>
<form action="userResults.jsp">
    <button type="submit">Show results</button>
</form>
<form method="get" action="LogoutUser">
    <button type="submit">Logout</button>
</form>
</body>
</html>
