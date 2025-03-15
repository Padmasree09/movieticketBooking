package com.theatre;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addMovie")
public class AddMovieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String genre = request.getParameter("genre");
        int duration = 0;

        try {
            duration = Integer.parseInt(request.getParameter("duration"));
        } catch (NumberFormatException e) {
            response.getWriter().write("Invalid duration format");
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement("INSERT INTO movies (name, genre, duration) VALUES (?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, genre);
            stmt.setInt(3, duration);
            stmt.executeUpdate();
            response.sendRedirect("movies");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Movie Addition Failed: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(stmt, conn);
        }
    }
}