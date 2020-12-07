package testing;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Connector {
	private final String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String jdbcURL = "jdbc:sqlserver://127.0.0.1:1433;" 
			+ "database=StateBills;" 
			+ "user=javaUser;"
			+ "password=;" 
			+ "encrypt=false;" 
			+ "trustServerCertificate=true;" 
			+ "loginTimeout=30;";
	private Connection dbconn = null;
	public Connector() {
		if(!isConnected())
			connect();
	}
	private void connect() {
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
			}else
				System.out.println("Input is NOT custom from config.ini");
		} catch (FileNotFoundException err) {
			Component frame=null;
			//file not found exception here would mean that the config.ini file does not exist where it should be
			//by default the file should be in the root folder with the program
			JOptionPane.showMessageDialog(frame, 	"The config.ini either does not exist\n"
											+	"or is in the wrong place.  Please put\n"
											+	"the config.ini file in the root with the program.");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		try {
			// Connect to the database
			dbconn = DriverManager.getConnection(jdbcURL);
			System.out.println("Connected to the database");
		} catch (SQLException err) {
			System.err.println("Error connecting to the database");
			err.printStackTrace(System.err);
			System.exit(0);
		}
	}
	private boolean isConnected() {
		return !(dbconn == null);
	}
	protected void disconnect() {
		try {
			dbconn.close();
		} catch (SQLException err) {
			System.err.println("SQL Error");
			err.printStackTrace(System.err);
		}
		System.out.println("Disconnected");
	}	

}
