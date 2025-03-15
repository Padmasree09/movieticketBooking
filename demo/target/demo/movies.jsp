<%-- Updated movies.jsp --%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> <%@ page import="java.util.List" %> <%@ page
import="com.theatre.Movie" %> <%@ page contentType="text/html; charset=UTF-8"
language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Movie Listings</title>
    <link rel="stylesheet" type="text/css" href="styles.css" />
    <script>
      function reserveSeat(movieId) {
        var userName = prompt("Enter your name:");
        if (!userName) return;

        var seatNumber = prompt("Enter seat number (e.g., A1, B3):");
        if (!seatNumber) return;

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "reserve", true);
        xhr.setRequestHeader(
          "Content-Type",
          "application/x-www-form-urlencoded"
        );
        xhr.onreadystatechange = function () {
          if (xhr.readyState == 4) {
            if (xhr.status == 200) {
              alert(xhr.responseText);
            } else {
              alert("Error: " + xhr.status);
            }
          }
        };
        xhr.send(
          "movie_id=" +
            encodeURIComponent(movieId) +
            "&user_name=" +
            encodeURIComponent(userName) +
            "&seat_number=" +
            encodeURIComponent(seatNumber)
        );
      }
    </script>
  </head>
  <body>
    <h1>Available Movies</h1>

    <% if (session.getAttribute("admin") != null) { %>
    <div class="admin-controls">
      <a href="admin.jsp" class="button">Add New Movie</a>
    </div>
    <% } %>

    <table>
      <tr>
        <th>Name</th>
        <th>Genre</th>
        <th>Duration</th>
        <th>Action</th>
      </tr>
      <c:forEach var="movie" items="${movies}">
        <tr>
          <td><c:out value="${movie.name}" /></td>
          <td><c:out value="${movie.genre}" /></td>
          <td><c:out value="${movie.duration}" /> mins</td>
          <td>
            <button onclick="reserveSeat(${movie.id})">Reserve Seat</button>
          </td>
        </tr>
      </c:forEach>
    </table>

    <c:if test="${empty movies}">
      <p>No movies available at this time.</p>
    </c:if>

    <div class="footer">
      <a href="index.jsp">Return to Home</a>
      <% if (session.getAttribute("admin") == null) { %>
      <a href="login.jsp">Admin Login</a>
      <% } else { %>
      <a href="logout">Logout</a>
      <% } %>
    </div>
  </body>
</html>
