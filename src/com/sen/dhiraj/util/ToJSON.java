package com.sen.dhiraj.util;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.owasp.esapi.ESAPI;

public class ToJSON {
	
	public JSONArray toJSONArray(ResultSet rs){
		JSONArray json = new JSONArray();
		String temp = null;
		
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while(rs.next()){
				int numColumns = rsmd.getColumnCount();
				
				JSONObject jObject = new JSONObject();
				System.out.println("Column count = "+numColumns);
				for (int i=1;i<numColumns+1; i++){
					
					String column_name = rsmd.getColumnName(i);
					System.out.println("Column Name = "+column_name);
					if (rsmd.getColumnType(i) == java.sql.Types.ARRAY){
						jObject.put(column_name, rs.getArray(column_name));
						
					}
					else if (rsmd.getColumnType(i) == java.sql.Types.BIGINT){
						jObject.put(column_name, rs.getInt(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.BOOLEAN){
						jObject.put(column_name, rs.getBoolean(column_name));
                   	 /*Debug*/ System.out.println("ToJson: BOOLEAN");
                    }
                    else if(rsmd.getColumnType(i)==java.sql.Types.BLOB){
                    	jObject.put(column_name, rs.getBlob(column_name));
                   	 /*Debug*/ System.out.println("ToJson: BLOB");
                    }
                    else if(rsmd.getColumnType(i)==java.sql.Types.DOUBLE){
                    	jObject.put(column_name, rs.getDouble(column_name)); 
                   	 /*Debug*/ System.out.println("ToJson: DOUBLE");
                    }
                    else if(rsmd.getColumnType(i)==java.sql.Types.FLOAT){
                    	jObject.put(column_name, rs.getFloat(column_name));
                   	 /*Debug*/ System.out.println("ToJson: FLOAT");
                    }
                    else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
                    	jObject.put(column_name, rs.getInt(column_name));
                   	 /*Debug*/ System.out.println("ToJson: INTEGER");
                    }
                    else if(rsmd.getColumnType(i)==java.sql.Types.NVARCHAR){
                    	jObject.put(column_name, rs.getNString(column_name));
                   	 /*Debug*/ System.out.println("ToJson: NVARCHAR");
                    }
                    else if(rsmd.getColumnType(i)== java.sql.Types.VARCHAR){
                    	temp = rs.getString(column_name);
                    	temp = ESAPI.encoder().canonicalize(temp);
                    	temp = ESAPI.encoder().encodeForHTML(temp);
                    	jObject.put(column_name, temp);
                    	//jObject.put(column_name, rs.getString(column_name));
                    }
                    else if(rsmd.getColumnType(i)==java.sql.Types.SMALLINT){
                    	jObject.put(column_name, rs.getInt(column_name));
                   	 /*Debug*/ System.out.println("ToJson: SMALLINT");
                    }
                    else if(rsmd.getColumnType(i)==java.sql.Types.DATE){
                    	jObject.put(column_name, rs.getDate(column_name));
                   	 /*Debug*/ System.out.println("ToJson: DATE");
                    }
                    else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
                    	jObject.put(column_name, rs.getTimestamp(column_name));
                   	 /*Debug*/ System.out.println("ToJson: TIMESTAMP");
                    }
                    else if(rsmd.getColumnType(i)==java.sql.Types.NUMERIC){
                    	jObject.put(column_name, rs.getBigDecimal(column_name));
                   	 // /*Debug*/ System.out.println("ToJson: NUMERIC");
                     }
                    else {
                    	jObject.put(column_name, rs.getObject(column_name));
                   	 /*Debug*/ System.out.println("ToJson: Object "+column_name);
                    } 
				}
				json.put(jObject);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

}
