<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="alltests" class="com.service.serviceImpl.GetTestsServiceImpl" scope="page"/>
<%session.setAttribute("idTopic", session.getAttribute("idSubject"));%>
<html>
<head>
    <title>Edit test</title>
</head>
<body>
<h4>Edit test</h4>

<table border="2">
    <tr>
        <td>Subject</td>
        <td>Answer 1</td>
        <td>Answer 2</td>
        <td>Answer 3</td>
        <td>True Answer</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <c:forEach items="${alltests.getTestsList(sessionScope.idTopic)}" var="test">
        <tr>
            <td>${test.question}</td>
            <td>${test.answer1}</td>
            <td>${test.answer2}</td>
            <td>${test.answer3}</td>
            <td>${test.answer4}</td>
            <td>
                <form method="get" action="takeQuestion">
                    <button name="topic1" value="${test.question}" type="submit">Change question</button>
                </form>
            </td>
            <td>
                <form method="post" action="deleteQuestion">
                    <button name="topic2" value="${test.question}" type="submit">Delete question</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="workWithTest.jsp">
    <p><input type="submit" value="Edit test Page"></p>
</form>
</body>
</html>
