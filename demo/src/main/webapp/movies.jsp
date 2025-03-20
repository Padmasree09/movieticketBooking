<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Movie Theater - Available Movies</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        margin: 20px;
      }
      .container {
        max-width: 1000px;
        margin: 0 auto;
      }
      h1 {
        color: #333;
      }
      table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
      }
      th,
      td {
        padding: 10px;
        text-align: left;
        border-bottom: 1px solid #ddd;
      }
      th {
        background-color: #f2f2f2;
      }
      .button {
        display: inline-block;
        padding: 8px 16px;
        background-color: #4caf50;
        color: white;
        text-decoration: none;
        border-radius: 4px;
        margin: 5px 0;
      }
      .admin-button {
        background-color: #2196f3;
      }
      .no-movies {
        padding: 20px;
        text-align: center;
        font-style: italic;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <h1>Available Movies</h1>

      <c:if test="${sessionScope.admin != null}">
        <a
          href="${pageContext.request.contextPath}/admin/addMovie"
          class="button admin-button"
          >Add New Movie</a
        >
      </c:if>

      <c:choose>
        <c:when test="${not empty movies}">
          <table>
            <thead>
              <tr>
                <th>Name</th>
                <th>Genre</th>
                <th>Duration</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="movie" items="${movies}">
                <tr>
                  <td>${movie.name}</td>
                  <td>${movie.genre}</td>
                  <td>${movie.duration} mins</td>
                  <td>
                    <a
                      href="${pageContext.request.contextPath}/showtimes?movieId=${movie.id}"
                      class="button"
                      >View Showtimes</a
                    >
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:when>
        <c:otherwise>
          <div class="no-movies">
            <p>No movies available at this time.</p>
          </div>
        </c:otherwise>
      </c:choose>

      <div style="margin-top: 20px">
        <a href="${pageContext.request.contextPath}/" class="button"
          >Return to Home</a
        >
        <c:choose>
          <c:when test="${sessionScope.admin == null}">
            <a
              href="${pageContext.request.contextPath}/admin/login"
              class="button admin-button"
              >Admin Login</a
            >
          </c:when>
          <c:otherwise>
            <a
              href="${pageContext.request.contextPath}/admin/logout"
              class="button admin-button"
              >Logout</a
            >
          </c:otherwise>
        </c:choose>
      </div>
    </div>
  </body>
</html>
