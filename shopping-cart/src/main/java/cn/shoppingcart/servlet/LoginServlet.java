package cn.shoppingcart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.shoppingcart.connection.DbCon;
import cn.shoppingcart.dao.UserDao;
import cn.shoppingcart.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
	    
		String email = request.getParameter("email");
		String password = (String)request.getParameter("password");
		
		try {
			UserDao udao = new UserDao(DbCon.getConnection());
			
			User user = udao.userLogin(email, password);
			
			//out.println(user.getId() + " " + user.getName() + " " + user.getEmail());
			
			if(user != null) {
				
				out.println("Login Successfully");
				//i want to set this user value in session to use for next. 
				request.getSession().setAttribute("auth", user);
			    //after login successfully i want to redirect to index.jsp page
				response.sendRedirect("index.jsp");
				
			}else {
				out.println("<h1>Login failed!!!!<h1>");
				out.println("<a href='login.jsp'>Go to Login page</a>");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
