<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <button type="submit">Login</button>
</form>
<p style="color:red;"><%= request.getParameter("error") %></p>

<form action="register.jsp" method="get" style="margin-top: 15px;">
    <button type="submit">Register</button>
</form>
</body>
</html>