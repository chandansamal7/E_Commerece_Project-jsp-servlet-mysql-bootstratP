package cn.shoppingcart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.shoppingcart.model.Cart;

/**
 * Servlet implementation class RemoveFromCartServlet
 */
@WebServlet("/RemoveFromCartServlet")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try(PrintWriter out = response.getWriter()){
			
			String id = request.getParameter("id");
			//out.println("product id is: " + id);
			
			if(id !=null) {
				
				ArrayList<Cart> cart_list = (ArrayList<Cart>)request.getSession().getAttribute("cart-list");
				
				if(cart_list != null) {
					
					for(Cart item :cart_list) {
						
						if(item.getId() == Integer.parseInt(id)) {
							
							cart_list.remove(cart_list.indexOf(item));
							
							break;
						}
					}
					response.sendRedirect("cart.jsp");
				}
				
			}else {
				response.sendRedirect("cart.jsp");
			}
			
			
		}
	}

	
}
