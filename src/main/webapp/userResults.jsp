<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="result" class="com.DAO.DAOImpl.TestDAOImpl" scope="page"/>
<%session.setAttribute("idUser", session.getAttribute("id"));%>

<html>
<head>
    <title>Results</title>
</head>
<body>
<h3>Results</h3>

<table border="2">
    <tr>
        <td>Subject</td>
        <td>Score</td>
        <td>Date Test</td>
    </tr>
    <c:forEach items="${result.getResults(sessionScope.idUser)}" var="resultTable">
        <tr>
            <td>${resultTable.nameSubject}</td>
            <td><p>${resultTable.score}%/100%</p></td>
            <td>${resultTable.dateTest}</td>
        </tr>
    </c:forEach>
</table>
<a href="userPage.jsp">To home page</a>
</body>
</html>
