package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class lairGUI {
	
	private final int WIDTH = 1024;
	private final int HEIGHT = 768;
	
	private final int LOGIN_WIDTH = 600;
	private final int LOGIN_HEIGHT = 300;

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
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 64, 998, 794);
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
				homeFrame.setSize(LOGIN_WIDTH, LOGIN_HEIGHT);
				homeFrame.setLocationRelativeTo(null);
				homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				homeFrame.getContentPane().setLayout(null);
				homeFrame.setResizable(false);
				homeFrame.setVisible(true);

				JButton loginButton = new JButton("Enter");
				loginButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
				loginButton.setBounds(380, 120, 162, 33);
				homeFrame.add(loginButton);
				homeFrame.getRootPane().setDefaultButton(loginButton);
				
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
				
				loginButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.out.println(userName.getUIClassID());
						if(loginButton.isEnabled()) {
							if(control.isAccount(userName.getText(), Security.makeHash(userPass.getText()))) {
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
