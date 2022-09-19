package com.jxut.util;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DbUtil {
	
	private static String DRIVER ="com.mysql.cj.jdbc.Driver";
	private static String URL ="jdbc:mysql://localhost:3306/springboot?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
	private static String USERNAME ="root";
	private static String PASSWORD ="root";
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void closeAll(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(stmt!=null) {
				stmt.close();
			}
			if(conn!=null) {
				conn.close();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}