<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Movie Theater Booking System</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f5f5f5;
      }
      .hero {
        background-color: #2c3e50;
        color: white;
        padding: 50px 20px;
        text-align: center;
      }
      .button {
        display: inline-block;
        padding: 10px 20px;
        background-color: #3498db;
        color: white;
        text-decoration: none;
        border-radius: 5px;
        margin: 10px;
      }
    </style>
  </head>
  <body>
    <div class="hero">
      <h1>Welcome to MovieTime Theater</h1>
      <p>Book your favorite movies with ease and comfort</p>
      <a href="<%=request.getContextPath()%>/movies" class="button">Browse Movies</a>
    </div>
  </body>
</html>
