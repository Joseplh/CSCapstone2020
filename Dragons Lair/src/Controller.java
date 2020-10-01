
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
			File file = new File("config.ini");
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
	
	public String[][] select(String query) {
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
			rs = sqlStatement.executeQuery(query);
		} catch (SQLException e) {
			System.err.println("Error executing query");
			e.printStackTrace();
			System.exit(0);
		}
		
		return format(rs);
	}
	
	/* TODO: Need a method for taking in an insert, update, delete query
	 * 		 Maybe return boolean/int depending on if it was successful or not
	 * 		 executeUpdate(query)
	 * 
	 */
	
	
	/* TODO: Need a method that takes in a ResultSet rs and 
	 *       returns a 2d String array. In order to display 
	 *       information in a JTable
	 * 
	 */
	public String[][] format(ResultSet rs){
		String[][] data = null;
		int columns = 0;
		
		
		
		
		
		
		try {
			columns = rs.getMetaData().getColumnCount();
		} catch (SQLException e1) {
			System.err.println("Error getting metadata on ResultSet");
			e1.printStackTrace();
			System.exit(0);
		}
		
		try {
			while(rs.next()){
				for(int i = 1; i < columns; i++) {
					System.out.print(rs.getString(i) + " ");
					
				}
				System.out.println("");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
}

