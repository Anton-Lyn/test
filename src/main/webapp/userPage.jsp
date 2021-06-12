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
<form action="userTest" method="post">
<c:forEach items="${sub.subjectList}" var="subject">
<p>${subject.nameSubject}</p>
    <p><input type="radio" value="${subject.nameSubject}, a1" name="q1">a1</p>
    <p><input type="radio" value="${subject.nameSubject}, a2" name="q1">a2</p>
    <p><input type="radio" value="${subject.nameSubject}, a3" name="q1">a2</p>
<%--<p><button type="submit">go</button></p>--%>
<p>${subject.nameSubject}</p>
    <p><input type="radio" value="${subject.nameSubject}, ta1" name="q2">ta1</p>
    <p><input type="radio" value="${subject.nameSubject}, ta2" name="q2">ta2</p>
    <p><input type="radio" value="${subject.nameSubject}, ta3" name="q2">ta2</p>
<p><button type="submit">go</button></p>
</c:forEach>
    </form>


    <%--<c:forEach items="${sub.subjectList}" var="subject">--%>
    <%--    <form action="userTest" method="get">--%>
    <%--        <p>${subject.nameSubject}</p>--%>
    <%--        <p><input type="checkbox" name="s1" value="${subject.idSubject}">${subject.idSubject}</p>--%>
    <%--        <p><input type="checkbox" name="s2" value="${subject.idSubject}">${subject.complexitySubject}</p>--%>
    <%--        <p><input type="checkbox" name="s3" value="${subject.idSubject}">${subject.dateCreatedSubject}</p>--%>
    <%--        <button type="submit">go</button>--%>
    <%--    </form>--%>
    <%--</c:forEach>--%>

    <form method="get" action="LogoutUser">
    <button type="submit">Logout</button>
    </form>
    </body>
    </html>
