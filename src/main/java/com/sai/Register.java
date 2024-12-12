package com.sai;



import java.beans.Statement;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@WebServlet("/Register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private String uname;
	Connection con=null;
	Statement st=null;
	private String phone;
	

    
    public Register() {
        super();
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydairy?useSSL=false","root","root");
//        	st=con.createStatement();
        }
        catch(SQLException e)
        {
        	System.out.println(e.getMessage());
        }
        catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
    }
    

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 String fname = request.getParameter("fname");
         String lname = request.getParameter("lname");
         String uname=request.getParameter("uname");
         String phone = request.getParameter("phone");
         String email = request.getParameter("email");
         String password = request.getParameter("password");
//         String city = request.getParameter("city");
    	// Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Database insertion logic
        try  {
            String query = "INSERT INTO person (fname, lname,uname, phone, email, password) VALUES ( ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);

            // Set parameters
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2,lname);
            preparedStatement.setString(3,uname);
            preparedStatement.setString(4,phone);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, password);
//            preparedStatement.setString(7, city);

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                out.println("<html><body style='text-align: center; font-family: Arial;'>");
                out.println("<h2>Registration Successful!</h2>");
                out.println("<p>Welcome, " + fname + " " + lname + "!</p>");
                out.println("<p><a href='login.html' style='color: #ff7e5f; text-decoration: none;'>Click here to Login</a></p>");
                out.println("</body></html>");
            } else {
                out.println("<h2>Registration Failed. Please try again.</h2>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h2>Database Error: " + e.getMessage() + "</h2>");
        }
//        // Retrieve form data
//        String uname = request.getParameter("uname");
//        String fname=request.getParameter("fname");
//       String lname = request.getParameter("lname");
//        String mobileno = request.getParameter("mobileno");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        String city = request.getParameter("city");
//
//        // Process the data (e.g., store in database, etc.)
//        // For demonstration, just display the input
////        out.println("<html><body>");
////        out.println("<h2>Registration Successful!</h2>");
////        out.println("<p>User Name: " + uname + "</p>");
//////      out.println("<p>Last Name: " + lname + "</p>");
////        out.println("<p>Mobile Number: " + mobileno + "</p>");
////        out.println("<p>Email: " + email + "</p>");
////        out.println("<p>City: " + city + "</p>");
////        out.println("<a href=\"register.html\">Go Back</a>");
////        out.println("</body></html>");
//        
//        out.println("<html><body style='text-align: center; font-family: Arial;'>");
//        out.println("<h2>Registration Successful!</h2>");
//        out.println("<p>Welcome, " + fname + " " + lname + "!</p>");
//        out.println("<p><a href='login.html' style='color: #ff7e5f; text-decoration: none;'>Click here to Login</a></p>");
//        out.println("</body></html>");
    }
}
