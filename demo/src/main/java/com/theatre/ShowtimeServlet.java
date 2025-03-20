package com.theatre;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/showtimes")
public class ShowtimeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String movieIdParam = request.getParameter("movieId");

        if (movieIdParam == null || movieIdParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/movies");
            return;
        }

        int movieId;
        try {
            movieId = Integer.parseInt(movieIdParam);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/movies");
            return;
        }

        Connection conn = null;
        PreparedStatement movieStmt = null;
        PreparedStatement showtimeStmt = null;
        ResultSet movieRs = null;
        ResultSet showtimeRs = null;

        try {
            conn = DatabaseUtil.getConnection();

            // Get movie details
            movieStmt = conn.prepareStatement("SELECT id, name, genre, duration FROM movies WHERE id = ?");
            movieStmt.setInt(1, movieId);
            movieRs = movieStmt.executeQuery();

            if (!movieRs.next()) {
                // Movie not found
                response.sendRedirect(request.getContextPath() + "/movies");
                return;
            }

            Movie movie = new Movie(
                    movieRs.getInt("id"),
                    movieRs.getString("name"),
                    movieRs.getString("genre"),
                    movieRs.getInt("duration"));
            request.setAttribute("movie", movie);

            // Get showtimes for this movie
            showtimeStmt = conn.prepareStatement(
                    "SELECT s.id, s.show_date, s.start_time, s.price, " +
                            "t.id as theater_id, t.name as theater_name " +
                            "FROM showtimes s " +
                            "JOIN theaters t ON s.theater_id = t.id " +
                            "WHERE s.movie_id = ? " +
                            "ORDER BY s.show_date, s.start_time");
            showtimeStmt.setInt(1, movieId);
            showtimeRs = showtimeStmt.executeQuery();

            List<Showtime> showtimes = new ArrayList<>();
            while (showtimeRs.next()) {
                Showtime showtime = new Showtime(
                        showtimeRs.getInt("id"),
                        movieId,
                        showtimeRs.getInt("theater_id"),
                        showtimeRs.getString("theater_name"),
                        showtimeRs.getDate("show_date"),
                        showtimeRs.getTime("start_time"),
                        showtimeRs.getDouble("price"));
                showtimes.add(showtime);
            }

            request.setAttribute("showtimes", showtimes);
            request.getRequestDispatcher("showtimes.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Error loading showtimes: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(movieRs, movieStmt, null);
            DatabaseUtil.closeResources(showtimeRs, showtimeStmt, conn);
        }
    }
}