package com.sai;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DeleteDairyid")
public class DeleteDairyid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con=null;
       Statement st=null;
    
    public DeleteDairyid() {
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
        catch(ClassNotFoundException e)
        {
        	System.out.println(e.getMessage());
        }
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		int id=Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		ResultSet rs=st.executeQuery("select * from datesinsert where id="+id);
		if(rs.next())
		{
			st.executeUpdate("delete from datesinsert where id="+id);
			PrintWriter pw=response.getWriter();
			pw.print("<h1>Record Delete Successfull.....<h1>");
		}
		else {
			request.setAttribute("msg","<h1>Invalid id please login again<h1><br>");
			RequestDispatcher rd=request.getRequestDispatcher("DeleteDairyid.jsp");
			rd.forward(request,response);
		}
	}
	catch(SQLException e)
	{
		System.out.println(e.getMessage());
	}
	}

}
