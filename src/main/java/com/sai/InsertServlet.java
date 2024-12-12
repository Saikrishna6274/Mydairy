package com.sai;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con=null;
       Statement st=null;
    
    public InsertServlet() {
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
            catch(Exception e)
            {
            	System.out.println(e.getMessage());
            	
            }
            }
    
      	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      	}
      	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      	  response.setContentType("text/html");
          PrintWriter out = response.getWriter();
      		try {
      			int id=Integer.parseInt(request.getParameter("id"));
      			String Date=request.getParameter("date");
      			String desc=request.getParameter("desc");
      			st.executeUpdate("insert into datesinsert values("+id+",'"+Date+"','"+desc+"')");
      			}
      			catch(SQLException e)
      			{
      				System.out.println(e.getMessage());
      			}
      			PrintWriter pw=response.getWriter();
      			pw.print("Insert record is sucessfully.....");
      			pw.print("<p><a href='welcome.html' style='color: #2575fc; text-decoration: none;'>Go Back to Welcome Page</a></p>");
      		}
      	}