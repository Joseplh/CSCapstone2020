package catalogParser;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class reader {
	static String title;
	static int issue;
	static boolean unique;
	static String description;
	Controller control = new Controller();
	public void read() throws IOException {
		BufferedReader input = null;
		try {
			String fileName = "MILLARD_20190822.csv";
			 input = new BufferedReader(new FileReader(fileName));
		}catch(FileNotFoundException err) {
			err.printStackTrace();
		}
		
		String cola = "";//quantity ordered
		String colb = "";//catalog id
		String colc = "";//description to be broken down
		String cold = "";//price
		String cole = "";//misc?
		String colf = "";//misc?
		String colg = "";//misc?
		String colh = "";//misc?
		String coli = "";//publisher
		String line;
		boolean quote = false;
		input.readLine();//eating the first 4 lines
		input.readLine();
		input.readLine();
		input.readLine();
		//int counter = 5;
		int pos;
		
		while((line = input.readLine()) != null) {
			cola = "";//quantity ordered
			colb = "";//catalog id
			colc = "";//description to be broken down
			cold = "";//price
			cole = "";//misc?
			colf = "";//misc?
			colg = "";//misc?
			colh = "";//misc?
			coli = "";//publisher
			pos = 0;
			//System.out.print(counter++);
			for(int x=0;x<line.length();x++) {
				//System.out.print(line.charAt(x));
				switch(pos) {
					case 0:
						if(line.charAt(x)==',' && !quote) {
							pos++;
							break;
						}else if(line.charAt(x)=='"')
							if(quote==true)
								quote = false;
							else if(quote==false)
								quote = true;
						if(line.charAt(x)!='"')
							cola = cola + line.charAt(x);
					break;
					case 1:
						if(line.charAt(x)==',' && !quote) {
							pos++;
							break;
						}else if(line.charAt(x)=='"')
							if(quote==true)
								quote = false;
							else if(quote==false)
								quote = true;
						if(line.charAt(x)!='"' && line.charAt(x)!=' ')
							colb = colb + line.charAt(x);
					break;
					case 2:
						if(line.charAt(x)==',' && !quote) {
							pos++;
							break;
						}else if(line.charAt(x)=='"')
							if(quote==true)
								quote = false;
							else if(quote==false)
								quote = true;
						if(line.charAt(x)!='"' && line.charAt(x)!=' ')
							colc = colc + line.charAt(x);
						if(line.charAt(x)==' ' && quote)
							colc = colc + " ";
					break;
					case 3:
						if(line.charAt(x)==',' && !quote) {
							pos++;
							break;
						}else if(line.charAt(x)=='"')
							if(quote==true)
								quote = false;
							else if(quote==false)
								quote = true;
						if(line.charAt(x)!='"')
							cold = cold + line.charAt(x);
					break;
					case 4:
						if(line.charAt(x)==',' && !quote) {
							pos++;
							break;
						}else if(line.charAt(x)=='"')
							if(quote==true)
								quote = false;
							else if(quote==false)
								quote = true;
						if(line.charAt(x)!='"')
							cole = cole + line.charAt(x);
					break;
					case 5:
						if(line.charAt(x)==',' && !quote) {
							pos++;
							break;
						}else if(line.charAt(x)=='"')
							if(quote==true)
								quote = false;
							else if(quote==false)
								quote = true;
						if(line.charAt(x)!='"')
							colf = colf + line.charAt(x);
					break;
					case 6:
						if(line.charAt(x)==',' && !quote) {
							pos++;
							break;
						}else if(line.charAt(x)=='"')
							if(quote==true)
								quote = false;
							else if(quote==false)
								quote = true;
						if(line.charAt(x)!='"')
							colg = colg + line.charAt(x);
					break;
					case 7:
						if(line.charAt(x)==',' && !quote) {
							pos++;
							break;
						}else if(line.charAt(x)=='"')
							if(quote==true)
								quote = false;
							else if(quote==false)
								quote = true;
						if(line.charAt(x)!='"')
							colh = colh + line.charAt(x);
					break;
					case 8:
						if(line.charAt(x)==',' && !quote) {
							pos++;
							break;
						}else if(line.charAt(x)=='"')
							if(quote==true)
								quote = false;
							else if(quote==false)
								quote = true;
						if(line.charAt(x)!='"' && line.charAt(x)!=' ')
							coli = coli + line.charAt(x);
						if(line.charAt(x)==' ' && quote)
							coli = coli + " ";
					break;					
				}
			}
			
			System.out.print(/*cola+","+colb+","+*/colc/*+","+cold+","+cole+","+colf+","+colg+","+colh+","+coli*/+"\n");
			title = "";//clean data input and set to defaults
			issue = -1;
			unique = false;
			description = colc;
			title = parseTitle(colc);
			System.out.print(title + " | Issue:"+ issue + "\n");
			//if(counter++ == 300)
				//break;
			if(colc.contains(" VAR"))
				unique = true;
			if(unique)
				control.insert("INSERT INTO Catalog ([Catalog ID], Distributor, Description, Issue, [Unique Print], Flag, Title) "+
							"VALUES('"+colb+"', '"+coli+"', '"+description+"', '"+issue+"', "+"1, 0, '"+ title + "');");
			else
				control.insert("INSERT INTO Catalog ([Catalog ID], Distributor, Description, Issue, [Unique Print], Flag, Title) "+
							"VALUES('"+colb+"', '"+coli+"', '"+description+"', '"+issue+"', "+"0, 0, '"+ title + "');");
		}
	}
	private static String parseTitle(String colc) {
		if(colc.contains("#"))
			return parseTitleComic(colc);
		else if(colc.contains(" VOL "))
			return parseTitleNovel(colc);
		else 
			return parseTitleMerch(colc);
	}
	private static String parseTitleMerch(String colc) {//merch is the default where it is a unique item
		issue = -1;
		unique = true;
		return colc;
	}
	private static String parseTitleNovel(String colc) {
		// TODO Auto-generated method stub
		title = "";
		String issueStr = "";
		int titleEnd = 0;
		for(int x=0;x<colc.length();x++) {
			if(colc.charAt(x)==' ' && colc.charAt(x+1)=='V' && colc.charAt(x+2)=='O' && colc.charAt(x+3)=='L' && colc.charAt(x+4)==' ') {
				issueStr += colc.charAt(x+5);
				issueStr += colc.charAt(x+6);
				titleEnd = x;
				break;
			}
		}
		for(int x=0; x<titleEnd; x++) {
			title = title + colc.charAt(x);
		}
		unique = false;
		issue = Integer.parseInt(issueStr);
		return title;
	}
	private static String parseTitleComic(String colc) {
		// TODO Auto-generated method stub
		int x = 0;
		String issueStr = "";
		title = "";
		while(x != colc.length()) {
			title = title+colc.charAt(x);
			if(colc.charAt(x+2)=='#')
				break;
			x++;
		}
		x += 2;
		while(x++ < colc.length()) {
			if(x==colc.length())
				break;
			if(colc.charAt(x)==' ')
				break;
			issueStr = issueStr+colc.charAt(x);
		}
		issue = Integer.parseInt(issueStr);
		unique = false;
		return title;
	}
}
