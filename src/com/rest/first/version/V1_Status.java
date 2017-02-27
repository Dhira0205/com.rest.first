package com.rest.first.version;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.sen.dhiraj.util.ToJSON;

@Path("/v1/status")
public class V1_Status {
	
	private Connection conn = null;
	private Statement stmt = null;
	
	private static final String api_version = "00.01";
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle(){
		return "<p>java Web Services</p>";
	}
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion(){
		return "<p> API version</p>"+api_version;
	}
	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDate(){
		String returnString = "";
		String myString = "";
		DBConnection dbConn = new DBConnection();
		this.conn = dbConn.getConnection();
		try {
			this.stmt = dbConn.getStatement(this.conn);
			ResultSet rs = dbConn.getResult("select SYSTIMESTAMP from dual", this.stmt);
			while (rs.next()){
				myString = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (this.conn != null)
				dbConn.closeConn(this.conn);
		}
		returnString = "<p> Current Time =" +myString;
		return returnString;
		
	}
	
	@Path("/JSONDetails")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllParts(){
		String returnString = null;
		Response rb = null;
		JSONArray json = new JSONArray();
		DBConnection dbConn = new DBConnection();
		ToJSON toJSON = new ToJSON();
		this.conn = dbConn.getConnection();
		try {
			this.stmt = dbConn.getStatement(this.conn);
			ResultSet rs = dbConn.getResult("select * from PC_PARTS", this.stmt);
			json = toJSON.toJSONArray(rs);
			returnString = json.toString();
			rb = Response.ok(returnString).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (this.conn != null)
				dbConn.closeConn(this.conn);
		}
		return rb;	
	}
	
}
