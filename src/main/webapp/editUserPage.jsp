<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User Page</title>
</head>
<body>

<form action="EditUser" method="post">
    <h1>Edit User</h1>
    <p>Enter new values for the user.</p>
    <label>New Name</label>
    <input type="text" placeholder="Enter new Name" name="newName" required>
    <label>New Email</label>
    <input type="email" placeholder="Enter new email" name="newEmail" required>
    <label>New Password</label>
    <input type="password" placeholder="Enter new password" name="newPassword" minlength="8" required>
    <label>New Role</label>
    <select size="1" name="newRole">
        <option value="admin">Admin</option>
        <option value="user">User</option>
    </select>
    <select size="1" name="newLang">
        <option value="1">Русский</option>
        <option value="2">English</option>
    </select>
    <select size="1" name="newStatus">
        <option value="true">Blocked</option>
        <option value="false">Not blocked</option>
    </select>
    <button type="submit">Edit user</button>
</form>
</body>
</html>
