package cn.shoppingcart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.shoppingcart.model.User;



public class UserDao {
	
	//when we create instance of this class we need to pass the connection.
	private Connection conn;
	
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public UserDao(Connection conn) {
		this.conn = conn;
	}
	
	//creating method to log in user
	
	public User userLogin(String email, String password) {
		
		User user = null;
		
		try {
			
			query ="select * from users where email=? and password=?";
			
			pst =this.conn.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, password);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				
				// if there is any user it will create an obj for this and set the value as well.
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return user;
		
	}
	
	
	
	

}
