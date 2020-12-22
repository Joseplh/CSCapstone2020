package loginCredentials;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;

public class Controller {

	private Connection dbConnection = null;
	private final String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String jdbcURL = "";
	public void connect() {
		try {
			File file = new File("codedConfig.ini");
			
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
	public boolean isConnected() {
		return (dbConnection != null);
	}
	public void execute(String query) {
		/*
		 * Method to insert, update, delete info. Returns 0 for statements that return nothing or the row count 
		 * 
		 */
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
			sqlStatement.execute(query);
		} catch (SQLException e) {
			System.err.println("Error executing query");
			e.printStackTrace();
			System.exit(0);
		}
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
	protected boolean isStore(String store) {
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
			rs = sqlStatement.executeQuery("SELECT CASE WHEN EXISTS ( SELECT * FROM [Stores] WHERE [Store] = '"+store+"')THEN CAST(1 AS BIT)ELSE CAST(0 AS BIT) END");
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
	public int getStoreCount() {
		if (!isConnected()) {
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
			rs = sqlStatement.executeQuery("SELECT COUNT(*) AS result FROM Stores;");
		} catch (SQLException e) {
			System.err.println("Error executing query");
			e.printStackTrace();
			System.exit(0);
		}
		
		try {
			rs.next();
			return Integer.parseInt(rs.getString("result"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}


	public String[] getStoreList() {
		if (!isConnected()) {
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
			rs = sqlStatement.executeQuery("SELECT * FROM Stores;");
		} catch (SQLException e) {
			System.err.println("Error executing query");
			e.printStackTrace();
			System.exit(0);
		}
		
		try {
			int columns = getStoreCount();
			String[] list = new String[columns];
			for(int x=0; x<columns; x++) {
				rs.next();
				list[x] = rs.getString("Store");
			}
			return list;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

