package com.sai;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginServlet1")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    Connection con=null;
    Statement st=null;
 
 public LoginServlet() {
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
		String username=request.getParameter("uname");
		String password=request.getParameter("password");
		ResultSet rs=st.executeQuery("select * from person where uname='"+username+"' and password='"+password+"' ");
		RequestDispatcher rd=null;
		if(rs.next())
		{
		rd=request.getRequestDispatcher("welcome.html");
		rd.forward(request, response);
		}
		else {
			request.setAttribute("msg","invalid login please login again" );
			rd=request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
	}
	catch(SQLException e){
		System.out.println(e.getMessage());
	}
	}

}
