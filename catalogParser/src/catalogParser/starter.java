package catalogParser;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class starter {
	private reader read = new reader();
	private JFrame homeFrame;
	private JTextField fileCSVloc;
	private JTextField userPass;
	@SuppressWarnings("unused")//it is not "unused", it is used on line 31 when the MainPage() is created and called.
	private static starter window;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new starter();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
	public starter() {
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
							if(read.read(fileCSVloc.getText())==1)
								JOptionPane.showMessageDialog(frame, "Import has reported success.");
							if(read.read(fileCSVloc.getText())==0)
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

