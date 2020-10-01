/*This program is a prof of concept for pulling a csv file into memory and parsing through it.  
 * As the data is parsed through it is then loaded into a linked list which is then sorted afterwards based on the unique ID.
 * Duplicate IDs are the same document under a different legislative body name and should be added as a separate status within the list.
 * This status within a linked list is another simpler linked list that holds the status and date of change.*/
import java.io.*;

public class CSVBuilder {
	public CSVBuilder() {
		//load .csv file into memory
		try {
			File file = new File("downloadFile.csv");
		
			BufferedReader br;
		
			br = new BufferedReader(new FileReader(file));
		
			//read .csv file and parse into objBill list
			String fileLine = br.readLine();//initialized with first line which is only the titles, usless data
			while(((fileLine) = br.readLine()) != null){//looping through file line by line as a single string
				boolean ignoreComma = false;
				String doc = "";
				String intro = "";
				String stat = "";
				String desc = "";
				String IDst = "";//temporary holder for ID as it is a string
				int ID = -1;
				System.out.println(fileLine);
				int stage = 0;
				for(int x = 0; x < fileLine.length(); x++) {
					switch(stage) {
						case 0://parse for doc string
							switch(fileLine.charAt(x)) {
								case '"'://used to determine if comma in string
									if(ignoreComma)
										ignoreComma = false;
									else
										ignoreComma = true;
									break;
								case ',':
									if(ignoreComma)
										doc += ',';
									else
										stage = 1;
									break;
								default:
									doc += fileLine.charAt(x);									
							}
							break;
						case 1://parse for intro string
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
									stage = 2;
								break;
							default:
								intro += fileLine.charAt(x);									
						}
							break;
						case 2://parse for stat string
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
										stage = 3;
									break;
								default:
									stat += fileLine.charAt(x);									
							}
							break;
						case 3://parse for desc string
							switch(fileLine.charAt(x)) {
								case '"'://used to determine if comma in string
									if(ignoreComma)
										ignoreComma = false;
									else
										ignoreComma = true;
									break;
								case ',':
									if(ignoreComma)
										desc += ',';
									else
										stage = 4;
									break;
								default:
									desc += fileLine.charAt(x);									
							}
							break;
						case 4://parse for IDst as a string
							switch(fileLine.charAt(x)) {
								case '"'://used to determine if comma in string
									if(ignoreComma)
										ignoreComma = false;
									else
										ignoreComma = true;
									break;
								case ',':
									if(ignoreComma)
										IDst += ',';
									else
										stage = 5;
									break;
								default:
									IDst += fileLine.charAt(x);									
							}
							break;
						default:
							System.out.println("Should not Happen 0x001");
					}
				}
				ID = Integer.parseInt(IDst);
				System.out.println(doc);
				System.out.println(intro);
				System.out.println(stat);
				System.out.println(desc);
				System.out.println(IDst);
				System.out.println(ID);
				
				/*This section takes the parsed information and adds it into the linked list database.*/
				
			}
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
