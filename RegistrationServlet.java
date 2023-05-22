package com.uniqueddeveloper.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().print("Hello");
		String uname = request.getParameter("name");
		String uemail = request.getParameter("email");
		String upwd = request.getParameter("pass");
		String umobile = request.getParameter("contact");
		RequestDispatcher dispatcher = null;	
		Connection con = null;
		try {
//			response.getWriter().print("inside Tryyyyy");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/youtube?useSSL=false", "root", "lonari");
			PreparedStatement pst = con.prepareStatement("insert into users(uname,upwd,uemail,umobile) values(?,?,?,?) ");
			pst.setString(1, uname);
			pst.setString(2, upwd);
			pst.setString(3, uemail);
			pst.setString(4, umobile);
//			pst.executeUpdate();
//			response.getWriter().print("inside Try2");
//			String sql = "INSERT INTO mytable (uname, upwd, uemail, umobile) VALUES (?, ?, ?, ?)";
			
            // Create a prepared statement with the SQL query
//            PreparedStatement statement = con.prepareStatement(sql);

//            // Set values for the prepared statement parameters
//            statement.setString(1, uname);
//            statement.setString(2, upwd);
//            statement.setString(3, uemail);
//            statement.setString(4, umobile);
//            
//            int rowsInserted = pst.executeUpdate();
//
//            if (rowsInserted > 0) {
//                System.out.println("Data inserted successfully!");
//            } else {
//                System.out.println("Failed to insert data!");
//            }
//			
//			
//			
			
			
//			response.getWriter().print("\n no problem till here");
			int rowCount = pst.executeUpdate();
//			response.getWriter().print("Executed");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			if(rowCount > 0) {
				request.setAttribute("status", "success");
//				dispatcher = request.getRequestDispatcher("registration.jsp");
			}
			else {
				request.setAttribute("status", "Failed");
				
			}
			dispatcher.forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				
//				response.setContentType("text/html");
//				PrintWriter pw=response.getWriter();
//				pw.println(e);
//				//out.printWriter(e);
				e.printStackTrace();
			}
			
		}
		
		
	}

}
