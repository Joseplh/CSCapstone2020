import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.microsoft.sqlserver.jdbc.SQLServerException;
//Developed by Joseph Maxwell for 
//consolidating the components into a single main and then attempting to download, compile and upload the database.
//
public class Main {
	//The SQL Server JDBC Driver is in 
	//C:\Program Files\Microsoft JDBC Driver 6.0 for SQL Server\sqljdbc_6.0\enu\auth\x64
	private static final String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	  
	//The JDBC connection URL which allows for Windows authentication is defined below.
	private static String jdbcURL = "jdbc:sqlserver://127.0.0.1:1433;"
            + "database=StateBills;"
            + "user=javaUser;"
            + "password=;"
            + "encrypt=false;"
            + "trustServerCertificate=true;"
            + "loginTimeout=30;";
	//used in the sql insert to create and hold the connection to the database
	private static Connection databaseConnection= null;
	private static String date;
	private static boolean use = false;
			
			
			
			//"jdbc:sqlserver://localhost:1433;databasename=TestingDB;integratedSecurity=true;";
	//To make Windows authenticaion work we have to set the path to sqljdbc_auth.dll at the command line
	public static void main(String[] args) {
		//Stage 0: Setup config file
		try {
			File file = new File("config.ini");
			BufferedReader br;		
			br = new BufferedReader(new FileReader(file));
			if(br.readLine().matches("custom=true")) {
				System.out.println("Input is custom from config.ini");
				jdbcURL = br.readLine() //"jdbc:sqlserver://127.0.0.1:1433;"
						+ br.readLine() //"database=StateBills;"
						+ br.readLine() //"user=javaUser;"
						+ br.readLine() //"password=;"
						+ br.readLine() //"encrypt=false;"
						+ br.readLine() //"trustServerCertificate=true;"
						+ br.readLine();//"loginTimeout=30;"
				if(((date) = br.readLine()) != null) {
					System.out.println("date:"+date);use = true;}
				br.close();
			}
		}catch(Exception err) {
			
		}
		//Stage 1: Download the daily .csv file
		Downloader();
		//System.out.print(currentDate());
		
		//Stage 2: push new data to database
		System.out.println("Program started");
	    try
	    {
	       Class.forName(jdbcDriver).newInstance();
	       System.out.println("JDBC driver loaded");
	    }
	    catch (Exception err)
	    {
	       System.err.println("Error loading JDBC driver");
	       err.printStackTrace(System.err);
	       System.exit(0);
	    }
	    try
	    {
	      //Connect to the database
	      databaseConnection = DriverManager.getConnection(jdbcURL);
	      System.out.println("Connected to the database");
	    }
	    catch (SQLException err)
	    {
	       System.err.println("Error connecting to the database");
	       err.printStackTrace(System.err);
	       System.exit(0);
	    }
	    //Start loop through csv file and insert into the sql database.
		CSVBuilder("download.csv");
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
	public static void Downloader() {
		try (BufferedInputStream in = new BufferedInputStream(new URL("https://nebraskalegislature.gov/bills/search_by_date.php?SessionDay="+currentDate(use)+"&print=csv").openStream());
			FileOutputStream fileOutputStream = new FileOutputStream("download.csv")) {
		    byte dataBuffer[] = new byte[1024];
		    int bytesRead;
		    System.out.println("Downloading...");
		    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
		        fileOutputStream.write(dataBuffer, 0, bytesRead);
		    }
		} catch (IOException e) {
		    // handle exception
		}
	}
	public static String currentDate(boolean use) {
		if(use == true)
			return date;
		Date dNow = new Date( );
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("yyyy-MM-dd");

	      System.out.println("Current Date: " + ft.format(dNow));
		return ft.format(dNow);
	}
	public static void insertSQL(String SQLStatement) {
	    try
	    {
	      //declare the statement object
	      Statement sqlStatement = databaseConnection.createStatement();
	 
	      //Build the command string
	      //String commandString="insert into people values";
	      //commandString+="('Joseph','Maxwell','6467 Pierce St.',1000.57)";
	      String commandString=SQLStatement;
	      //print the command string to the screen
	      System.out.println("\nCommand string:");
	      System.out.println(commandString);
	      
	      //execute the command using the execute method
	      sqlStatement.execute(commandString);
	      
	      System.out.println("122: Completed statement");

	      //close the database connection

	    }catch(SQLServerException err) {
	    	System.err.println("Duplicate SQL statement");
	    	err.printStackTrace();
	    }catch (SQLException err){
	       System.err.println("SQL Error");
	       err.printStackTrace(System.err);
	       System.exit(0);
	    }
	}
	public static void CSVBuilder(String CSVFile) {//this method takes in a .csv file and parses through the data within
		//It is specifically designed for the nebraska state legislature .csv file and currently only prints the contents.
		//load .csv file into memory
		try {
			//System.out.println("11:Opening file");
			//standard open file to be read.
			File file = new File(CSVFile);
			BufferedReader br;		
			br = new BufferedReader(new FileReader(file));
			String fileLine = br.readLine();//initialized with first line which is only the titles, useless data
			boolean ignoreComma = false;
			String doc = "";
			String intro = "";
			String stat = "";
			String desc = "";
			String IDst = "";//temporary holder for ID as it is a string
			int ID = -1;
			int column = 0;
			while(((fileLine) = br.readLine()) != null) {
				System.out.println(fileLine);
				for(int x = 0; x < fileLine.length(); x++) {
					switch(column) {
						case 0://Column for Document
							switch(fileLine.charAt(x)) {
								case '"'://used to determine if comma in string
									if(fileLine.charAt(x+1)=='"') {//csv file use "" to allow for " in text EX: "test" -> ""test""
										doc += '"';
										x++;//causes the fileLine to skip the second "
									}else if(ignoreComma)
										ignoreComma = false;
									else
										ignoreComma = true;
									break;
								case ',':
									if(ignoreComma)
										doc += ',';
									else
										column = 1;
										
									break;
								default:
									doc += fileLine.charAt(x);
									//System.out.println("48:adding char to doc");
							}
							break;
						case 1://Column for Primary Introducer
							switch(fileLine.charAt(x)) {
								case '"'://used to determine if comma in string
									if(ignoreComma)
										ignoreComma = false;
									else
										ignoreComma = true;
									break;
								case ',':
									if(ignoreComma)
										intro += ',';
									else
										column = 2;
									break;
								default:
									intro += fileLine.charAt(x);
									//System.out.println("67:adding char to intro");
							}
							break;
						case 2://Column for Status
							switch(fileLine.charAt(x)) {
								case '"'://used to determine if comma in string
									if(ignoreComma)
										ignoreComma = false;
									else
										ignoreComma = true;
									break;
								case ',':
									if(ignoreComma)
										stat += ',';
									else
										column = 3;
									break;
								default:
									stat += fileLine.charAt(x);
									//System.out.println("86:adding char to stat");
							}
							break;
						case 3://Column for Description
							switch(fileLine.charAt(x)) {
								case '"'://used to determine if comma in string
									if(fileLine.charAt(x+1)=='"') {//csv file use "" to allow for " in text EX: "test" -> ""test""
										desc += '"';
										x++;//causes the fileLine to skip the second "
									}else if(ignoreComma)
										ignoreComma = false;
									else
										ignoreComma = true;
									break;
								case ',':
									if(ignoreComma)
										desc += ',';
									else
										column = 4;
									break;
								case '\'':
									break;
								default:
									desc += fileLine.charAt(x);
									//System.out.println("108:adding char to desc");
							}
							break;
						case 4://Column for Document ID
								if(fileLine.charAt(x) != '"')
									IDst += fileLine.charAt(x);
								//System.out.println("110:adding char to IDst");
								if(fileLine.length()==x+1) {
									column = 0;
									//System.out.println("end of line found");
								}
							break;
						default:
							throw new Exception("Default case reached for column switch, THIS SHOULD NOT HAPPEN.");
					}
				}
				System.out.println("Past FOR loop");
				if(column==0) {//at the true end of the row and must reset all values
					ID = Integer.parseInt(IDst);
					//System.out.print("doc:"+doc);
					//System.out.print(" intro:"+intro);
					//System.out.print(" stat:"+stat);
					//System.out.print(" desc:"+desc);
					//System.out.print(" IDst:"+IDst);
					//System.out.print(" ID:"+ID+"\n");
					System.out.println("INSERT INTO CombinedTable VALUES ('"+doc+"', '"+intro+"', '"+stat+"', '"+desc+"', "+ID+", '"+currentDate(use)+"')");
					insertSQL("INSERT INTO CombinedTable VALUES ('"+doc+"', '"+intro+"', '"+stat+"', '"+desc+"', "+ID+", '"+currentDate(use)+"')");
					doc = "";
					intro = "";
					stat = "";
					desc = "";
					IDst = "";
					ID = -1;
					//System.out.println("134:reset temporary values");
				}
			}
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception err) {
			
		}
		//insertSQL("INSERT INTO CombinedTable VALUES ('"+doc+"', '"+intro+"', '"+stat+"', '"+desc+"', "+ID+", '"+currentDate()+"')");
	}
	

}
