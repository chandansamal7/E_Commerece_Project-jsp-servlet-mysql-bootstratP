package cn.shoppingcart.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//I will simply remove the session in this case
		
		PrintWriter out = response.getWriter();
		
		//i am trying to get the session and i will check wheather it is null or not
		
		if(request.getSession().getAttribute("auth") != null) {
			
			//if it is not null i.e user is logged in so i have to remove that attribute.
			request.getSession().removeAttribute("auth");
			
			out.println("Logout successfully");
			
			//after removing attr send the user to login page
			response.sendRedirect("login.jsp");
		}else {
			//if auth is null
			response.sendRedirect("index.jsp");
		}
	}

	

}
