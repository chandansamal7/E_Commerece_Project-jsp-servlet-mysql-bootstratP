package cn.shoppingcart.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.shoppingcart.connection.DbCon;
import cn.shoppingcart.dao.OrderDao;
import cn.shoppingcart.model.User;

/**
 * Servlet implementation class CancelOrderServlet
 */
@WebServlet("/cancel-order")
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out = response.getWriter()){
			
			String id = request.getParameter("id");
			User auth = (User)request.getSession().getAttribute("auth");
			
			if(id != null && auth != null) {
				
				OrderDao oDao = new OrderDao(DbCon.getConnection());
				
				oDao.cancelOrder(Integer.parseInt(id));
				
			}
			response.sendRedirect("orders.jsp");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
