<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>${movie.name} - Showtimes</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        margin: 20px;
      }
      .container {
        max-width: 1000px;
        margin: 0 auto;
      }
      h1,
      h2 {
        color: #333;
      }
      .movie-info {
        background-color: #f9f9f9;
        padding: 15px;
        border-radius: 5px;
        margin-bottom: 20px;
      }
      .showtimes-grid {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
        gap: 15px;
        margin-top: 20px;
      }
      .showtime-card {
        border: 1px solid #ddd;
        border-radius: 5px;
        padding: 15px;
        background-color: white;
      }
      .showtime-date {
        font-weight: bold;
        color: #333;
        margin-bottom: 5px;
      }
      .showtime-time {
        color: #666;
        margin-bottom: 5px;
      }
      .showtime-theater {
        color: #666;
        margin-bottom: 5px;
      }
      .showtime-price {
        font-weight: bold;
        color: #4caf50;
        margin-bottom: 10px;
      }
      .button {
        display: inline-block;
        padding: 8px 16px;
        background-color: #4caf50;
        color: white;
        text-decoration: none;
        border-radius: 4px;
        margin: 5px 0;
        text-align: center;
      }
      .back-button {
        background-color: #607d8b;
      }
      .no-showtimes {
        padding: 20px;
        text-align: center;
        font-style: italic;
        background-color: #f9f9f9;
        border-radius: 5px;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <h1>Showtimes for ${movie.name}</h1>

      <div class="movie-info">
        <h2>${movie.name}</h2>
        <p>Genre: ${movie.genre}</p>
        <p>Duration: ${movie.duration} minutes</p>
      </div>

      <c:choose>
        <c:when test="${not empty showtimes}">
          <div class="showtimes-grid">
            <c:forEach var="showtime" items="${showtimes}">
              <div class="showtime-card">
                <div class="showtime-date">${showtime.formattedDate}</div>
                <div class="showtime-time">${showtime.formattedTime}</div>
                <div class="showtime-theater">${showtime.theaterName}</div>
                <div class="showtime-price">${showtime.formattedPrice}</div>
                <a
                  href="${pageContext.request.contextPath}/seats?showtimeId=${showtime.id}"
                  class="button"
                  >Select Seats</a
                >
              </div>
            </c:forEach>
          </div>
        </c:when>
        <c:otherwise>
          <div class="no-showtimes">
            <p>No showtimes available for this movie at this time.</p>
          </div>
        </c:otherwise>
      </c:choose>

      <div style="margin-top: 20px">
        <a
          href="${pageContext.request.contextPath}/movies"
          class="button back-button"
          >Back to Movies</a
        >
      </div>
    </div>
  </body>
</html>
