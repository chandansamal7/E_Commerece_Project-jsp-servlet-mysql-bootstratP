package cn.shoppingcart.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {
	
	private static Connection connection = null;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		if(connection == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/ecommerce_cart";
			connection = DriverManager.getConnection(url, "root", "@Samal7377");
			System.out.println("connection built");
		}
		return connection;
	}

}
