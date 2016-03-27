package ru.tritec.jdbcproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Runner {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection con = null;

		con = DriverManager.getConnection("jdbc:sqlite:users_db.db");
		Statement stat = con.createStatement();
		ResultSet rs = stat.executeQuery("SELECT * FROM users");
		while(rs.next()){
			System.out.println("name = " + rs.getString("name"));
		}
		
		con.close();
	}

}
