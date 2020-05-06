package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hospital {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagement?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertHospital(String type, String name, String telno, String address)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement    
			String query = " insert into hospitals(`HospitalID`,`hType`,`HosName`,`HosTelNo`,`HosAddress`)" + " values (?, ?, ?, ?, ?)"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, 0);    
			preparedStmt.setString(2, type);    
			preparedStmt.setString(3, name);    
			preparedStmt.setString(4, telno);    
			preparedStmt.setString(5, address);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newHospitals = readHospitals(); 
			output =  "{\"status\":\"success\", \"data\": \"" +        
							newHospitals + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the hospitals.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	} 
	
	public String readHospitals()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr><th>Hospital Type</th><th>Hospital Name</th><th>Telno</th><th>Address</th><th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from hospitals";    
			Statement stmt = con.createStatement();    
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String HospitalID = Integer.toString(rs.getInt("HospitalID"));     
				String hType = rs.getString("hType");     
				String HosName = rs.getString("HosName");     
				String HosTelNo = rs.getString("HosTelNo");     
				String HosAddress = rs.getString("HosAddress"); 
			
	 
				// Add into the html table 
				output += "<tr><td><input id=\'hidHospitalIDUpdate\' name=\'hidHospitalIDUpdate\' type=\'hidden\' value=\'" + HospitalID + "'>" 
							+ hType + "</td>";      
				output += "<td>" + HosName + "</td>";     
				output += "<td>" + HosTelNo + "</td>";     
				output += "<td>" + HosAddress + "</td>"; 
	 
				// buttons     
//				output += "<td><input name=\'btnUpdate\' type=\'button\' value=\'Update\' class=\' btnUpdate btn btn-secondary\'></td>"
//						//+ "<td><form method=\"post\" action=\"items.jsp\">      "
//						+ "<input name=\'btnRemove\' type=\'submit\' value=\'Remove\' class=\'btn btn-danger\'> "
//						+ "<input name=\"hidItemIDDelete\" type=\"hidden\" value=\"" + itemID + "\">" + "</form></td></tr>"; 
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-hospitalid='" + HospitalID + "'>" + "</td></tr>"; 
			} 
	 
			con.close(); 
	 
			// Complete the html table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the hospitals.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 
	
	public String updateHospital(String ID, String type, String name, String telno, String address)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE hospitals SET hType=?,HosName=?,HosTelNo=?,HosAddress=? WHERE HospitalID=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, type);    
			preparedStmt.setString(2, name);    
			preparedStmt.setString(3, telno);    
			preparedStmt.setString(4, address);    
			preparedStmt.setInt(5, Integer.parseInt(ID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newHospitals = readHospitals();    
			output = "{\"status\":\"success\", \"data\": \"" +        
									newHospitals + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the hospital.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	public String deleteHospital(String HospitalID)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for deleting."; } 
	 
			// create a prepared statement    
			String query = "delete from hospitals where HospitalID=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(HospitalID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newHospitals = readHospitals();    
			output = "{\"status\":\"success\", \"data\": \"" +       
								newHospitals + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the hospital.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 

}
