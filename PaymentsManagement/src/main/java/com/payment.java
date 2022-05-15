package com;

import java.sql.*;

public class payment {

    public Connection connect(){
    	
        //database connection details
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";
        String dbName = "electricitypower";
        String dbUsername = "root";
        String dbPassword = "";
        
        Connection conn = null;
        
        try {
        	//connecting the database
        	Class.forName(dbDriver);
        	conn = DriverManager.getConnection(dbURL+dbName, dbUsername, dbPassword);
        	
        	//if successfully connected this will be printed in the terminal
        	System.out.print("Database connected successfully");
        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        return conn;
    }
    
    
    //method to insert data
    public String insertpayment(String pAccNo, String pCus, String pAmount, String pDate) {
    	Connection conn = connect();
    	
    	String Output = "";
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "INSERT INTO paymanage (pAccNo,pCus,pAmount,pDate) VALUES (?,?,?,?)";
        	
        	//binding data to SQL query
        	PreparedStatement preparedStatement = conn.prepareStatement(query);
        	preparedStatement.setString(1, pAccNo);
        	preparedStatement.setString(2, pCus);
        	preparedStatement.setDouble(3, Double.parseDouble(pAmount));
        	preparedStatement.setString(4, pDate);
        	
        	//execute the SQL statement
        	preparedStatement.execute();
        	conn.close();
        	
        	Output = "payment inserted successfully";
        	
    	} catch(Exception e) {
    		Output = "Failed to insert the payment";
    		System.err.println(e.getMessage());
    	}
    	
    	return Output;
    }
    
    //method to update data
    public String updatepayment(String pID, String pAccNo, String pCus, String pAmount, String pDate) {
    	Connection conn = connect();
    	
    	String Output = "";
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "UPDATE paymanage SET pAccNo = ?,pCus = ?,pAmount = ?,pDate = ? WHERE pID = ?";
        	
        	//binding data to SQL query
        	PreparedStatement preparedStatement = conn.prepareStatement(query);
        	preparedStatement.setString(1, pAccNo);
        	preparedStatement.setString(2, pCus);
        	preparedStatement.setDouble(3, Double.parseDouble(pAmount));
        	preparedStatement.setString(4, pDate);
        	preparedStatement.setString(5, pID);
        	
        	//execute the SQL statement
        	preparedStatement.executeUpdate();
        	conn.close();
        	
        	Output = "payment updated successfully";
        	
    	} catch(Exception e) {
    		Output = "Failed to update the payment";
    		System.err.println(e.getMessage());
    	}
    	
    	return Output;
    }
    
    
    //method to read data
    public String readpayment() {
    	Connection conn = connect();
    	
    	String Output = "";
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "SELECT * FROM paymanage";
        	
        	//executing the SQL query
        	Statement statement = conn.createStatement();
        	ResultSet resultSet = statement.executeQuery(query);
        	
        	// Prepare the HTML table to be displayed
    		Output = "<table border='1'><tr><th>payment Code</th>" +"<th>payment Name</th><th>payment Price</th>"
    		+ "<th>payment Description</th>"
    		+ "<th>Update</th><th>Remove</th></tr>";

        	while(resultSet.next()) {
        		String pID = Integer.toString(resultSet.getInt("pID"));
        		String pAccNo = resultSet.getString("pAccNo");
        		String pCus = resultSet.getString("pCus");
        		String pAmount = Double.toString(resultSet.getDouble("pAmount"));
        		String pDate = resultSet.getString("pDate");

        		// Add a row into the HTML table
        		Output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='"+pID+"'>" + pAccNo + "</td>"; 
        		Output += "<td>" + pCus + "</td>"; 
        		Output += "<td>" + pAmount + "</td>"; 
        		Output += "<td>" + pDate + "</td>";

        		// buttons
        		Output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-sm btn-secondary'></td>" 
        				+ "<td><form method='post' action='paymanage.jsp'>"
        				+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-sm btn-danger'>"
        				+ "<input name='hidItemIDDelete' type='hidden' value='" + pID + "'>"
        				+ "</form></td></tr>";
        	}

        	conn.close();
        	
        	// Complete the HTML table
        	Output += "</table>";
        	
    	} catch(Exception e) {
    		Output = "Failed to read the paymanage";
    		System.err.println(e.getMessage());
    	}
    	
    	return Output;
    }
    
    //method to delete data
    public String deletepayment(String pID) {
    	String Output = "";
    	Connection conn = connect();
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "DELETE FROM paymanage WHERE pID = ?";
        	
        	//binding data to the SQL query
        	PreparedStatement preparedStatement = conn.prepareStatement(query);
        	preparedStatement.setInt(1, Integer.parseInt(pID));
        	
        	//executing the SQL statement
        	preparedStatement.execute();
        	conn.close();
        	
        	Output = "Deleted successfully";
        	
    	} catch(Exception e) {
    		Output = "Failed to delete the payment";
    		System.err.println(e.getMessage());
    	}
    	return Output;
    }
}
