<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>
<body>
<h1>User Registration</h1>
<form action="${pageContext.request.contextPath}/register" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <label for="secretKey">Secret Key (Optional):</label>
    <input type="text" id="secretKey" name="secretKey" placeholder="default: auto-generated">
    <br>
    <button type="submit">Register</button>
</form>
<p style="color:red;"><%= request.getParameter("error") %></p>
<p style="color:green;"><%= request.getParameter("success") %></p>
<p><a href="index.jsp">Back to Login</a></p>
</body>
</html>