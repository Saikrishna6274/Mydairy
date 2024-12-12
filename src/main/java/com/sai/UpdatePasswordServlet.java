package com.sai;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdatePasswordServlet")
public class UpdatePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydairy?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!newPassword.equals(confirmPassword)) {
            response.sendRedirect("updatePassword.jsp?message=Passwords+do+not+match");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "UPDATE person SET password = ? WHERE uname = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newPassword); // Use hashed passwords in a real-world scenario
            statement.setString(2, username);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                response.sendRedirect("login.jsp?message=Password+updated+successfully");
            } else {
                response.sendRedirect("updatePassword.jsp?message=Password+update+failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("updatePassword.jsp?message=Error+occurred+while+updating+password");
        }
    }
}
