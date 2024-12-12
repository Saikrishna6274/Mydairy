package com.sai;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DisplayDiary")
public class DisplayDiary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con=null;
       Statement st=null;
    
    public DisplayDiary() {
        super();
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydairy?useSSL=false","root","root");
        	st=con.createStatement();
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
		try {
			PrintWriter pw=response.getWriter();
			ResultSet rs=st.executeQuery("select * from datesinsert");
			pw.print("<table border='1' align ='center'>");
			pw.print("<tr><th>ID</th> <th>Date</th><th>Description</th><tr>");
			while(rs.next())
			{
				pw.print("<tr>");
				pw.print("<td>"+rs.getString(1)+"</td>");
				pw.print("<td>"+rs.getString(2)+"</td>");
				pw.print("<td>"+rs.getString(3)+"</td>");
				pw.print("</tr>");
				
			}
		}
			catch (SQLException e) {
				
			System.out.println(e.getMessage());
			}	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	}

}
