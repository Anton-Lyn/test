<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="alltests" class="com.service.serviceImpl.GetTestsServiceImpl" scope="page"/>
<%session.setAttribute("idTopic", session.getAttribute("topicId"));%>

<html>
<head>
    <title>Test Page</title>
</head>
<body>
<form action="getAnswers" method="get">
    <c:forEach items="${alltests.getTestsList(sessionScope.idTopic)}" var="test">
        <p>${test.question}</p>
        <p><input type="radio" value="${test.question},${test.answer1}" name="${test.question}">${test.answer1}</p>
        <p><input type="radio" value="${test.question},${test.answer2}" name="${test.question}">${test.answer2}</p>
        <p><input type="radio" value="${test.question},${test.answer3}" name="${test.question}">${test.answer3}</p>
        <p><input type="radio" value="${test.question},${test.answer4}" name="${test.question}">${test.answer4}</p>
    </c:forEach>
    <button type="submit">Finish</button>
</form>
</body>
</html>
