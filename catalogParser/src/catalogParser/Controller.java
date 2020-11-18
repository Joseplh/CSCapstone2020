package catalogParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.sql.*;

import com.microsoft.sqlserver.jdbc.SQLServerException;

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
	 * @param title	   The request title.
	 * @param quantity The request quantity.
	 * @param issue	   The request issue number.
	 */

	
	/*
	 * Returns true if connected, false if not
	 */
	public boolean isConnected() {
		return (dbConnection != null);
	}
	public int insert(String query) {
		if(!isConnected()) {
			connect();
		}
		
		Statement sqlStatement = null;		
		
		try {
			sqlStatement = dbConnection.createStatement();
		} catch (SQLException e) {
			System.err.println("Error connecting to the database");
			e.printStackTrace();
			System.exit(0);
			
		}	
		
		try {
			return sqlStatement.executeUpdate(query);
		} catch (SQLServerException e){
			System.err.println("Duplicate value found... Continuing");
		} catch (SQLException e) {
			System.err.println("Error executing query");
			e.printStackTrace();
			System.exit(0);
		}
		
		return 1;
	}	
}

