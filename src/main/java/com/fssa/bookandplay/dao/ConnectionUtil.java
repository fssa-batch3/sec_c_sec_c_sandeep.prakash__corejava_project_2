package com.fssa.bookandplay.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection() {

		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/BookandPlayBackend";
		String userName = "root";
		String passWord = "123456";
		try {
//	            Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);
			System.out.println("connected");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to connect to the database");
		}
		return con;
	}

	public static void close(Connection conn, Statement stmt, ResultSet rs,PreparedStatement ps,CallableStatement cs) {

		try {
		
			if (rs != null) {
				rs.close();
			}
			if(cs!=null) {
				cs.close();
			}
			if(ps!=null) {
				ps.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// No need re throw the exception.
		}
	}

	public static void main(String[] args) {
		Connection con = getConnection();

	}

}
