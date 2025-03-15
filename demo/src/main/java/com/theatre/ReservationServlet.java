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

@WebServlet("/reserve")
public class ReservationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("user_name");
        String seatNumber = request.getParameter("seat_number");
        int movieId = 0;

        try {
            movieId = Integer.parseInt(request.getParameter("movie_id"));
        } catch (NumberFormatException e) {
            response.getWriter().write("Invalid movie ID");
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            // Check if the seat is already reserved
            stmt = conn.prepareStatement(
                    "INSERT INTO reservations (movie_id, user_name, seat_number) VALUES (?, ?, ?)");
            stmt.setInt(1, movieId);
            stmt.setString(2, userName);
            stmt.setString(3, seatNumber);
            stmt.executeUpdate();

            response.getWriter().write("Reservation Successful!");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Reservation Failed: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(stmt, conn);
        }
    }
}