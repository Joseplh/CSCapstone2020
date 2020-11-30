package main.java;

import java.io.FileNotFoundException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class reader {
	private JFrame homeFrame;
	private JTextField fileCSVloc;
	private JTextField userPass;
	static String title;
	static int issue;
	static boolean unique;
	static String description;
	Controller control = new Controller();
	BufferedReader input = null;
	public int read(String fileName) throws IOException {
		try {
			 input = new BufferedReader(new FileReader(fileName));
		}catch(FileNotFoundException err) {
			err.printStackTrace();
			return 0;
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
				control.insert("INSERT INTO Catalog ([Catalog ID], Distributor, Title, Issue, [Unique Print], Flag, Series) "+
							"VALUES('"+colb+"', '"+coli+"', '"+description+"', '"+issue+"', "+"1, 0, '"+ title + "');");
			else
				control.insert("INSERT INTO Catalog ([Catalog ID], Distributor, Title, Issue, [Unique Print], Flag, Series) "+
							"VALUES('"+colb+"', '"+coli+"', '"+description+"', '"+issue+"', "+"0, 0, '"+ title + "');");
		}
		return 1;
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
	public reader() {
		initializeHome();
	}
	public Frame getHomeFrame() {
		return homeFrame;
	}
	private void initializeHome() {
		//defining the homepage and the frame it is contained by
				//added by Joseph Maxwell
				homeFrame = new JFrame("Tool Page");
				homeFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
				homeFrame.setBackground(Color.WHITE);
				homeFrame.setResizable(false);
				homeFrame.setTitle("Tools");
				homeFrame.setSize(367, 142);
				homeFrame.setLocationRelativeTo(null);
				homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				homeFrame.getContentPane().setLayout(null);
				homeFrame.setVisible(true);
				
				JLabel CSVLabel = new JLabel("Import .CSV");
				CSVLabel.setBounds(10,11,162,33);
				CSVLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
				homeFrame.getContentPane().add(CSVLabel);
				
				fileCSVloc = new JTextField();
				fileCSVloc.setText("[\"c:/location/address.csv\" OR \"rootFolder.csv\"]");
				fileCSVloc.setEditable(true);
				fileCSVloc.setFont(new Font("Tahoma", Font.PLAIN, 14));
				fileCSVloc.setBounds(10, 55, 334, 33);
				fileCSVloc.setColumns(10);
				homeFrame.getContentPane().add(fileCSVloc);
				
				JButton importCSV = new JButton("Import .CSV");
				importCSV.setForeground(new Color(0, 0, 0));
				importCSV.setBackground(Color.GRAY);
				importCSV.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
				importCSV.setBounds(145, 11, 162, 33);
				homeFrame.getContentPane().add(importCSV);

				userPass = new JTextField();
				userPass.setEditable(true);
				userPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
				userPass.setBounds(200, 120, 162, 33);
				userPass.setColumns(10);
				
				importCSV.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//import csv
						Component frame = null;
						try {
							if(read(fileCSVloc.getText())==1)
								JOptionPane.showMessageDialog(frame, "Import has reported success.");
							if(read(fileCSVloc.getText())==0)
								JOptionPane.showMessageDialog(frame, "Import failed: file not found.");
						} catch (HeadlessException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
	}
}
