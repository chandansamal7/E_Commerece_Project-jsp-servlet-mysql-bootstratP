package cn.shoppingcart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.shoppingcart.model.Cart;
import cn.shoppingcart.model.Product;

public class ProductDao {
	
private Connection conn;
	
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ProductDao(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public List<Product> getAllProduct(){
		
		Product product = null;
		
		List<Product> products = new ArrayList<>();
		
		try {
			
			query ="select * from products";
			pst = this.conn.prepareStatement(query);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				product = new Product();
				
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getDouble("price"));
				product.setImage(rs.getString("image"));
				
				products.add(product);
				
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return products;
	}
	
	public List<Cart> getCartProducts(ArrayList<Cart> cartList){
		
		List<Cart> products = new ArrayList<>();
		
		try {
			if(cartList.size() > 0) {
				
				//when we confirm that our cartList has item then we will use forloop.
				for(Cart item:cartList) {
					
					query = "select * from products where id = ?";
					pst = this.conn.prepareStatement(query);
					pst.setInt(1,item.getId());
					rs = pst.executeQuery();
					
					while(rs.next()) {
						
						Cart row = new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price")*item.getQuantity());
						row.setQuantity(item.getQuantity());
						
						products.add(row);
					}
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	
	public double getTotalCartPrice(ArrayList<Cart> cartList) {
		double sum = 0;
		
		try {
			
			if(cartList.size() > 0) {
				for(Cart item:cartList) {
					
					query = "select price from products where id = ?";
					pst = this.conn.prepareStatement(query);
					pst.setInt(1,item.getId());
					rs = pst.executeQuery();
					
					while(rs.next()) {
						sum += rs.getDouble("price") * item.getQuantity();
					}
					
				}
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sum;
	}

	public Product getSingleProduct(int id) {
		
      Product row = null;
		
		try {
			
			query = "select * from products where id=?";
			
			pst = this .conn.prepareStatement(query);
			pst.setInt(1, id);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
				row = new Product();
				
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return row;
	 }
}

	
	
