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
import java.time.LocalDate;

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
		insert(String.format("INSERT INTO [newDLC].[dbo].[Order]([Store Code], [Customer Code], Title, Comments, "
				+ "[Issue Start], [Issue End], Quantity, Cost) VALUES('%s', '%s', '%s', '%s', %d, %d, %d, %f)", storeCode, customerCode, title, comments, issueStart, issueEnd, quantity, cost));
	}
	//altered the insert statements to directly return as opposed to a useless one: Joseph
	public int insertCustomer(String first, String last, String email, String phone) {
		return insert("INSERT INTO [newDLC].[dbo].[Customer]([Store Code], [Last Name], [First Name], [Email], [Phone #1]) VALUES('dl1', '" + last + "', '" + first + "', '" + email + "', '" + phone + "')");
	}

	public int deleteCustomer(int ccode) {
		return insert("DELETE FROM Customer WHERE [Customer Code] = " + ccode);
	}
	
	/**
	 * Handler for deleting an individual order.
	 * 
	 * @param id The id of the order to delete.
	 */
	public void deleteOrder(int id) {
		insert(String.format("DELETE FROM [newDLC].[dbo].[Order] WHERE ID=%d", id));
	}
	
	
	public String[][] getDistinctTitles() {
		return select("SELECT DISTINCT [Title] FROM [newDLC].[dbo].[Order]");
	}

	public int updateCustomer(int ccode, String first, String last, String email, String phone) {
		return insert("UPDATE [newDLC].[dbo].[Customer] Set [Last Name] = '" + last + "', [First Name] = '" + first + "', [Email] = '" + email + "', [Phone #1] = '" + phone + "' WHERE [Customer Code] = " + ccode);
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
		return insert("UPDATE [newDLC].[dbo].[Customer] Set [Store Code] = '" + storeCode + "', [Last Name] = '" + lName + "', [First Name] = '" + fName + "', [Address-1] = '" + address
				+ "', [City] = '" + city + "', [State] = '" + state + "', [ZIP] = '" + zip + "', [Phone #1] = '" + phone1 + "', [Phone #2] = '" + phone2 + "', [Email] = '" + email + "' WHERE [Customer Code] = " + ccode);
	}

	/**
	 * Returns the following columns from the customer table.
	 *
	 * @param title   Title for the new title
	 * @param distr   Distributor for new title
	 * @param release Release date of new title
	 * @param tCode   Key for the title in the catalog table
	 * @return {int} 	0 or row count, negative if error.
	 */
	public int insertTitle(String title, String distr, String release, String tCode) {
		return insert("INSERT INTO Catalog([Title], [Series], [Distributor], [Release], [Catalog ID]) VALUES('" + title + "', '" + title + "', '" + distr + "', '" + release + "', '" + tCode + "')");
	}

	/**
	 * Deletes a title from the Catalog table
	 *
	 * @param tCode    Key for the title in the catalog table
	 * @return {int} 	0 or row count, negative if error.
	 */
	public int deleteTitle(String tCode) {
		return insert("DELETE FROM Catalog WHERE [Catalog ID] = '" + tCode + "'");
	}

	/**
	 * Returns the following columns from the customer table.
	 *
	 * @param flag     New value for new release column in table for given title
	 * @param release  Updated release date for title
	 * @param title    New title for a given title in the Catalog table
	 * @param unique   New unique print value
	 * @param tCode    Key for the title in the catalog table
	 * @return {int}   0 or row count, negative if error.
	 */
	public int updateTitle(boolean flag, String title, String release, boolean unique, String tCode) {
		return insert("UPDATE Catalog Set [Flag] = '" + flag + "', [Title] = '" + title + "', [Release] = CAST('" + release + "' AS DATE), [Unique Print] = '" + unique + "' WHERE [Catalog ID] = '" + tCode + "'");
	}
	
	
	/**
	 * Handler for retrieving a single customer order.
	 * 
	 * @param id The order id to retrieve.
	 * @return The order by id.
	 */
	public String[] getOrder(int id) {
		return select(String.format("Select * FROM [newDLC].[dbo].[Order] WHERE ID=%d", id))[0];
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
		return select("SELECT [Last Name], [First Name], [Phone #1], [Email], [Customer Code] FROM [newDLC].[dbo].[Customer]");
	}
	
	/**
	 * Handler for updating an individual order in the database.
	 *
	 * @param storeCode    The unique store code.
	 * @param customerCode The unique customer code.
	 * @param title        The request title.
	 * @param comments     Any additional comments.
	 * @param issueStart   The first requested issue number (-1 means no specified start issue).
	 * @param issueEnd     The last requested issue number (-1 means ongoing indefinitely).
	 * @param quantity     The request quantity.
	 * @param cost         The cost of the requested title.
	 * @param id		   The order id.
	 */
	public void updateOrder(String storeCode, String customerCode, String title, String comments, int issueStart, int issueEnd, int quantity, float cost, int id) {
		insert(String.format("UPDATE [newDLC].[dbo].[Order] SET [Store Code]='%s', [Customer Code]='%s', Title='%s', Comments='%s', "
				+ "[Issue Start]=%d, [Issue End]=%d, Quantity=%d, Cost=%f WHERE ID=%d", storeCode, customerCode, title, comments, issueStart, issueEnd, quantity, cost, id));
	}
	
	public String[][] getCustomerData(int customerCode) {
		return select(String.format("SELECT [Store Code], [Last Name], [First Name], [Address-1], [City], [State], [ZIP], [Phone #1], [Phone #2], [Email] FROM [newDLC].[dbo].[Customer] WHERE [Customer Code] = %d", customerCode));
	}

	public String[][] getReports() {
		return new String[0][0];
	}
	
	public String[][] getOrdersByTitle(String title) {
		return select(String.format("SELECT [Last Name], [First Name], [Quantity] FROM [newDLC].[dbo].[Order] AS [order] INNER JOIN (SELECT [Last Name], [First Name], [Customer Code] FROM [newDLC].[dbo].[Customer]) AS [customer] ON [order].[Customer Code] = [customer].[Customer Code] WHERE [order].[Title] = '%s'", title));
	}

	/**
	 * Handler for fetching the requests for a given customer.
	 *
	 * @param customerCode    The customer code.
	 * @return {String[][]} 	Contains the given query ResultSet.
	 */
	public String[][] getRequests(String customerCode) {
		return select(String.format("Select [Store Code], Title, [Issue Start], [Issue End], ID"
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
	public String[][] getTimeSensitiveTitles() {
		LocalDate today = LocalDate.now();
		/* TODO: improved dating for month, pull issue and title columns */
		return select("SELECT [Flag], [Release], [Title], [Distributor], [Catalog ID], [Unique Print] FROM Catalog WHERE MONTH(Release) = " + today.getMonthValue() + "OR MONTH(Release) = " + (today.getMonthValue() - 1) + "OR MONTH(Release) = " + (today.getMonthValue() + 1) + "AND YEAR(Release) = " + today.getYear());
	}

	/**
	 * Calls method to query the Titles from the database. Returns String[][].
	 *
	 * @return {String} 2D array of the titles from database.
	 */
	public String[][] getAllTitles() {
		/* TODO: pull issue and title columns */
		return select("SELECT [Flag], [Release], [Title], [Distributor], [Catalog ID], [Unique Print] FROM Catalog");
	}

	public void resetFlags() {
		insert("UPDATE Catalog Set [Flag] = '" + false + "'");
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


		@SuppressWarnings("resource")//the workbook IS closed near end of method
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

