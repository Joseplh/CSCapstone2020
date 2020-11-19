
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;

public class ReadFile {

	public static void main(String[] args) {
		String strPathName = "C:\\CSCI_4970\\";
		String strFileName = "BLONDO_20190808.csv";
		String strFilepathName = strPathName + strFileName;
		ProcessFile(strFilepathName);

	}

	public static void ProcessFile (String strFilepathName)
	{
		//System.out.println("Will process file " + strFilepathName);
		try {
			FileReader frFile = new FileReader(strFilepathName);
			BufferedReader brBuffer = new BufferedReader(frFile);
			String strLine = "";

			while ((strLine = brBuffer.readLine()) != null) {
				//System.out.println(strLine);
				ProcessLine(strLine);
			} //while

			brBuffer.close();
		} //try
		catch (IOException e) {
			//System.out.println("A read error has occurred");
			System.err.println("Error reading from input file");
			e.printStackTrace(System.err);
			System.exit(0);

		} // catch (IOException e)
	
	} // ProcessFile

	public static void ProcessLine (String strLine)
	{
		//System.out.println("Will process line: <" + strLine + ">");
		String pattern = "(.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*)";
		
		//Create a Pattern object
		Pattern r = Pattern.compile(pattern);
		
		//Create Matcher object
		Matcher m = r.matcher(strLine);
		if (m.find()) {
			//Fields
			//1 - Quantity
			//2 - Date Code
			//3 - Description
			//4 - Retail
			//5 - Wholesale
			//6 - Business Cost (Quantity x Wholesale)
			//7 - ?? category??
			//8 - ?? category??
			//9 - Distributor
			//System.out.println("0) " + m.group(0));
			//System.out.println("1) " + m.group(1));
			String mg9 = m.group(9).replaceAll("^\"|\"$", "");
			System.out.printf("%4s%7s%8s%7s%5s%4s  [%30s]", m.group(1),m.group(4),m.group(5),
					m.group(6),m.group(7),m.group(8),mg9);
			String mg2 = m.group(2).replaceAll("^\"|\"$", "");
			mg2 = mg2.trim();
			String mg3 = m.group(3).replaceAll("^\"|\"$", "");
			System.out.printf("[%s] [%s]\n", mg2, mg3);
			//System.out.println("2) " + m.group(2));
			//System.out.println("3) " + m.group(3));
			//System.out.println("4) " + m.group(4));
			//System.out.println("5) " + m.group(5));
			//System.out.println("6) " + m.group(6));
			//System.out.println("7) " + m.group(7));
			//System.out.println("8) " + m.group(8));
			//System.out.println("9) " + m.group(9));

		}
		else {
			//System.out.println("No match");
		}
		
	} // ProcessLine

}
