package main.java;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileFilter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class Reports extends JPanel implements Tile {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller control;
	private DefaultTableModel requestsModel;
	private String [][] requestsData; 
	private String requestColumns[];
	private JTable title_request_table;
	private final int titleColumn = 0;

	public Reports(Controller control) {
		this.control = control;
		setLayout(null);
		setBackground(Color.LIGHT_GRAY);
		
		/* Report Data */
		String data[][] = control.getReports();

		/* Report Table Column Names */
		String column[] = { };
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
		add(reports_pane);
		
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
		
		
		// The two scrollpanes and two tables 
		JScrollPane new_titles_sp = new JScrollPane();
		new_titles_sp.setBounds(10, 87, 415, 507);
		new_week_pulls.add(new_titles_sp);
		
		String col[]={"Title","Issue #","Price", "Qty", "# of Customers"}; 
		JTable new_titles_table = new JTable(da1, col);
		new_titles_table.setAutoCreateRowSorter(true);
		new_titles_sp.setViewportView(new_titles_table);
		
		JScrollPane titles_request_pane = new JScrollPane();
		titles_request_pane.setBounds(455, 225, 475, 369);
		new_week_pulls.add(titles_request_pane);
		
		requestColumns = new String[] {"Last Name","First Name","Qty"};
		requestsModel = new DefaultTableModel(requestsData, requestColumns) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		title_request_table = new JTable(requestsModel);
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
		
		// Labels for customer requests on new week pulls
		JLabel lblCustomerRequestsFor = new JLabel("Customer Requests for:");
		JLabel title_lbl = new JLabel("Flash, The");
		JLabel qty_lbl = new JLabel("Total Qty: 5");
		JLabel number_customers_lbl = new JLabel("# of Customers: 4");
		
		lblCustomerRequestsFor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCustomerRequestsFor.setBounds(455, 133, 176, 14);
		new_week_pulls.add(lblCustomerRequestsFor);
		title_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		title_lbl.setBounds(455, 158, 90, 14);
		new_week_pulls.add(title_lbl);
		qty_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		qty_lbl.setBounds(455, 181, 90, 14);
		new_week_pulls.add(qty_lbl);
		number_customers_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		number_customers_lbl.setBounds(455, 204, 141, 14);
		new_week_pulls.add(number_customers_lbl);
		
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
		
		
		/* monthly breakdown */
		
		monthly_breakdown.setLayout(null);
		JScrollPane breakdown_pane = new JScrollPane();
		breakdown_pane.setBounds(10, 11, 443, 594);
		monthly_breakdown.add(breakdown_pane);
		
		String col3[]={"Title","Qty","Pending Issue #", "Flagged in last 6 months"}; 
		JTable breakdown_table = new JTable(da3, col3);
		breakdown_table.setAutoCreateRowSorter(true);
		breakdown_pane.setViewportView(breakdown_table);
		
		
		
		// These are the labels that hold the value of the given request on monthly breakdown 
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
		
		
		// These are the labels of text on the monthly breakdown screen, not the amount value 
		JLabel titles_lbl = new JLabel("Titles");
		JLabel customers_lbl = new JLabel("Customers");
		JLabel special_lbl = new JLabel("Special Order Notes");
		JLabel pending_lbl= new JLabel("Pending Issue # Requests");
		JLabel flagged_lbl = new JLabel("Titles not flagged in over 6 months");
		JLabel zero_Requests_lbl = new JLabel("Titles have 0 Customer Requests");
		JLabel breakdown_lbl = new JLabel("Database currently has:");
		
		breakdown_lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		breakdown_lbl.setBounds(463, 144, 176, 14);
		monthly_breakdown.add(breakdown_lbl);
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
		
		// These are the 5 different export buttons for the monthly breakdown screen 
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
		
		new_titles_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[][] orders = control.getOrdersByTitle(new_titles_table.getValueAt(new_titles_table.getSelectedRow(), titleColumn).toString());
				requestsModel.setDataVector(orders, requestColumns);
			}
		});
		
		export_customers_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    String filePath = control.saveFile(monthly_breakdown, "Customers");  // Calls saveFile() in controller. Essentially opens file saver and gets save destination.
			    if (filePath != null) {
			    	String columns[] = {"Last Name", "First Name", "Phone #1", "Email"}; 		// Columns for your excel sheet
				    String query = "SELECT [Last Name], [First Name], [Phone #1], [Email] FROM [newDLC].[dbo].[Customer]"; // Query matching the columns
				    control.exportXLSX(null, query, filePath, "Customers", columns); 			// Calling export. Check the function for param values
			    }
			    
			}
			
		});
		
		
		//Monthly Title
		export_titles_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String filePath = control.saveFile(monthly_breakdown, "Titles");
                if (filePath != null) {
                    String columns[] = {"Title", "Customer", "Quantity"};
                    String query = "SELECT [Title], [Customer Code], [Quantity] FROM [Order]";
                    control.exportXLSX(null, query, filePath, "Titles", columns);
                }

            }
        });
		
		
		
	}
	
}
