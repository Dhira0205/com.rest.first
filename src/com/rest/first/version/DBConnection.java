package com.rest.first.version;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	public Connection conn = null;
	public Statement stmt = null;
	public ResultSet rs = null;
	
	public Connection getConnection(){
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			this.conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","dhiraj","Passw0rd");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.conn;
	}
	
	public Statement getStatement(Connection con) throws SQLException{
		this.stmt = con.createStatement();
		return this.stmt;
	}
	
	public ResultSet getResult(String query, Statement stmnt) throws SQLException{
		this.rs = stmnt.executeQuery(query);
		return this.rs;
	}
	
	public void closeConn(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String arg[]){
		DBConnection testDB = new DBConnection();
		
		Connection con = testDB.getConnection();
		try {
			Statement stmnt =  testDB.getStatement(con);
			ResultSet rs  = testDB.getResult("select * from staff", stmnt);
			while (rs.next()){
				System.out.println(rs.getString(1));  
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (con != null)
			testDB.closeConn(con);
		}
	}
}
