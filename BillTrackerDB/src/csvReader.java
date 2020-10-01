import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class csvReader {
	public static String fileName = "inputFileTEST.csv";
	public static void main(String[] args) {
		try {
			//System.out.println("11:Opening file");
			//standard open file to be read.
			File file = new File(fileName);
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
								default:
									desc += fileLine.charAt(x);
									//System.out.println("108:adding char to desc");
							}
							break;
						case 4://Column for Document ID
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
				//System.out.println("Past FOR loop");
				if(column==0) {//at the true end of the row and must reset all values
					ID = Integer.parseInt(IDst);
					System.out.print("doc:"+doc);
					System.out.print(" intro:"+intro);
					System.out.print(" stat:"+stat);
					System.out.print(" desc:"+desc);
					System.out.print(" IDst:"+IDst);
					System.out.print(" ID:"+ID+"\n");
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
	}

}
