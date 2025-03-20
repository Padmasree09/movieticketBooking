// package com.theatre;

// import java.io.IOException;
// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet;
// import jakarta.servlet.http.HttpServlet;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @WebServlet("/seats")
// public class SeatsServlet extends HttpServlet {
//     private static final long serialVersionUID = 1L;

//     protected void doGet(HttpServletRequest request, HttpServletResponse response)
//             throws ServletException, IOException {

//         String showtimeIdParam = request.getParameter("showtimeId");

//         if (showtimeIdParam == null || showtimeIdParam.isEmpty()) {
//             response.sendRedirect(request.getContextPath() + "/movies");
//             return;
//         }

//         int showtimeId;
//         try {
//             showtimeId = Integer.parseInt(showtimeIdParam);
//         } catch (NumberFormatException e) {
//             response.sendRedirect(request.getContextPath() + "/movies");
//             return;
//         }

//         Connection conn = null;
//         PreparedStatement showtimeStmt = null;
//         PreparedStatement seatsStmt = null;
//         PreparedStatement bookedSeatsStmt = null;
//         ResultSet showtimeRs = null;
//         ResultSet seatsRs = null;
//         ResultSet bookedSeatsRs = null;

//         try {
//             conn = DatabaseUtil.getConnection();

//             // Get showtime details
//             showtimeStmt = conn.prepareStatement(
//                     "SELECT s.id, s.show_date, s.start_time, s.price, " +
//                             "m.id as movie_id, m.name as movie_name, " +
//                             "t.id as theater_id, t.name as theater_name " +
//                             "FROM showtimes s " +
//                             "JOIN movies m ON s.movie_id = m.id " +
//                             "JOIN theaters t ON s.theater_id = t.id " +
//                             "WHERE s.id = ?");
//             showtimeStmt.setInt(1, showtimeId);
//             showtimeRs = showtimeStmt.executeQuery();

//             if (!showtimeRs.next()) {
//                 response.sendRedirect(request.getContextPath() + "/movies");
//                 return;
//             }

//             Showtime showtime = new Showtime(
//                     showtimeRs.getInt("id"),
//                     showtimeRs.getInt("movie_id"),
//                     showtimeRs.getInt("theater_id"),
//                     showtimeRs.getString("theater_name"),
//                     showtimeRs.getDate("show_date"),
//                     showtimeRs.getTime("start_time"),
//                     showtimeRs.getDouble("price"));
//             showtime.setMovieName(showtimeRs.getString("movie_name"));
//             request.setAttribute("showtime", showtime);

//             // Get all seats for this theater
//             seatsStmt = conn.prepareStatement(
//                     "SELECT id, theater_id, row_name, seat_number FROM seats WHERE theater_id = ? ORDER BY row_name, seat_number");
//             seatsStmt.setInt(1, showtime.getTheaterId());
//             seatsRs = seatsStmt.executeQuery();

//             Map<String, List<Seat>> seatsByRow = new HashMap<>();
//             while (seatsRs.next()) {
//                 Seat seat = new Seat(
//                         seatsRs.getInt("id"),
//                         seatsRs.getInt("theater_id"),
//                         seatsRs.getString("row_name"),
//                         seatsRs.getInt("seat_number"),
//                         true // Assume available initially
//                 );

//                 String rowName = seat.getRowName();
//                 if (!seatsByRow.containsKey(rowName)) {
//                     seatsByRow.put(rowName, new ArrayList<>());
//                 }
//                 seatsByRow.get(rowName).add(seat);
//             }

//             // Get booked seats for this showtime
//             bookedSeatsStmt = conn.prepareStatement(
//                     "SELECT bs.seat_id FROM bookings b " +
//                             "JOIN booked_seats bs ON b.id = bs.booking_id " +
//                             "WHERE b.showtime_id = ?");
//             bookedSeatsStmt.setInt(1, showtimeId);
//             bookedSeatsRs = bookedSeatsStmt.executeQuery();

//             List<Integer> bookedSeatIds = new ArrayList<>();
//             while (bookedSeatsRs.next()) {
//                 bookedSeatIds.add(bookedSeatsRs.getInt("seat_id"));
//             }

//             // Mark booked seats as unavailable
//             for (List<Seat> row : seatsByRow.values()) {
//                 for (Seat seat : row) {
//                     if (bookedSeatIds.contains(seat.getId())) {
//                         seat.setAvailable(false);
//                     }
//                 }
//             }

//             request.setAttribute("seatsByRow", seatsByRow);
//             request.getRequestDispatcher("seats.jsp").forward(request, response);

//         } catch (SQLException e) {
//             e.printStackTrace();
//             response.getWriter().write("Error loading seats: " + e.getMessage());
//         } finally {
//             DatabaseUtil.closeResources(showtimeRs, showtimeStmt, null);
//             DatabaseUtil.closeResources(seatsRs, seatsStmt, null);
//             DatabaseUtil.closeResources(bookedSeatsRs, bookedSeatsStmt, conn);
//         }
//     }
// }