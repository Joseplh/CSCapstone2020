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
	/*
	 * Returns true if connected, false if not
	 */
	public boolean isConnected() {
		return (dbConnection != null);
	}
	/*
	 * Returns the following columsn from the customer table
	 */
	public String[][] getCustomers() {
		return select("SELECT [Last Name], [First Name], [Phone #1], [Email] FROM DLC.dbo.Customer");
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
		//TODO: domain for data as arraylist or equivilant
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
}

