/*This java program is designed to append .csv files from the nebraska state legislature to a compiled table.
 * https://nebraskalegislature.gov/bills/search_by_date.php?SessionDay=2020  This link provides a list for the annual
 * session.*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BillCSVappend {
	public BillCSVappend() throws IOException {
		//Create variables
		String fileYear;//manually input by the user, this is the year for the file
		String fileToAppend;//This is the full link to the file that is to be appended to the combined list
		//Create User input Scanner
		Scanner input = new Scanner(System.in);
		System.out.println("Hello, this program is designed to append a .csv file from the nebraska state legislature."
				+ "			\nThe appropriate input should be the full file link \"C:\\Users\\test\\file.csv\"."
				+ "			\nAs for the output, it will be placed in the \"C:\\combinedFile.csv\".\n\n");
		System.out.printf("Appended File Location:");
		fileToAppend = input.nextLine();
		System.out.printf("Appended File Year:");
		fileYear = input.nextLine();
		
		//Open file to append
		File file = new File(fileToAppend);
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		
		//Read "addBillList" into linkedList
		input.close();
		String fileLine = br.readLine();//creates fileLine and clears first row
		while(((fileLine)=br.readLine())!=null) {
			appendToFile(fileLine + "," +fileYear + "\n", "C:\\\\Users\\\\Joseph\\\\workspace\\\\BillTrackerDB\\\\src\\\\CombinedTable.csv");
		}
		System.out.print("Appended");
		br.close();
	}
	public static void appendToFile(String textToAppend, String fileAppendedTo) throws IOException{
		BufferedWriter writer = new BufferedWriter(
				new FileWriter(fileAppendedTo, true)
				);
		writer.write(textToAppend);
		writer.close();
	}
}
