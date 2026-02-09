package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
		private static final String url="jdbc:mysql://localhost:3306/MyDB";
		private static final String username="root";
		private static final String password="Your_Password";
		static {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
		public static Connection getConnection() throws SQLException {
			Connection con=DriverManager.getConnection(url,username,password);
			return con;
		}
	}
