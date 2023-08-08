package com.fssa.bookandplay.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import io.github.cdimascio.dotenv.Dotenv;
/**
 * The ConnectionUtil Class has a method  get connection which help to connect  with database
 */
public class ConnectionUtil {
	/**
	 * Creating a Logger Class For Display Message
	 */

	public static Connection getConnection() {
        Connection con = null;

        String url, userName, passWord;

        if (System.getenv("CI") != null) {
            url = System.getenv("DATABASE_HOST");
            userName = System.getenv("DATABASE_USERNAME");
            passWord = System.getenv("DATABASE_PASSWORD");
            
        } else {
            Dotenv env = Dotenv.load();
            url = env.get("DATABASE_HOST");
            userName = env.get("DATABASE_USERNAME");
            passWord = env.get("DATABASE_PASSWORD");
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
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
			/**
			 *  No need re throw the exception.
			 */
		
		}
	}

public static void main(String[] args) {
		Connection con = getConnection();

	}

}