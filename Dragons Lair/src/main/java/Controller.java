package main.java;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	 * @param storeCode    The unique store code.
	 * @param customerCode The unique customer code.
	 * @param title        The request title.
	 * @param comments     Any additional comments.
	 * @param issueStart   The first requested issue number (-1 means no specified start issue).
	 * @param issueEnd     The last requested issue number (-1 means ongoing indefinitely).
	 * @param quantity     The request quantity.
	 * @param cost         The cost of the requested title.
	 */
	public void addRequest(String storeCode, String customerCode, String title, String comments, int issueStart, int issueEnd, int quantity, float cost) {
		// TODO: Add custom store code number
		insert(String.format("INSERT INTO [DLC].[dbo].[Order]([Store Code], [Customer Code], Title, Comments, "
				+ "[Issue Start], [Issue End], Quantity, Cost) VALUES('%s', '%s', '%s', '%s', %d, %d, %d, %f)", storeCode, customerCode, title, comments, issueStart, issueEnd, quantity, cost));
	}
	//altered the insert statements to directly return as opposed to a useless one: Joseph
	public int insertCustomer(String first, String last, String email, String phone) {
		return insert("INSERT INTO [DLC].[dbo].[Customer]([Last Name], [First Name], [Email], [Phone #1]) VALUES('" + last + "', '" + first + "', '" + email + "', '" + phone + "')");
	}

	public int deleteCustomer(int ccode) {
		return insert("DELETE FROM Customer WHERE [Customer Code] = " + ccode);
	}

	public int updateCustomer(int ccode, String first, String last, String email, String phone) {
		return insert("UPDATE [DLC].[dbo].[Customer] Set [Last Name] = '" + last + "', [First Name] = '" + first + "', [Email] = '" + email + "', [Phone #1] = '" + phone + "' WHERE [Customer Code] = " + ccode);
	}

	/**
	 * @param storeCode
	 * @param lName
	 * @param fName
	 * @param address
	 * @param city
	 * @param state
	 * @param zip
	 * @param phone1
	 * @param phone2
	 * @param email
	 * @param ccode
	 * @return
	 */
	public int updateCustomerFull(String storeCode, String lName, String fName, String address, String city, String state, String zip, String phone1, String phone2, String email, int ccode) {
		return insert("UPDATE [DLC].[dbo].[Customer] Set [Store Code] = '" + storeCode + "', [Last Name] = '" + lName + "', [First Name] = '" + fName + "', [Address-1] = '" + address
				+ "', [City/State] = '" + city + "', [ZIP] = '" + zip + "', [Phone #1] = '" + phone1 + "', [Phone #2] = '" + phone2 + "', [Email] = '" + email + "' WHERE [Customer Code] = " + ccode);
	}

	/**
	 * Returns the following columns from the customer table.
	 *
	 * @param title Title for the new title
	 * @param distr Distributor for new title
	 * @param sub   Distc Sub for new title
	 * @param tCode Key for the title in the catalog table
	 * @return {int} 	0 or row count, negative if error.
	 */
	public int insertTitle(String title, String distr, String sub, String tCode) {
		return insert("INSERT INTO Catalog([Description], [Distributor], [Disct? Sub], [Calalog ID]) VALUES('" + title + "', '" + distr + "', '" + sub + "', '" + tCode + "')");
	}

	/**
	 * Deletes a title from the Catalog table
	 *
	 * @param tCode    Key for the title in the catalog table
	 * @return {int} 	0 or row count, negative if error.
	 */
	public int deleteTitle(String tCode) {
		return insert("DELETE FROM Catalog WHERE [Calalog ID] = '" + tCode + "'");
	}

	/**
	 * Returns the following columns from the customer table.
	 *
	 * @param title    New title for a given title in the Catalog table
	 * @param sub        New Distinct Sub value
	 * @param tCode    Key for the title in the catalog table
	 * @return {int} 	0 or row count, negative if error.
	 */
	public int updateTitle(String title, String sub, String tCode) {
		return insert("UPDATE Catalog Set [Description] = '" + title + "', [Disct? Sub] = '" + sub + "' WHERE [Calalog ID] = '" + tCode + "'");
	}


	/**
	 * Determines whether the database is connected of not
	 *
	 * @return {boolean} True if connected, false if not
	 */
	public boolean isConnected() {
		return (dbConnection != null);
	}


	/**
	 * Returns the following columns from the customer table.
	 *
	 * @return {String[][]} 	Contains the given query ResultSet.
	 */
	public String[][] getCustomers() {
		return select("SELECT [Last Name], [First Name], [Phone #1], [Email], [Customer Code] FROM [DLC].[dbo].[Customer]");
	}

	public String[][] getCustomerData(int customerCode) {
		return select(String.format("SELECT [Store Code], [Last Name], [First Name], [Address-1], [City/State], [ZIP], [Phone #1], [Phone #2], [Email] FROM [DLC].[dbo].[Customer] WHERE [Customer Code] = %d", customerCode));
	}

	public String[][] getReports() {
		return new String[0][0];
	}

	/**
	 * Handler for fetching the requests for a given customer.
	 *
	 * @param customerCode    The customer code.
	 * @return {String[][]} 	Contains the given query ResultSet.
	 */
	public String[][] getRequests(String customerCode) {
		return select(String.format("Select [Store Code], Title, [Issue Start], [Issue End],"
				+ " Quantity, Cost, Comments FROM [newDLC].[dbo].[Order] WHERE [Customer Code]=%s", customerCode));
	}

	public String[][] getIndividualTitles() {
		return select(String.format("SELECT DISTINCT [Title] FROM [newDLC].[dbo].[Catalog]"));
	}

	/**
	 * Calls method to query the Titles from the database. Returns String[][].
	 *
	 * @return {String} 2D array of the titles from database.
	 */
	public String[][] getTitles() {
		return select("SELECT [Description], [Disct? Sub], [Distributor], [Calalog ID] FROM Catalog");
	}


	/**
	 * Executes a given query and returns the resultset as a String[][].
	 *
	 * @param query				Query to be executed.
	 * @return {String[][]} 	2D array of the given query.
	 */
	public String[][] select(String query) {
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
			rs = sqlStatement.executeQuery(query);
		} catch (SQLException e) {
			System.err.println("Error executing query");
			e.printStackTrace();
			System.exit(0);
		}
		
		return format(rs);
	}

	/**
	 * Method to insert, update, delete info. Returns 0 for statements that return nothing or the row count.
	 * 
	 * @param query		Query to execute.
	 * @return {int} 	0 or row count, negative if error.
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
	/**
	 * Converts a given ResultSet to String[][]
	 *
	 * @param rs	ResultSet to be converted
	 * @return 		{String[][]} Given ResultSet converted
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

	/**
	 * Exports given data to an .xlsx file, written to a specified destination 'path'
	 *
	 * @param in     	String[][] dataset to export to excel, null if using 'query'
	 * @param query 	Query to execute, null if using 'in'
	 * @param path      Directory path where file is saved
	 * @param name		Name of file
	 * @param columns	Column names.
	 * @return {boolean} True if export was succesful, false otherwise
	 */
	public boolean exportXLSX(String[][] in, String query, String path, String name, String[] columns) {
		String result[][] = null;
		boolean confirmed = false;
		if(in != null && query == null) {
			result = in;
		}
		else {
			result = select(query);
		}

		int rows = result.length;
		int rowCount = 1;
		int columnCount = 0;
		int i = 0;
		int z = 0;


		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(name);

		//Header font
		Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Header data
        Row headerRow = sheet.createRow(0);
        Cell headerCell;
        for(i=0; i<columns.length; i++) {
        	headerCell = headerRow.createCell(i);
        	headerCell.setCellValue(columns[i]);
        }

        i = 0;
        while(i < rows) {

        	Row row = sheet.createRow(rowCount++);
        	columnCount = 0;
        	for(z=0; z<columns.length; z++) {
        		Cell cell = row.createCell(columnCount++);
            	cell.setCellValue(result[i][z]);

        	}
        	i++;
        }

        try {
			FileOutputStream outputStream = new FileOutputStream(path);
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
			confirmed = true;
		} catch (IOException e) {
			System.out.println("Datababse error:");
			confirmed = false;
			e.printStackTrace();
		}

		return confirmed;
	}

	/**
	 * Opens up a file chooser for a user to select save destination. Returns path
	 *
	 * @param panel		Parent component of the dialog
	 * @param name		String to name saved file
	 * @return {String} Full path to save location
	 */
	public String saveFile(JPanel panel, String name){
		String path = null;
		String choosertitle = "Select Save Location";
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle(choosertitle);
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
	    if (chooser.showSaveDialog(panel) == JFileChooser.APPROVE_OPTION) {
	    	path = chooser.getSelectedFile().getAbsolutePath();
	    	path += "\\" + name + "_" + getDate() + ".xlsx";

	    }
	    else {
	    	System.out.println("No Selection ");
	     }
	    System.out.println(path);
		return path;
	}

	/**
	 * Returns todays date(string) in the format "MM-DD-YYYY"
	 *
	 * @return {String} Todays date "MM-DD-YYYY"
	 */
	public String getDate() {
		String todaysDate = null;

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();
        todaysDate = dateFormat.format(date);

		return todaysDate;

	}
}

