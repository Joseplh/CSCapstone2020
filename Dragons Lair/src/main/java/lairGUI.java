package main.java;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.Frame;

public class lairGUI {

	private JFrame frame;
	private Customers customers;
	private Reports reports;
	private Titles titles;
	private static Controller control;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lairGUI window = new lairGUI();
					window.frame.setVisible(true);
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
		titles = new Titles(control);
		initialize();
	
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 34, 998, 694);
		frame.getContentPane().add(tabbedPane);
		
		tabbedPane.addTab("Customers", null, customers, null);
		tabbedPane.addTab("Titles", null, titles, null);
		tabbedPane.addTab("Reports", null, reports, null);
	}
	
	public Frame getFrame() {
		return frame;
	}

}
