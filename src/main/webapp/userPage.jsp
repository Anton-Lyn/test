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
    <h4>You login by: ${sessionScope.name}</h4>
    <h4>You login: ${sessionScope.login}</h4>
    <h4> Your role: ${sessionScope.role} </h4>
</div>

<form method="get" action="login">
    <select size="1" name="sort">
        <option value="1">By the name</option>
        <option value="2">In Complexity From Easy To Difficult</option>
        <option value="3">In Complexity From Difficult To Easy</option>
        <option value="4">Frequently Requested</option>
        <option value="5">Rarely Requested</option>
    </select>
    <button type="submit">Sort</button>
</form>

<table border="2">
    <tr>
        <td>Subject</td>
        <td>Complexity</td>
        <td>Test run</td>
        <td>Time to complete the test</td>
        <td>Frequency</td>
    </tr>
    <c:forEach items="${sub.getSubjectList(sessionScope.sort)}" var="subject">
        <tr>
            <td>${subject.nameSubject}</td>
            <td>
                <c:choose>
                    <c:when test="${subject.complexitySubject == 1}">
                        Легкий / Easy
                    </c:when>
                    <c:when test="${subject.complexitySubject == 2}">
                        Средний / Middle
                    </c:when>
                    <c:when test="${subject.complexitySubject == 3}">
                        Сложный / Hard
                    </c:when>
                </c:choose>
            </td>
            <td>
                <form method="get" action="userTest">
                    <button name="topic" value="${subject.idSubject}" type="submit">Пройти тест</button>
                </form>
            </td>
            <td>${subject.timeTest}</td>
            <td>${subject.frequency}</td>
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
