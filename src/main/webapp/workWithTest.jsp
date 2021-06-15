<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="sub" class="com.service.serviceImpl.SubjectServiceImpl" scope="page"/>
<html>
<head>
    <title>Work with Test</title>
</head>
<body>

<table border="2">
  <tr>
    <td>Subject</td>
    <td>Delete</td>
    <td>Edit Test</td>
    <td>Add new question</td>
  </tr>
  <c:forEach items="${sub.getSubjectList(1)}" var="subject">
    <tr>
      <td>${subject.nameSubject}</td>

      <td>
        <form method="get" action="editTests">
          <button name="deleteTest" value="${subject.idSubject}" type="submit">Delete Test</button>
        </form>
      </td>

      <td>
        <form method="post" action="editTests">
          <button name="subject" value="${subject.idSubject}" type="submit">Edit Test</button>
        </form>
      </td>

      <td>
        <form method="get" action="addQuestion">
          <button name="subject" value="${subject.idSubject}" type="submit">Add question</button>
        </form>
      </td>
    </tr>
  </c:forEach>
</table>
<form action="adminPage.jsp">
  <p><input type="submit" value="Admin Page"></p>
</form>
</body>
</html>
