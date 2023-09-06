package com.fssa.bookandplay.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fssa.bookandplay.exceptions.DAOException;

/**
 * The ConnectionUtil Class has a method get connection which help to connect
 * with database
 */
public class ConnectionUtil {

	private ConnectionUtil() {
		// private constructor
	}

	/**
	 * Creating a Logger Class For Display Message
	 * 
	 * @throws DAOException
	 */
	static Logger logger = new Logger();

	public static Connection getConnection() {
		Connection con = null;

		String url;
		String userName;
		String passWord;

		url = System.getenv("DATABASE_HOST");
		userName = System.getenv("DATABASE_USERNAME");
		passWord = System.getenv("DATABASE_PASSWORD");
//	       String url = "jdbc:mysql://localhost:3306/bookandplaybackend2";
//	        String userName = "root";
//	        String passWord = "123456";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);
			logger.info("connected");
		} catch (Exception e) {
			throw new RuntimeException("Unable to connect to the database");
		}

		return con;
	}



}
