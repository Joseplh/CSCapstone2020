package testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;

//import com.microsoft.sqlserver.jdbc.SQLServerException;
/**
 * Developed by Joseph Maxwell for consolidating the components into a single
 * main and then attempting to download, compile and upload the database.
 */
public class Main {
	// The SQL Server JDBC Driver is in
	// C:\Program Files\Microsoft JDBC Driver 6.0 for SQL
	// Server\sqljdbc_6.0\enu\auth\x64
	private static final String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	// The JDBC connection URL which allows for Windows authentication is defined
	// below.
	private static String jdbcURL = "jdbc:sqlserver://127.0.0.1:1433;" + "database=StateBills;" + "user=javaUser;"
			+ "password=;" + "encrypt=false;" + "trustServerCertificate=true;" + "loginTimeout=30;";
	// used in the sql insert to create and hold the connection to the database
	private static Connection databaseConnection = null;

	// "jdbc:sqlserver://localhost:1433;databasename=TestingDB;integratedSecurity=true;";
	// To make Windows authenticaion work we have to set the path to
	// sqljdbc_auth.dll at the command line
	public static void main(String[] args) {
		// Stage 0: Setup config file
		try {
			File file = new File("config.ini");
			BufferedReader br;
			br = new BufferedReader(new FileReader(file));
			if (br.readLine().matches("custom=true")) {
				System.out.println("Input is custom from config.ini");
				jdbcURL = br.readLine() // "jdbc:sqlserver://127.0.0.1:1433;"
						+ br.readLine() // "database=StateBills;"
						+ br.readLine() // "user=javaUser;"
						+ br.readLine() // "password=;"
						+ br.readLine() // "encrypt=false;"
						+ br.readLine() // "trustServerCertificate=true;"
						+ br.readLine();// "loginTimeout=30;"
				br.close();
			}
		} catch (Exception err) {

		}

		System.out.println("Program started");
		try {
			Class.forName(jdbcDriver);
			System.out.println("JDBC driver loaded");
		} catch (Exception err) {
			System.err.println("Error loading JDBC driver");
			err.printStackTrace(System.err);
			System.exit(0);
		}
		try {
			// Connect to the database
			databaseConnection = DriverManager.getConnection(jdbcURL);
			System.out.println("Connected to the database");
		} catch (SQLException err) {
			System.err.println("Error connecting to the database");
			err.printStackTrace(System.err);
			System.exit(0);
		}
		/*
		 * ------------------------------------------------------------- The connection
		 * is open and you can now run SQL statement here
		 * -------------------------------------------------------------
		 */
		try {
			// declare the statement object
			Statement sqlStatement = databaseConnection.createStatement();

			// Build the command string
			// String commandString="insert into people values";
			// commandString+="('Joseph','Maxwell','6467 Pierce St.',1000.57)";
			String commandString = "INSERT INTO \"Catalog\" (\"Description\", \"Issue\", \"Disct? Sub\", Form) "
					+ "VALUES ('ALIENS PREDATOR PROMETHEUS FIRE AND STONE TP (C: 0-1-2)', 1, 0, 0), "
					+ "('BLOODBORNE #13 CVR A STOKELY (MR)', 13, 0, 0), "
					+ "('AMULET SC VOL 01 STONEKEEPER NEW PTG', 1, 0, 0), "
					+ "('AQUAMAN #50 YOTV THE OFFER', 50, 0, 0), "
					+ "('DUCKTALES #4 CVR A GHIGLIONE (C: 1-0-0)', 4, 0, 0);";
			// print the command string to the screen
			System.out.println("\nCommand string:");
			System.out.println(commandString);

			// execute the command using the execute method
			sqlStatement.execute(commandString);

			System.out.println("122: Completed statement");

			// close the database connection
		} catch (SQLException err) {
			System.err.println("SQL Error");
			err.printStackTrace(System.err);
			System.exit(0);
		}

		/*
		 * ----------------------------------------- After this point the connection is
		 * closed -----------------------------------------
		 */
		try {
			databaseConnection.close();
		} catch (SQLException err) {
			System.err.println("SQL Error");
			err.printStackTrace(System.err);
			System.exit(0);
		}
		System.out.println("Program finished");
		System.out.println("Success!!!");

	}
}
