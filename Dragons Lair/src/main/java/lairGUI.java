package main.java;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class lairGUI {

	private JFrame frame;
	private JFrame homeFrame;
	private JTextField userName;
	private JTextField userPass;
	private Customers customers;
	private Reports reports;
	private Titles catalog;
	private static Controller control;
	private boolean loggedIn;
	private static lairGUI window;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new lairGUI();
					window.homeFrame.setVisible(true);
					window.frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
	public lairGUI() {
		control = new Controller();
		customers = new Customers(control);
		reports = new Reports(control);
		catalog = new Titles(control);
		loggedIn = false;
		initialize();
		initializeHome();
	
	}	
	private void initialize() {
		frame = new JFrame("Main Page");
		frame.setBounds(100, 100, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 34, 998, 694);
		frame.getContentPane().add(tabbedPane);
		
		tabbedPane.addTab("Customers", null, customers, null);
		tabbedPane.addTab("Catalog", null, catalog, null);
		tabbedPane.addTab("Reports", null, reports, null);
				
	}
	public Frame getFrame() {
		return frame;
	}
	public Frame getHomeFrame() {
		return homeFrame;
	}
	private void initializeHome() {
		//defining the homepage and the frame it is contained by
				//added by Joseph Maxwell
				homeFrame = new JFrame("Home Page");
				homeFrame.setBounds(100, 100, 600, 360);
				homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				homeFrame.getContentPane().setLayout(null);
				homeFrame.setResizable(false);

				JButton logginButton = new JButton("Enter");
				logginButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
				logginButton.setBounds(380, 120, 162, 33);
				homeFrame.add(logginButton);
				
				JLabel labelA = new JLabel("Username");
				labelA.setBounds(30,80,162,33);
				labelA.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
				homeFrame.add(labelA);
				
				JLabel labelB = new JLabel("Password");
				labelB.setBounds(200,80,162,33);
				labelB.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
				homeFrame.add(labelB);
				
				userName = new JTextField();
				userName.setEditable(true);
				userName.setFont(new Font("Tahoma", Font.PLAIN, 14));
				userName.setBounds(30, 120, 162, 33);
				userName.setColumns(10);
				homeFrame.add(userName);

				userPass = new JTextField();
				userPass.setEditable(true);
				userPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
				userPass.setBounds(200, 120, 162, 33);
				userPass.setColumns(10);
				homeFrame.add(userPass);
				
				logginButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						System.out.println(userName.getUIClassID());
						if(logginButton.isEnabled()) {
							if(control.isAccount(userName.getText(), security.makeHash(userPass.getText()))) {
								System.out.println("Match");
								window.homeFrame.setVisible(false);
								window.frame.setVisible(true);
							}
							else
								System.out.println("No Match");
						}
					}
				});
	}
}
