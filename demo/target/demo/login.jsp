<%-- Updated login.jsp --%> <%@ page language="java" contentType="text/html;
charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Admin Login</title>
    <link rel="stylesheet" type="text/css" href="styles.css" />
  </head>
  <body>
    <h2>Admin Login</h2>

    <% if(request.getAttribute("errorMessage") != null) { %>
    <div class="error-message"><%= request.getAttribute("errorMessage") %></div>
    <% } %>

    <form action="login" method="post">
      <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required />
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required />
      </div>
      <input type="submit" value="Login" />
    </form>
  </body>
</html>
