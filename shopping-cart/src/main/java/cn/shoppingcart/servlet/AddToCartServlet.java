package cn.shoppingcart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.shoppingcart.model.Cart;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out = response.getWriter()) {
			
			ArrayList<Cart> cartList = new ArrayList<>();
			
			//we need id from the product. we received the id from the url.
			int id =Integer.parseInt(request.getParameter("id"));
			
			//CREATING CART OBJECT
			Cart cart = new Cart();
			
			cart.setId(id);
			cart.setQuantity(1);
			
			//System.out.println(cart.getName());
			
			HttpSession session = request.getSession();
			
			ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
			
			//i.e there is no session attribute whose name is cart-list(no product in cart list)
			if(cart_list == null) {
				
				//add product to the cartList
				cartList.add(cart);
				//System.out.println(cartList);
				
				session.setAttribute("cart-list", cartList);
				out.println("session created and added to the list");
				response.sendRedirect("index.jsp");
				
			}else {
//				out.println("cart list exist");
				
				//IF IT IS EXIT WE NEED TO GET THAT CARTLIST
				 cartList = cart_list; 
				 
	                boolean exist = false;
				
	                for (Cart c : cart_list) {
	                    if (c.getId() == id) {
	                        exist = true;
	                        out.println("Item Already in Cart.");   
	                        response.sendRedirect("cart.jsp");
	                       // out.println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='cart.jsp'>GO to Cart Page</a></h3>");

	                    }
	                }

	                if (!exist) {
	                    cartList.add(cart);
	                    response.sendRedirect("index.jsp");	                }
               }
			}
		
		}
	}

