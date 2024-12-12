package com.sai;

import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection con=null;
    Statement st=null;

    // Database connection details
    //private static final String DB_URL = "jdbc:mysql://localhost:3306/mydiary?useSSL=false","root","root";
//    private static final String DB_USER = "root"; // Replace with your database username
//    private static final String DB_PASSWORD = "root"; // Replace with your database password

    public UpdateServlet() {
        super();
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydairy?useSSl=false","root", "root");
//        st=con.createStatement();
        }
        catch(Exception e) {
        	System.out.println(e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get parameters from the update form
        int id = Integer.parseInt(request.getParameter("id"));
        String date = request.getParameter("date");
        String desc = request.getParameter("desc");

        // Database update logic
//        (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
        try {  String query = "UPDATE datesinsert SET date= ?, description = ? WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);

            // Set parameters for the query
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, desc);
            preparedStatement.setInt(3, id);

            int rowsUpdated = preparedStatement.executeUpdate();

            // Response to the client
            if (rowsUpdated > 0) {
                out.println("<html><body style='text-align: center; font-family: Arial;'>");
                out.println("<h2>Record updated successfully!</h2>");
                out.println("<p><a href='welcome.html' style='color: #2575fc; text-decoration: none;'>Go Back to Welcome Page</a></p>");
                out.println("</body></html>");
            } else {
                out.println("<html><body style='text-align: center; font-family: Arial;'>");
                out.println("<h2>No record found with ID: " + id + "</h2>");
                out.println("<p><a href='update.html' style='color: #2575fc; text-decoration: none;'>Try Again</a></p>");
                out.println("</body></html>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body style='text-align: center; font-family: Arial;'>");
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
            out.println("<p><a href='update.html' style='color: #2575fc; text-decoration: none;'>Try Again</a></p>");
            out.println("</body></html>");
        }
    }
}
