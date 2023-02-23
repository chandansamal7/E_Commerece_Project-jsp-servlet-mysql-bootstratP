package cn.shoppingcart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.shoppingcart.connection.DbCon;
import cn.shoppingcart.dao.OrderDao;
import cn.shoppingcart.model.Cart;
import cn.shoppingcart.model.Order;
import cn.shoppingcart.model.User;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out = response.getWriter()){
			
			//out.println("Check Out Servlet");
			
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
			Date date = new Date();
			
			//retrive all cart products
			ArrayList<Cart> cart_list = (ArrayList<Cart>)request.getSession().getAttribute("cart-list");
			
			//user authentication
			User auth = (User)request.getSession().getAttribute("auth");
			
			//check auth and cart list
			if(cart_list != null && auth != null) {
				
				for(Cart item : cart_list) {
					
					//creating order obj
					Order order  = new Order();
					
					order.setId(item.getId());
					order.setUid(auth.getId());
					order.setQuantity(item.getQuantity());
					order.setDate(formatter.format(date));
					
					//instatiate Dao class
					OrderDao oDao = new OrderDao(DbCon.getConnection());
					
					// calling insert method
					boolean result = oDao.insertOrder(order);
					
					//if one product failed to insert then i will come out from the loop 
					if(!result) break;
					
				}
				
				cart_list.clear();
				response.sendRedirect("orders.jsp");
				
			}else {
				if(auth == null) response.sendRedirect("login.jsp");
				response.sendRedirect("cart.jsp");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
