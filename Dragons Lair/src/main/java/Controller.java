package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.sql.*;

public class Controller {

	private Connection dbConnection = null;
	private final String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String jdbcURL = "";
	
	public void connect() {
		try {
			URL config = getClass().getClassLoader().getResource("config.ini");
			File file = new File(config.toURI());
			
			BufferedReader br;
			br = new BufferedReader(new FileReader(file));
			if (br.readLine().matches("custom=true")) {
				System.out.println("Input is custom from config.ini");
				jdbcURL = br.readLine() // "jdbc:sqlserver://70.171.162.251;"
						+ br.readLine() // "database=DLC;"
						+ br.readLine() // "user=RemoteUser;"
						+ br.readLine() // "password= ***********;"
						+ br.readLine() // "encrypt=false;"
						+ br.readLine() // "trustServerCertificate=true;"
						+ br.readLine();// "loginTimeout=30;"
				br.close();
			}
		} catch (Exception err) {
			System.err.println("Error reading from config.ini");
			err.printStackTrace(System.err);
			System.exit(0);
		}
        
        try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e1) {
			System.err.println("Error loading JDBC driver");
			e1.printStackTrace(System.err);
			System.exit(0);
		} 
        
		try {
			dbConnection = DriverManager.getConnection(jdbcURL);
			     
		} catch (SQLException e) {
			System.err.println("Error connecting to the database");
			e.printStackTrace(System.err);
			System.exit(0);
		}
	}
	
	
	/**
	 * Handler for adding a request to the database.
	 * 
	 * @param customerCode The unique customer code.
	 * @param title	   	   The request title.
	 * @param quantity 	   The request quantity.
	 * @param issue	   	   The request issue number.
	 */
	public void addRequest(String customerCode, String title, int quantity, int issue) {
		// TODO: Add custom store code number
		insert(String.format("INSERT INTO [DLC].[dbo].[Order]([Store Code], [Customer Code], Description, [Issue Start], "
				+ "[Issue End], Quantity) VALUES('%s','%s','%s',%d,%d,%d)", "dl2", customerCode, title, issue, issue, quantity));
	}
	//altered the insert statements to directly return as opposed to a useless one: Joseph
	public int insertCustomer(String first, String last, String email, String phone) {
		return insert("INSERT INTO Customer([Last Name], [First Name], [Email], [Phone #1]) VALUES('" + last + "', '" + first + "', '" + email + "', '" + phone + "')");
	}

	public int deleteCustomer(int ccode) {
		return insert("DELETE FROM Customer WHERE [Customer Code] = " + ccode);
	}

	public int updateCustomer(int ccode, String first, String last, String email, String phone) {
		return insert("UPDATE Customer Set [Last Name] = '" + last + "', [First Name] = '" + first + "', [Email] = '" + email + "', [Phone #1] = '" + phone + "' WHERE [Customer Code] = " + ccode);
	}

	public int insertTitle(String title, String distr, String sub, String tCode) {
		return insert("INSERT INTO Catalog([Description], [Distributor], [Disct? Sub], [Calalog ID]) VALUES('" + title + "', '" + distr + "', '" + sub + "', '" + tCode + "')");
	}

	public int deleteTitle(String tCode) {
		return insert("DELETE FROM Catalog WHERE [Calalog ID] = '" + tCode + "'");
	}

	public int updateTitle(String title, String sub, String tCode) {
		return insert("UPDATE Catalog Set [Description] = '" + title + "', [Disct? Sub] = '" + sub + "' WHERE [Calalog ID] = '" + tCode + "'");
	}

	/*
	 * Returns true if connected, false if not
	 */
	public boolean isConnected() {
		return (dbConnection != null);
	}

	/*
	 * Returns the following columns from the customer table
	 */
	public String[][] getCustomers() {
		return select("SELECT [Last Name], [First Name], [Phone #1], [Email], [Customer Code] FROM Customer");
	}
	
	public String[][] getReports() {
		return new String[0][0];
	}	
	
	/**
	 * Handler for fetching the requests for a given customer.
	 * 
	 * @param customerCode  The customer code.
	 * @return {String[][]} with the formatted request information.
	 */
	public String[][] getRequests(String customerCode) {
		return select(String.format("Select [Store Code], Description, [Issue Start], [Issue End],"
				+ " Quantity FROM [DLC].[dbo].[Order] WHERE [Customer Code]=%s", customerCode));
	}	
	
	public String[][] getTitles() {
		return select("SELECT [Description], [Disct? Sub], [Distributor], [Calalog ID] FROM Catalog");
	}	
	public String[][] select(String query) {
		if(!isConnected()) {
			connect();
		}
		
		Statement sqlStatement = null;
		ResultSet rs = null;
		
		
		try {
			sqlStatement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			System.err.println("Error connecting to the database");
			e.printStackTrace();
			System.exit(0);
			
		}	
		
		try {
			rs = sqlStatement.executeQuery(query);
		} catch (SQLException e) {
			System.err.println("Error executing query");
			e.printStackTrace();
			System.exit(0);
		}
		
		return format(rs);
	}
	/*
	 * Method to insert, update, delete info. Returns 0 for statements that return nothing or the row count 
	 * 
	 */
	public int insert(String query) {
		if(!isConnected()) {
			connect();
		}
		
		Statement sqlStatement = null;
		int result = 0;
		
		
		try {
			sqlStatement = dbConnection.createStatement();
		} catch (SQLException e) {
			System.err.println("Error connecting to the database");
			e.printStackTrace();
			System.exit(0);
			
		}	
		
		try {
			result = sqlStatement.executeUpdate(query);
		} catch (SQLException e) {
			System.err.println("Error executing query");
			e.printStackTrace();
			System.exit(0);
		}
		
		return result;
	}	
	/* TODO: Need a method for taking in an insert, update, delete query
	 * 		 Maybe return boolean/int depending on if it was successful or not
	 * 		 executeUpdate(query)
	 *
	 */
	public String[][] format(ResultSet rs){
		String[][] data = null;
		int columns = 0;
		int rows = 0;

		try {
			columns = rs.getMetaData().getColumnCount();
			while(rs.next()) {
				rows += 1;
			}
			rs.first();
		} catch (SQLException e1) {
			System.err.println("Error getting metadata on ResultSet");
			e1.printStackTrace();
			System.exit(0);
		}
		
		try {
			data = new String[rows][columns];
			for(int j = 0; j < rows; j++) {
				for(int k = 0; k < columns; k++) {
					data[j][k] = rs.getString(k+1);
				}
				rs.next();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	protected boolean isAccount(String user, String pass) {
		//Created by Joseph: This is a sql statement to check if the username and password are valid
		if(!isConnected()) {
			connect();
		}
		
		Statement sqlStatement = null;		
		ResultSet rs = null;
		
		try {
			sqlStatement = dbConnection.createStatement();
		} catch (SQLException e) {
			System.err.println("Error connecting to the database");
			e.printStackTrace();
			System.exit(0);
			
		}	
		
		try {
			rs = sqlStatement.executeQuery("SELECT CASE WHEN EXISTS ( SELECT * FROM [Account] WHERE [User] = '"+user+"' AND [Pass] = '"+pass+"')THEN CAST(1 AS BIT)ELSE CAST(0 AS BIT) END");
			while(rs.next()) {
				if(rs.getInt(1) == 1)
					return true;
			}
		} catch (SQLException e) {
			System.err.println("Error executing query in password check");
			e.printStackTrace();
			System.exit(0);
		}
		return false;
	}
}

