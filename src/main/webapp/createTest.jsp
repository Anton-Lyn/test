<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Creat Test</title>
</head>
<body>
<h3>Create Test</h3>
<form method="post" action="createSubject">
    <p><label>Name test</label></p>
    <p><input type="text" placeholder="Enter the name of the subject for the test" name="nameNewSubject" required></p>
    <p><label>Complexity</label></p>
    <p><select size="1" name="complexity">
        <option value="1">Легкий</option>
        <option value="2">Средний</option>
        <option value="3">Сложный</option>
    </select></p>
    <p><label>Time to the test in minutes.</label></p>
    <p><input type="number" max="720" name="time" required></p>
    <button type="submit">Create test</button>
</form>

<form action="adminPage.jsp">
    <p><input type="submit" value="Admin page"></p>
</form>

</body>
</html>
