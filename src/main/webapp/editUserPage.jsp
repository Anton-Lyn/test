<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Edit User Page</title>
</head>
<body>

<p>${sessionScope.UserName}</p>

<form action="EditUser" method="post">
    <h1>Edit User</h1>
    <p>Enter new values for the user.</p>
    <label>New Name</label>
    <input type="text" placeholder="${sessionScope.UserName}" name="newName" required>
    <label>New Email</label>
    <input type="email" placeholder="${sessionScope.UserEmail}" name="newEmail" required>
    <label>New Password</label>
    <input type="password" placeholder="Enter new password" name="newPassword" minlength="8" required>
    <label>New Role</label>
    <select size="1" name="newRole">
        <c:choose>
            <c:when test="${sessionScope.UserRole == 2}">
                <option value="user">User</option>
                <option value="admin">Admin</option>
            </c:when>
            <c:when test="${sessionScope.UserRole == 1}">
                <option value="admin">Admin</option>
                <option value="user">User</option>
            </c:when>
        </c:choose>
    </select>
    <select size="1" name="newLang">
        <c:choose>
            <c:when test="${sessionScope.UserLang == 1}">
                <option value="1">Русский</option>
                <option value="2">English</option>
            </c:when>
            <c:when test="${sessionScope.UserLang == 2}">
                <option value="2">English</option>
                <option value="1">Русский</option>
            </c:when>
        </c:choose>
    </select>
    <select size="1" name="newStatus">
        <c:choose>
            <c:when test="${sessionScope.UserStatus.equals(true)}">
                <option value="true">Blocked</option>
                <option value="false">Not blocked</option>
            </c:when>
            <c:when test="${sessionScope.UserStatus.equals(false)}">
                <option value="false">Not blocked</option>
                <option value="true">Blocked</option>
            </c:when>
        </c:choose>
    </select>
    <button type="submit">Edit user</button>
</form>
</body>
</html>
