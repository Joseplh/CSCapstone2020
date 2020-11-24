import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JLabel;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;

public class window1 {

	private JFrame frame;
	private JTable customerTable;
	private JTextField lNameBox;
	private JTextField phoneBox;
	private JTextField emailBox;
	private JTextField fNameBox;

	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window1 window = new window1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public window1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 34, 998, 694);
		frame.getContentPane().add(tabbedPane);
		
	
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Reports", null, panel_2, null);
		panel_2.setLayout(null);
		
		
		/****************************************************/
		/****************************************************/
		/****************************************************/
		/****************************************************/
		/****************************************************/
		String da1[][]={{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"},
				{"Badger","","14.99", "10", "10"}};    
		
		String da2[][]={ {"Schrack","Jesse","2"},    
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"},
                {"Schrack","Jesse","2"}};    
		
		String da3[][]={{"Badger","10","Y", "Y"},
				{"Badger","10","Y", "Y"},
				{"Badger","10","Y", "Y"},
				{"Badger","10","Y", "N"},
				{"Badger","10","N", "Y"},
				{"Badger","10","Y", "Y"},
				{"Badger","10","Y", "Y"},
				{"Badger","10","Y", "N"},
				{"Badger","10","N", "Y"},
				{"Badger","10","Y", "Y"},
				{"Badger","10","Y", "Y"},
				{"Badger","10","Y", "N"},
				{"Badger","10","Y", "Y"},
				{"Badger","10","Y", "Y"},
				{"Badger","10","Y", "N"},
				{"Badger","10","N", "Y"},
				{"Badger","10","Y", "Y"},
				{"Badger","10","Y", "Y"},
				{"Badger","10","Y", "N"},
				{"Badger","10","N", "Y"},
				{"Badger","10","Y", "Y"},
				{"Badger","10","Y", "Y"},
				{"Badger","10","Y", "N"},
				{"Badger","10","N", "Y"},
				{"Badger","10","Y", "Y"},
				{"Badger","10","Y", "Y"},
				{"Badger","10","Y", "N"},
				{"Badger","10","N", "Y"},
				{"Badger","10","Y", "Y"},
				{"Badger","10","Y", "Y"},
				{"Badger","10","Y", "N"},
				{"Badger","10","N", "Y"},
				{"Badger","10","Y", "Y"},
				{"Badger","10","Y", "Y"},
				{"Badger","10","Y", "N"},
				{"Badger","10","N", "Y"},
				{"Badger","10","Y", "Y"},
				{"Badger","10","Y", "Y"},
				{"Badger","10","Y", "N"},
				{"Badger","10","N", "Y"}};       
		
		
		JTabbedPane reports_pane = new JTabbedPane(JTabbedPane.TOP);
		reports_pane.setBounds(10, 11, 973, 644);
		panel_2.add(reports_pane);
		
		JPanel new_week_pulls = new JPanel();
		new_week_pulls.setForeground(Color.BLACK);
		new_week_pulls.setBorder(null);
		reports_pane.addTab("New Week Pulls", null, new_week_pulls, null);
		new_week_pulls.setLayout(null);
		
		JPanel monthly_breakdown = new JPanel();
		reports_pane.addTab("Monthly Breakdown", null, monthly_breakdown, null);

		JPanel reports_history = new JPanel();
		reports_pane.addTab("Reports History", null, reports_history, null);
		
		
		
		/* New week pulls tab */
		JScrollPane new_titles_sp = new JScrollPane();
		new_titles_sp.setBounds(10, 97, 415, 497);
		new_week_pulls.add(new_titles_sp);
		
		// Titles table on new week pulls
		String col[]={"Title","Issue #","Price", "Qty", "# of Customers"}; 
		JTable new_titles_table = new JTable(da1, col);
		new_titles_table.setAutoCreateRowSorter(true);
		new_titles_sp.setViewportView(new_titles_table);
		
		JScrollPane titles_request_pane = new JScrollPane();
		titles_request_pane.setBounds(455, 225, 475, 369);
		new_week_pulls.add(titles_request_pane);
		
		// Title requests table on new week pulls
		String col1[]={"Last Name","First Name","Qty"};   
		JTable title_request_table = new JTable(da2, col1);
		title_request_table.setAutoCreateRowSorter(true);
		titles_request_pane.setViewportView(title_request_table);
		
		// The two export buttons for new week pulls 
		JButton export_flags_button = new JButton("Export New Week Flags");
		JButton export_request_button = new JButton("Export Request List");
		
		export_flags_button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		export_flags_button.setBounds(315, 30, 177, 37);
		new_week_pulls.add(export_flags_button);
		export_request_button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		export_request_button.setBounds(753, 177, 177, 37);
		new_week_pulls.add(export_request_button);
		
		
		// These labels are used to show the corresponding value from the DB
		JLabel cur_flagged_lbl = new JLabel("1000");
		JLabel cur_flagged_cust_lbl = new JLabel("231");
		JLabel trigger_issue_lbl = new JLabel("12");
		JLabel no_requests_lbl = new JLabel("14");
		
		cur_flagged_lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		cur_flagged_lbl.setBounds(10, 10, 47, 14);
		new_week_pulls.add(cur_flagged_lbl);
		cur_flagged_cust_lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		cur_flagged_cust_lbl.setBounds(10, 30, 47, 14);
		new_week_pulls.add(cur_flagged_cust_lbl);
		trigger_issue_lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		trigger_issue_lbl.setBounds(10, 50, 47, 14);
		new_week_pulls.add(trigger_issue_lbl);
		no_requests_lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		no_requests_lbl.setBounds(10, 70, 47, 14);
		new_week_pulls.add(no_requests_lbl);
		
		
		// Labels for the sentences/strings on new week pulls
		JLabel lbl1 = new JLabel("Titles Currently Flagged");
		JLabel lbl2 = new JLabel("Customers");
		JLabel lbl3 = new JLabel("Titles have triggered Issue #'s");
		JLabel lbl4 = new JLabel("Titles Have No Customer Requests");

		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl1.setBounds(58, 10, 176, 14);
		new_week_pulls.add(lbl1);
		lbl2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl2.setBounds(58, 30, 176, 14);
		new_week_pulls.add(lbl2);
		lbl3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl3.setBounds(58, 50, 200, 14);
		new_week_pulls.add(lbl3);
		lbl4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl4.setBounds(58, 70, 239, 14);
		new_week_pulls.add(lbl4);
		/* End of new week pulls */
		
		
		
		
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();
        dateFormat.format(date);
        
		JLabel date_label = new JLabel("Today's Date:");
		date_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		date_label.setBounds(738, 11, 103, 14);
		new_week_pulls.add(date_label);
		
		JLabel label = new JLabel(dateFormat.format(date));
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(840, 11, 90, 14);
		new_week_pulls.add(label);
		
		JLabel lblCustomerRequestsFor = new JLabel("Customer Requests for:");
		lblCustomerRequestsFor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCustomerRequestsFor.setBounds(455, 133, 176, 14);
		new_week_pulls.add(lblCustomerRequestsFor);
		
		JLabel title_lbl = new JLabel("Flash, The");
		title_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		title_lbl.setBounds(455, 158, 90, 14);
		new_week_pulls.add(title_lbl);
		
		JLabel qty_lbl = new JLabel("Total Qty: 5");
		qty_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		qty_lbl.setBounds(455, 181, 90, 14);
		new_week_pulls.add(qty_lbl);
		
		JLabel number_customers_lbl = new JLabel("# of Customers: 4");
		number_customers_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		number_customers_lbl.setBounds(455, 204, 141, 14);
		new_week_pulls.add(number_customers_lbl);
		
	
		
		
		/* monthly breakdown */
		monthly_breakdown.setLayout(null);
		
		JScrollPane breakdown_pane = new JScrollPane();
		breakdown_pane.setBounds(10, 11, 443, 594);
		monthly_breakdown.add(breakdown_pane);
		
		String col3[]={"Title","Qty","Pending Issue #", "Flagged in last 6 months"}; 
		JTable breakdown_table = new JTable(da3, col3);
		breakdown_table.setAutoCreateRowSorter(true);
		
		breakdown_pane.setViewportView(breakdown_table);
		
		JLabel breakdown_lbl = new JLabel("Database currently has:");
		breakdown_lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		breakdown_lbl.setBounds(463, 144, 176, 14);
		monthly_breakdown.add(breakdown_lbl);
		
		
		/* These are the labels that hold the value of the given request on monthly breakdown */
		JLabel no_titles_lbl = new JLabel("1000");
		JLabel no_customers_lbl = new JLabel("231");
		JLabel no_special_lbl = new JLabel("12");
		JLabel no_pending_lbl = new JLabel("14");
		JLabel no_flagged_lbl = new JLabel("77");
		JLabel no_zeroRequest_lbl = new JLabel("23");
		
		//Font, bounds, and adding lbl's to the panel
		no_titles_lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		no_titles_lbl.setBounds(471, 175, 55, 14);
		monthly_breakdown.add(no_titles_lbl);
		no_customers_lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		no_customers_lbl.setBounds(471, 210, 55, 14);
		monthly_breakdown.add(no_customers_lbl);
		no_special_lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		no_special_lbl.setBounds(471, 245, 55, 14);
		monthly_breakdown.add(no_special_lbl);
		no_pending_lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		no_pending_lbl.setBounds(471, 280, 55, 14);
		monthly_breakdown.add(no_pending_lbl);
		no_flagged_lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		no_flagged_lbl.setBounds(471, 315, 55, 14);
		monthly_breakdown.add(no_flagged_lbl);
		no_zeroRequest_lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		no_zeroRequest_lbl.setBounds(471, 350, 55, 14);
		monthly_breakdown.add(no_zeroRequest_lbl);
		
		
		/* These are the labels of text on the monthly breakdown screen, not the amount value */
		JLabel titles_lbl = new JLabel("Titles");
		JLabel customers_lbl = new JLabel("Customers");
		JLabel special_lbl = new JLabel("Special Order Notes");
		JLabel pending_lbl= new JLabel("Pending Issue # Requests");
		JLabel flagged_lbl = new JLabel("Titles not flagged in over 6 months");
		JLabel zero_Requests_lbl = new JLabel("Titles have 0 Customer Requests");
		
		titles_lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titles_lbl.setBounds(536, 175, 176, 14);
		monthly_breakdown.add(titles_lbl);
		customers_lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		customers_lbl.setBounds(536, 210, 176, 14);
		monthly_breakdown.add(customers_lbl);
		special_lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		special_lbl.setBounds(536, 245, 176, 14);
		monthly_breakdown.add(special_lbl);
		pending_lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pending_lbl.setBounds(536, 280, 189, 14);
		monthly_breakdown.add(pending_lbl);
		flagged_lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		flagged_lbl.setBounds(536, 315, 250, 14);
		monthly_breakdown.add(flagged_lbl);
		zero_Requests_lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		zero_Requests_lbl.setBounds(536, 350, 232, 14);
		monthly_breakdown.add(zero_Requests_lbl);
		
		/* These are the 5 different export buttons for the monthly breakdown screen */
		JButton export_titles_btn = new JButton("Export");
		JButton export_customers_btn = new JButton("Export");
		JButton export_flagged_btn = new JButton("Export");
		JButton export_pending_btn = new JButton("Export");
		JButton export_zeroRequests_btn = new JButton("Export");
		
		// Font, bounds, and adding buttons to panel
		export_titles_btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		export_titles_btn.setBounds(869, 173, 89, 23);
		monthly_breakdown.add(export_titles_btn);
		export_customers_btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		export_customers_btn.setBounds(869, 208, 89, 23);
		monthly_breakdown.add(export_customers_btn);
		export_flagged_btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		export_flagged_btn.setBounds(869, 313, 89, 23);
		monthly_breakdown.add(export_flagged_btn);
		export_pending_btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		export_pending_btn.setBounds(869, 278, 89, 23);
		monthly_breakdown.add(export_pending_btn);
		export_zeroRequests_btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		export_zeroRequests_btn.setBounds(869, 348, 89, 23);
		monthly_breakdown.add(export_zeroRequests_btn);
		
		
		/* End of monthly breakdown */
		/* End of monthly breakdown */
		
		
		/****************************************************/
		/****************************************************/
		/****************************************************/
		/****************************************************/
		/****************************************************/
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		tabbedPane.addTab("Customers", null, panel, null);
		panel.setLayout(null);
		
		JButton AddCustBtn = new JButton("Add New Customer");
		AddCustBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		AddCustBtn.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		AddCustBtn.setBounds(70, 11, 174, 33);
		panel.add(AddCustBtn);
		
		JButton addRequestBtn = new JButton("Add Request");
		addRequestBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		addRequestBtn.setBounds(860, 309, 107, 48);
		panel.add(addRequestBtn);
		
		JButton editDelSelectedBtn = new JButton("Edit/delete Selected");
		editDelSelectedBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		editDelSelectedBtn.setBounds(860, 368, 107, 48);
		panel.add(editDelSelectedBtn);
		

		
		JButton saveCustBtn = new JButton("Save");
		saveCustBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		saveCustBtn.setBounds(860, 194, 107, 33);
		panel.add(saveCustBtn);
		saveCustBtn.setEnabled(false);
		
		JButton editCustBtn = new JButton("Edit");
		editCustBtn.setEnabled(false);
		/* Action listener for when the "Save Changes" button is clicked */
		saveCustBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				/* If the save button has been clicked and it is enabled */
				if(saveCustBtn.isEnabled()) {
					
					
					/* Code here to pull the new info and update database */
					
					saveCustBtn.setEnabled(false);
					editCustBtn.setEnabled(false);
					JOptionPane.showMessageDialog(frame,
						    "Changes have been saved!",
						    "Message",
						    JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		
		
		/* action listener for when the edit button for customer info is clicked */
		editCustBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/* If the button is clicked */
				if(editCustBtn.isEnabled()) {
					lNameBox.setEditable(false);
					fNameBox.setEditable(false);
					phoneBox.setEditable(false);
					emailBox.setEditable(false);
					editCustBtn.setEnabled(false);
				}
				else {
					/* Set the boxes to be editable so new info can be inputted */
					lNameBox.setEditable(true);
					fNameBox.setEditable(true);
					phoneBox.setEditable(true);
					emailBox.setEditable(true);
					editCustBtn.setEnabled(true);
					/* Enable the save button */
					saveCustBtn.setEnabled(true);
				}
			}
		});
		
		
		editCustBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		editCustBtn.setBounds(688, 10, 162, 33);
		panel.add(editCustBtn);
		
		JButton delCustBtn = new JButton("Delete");
		
		delCustBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		delCustBtn.setBounds(447, 10, 162, 33);
		panel.add(delCustBtn);
		delCustBtn.setEnabled(false);
		
		/* Action listener for account delete button */
		delCustBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/* If they want to delete an account */
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to delete the selected account from the system? This can't be undone.", "Delete User", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					
					/* Code here to remove the user from the database */
					
					
				    JOptionPane.showMessageDialog(null, "Account Deleted");
				}
			}
		});
		
		/* Dummy Data */
		String data[][]={ {"Jesse","Schrack","40234454", "jschrack@unomaha.edu"},    
                {"John","Doe","403499504", "jdoe@unomaha.edu"},
                {"John1","Doe1","403499504", "jdoe1@unomaha.edu"},
                {"John2","Doe2","403499504", "jdoe2@unomaha.edu"},
                {"John3","Doe3","403499504", "jdoe3@unomaha.edu"},
                {"John4","Doe4","403499504", "jdoe4@unomaha.edu"},
                {"John5","Doe5","403499504", "jdoe5@unomaha.edu"},
                {"John6","Doe6","403499504", "jdoe6@unomaha.edu"},
                {"John7","Doe7","403499504", "jdoe7@unomaha.edu"},
                {"John","Doe","403499504", "jdoe@unomaha.edu"},
                {"John1","Doe1","403499504", "jdoe1@unomaha.edu"},
                {"John2","Doe2","403499504", "jdoe2@unomaha.edu"},
                {"John3","Doe3","403499504", "jdoe3@unomaha.edu"},
                {"John4","Doe4","403499504", "jdoe4@unomaha.edu"},
                {"John5","Doe5","403499504", "jdoe5@unomaha.edu"},
                {"John6","Doe6","403499504", "jdoe6@unomaha.edu"},
                {"John7","Doe7","403499504", "jdoe7@unomaha.edu"},
                {"John","Doe","403499504", "jdoe@unomaha.edu"},
                {"John1","Doe1","403499504", "jdoe1@unomaha.edu"},
                {"John2","Doe2","403499504", "jdoe2@unomaha.edu"},
                {"John3","Doe3","403499504", "jdoe3@unomaha.edu"},
                {"John4","Doe4","403499504", "jdoe4@unomaha.edu"},
                {"John5","Doe5","403499504", "jdoe5@unomaha.edu"},
                {"John6","Doe6","403499504", "jdoe6@unomaha.edu"},
                {"John7","Doe7","403499504", "jdoe7@unomaha.edu"},
                {"John","Doe","403499504", "jdoe@unomaha.edu"},
                {"John1","Doe1","403499504", "jdoe1@unomaha.edu"},
                {"John2","Doe2","403499504", "jdoe2@unomaha.edu"},
                {"John3","Doe3","403499504", "jdoe3@unomaha.edu"},
                {"John4","Doe4","403499504", "jdoe4@unomaha.edu"},
                {"John5","Doe5","403499504", "jdoe5@unomaha.edu"},
                {"John6","Doe6","403499504", "jdoe6@unomaha.edu"},
                {"John7","Doe7","403499504", "jdoe7@unomaha.edu"},
                {"John8","Doe8","403499504", "jdoe8@unomaha.edu"}};    
		
		/* Customer Table Column Names */
		String column[]={"Last Name","First Name","Phone Number", "Email"};   
		
		/* Adding the scroll bar to the customer Table */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 91, 415, 564);
		panel.add(scrollPane);
		
		
		customerTable = new JTable(data, column);
		
		/* Listener for when a cell is selected from the customerTable */
		customerTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				/* If the text boxes displaying the customer information is editable, warn user to save the information */
				if(lNameBox.isEditable())
				{
					JOptionPane.showMessageDialog(frame,
						    "Please save the changes made to the current account",
						    "Save Warning",
						    JOptionPane.WARNING_MESSAGE);
				}
				
				/* Otherwise set the text boxes editable so the data can be changed */
				else {
					int i = customerTable.getSelectedRow();
					lNameBox.setText((String)customerTable.getValueAt(i, 0));
					fNameBox.setText((String)customerTable.getValueAt(i, 1));
					phoneBox.setText((String)customerTable.getValueAt(i, 2));
					emailBox.setText((String)customerTable.getValueAt(i, 3));
					
					delCustBtn.setEnabled(true);
				}
				
			}
		});
		
		scrollPane.setViewportView(customerTable);
	
		/* Declaring variables for "edit customer info" section */
		
		table = new JTable();
		table.setBounds(447, 288, 403, 367);
		panel.add(table);
		
		JButton btnNewButton = new JButton("Discard");
		btnNewButton.setEnabled(false);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(860, 114, 107, 33);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Export List");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(860, 607, 107, 48);
		panel.add(btnNewButton_1);
		
		JPanel panel_5 = new JPanel();
		panel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Customer Details", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_5.setBackground(Color.LIGHT_GRAY);
		panel_5.setBounds(447, 73, 403, 181);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lNameLabel = new JLabel("Last Name");
		lNameLabel.setBounds(10, 21, 73, 17);
		panel_5.add(lNameLabel);
		lNameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel fnameLabel = new JLabel("First Name");
		fnameLabel.setBounds(231, 22, 88, 14);
		panel_5.add(fnameLabel);
		fnameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lNameBox = new JTextField();
		lNameBox.setBounds(10, 41, 162, 33);
		panel_5.add(lNameBox);
		lNameBox.setEditable(false);
		lNameBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNameBox.setColumns(10);
		
		JLabel phoneLabel = new JLabel("Phone Number");
		phoneLabel.setBounds(10, 97, 119, 14);
		panel_5.add(phoneLabel);
		phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		phoneBox = new JTextField();
		phoneBox.setBounds(10, 122, 162, 33);
		panel_5.add(phoneBox);
		phoneBox.setEditable(false);
		phoneBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		phoneBox.setColumns(10);
		
		fNameBox = new JTextField();
		fNameBox.setBounds(231, 41, 162, 33);
		panel_5.add(fNameBox);
		fNameBox.setEditable(false);
		fNameBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fNameBox.setColumns(10);
		
		emailBox = new JTextField();
		emailBox.setBounds(231, 122, 162, 33);
		panel_5.add(emailBox);
		emailBox.setEditable(false);
		emailBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emailBox.setColumns(10);
		
		JLabel emailLabel = new JLabel("Email Address");
		emailLabel.setBounds(231, 97, 113, 14);
		panel_5.add(emailLabel);
		emailLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textField_3 = new JTextField();
		
		
		textField_3.setBounds(70, 60, 174, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Search:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 60, 72, 17);
		panel.add(lblNewLabel);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.LIGHT_GRAY);
		
		tabbedPane.addTab("Titles", null, panel1, null);
		panel1.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("Add New Title");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_2.setBounds(10, 11, 180, 41);
		panel1.add(btnNewButton_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 63, 459, 592);
		panel1.add(scrollPane_3);
		
		table_1 = new JTable();
		scrollPane_3.setViewportView(table_1);
		
		JButton btnResetNewWeek = new JButton("Reset New Week Flags?");
		btnResetNewWeek.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnResetNewWeek.setBounds(289, 11, 180, 41);
		panel1.add(btnResetNewWeek);
		
		JButton editTitleBtn = new JButton("Edit");
		editTitleBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		editTitleBtn.setEnabled(false);
		editTitleBtn.setBounds(505, 63, 162, 33);
		panel1.add(editTitleBtn);
		
		JButton delTitleBtn = new JButton("Delete");
		delTitleBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		delTitleBtn.setEnabled(false);
		delTitleBtn.setBounds(802, 63, 162, 33);
		panel1.add(delTitleBtn);
		
		JButton btnExportSingleTitle = new JButton("Export Requested\r\n");
		btnExportSingleTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExportSingleTitle.setEnabled(false);
		btnExportSingleTitle.setBounds(802, 382, 162, 49);
		panel1.add(btnExportSingleTitle);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBorder(new TitledBorder(null, "Title Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(505, 117, 459, 168);
		panel1.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(10, 22, 29, 17);
		panel_1.add(lblTitle);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textField = new JTextField();
		textField.setBounds(10, 43, 162, 33);
		panel_1.add(textField);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setEditable(false);
		textField.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(10, 87, 82, 14);
		panel_1.add(lblPrice);
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 112, 97, 33);
		panel_1.add(textField_1);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		JLabel lblSpecialOrderNotes = new JLabel("Special Order Notes");
		lblSpecialOrderNotes.setBounds(216, 87, 149, 14);
		panel_1.add(lblSpecialOrderNotes);
		lblSpecialOrderNotes.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textField_2 = new JTextField();
		textField_2.setBounds(216, 112, 230, 33);
		panel_1.add(textField_2);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		
		JTextArea txtrThisTitleCurrently = new JTextArea();
		txtrThisTitleCurrently.setBounds(558, 389, 221, 40);
		panel1.add(txtrThisTitleCurrently);
		txtrThisTitleCurrently.setText("This Title Currently has 24\r\nCustomer Requests");
		txtrThisTitleCurrently.setBackground(Color.LIGHT_GRAY);
		
		JButton button = new JButton("Discard");
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.setEnabled(false);
		button.setBounds(755, 313, 107, 33);
		panel1.add(button);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSave.setEnabled(false);
		btnSave.setBounds(607, 313, 107, 33);
		panel1.add(btnSave);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(449, 184, 2, 2);
		panel1.add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(132, 152, 58, 18);
		panel1.add(scrollPane_2);
	
		
		
		/* Tool bar for later */
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(10, 7, 87, 16);
		frame.getContentPane().add(toolBar);
		
		JPanel panel_3 = new JPanel();
		toolBar.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		toolBar.add(panel_4);
		
		 
	}
}
