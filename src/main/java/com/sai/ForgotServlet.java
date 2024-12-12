package com.sai;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ForgotServlet")
public class ForgotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   Connection con=null;
   Statement st=null;
    public ForgotServlet() {
        super();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydairy?useSSL=false","root","root");
            st=con.createStatement();
               }
              catch(ClassNotFoundException e)
              {
              	System.out.println(e.getMessage());
              }
              catch(SQLException e)
              {
              	System.out.println(e.getMessage());
              }
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        String name = request.getParameter("uname");
	        String query = "SELECT * FROM person WHERE uname = ?";
	        
	        PreparedStatement pstmt = con.prepareStatement(query);
	        pstmt.setString(1, name);
	        
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            System.out.println(rs.getString("uname") + " " + rs.getString("password"));
	            request.setAttribute("name", rs.getString("uname"));
	            request.setAttribute("password", rs.getString("password"));
	            RequestDispatcher rd = request.getRequestDispatcher("updatePassword1.jsp");
	            rd.forward(request, response);
	        }
	        
	        pstmt.close();
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


}
