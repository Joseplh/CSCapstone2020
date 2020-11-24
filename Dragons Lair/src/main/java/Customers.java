package main.java;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Customers extends JPanel implements Tile {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTable customerTable;
	private JTable titleTable;
	private JTextField lNameBox;
	private JTextField phoneBox;
	private JTextField emailBox;
	private JTextField fNameBox;
	private JTextField ccodeBox;
	private Controller control;
	private JButton addRequestBtn;
	private DefaultTableModel reportsModel;
	private String [][] reportsData; 
	private String reportColumns[];
	private JButton editDelSelectedBtn;
	private final int orderIdcolumn = 4;
	
	private DefaultTableModel customersModel;
	private String [][] customersData; 
	private String customerColumns[];
	private int lastnameCodeColumn = 0;
	private int firstnameCodeColumn = 1;
	private int customerCodeColumn = 4;
	//private int storeCodeColumn;  currently unused

	private Font font = new Font("Tahoma", Font.BOLD, 14);
	private Color color = new Color(240, 240, 240);

	public Customers(Controller control) {
		this.control = control;
		setLayout(null);
		setBackground(Color.LIGHT_GRAY);

		JPanel customerDetails = new JPanel();
		customerDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
		customerDetails.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Customer Details", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		customerDetails.setBackground(Color.LIGHT_GRAY);
		customerDetails.setBounds(447, 73, 403, 181);

		customerDetails.setLayout(null);

		/* Button deceleration */
		JButton AddCustBtn = new JButton("Add New Customer");
		AddCustBtn.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		AddCustBtn.setBounds(132, 11, 174, 33);
		add(AddCustBtn);

		addRequestHandler();
		editDeleteOrderHandler();

		JButton moreInfoBtn = new JButton("More Info");
		moreInfoBtn.setEnabled(false);
		moreInfoBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		moreInfoBtn.setBounds(860, 59, 107, 33);
		add(moreInfoBtn);

		JButton saveCustBtn = new JButton("Save");
		saveCustBtn.setEnabled(false);
		saveCustBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		saveCustBtn.setBounds(860, 221, 107, 33);
		add(saveCustBtn);

		JButton discardBtn = new JButton("Discard");
		discardBtn.setEnabled(false);
		discardBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		discardBtn.setBounds(860, 140, 107, 33);
		add(discardBtn);

		JButton editCustBtn = new JButton("Edit");
		editCustBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		editCustBtn.setBounds(447, 10, 162, 33);
		add(editCustBtn);
		editCustBtn.setEnabled(false);

		JButton delCustBtn = new JButton("Delete");
		delCustBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		delCustBtn.setBounds(688, 10, 162, 33);
		add(delCustBtn);
		delCustBtn.setEnabled(false);

		JButton exportBtn = new JButton("Export List");
		exportBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		exportBtn.setBounds(860, 607, 107, 48);
		add(exportBtn);

		/* Declaring variables for "edit customer info" section */
		lNameBox = new JTextField();
		lNameBox.setEditable(false);
		lNameBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lNameBox.setBounds(10, 41, 162, 33);
		lNameBox.setColumns(10);

		phoneBox = new JTextField();
		phoneBox.setEditable(false);
		phoneBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		phoneBox.setBounds(10, 122, 162, 33);
		phoneBox.setColumns(10);

		emailBox = new JTextField();
		emailBox.setEditable(false);
		emailBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emailBox.setBounds(231, 122, 162, 33);
		emailBox.setColumns(10);

		fNameBox = new JTextField();
		fNameBox.setEditable(false);
		fNameBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fNameBox.setBounds(231, 41, 162, 33);
		fNameBox.setColumns(10);

		ccodeBox = new JTextField();
		ccodeBox.setEditable(false);
		ccodeBox.setFont(new Font("Tahoma", Font.PLAIN, 14));

		ccodeBox.setColumns(10);

		JLabel fnameLabel = new JLabel("First Name");
		fnameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		fnameLabel.setBounds(231, 22, 88, 14);

		JLabel lNameLabel = new JLabel("Last Name");
		lNameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lNameLabel.setBounds(10, 21, 73, 17);

		JLabel phoneLabel = new JLabel("Phone Number");
		phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		phoneLabel.setBounds(10, 97, 119, 14);

		JLabel emailLabel = new JLabel("Email Address");
		emailLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		emailLabel.setBounds(231, 97, 113, 14);

		customerDetails.add(lNameLabel);
		customerDetails.add(fnameLabel);
		customerDetails.add(emailLabel);
		customerDetails.add(phoneLabel);
		customerDetails.add(lNameBox);
		customerDetails.add(phoneBox);
		customerDetails.add(fNameBox);
		customerDetails.add(emailBox);
		customerDetails.add(ccodeBox);
		add(customerDetails);


		/* Customer Data */
		//String data[][] = control.getCustomers(); unused

		/* Customer Table Column Names */
		//String column[] = {"Last Name", "First Name", "Phone Number", "Email", "Customer Code"}; unused

		/* Adding the scroll bar to the customer Table */
		JScrollPane customerScrollPane = new JScrollPane();
		customerScrollPane.setBounds(10, 65, 415, 590);
		add(customerScrollPane);
		customersData = control.getCustomers();
		customerColumns = new String[]{"Last Name", "First Name", "Phone Num", "Email", "Customer Code"};
		customersModel = new DefaultTableModel(customersData, customerColumns) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
		        return false;
		      }
		};
		
		customerTable = new JTable(customersModel);
		customerTable.setAutoCreateRowSorter(true);
		customerScrollPane.setViewportView(customerTable);
		
		
		customerTable.getTableHeader().setReorderingAllowed(false);
		customerTable.getColumnModel().getColumn(4).setMinWidth(0); // Must be set before maxWidth!!
		customerTable.getColumnModel().getColumn(4).setMaxWidth(0);
		customerTable.getColumnModel().getColumn(4).setWidth(0);

		JScrollPane reportsScrollPane = new JScrollPane();
		reportsScrollPane.setBounds(455, 288, 395, 367);
		add(reportsScrollPane);

		reportColumns = new String[]{"Store Code", "Description", "Issue Start", "Issue End", "ID", "Quantity"};
		reportsModel = new DefaultTableModel(reportsData, reportColumns) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		titleTable = new JTable(reportsModel);
		titleTable.setAutoCreateRowSorter(true);
		titleTable.getTableHeader().setReorderingAllowed(false);
		reportsScrollPane.setViewportView(titleTable);

		titleTable.getColumnModel().getColumn(0).setPreferredWidth(3);
		titleTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		titleTable.getColumnModel().getColumn(2).setPreferredWidth(3);
		titleTable.getColumnModel().getColumn(3).setPreferredWidth(3);
		titleTable.getColumnModel().getColumn(4).setMinWidth(0); // Must be set before maxWidth!!
		titleTable.getColumnModel().getColumn(4).setMaxWidth(0);
		titleTable.getColumnModel().getColumn(4).setWidth(0);
		titleTable.getColumnModel().getColumn(5).setPreferredWidth(3);

		/* Action listener for the add account button */
		AddCustBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (AddCustBtn.isEnabled()) {

					AddCustBtn.setEnabled(false);
					JFrame newAcc = new JFrame("Add New Customer");
					newAcc.setVisible(true);
					newAcc.setBounds(128, 91, 400, 240);
					newAcc.setResizable(false);
					newAcc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

					/* Action listener for when the add customer frame is closed */
					newAcc.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							AddCustBtn.setEnabled(true);
						}
					});

					/* Creating the panel that will take new customer info */
					JPanel accPanel = new JPanel();
					accPanel.setBounds(128, 91, 388, 207);
					accPanel.setLayout(null);

					JLabel lblNewLabel = new JLabel("First Name");
					lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblNewLabel.setBounds(28, 25, 78, 23);
					accPanel.add(lblNewLabel);

					JLabel lblLastName = new JLabel("Last Name");
					lblLastName.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblLastName.setBounds(214, 25, 78, 23);
					accPanel.add(lblLastName);

					JLabel lblEmailAddress = new JLabel("Email Address\r\n");
					lblEmailAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblEmailAddress.setBounds(28, 86, 151, 23);
					accPanel.add(lblEmailAddress);

					JLabel lblPhoneNumber = new JLabel("Phone Number");
					lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblPhoneNumber.setBounds(214, 86, 151, 23);
					accPanel.add(lblPhoneNumber);

					JTextField fnameAccField = new JTextField();
					fnameAccField.setBounds(28, 48, 136, 20);
					accPanel.add(fnameAccField);
					fnameAccField.setColumns(10);

					JTextField emailAccField = new JTextField();
					emailAccField.setColumns(10);
					emailAccField.setBounds(28, 109, 136, 20);
					accPanel.add(emailAccField);

					JTextField lnameAccField = new JTextField();
					lnameAccField.setColumns(10);
					lnameAccField.setBounds(214, 48, 136, 20);
					accPanel.add(lnameAccField);
					newAcc.add(accPanel);

					JTextField phoneAccField = new JTextField();
					phoneAccField.setBounds(214, 109, 136, 20);
					accPanel.add(phoneAccField);
					phoneAccField.setColumns(10);

					JButton addAcc = new JButton("Add");
					addAcc.setFont(new Font("Tahoma", Font.PLAIN, 14));

					addAcc.setBounds(148, 161, 89, 23);
					accPanel.add(addAcc);

					/* Action listener for when add button clicked on create new customer frame */
					addAcc.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							
								
							String emailpattern = "[a-zA-z0-9_.!#$%&'*+-/=?^_`{|}~;]+@[\\w]+.[\\w]+.{0,1}[\\w]+";
							String phonepattern = "\\(\\d{3}\\)\\d{3}-\\d{4}";
							
							Pattern r = Pattern.compile(emailpattern);
							Matcher m = r.matcher(emailAccField.getText());
							
							if(m.find()) {
								r = Pattern.compile(phonepattern);
								m = r.matcher(phoneAccField.getText());
					
								if(m.find()) {
									// Get rid of the add account panel we used
									newAcc.dispose();
									//Insert customer
									control.insertCustomer(fnameAccField.getText(), lnameAccField.getText(),
											emailAccField.getText(), phoneAccField.getText());
									
									AddCustBtn.setEnabled(true);
									//Reset model
									customersData = control.getCustomers();
									customersModel.setDataVector(customersData, customerColumns);
									customerTable.setModel(customersModel);
									customerTable.getTableHeader().setReorderingAllowed(false);
									customerTable.getColumnModel().getColumn(4).setMinWidth(0); // Must be set before maxWidth!!
									customerTable.getColumnModel().getColumn(4).setMaxWidth(0);
									customerTable.getColumnModel().getColumn(4).setWidth(0);
									
								}
								else {
									JOptionPane.showMessageDialog(null, "Please enter a valid phone number! (XXX)XXX-XXXX", "Account Information",
											JOptionPane.PLAIN_MESSAGE);
								}
								
								
							}
							else {
								JOptionPane.showMessageDialog(null, "Please enter a valid email!", "Account Information",
										JOptionPane.PLAIN_MESSAGE);
								
							}
							
							

						}
					});

				}
			}
		});

		/* Action listener for when the "Save Changes" button is clicked */
		saveCustBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				/* If the save button has been clicked and it is enabled */
				if (saveCustBtn.isEnabled()) {

					/* TODO: Code here to pull the new info and update database */

					

					
					String emailpattern = "^$|[a-zA-z0-9_.!#$%&'*+-/=?^_`{|}~;]+@[\\w]+\\.[\\w]+.{0,1}[\\w]";
					String phonepattern = "^$|\\(\\d{3}\\)\\d{3}\\-\\d{4}";
					
					Pattern r = Pattern.compile(emailpattern);
					Matcher m = r.matcher(emailBox.getText());
					
					if(m.find()) {
						r = Pattern.compile(phonepattern);
						m = r.matcher(phoneBox.getText());
			
						if(m.find()) {
							saveCustBtn.setEnabled(false);
							editCustBtn.setEnabled(false);
							discardBtn.setEnabled(false);

							lNameBox.setEditable(false);
							fNameBox.setEditable(false);
							phoneBox.setEditable(false);
							emailBox.setEditable(false);
							ccodeBox.setEditable(false);
							
							
							control.updateCustomer(Integer.parseInt(ccodeBox.getText()), fNameBox.getText(), lNameBox.getText(), emailBox.getText(), phoneBox.getText());
							
							customersData = control.getCustomers();
							customersModel.setDataVector(customersData, customerColumns);
							customerTable.setModel(customersModel);
							customerTable.getTableHeader().setReorderingAllowed(false);
							customerTable.getColumnModel().getColumn(4).setMinWidth(0); // Must be set before maxWidth!!
							customerTable.getColumnModel().getColumn(4).setMaxWidth(0);
							customerTable.getColumnModel().getColumn(4).setWidth(0);
							
							JOptionPane.showMessageDialog(null, "Changes have been saved!", "Message",
									JOptionPane.PLAIN_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Please enter a valid phone number or nothing! (XXX)XXX-XXXX", "Message",
									JOptionPane.PLAIN_MESSAGE);
						}
							
					}
					else {
						JOptionPane.showMessageDialog(null, "Please enter a valid email address!",
								"Account Information", JOptionPane.PLAIN_MESSAGE);
					}
						
				}
			}
		});

		exportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(customerTable.getSelectedRow() != -1) {
					String last = customerTable.getValueAt(customerTable.getSelectedRow(), lastnameCodeColumn).toString();
					String first = customerTable.getValueAt(customerTable.getSelectedRow(), firstnameCodeColumn).toString();
					String filePath = control.saveFile(customerDetails, last + "_" + first);

					if (filePath != null) {
						String columns[] = { "Store Code", "Description", "Issue Start", "Issue End", "Quantity" };
						control.exportXLSX(
								control.getRequests(customerTable
										.getValueAt(customerTable.getSelectedRow(), customerCodeColumn).toString()),
								null, filePath, "Customers", columns);
					}
				}


			}
		});

		//Willy Wonka
		/* Listener for when a cell is selected from the customerTable */
		customerTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				addRequestBtn.setEnabled(true);
				editDelSelectedBtn.setEnabled(false);
				moreInfoBtn.setEnabled(true);
				reportsData = control.getRequests(
						customerTable.getValueAt(customerTable.getSelectedRow(), customerCodeColumn).toString()
				);

				reportsModel.setDataVector(reportsData, reportColumns);
				titleTable.setModel(reportsModel);

				titleTable.getColumnModel().getColumn(0).setPreferredWidth(3);
				titleTable.getColumnModel().getColumn(1).setPreferredWidth(200);
				titleTable.getColumnModel().getColumn(2).setPreferredWidth(3);
				titleTable.getColumnModel().getColumn(3).setPreferredWidth(3);
				titleTable.getColumnModel().getColumn(4).setMinWidth(0); // Must be set before maxWidth!!
				titleTable.getColumnModel().getColumn(4).setMaxWidth(0);
				titleTable.getColumnModel().getColumn(4).setWidth(0);
				titleTable.getColumnModel().getColumn(5).setPreferredWidth(3);

				/*
				 * If the text boxes displaying the customer information is editable, warn user
				 * to save the information
				 */
				if (lNameBox.isEditable()) {
					JOptionPane.showMessageDialog(null,
							"Please save or discard the changes made to the current account", "Save Warning",
							JOptionPane.WARNING_MESSAGE);
				}

				/* Otherwise set the text boxes editable so the data can be changed */
				else {
					int i = customerTable.getSelectedRow();
					lNameBox.setText((String) customerTable.getValueAt(i, 0));
					fNameBox.setText((String) customerTable.getValueAt(i, 1));
					phoneBox.setText((String) customerTable.getValueAt(i, 2));
					emailBox.setText((String) customerTable.getValueAt(i, 3));
					ccodeBox.setText((String) customerTable.getValueAt(i, 4));

					editCustBtn.setEnabled(true);
					delCustBtn.setEnabled(true);
				}

			}
		});
		
		/**
		 * Handler for watching for a mouse click on a customers orders.
		 */
		titleTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editDelSelectedBtn.setEnabled(true);
				
			}
		});

		/* action listener for when the edit button for customer info is clicked */
		editCustBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				lNameBox.setEditable(true);
				fNameBox.setEditable(true);
				phoneBox.setEditable(true);
				emailBox.setEditable(true);

				saveCustBtn.setEnabled(true);
				discardBtn.setEnabled(true);

			}
		});

		/* Action listener for More Info btn */
		moreInfoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String[] stores = {"dl1", "dl2"};
				String[][] customer2D = control.getCustomerData(Integer.parseInt((String) customerTable.getValueAt(customerTable.getSelectedRow(), 4)));

				/*System.out.println(customer2D[0][0]); //Store Code
				System.out.println(customer2D[0][1]); //Last Name
				System.out.println(customer2D[0][2]); //First Name
				System.out.println(customer2D[0][3]); //Address
				System.out.println(customer2D[0][4]); //City
				System.out.println(customer2D[0][5]); //State
				System.out.println(customer2D[0][6]); //ZIP
				System.out.println(customer2D[0][7]); //Phone #1
				System.out.println(customer2D[0][8]); //Phone #2
				System.out.println(customer2D[0][9]); //Email*/

				moreInfoBtn.setEnabled(false);
				addRequestBtn.setEnabled(false);

				JFrame moreInfoFrame = new JFrame("Expanded Customer Information");
				moreInfoFrame.setVisible(true);
				centerFrame(moreInfoFrame);
				moreInfoFrame.setResizable(true);
				moreInfoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				moreInfoFrame.setBounds(950, 2500, 950, 250);
				moreInfoFrame.setLocationRelativeTo(null);


				/* Action listener for when the add customer frame is closed */
				moreInfoFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						moreInfoBtn.setEnabled(true);
						addRequestBtn.setEnabled(true);
					}
				});

				JPanel moreInfoPanel = new JPanel();
				centerFrame(moreInfoPanel);
				moreInfoPanel.setLayout(null);

				JLabel storeLabel = new JLabel("Store");
				storeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				storeLabel.setBounds(28, 25, 78, 23);
				moreInfoPanel.add(storeLabel);

				JLabel fNameLabel = new JLabel("First Name");
				fNameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				fNameLabel.setBounds(214, 25, 78, 23);
				moreInfoPanel.add(fNameLabel);

				JLabel lNameLabel = new JLabel("Last Name");
				lNameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				lNameLabel.setBounds(403, 25, 151, 23);
				moreInfoPanel.add(lNameLabel);

				JLabel addressLabel = new JLabel("Address");
				addressLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				addressLabel.setBounds(592, 25, 78, 23);
				moreInfoPanel.add(addressLabel);

				JLabel cityLabel = new JLabel("City");
				cityLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				cityLabel.setBounds(781, 25, 78, 23);
				moreInfoPanel.add(cityLabel);

				JLabel stateLabel = new JLabel("State");
				stateLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				stateLabel.setBounds(28, 86, 151, 23);
				moreInfoPanel.add(stateLabel);

				JLabel zipLabel = new JLabel("Zip");
				zipLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				zipLabel.setBounds(214, 86, 78, 23);
				moreInfoPanel.add(zipLabel);

				JLabel phone1Label = new JLabel("Phone #1");
				phone1Label.setFont(new Font("Tahoma", Font.BOLD, 14));
				phone1Label.setBounds(403, 86, 78, 23);
				moreInfoPanel.add(phone1Label);

				JLabel phone2Label = new JLabel("Phone #2");
				phone2Label.setFont(new Font("Tahoma", Font.BOLD, 14));
				phone2Label.setBounds(592, 86, 78, 23);
				moreInfoPanel.add(phone2Label);

				JLabel emailLabel = new JLabel("Email");
				emailLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				emailLabel.setBounds(781, 86, 78, 23);
				moreInfoPanel.add(emailLabel);


				JComboBox<?> storeField = new JComboBox<Object>(stores);
				storeField.setBounds(28, 48, 136, 20);
				storeField.setSelectedIndex(0);
				moreInfoPanel.add(storeField);

				JTextField fNameField = new JTextField();
				fNameField.setBounds(214, 48, 136, 20);
				moreInfoPanel.add(fNameField);
				fNameField.setColumns(10);

				JTextField lNameField = new JTextField();
				lNameField.setColumns(10);
				lNameField.setBounds(403, 48, 136, 20);
				moreInfoPanel.add(lNameField);
				moreInfoFrame.add(moreInfoPanel);

				JTextField addressField = new JTextField();
				addressField.setBounds(592, 48, 136, 20);
				moreInfoPanel.add(addressField);
				addressField.setColumns(10);

				JTextField cityField = new JTextField();
				cityField.setColumns(10);
				cityField.setBounds(781, 48, 136, 20);
				moreInfoPanel.add(cityField);

				JTextField stateField = new JTextField();
				stateField.setColumns(10);
				stateField.setBounds(28, 109, 136, 20);
				moreInfoPanel.add(stateField);

				JTextField zipField = new JTextField();
				zipField.setColumns(10);
				zipField.setBounds(214, 109, 136, 20);
				moreInfoPanel.add(zipField);
				moreInfoFrame.add(moreInfoPanel);

				JTextField phone1Field = new JTextField();
				phone1Field.setColumns(10);
				phone1Field.setBounds(403, 109, 136, 20);
				moreInfoPanel.add(phone1Field);
				moreInfoFrame.add(moreInfoPanel);

				JTextField phone2Field = new JTextField();
				phone2Field.setColumns(10);
				phone2Field.setBounds(592, 109, 136, 20);
				moreInfoPanel.add(phone2Field);
				moreInfoFrame.add(moreInfoPanel);

				JTextField emailField = new JTextField();
				emailField.setColumns(10);
				emailField.setBounds(781, 109, 136, 20);
				moreInfoPanel.add(emailField);
				moreInfoFrame.add(moreInfoPanel);

				JButton updateBtn = new JButton("Update Contact Info");
				updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
				centerComponentHorizontal(moreInfoPanel, updateBtn, 170, 170, 25);
				moreInfoPanel.add(updateBtn);

				storeField.setSelectedItem(customer2D[0][0]);
				lNameField.setText(customer2D[0][1]);
				fNameField.setText(customer2D[0][2]);
				addressField.setText(customer2D[0][3]);
				cityField.setText(customer2D[0][4]);
				stateField.setText(customer2D[0][5]);
				zipField.setText(customer2D[0][6]);
				phone1Field.setText(customer2D[0][7]);
				phone2Field.setText(customer2D[0][8]);
				emailField.setText(customer2D[0][9]);

				updateBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						try {
							control.updateCustomerFull((String) storeField.getSelectedItem(), lNameField.getText(), fNameField.getText(), addressField.getText(), cityField.getText(), stateField.getText(), zipField.getText(), phone1Field.getText(), phone2Field.getText(), emailField.getText(), Integer.parseInt((String) customerTable.getValueAt(customerTable.getSelectedRow(), 4)));

							createMessage(moreInfoFrame, moreInfoPanel, "Customer Information Updated Successfully!");
							reportsData = control.getRequests(
									customerTable.getValueAt(customerTable.getSelectedRow(), customerCodeColumn).toString());
							reportsModel.setDataVector(reportsData, reportColumns);
							//titleTable.setModel(reportsModel);

							customersData = control.getCustomers();
							customersModel.setDataVector(customersData, customerColumns);
							customerTable.setModel(customersModel);
							customerTable.getTableHeader().setReorderingAllowed(false);
							customerTable.getColumnModel().getColumn(4).setMinWidth(0); // Must be set before maxWidth!!
							customerTable.getColumnModel().getColumn(4).setMaxWidth(0);
							customerTable.getColumnModel().getColumn(4).setWidth(0);


						} catch (Exception e) {
							createMessage(moreInfoFrame, moreInfoPanel, "Customer Information could not be updated.");
						}
					}
				});


			}
		});


		/* Action listener for discard changes btn */
		discardBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lNameBox.setEditable(false);
				fNameBox.setEditable(false);
				phoneBox.setEditable(false);
				emailBox.setEditable(false);
				ccodeBox.setEditable(false);

				discardBtn.setEnabled(false);
				saveCustBtn.setEnabled(false);

				int i = customerTable.getSelectedRow();
				lNameBox.setText((String) customerTable.getValueAt(i, 0));
				fNameBox.setText((String) customerTable.getValueAt(i, 1));
				phoneBox.setText((String) customerTable.getValueAt(i, 2));
				emailBox.setText((String) customerTable.getValueAt(i, 3));
				ccodeBox.setText((String) customerTable.getValueAt(i, 4));

			}
		});

		/* Action listener for account delete button */
		delCustBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				/* If they want to delete an account */
				int reply = JOptionPane.showConfirmDialog(null,
						"Are you sure you would like to delete the selected account from the system? This can't be undone.",
						"Delete User", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {

					control.deleteCustomer(Integer.parseInt(ccodeBox.getText()));
					customersData = control.getCustomers();
					customersModel.setDataVector(customersData, customerColumns);
					customerTable.setModel(customersModel);
					customerTable.getTableHeader().setReorderingAllowed(false);
					customerTable.getColumnModel().getColumn(4).setMinWidth(0); // Must be set before maxWidth!!
					customerTable.getColumnModel().getColumn(4).setMaxWidth(0);
					customerTable.getColumnModel().getColumn(4).setWidth(0);

					JOptionPane.showMessageDialog(null, "Account Deleted");
				}
			}
		});
	}

	/**
	 * Handler for adding the request handler and populating the info box.
	 */
	private void addRequestHandler() {
		addRequestBtn = new JButton("Add Request");
		addRequestBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		addRequestBtn.setBounds(860, 339, 107, 48);
		addRequestBtn.setEnabled(false);
		add(addRequestBtn);
			    
		addRequestBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (addRequestBtn.isEnabled()) {

					String[] stores = {"dl1", "dl2"};
					String[][] titles2D = control.getIndividualTitles();
					String[] titles = new String[titles2D.length];

					for (int i = 0; i < titles2D.length; i++) {
						titles[i] = titles2D[i][0];
					}

					addRequestBtn.setEnabled(false);
					JFrame addRequestFrame = new JFrame("Add Request");
					addRequestFrame.setVisible(true);
					centerFrame(addRequestFrame);
					addRequestFrame.setResizable(false);
					addRequestFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					addRequestFrame.setBounds(400, 300, 700, 300);

					/* Action listener for when the add customer frame is closed */
					addRequestFrame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							addRequestBtn.setEnabled(true);
						}
					});

					JPanel addRequestPanel = new JPanel();
					centerFrame(addRequestPanel);
					addRequestPanel.setLayout(null);

					JLabel storeLabel = new JLabel("Store");
					storeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					storeLabel.setBounds(28, 25, 78, 23);
					addRequestPanel.add(storeLabel);

					JLabel titleLabel = new JLabel("Title");
					titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					titleLabel.setBounds(214, 25, 78, 23);
					addRequestPanel.add(titleLabel);

					JLabel commentLabel = new JLabel("Comments");
					commentLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					commentLabel.setBounds(28, 86, 151, 23);
					addRequestPanel.add(commentLabel);

					JLabel issueStartLabel = new JLabel("Issue Start");
					issueStartLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					issueStartLabel.setBounds(28, 138, 78, 23);
					addRequestPanel.add(issueStartLabel);

					JLabel issueEndLabel = new JLabel("Issue End");
					issueEndLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					issueEndLabel.setBounds(214, 138, 151, 23);
					addRequestPanel.add(issueEndLabel);

					JLabel quantityLabel = new JLabel("Quantity");
					quantityLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					quantityLabel.setBounds(408, 138, 151, 23);
					addRequestPanel.add(quantityLabel);

					JLabel costLabel = new JLabel("Cost");
					costLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					costLabel.setBounds(28, 200, 78, 23);
					addRequestPanel.add(costLabel);

					JComboBox<?> storeField = new JComboBox<Object>(stores);
					storeField.setBounds(28, 48, 136, 20);
					storeField.setSelectedIndex(0);
					addRequestPanel.add(storeField);

					JComboBox<?> titleField = new JComboBox<Object>(titles);
					titleField.setBounds(214, 48, 400, 20);
					titleField.setSelectedIndex(0);
					addRequestPanel.add(titleField);

					JTextField commentField = new JTextField();
					commentField.setColumns(10);
					commentField.setBounds(28, 109, 644, 20);
					addRequestPanel.add(commentField);
					addRequestFrame.add(addRequestPanel);

					JTextField issueStartField = new JTextField();
					issueStartField.setBounds(28, 160, 136, 20);
					addRequestPanel.add(issueStartField);
					issueStartField.setColumns(10);

					JTextField issueEndField = new JTextField();
					issueEndField.setColumns(10);
					issueEndField.setBounds(214, 160, 136, 20);
					addRequestPanel.add(issueEndField);

					JTextField quantityField = new JTextField();
					quantityField.setColumns(10);
					quantityField.setBounds(408, 160, 136, 20);
					addRequestPanel.add(quantityField);

					JTextField costField = new JTextField();
					costField.setColumns(10);
					costField.setBounds(28, 221, 136, 20);
					addRequestPanel.add(costField);
					addRequestFrame.add(addRequestPanel);

					JButton addBtn = new JButton("Add Request");
					addBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
					addBtn.setBounds(214, 220, 140, 25);
					//centerComponentHorizontal(addRequestPanel, addBtn, addRequestPanel.getHeight() - 75, 140, 25);
					addRequestPanel.add(addBtn);

					addBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							try {
								if (commentField.getText().equals("")) {
									commentField.setText(null);
								}
								if (issueStartField.getText().equals("")) {
									issueStartField.setText("-1");
								}
								if (issueEndField.getText().equals("")) {
									issueEndField.setText("-1");
								}
								if (quantityField.getText().equals("")) {
									quantityField.setText("-1");
								}
								if (costField.getText().equals("")) {
									costField.setText("-1");
								}

								control.addRequest((String) storeField.getSelectedItem(), customerTable.getValueAt(customerTable.getSelectedRow(), customerCodeColumn).toString(),
										(String) titleField.getSelectedItem(), commentField.getText(), Integer.parseInt(issueStartField.getText()), Integer.parseInt(issueEndField.getText()),
										Integer.parseInt(quantityField.getText()), Float.parseFloat(costField.getText()));
								createMessage(addRequestFrame, addRequestPanel, "Request successfully created!");
								reportsData = control.getRequests(
										customerTable.getValueAt(customerTable.getSelectedRow(), customerCodeColumn).toString());

								reportsModel.setDataVector(reportsData, reportColumns);
								titleTable.setModel(reportsModel);

								titleTable.getColumnModel().getColumn(0).setPreferredWidth(3);
								titleTable.getColumnModel().getColumn(1).setPreferredWidth(200);
								titleTable.getColumnModel().getColumn(2).setPreferredWidth(3);
								titleTable.getColumnModel().getColumn(3).setPreferredWidth(3);
								titleTable.getColumnModel().getColumn(4).setMinWidth(0); // Must be set before maxWidth!!
								titleTable.getColumnModel().getColumn(4).setMaxWidth(0);
								titleTable.getColumnModel().getColumn(4).setWidth(0);
								titleTable.getColumnModel().getColumn(5).setPreferredWidth(3);
							} catch (Exception e) {
								createMessage(addRequestFrame, addRequestPanel, "Request could not be created.");
							}
						}
					});

				}
			}
		});
	}
	
	/**
	 * Handler for editing or deleting the customers order.
	 */
	private void editDeleteOrderHandler() {
		editDelSelectedBtn = new JButton("Edit/delete Selected");
		editDelSelectedBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		editDelSelectedBtn.setBounds(860, 411, 107, 48);
		editDelSelectedBtn.setEnabled(false);
		add(editDelSelectedBtn);
			    
		editDelSelectedBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (editDelSelectedBtn.isEnabled()) {
					String[] orderData = control.getOrder(Integer.parseInt((String) titleTable.getValueAt(titleTable.getSelectedRow(), orderIdcolumn)));					
					String storeCode = orderData[0];
					String customerCode = orderData[1];
					//String title = orderData[2]; unused
					String comments = orderData[3];
					int issueStart = Integer.parseInt(orderData[4]);
					int issueEnd = Integer.parseInt(orderData[5]);
					int quantity = Integer.parseInt(orderData[6]);
					float cost = Float.parseFloat(orderData[7]);
					int id = Integer.parseInt(orderData[8]);
					int titleIndex = 0;
					int storeIndex = 0;
					
					String[] stores = {"dl1", "dl2"};
					String[][] titles2D = control.getIndividualTitles();
					String[] titles = new String[titles2D.length];
					
					for (int i = 0; i < stores.length; i++) {
						if (stores[i].equalsIgnoreCase(storeCode)) {
							storeIndex = i;
							break;
						}
					}
	
					for (int i = 0; i < titles2D.length; i++) {
						if (titles2D[i][0].equalsIgnoreCase(title)) {
							titleIndex = i;
						}
						titles[i] = titles2D[i][0];
					}

					addRequestBtn.setEnabled(false);
					JFrame addRequestFrame = new JFrame("Add Request");
					addRequestFrame.setVisible(true);
					centerFrame(addRequestFrame);
					addRequestFrame.setResizable(false);
					addRequestFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					addRequestFrame.setBounds(400, 300, 700, 300);

					/* Action listener for when the add customer frame is closed */
					addRequestFrame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							addRequestBtn.setEnabled(true);
						}
					});

					JPanel addRequestPanel = new JPanel();
					centerFrame(addRequestPanel);
					addRequestPanel.setLayout(null);

					JLabel storeLabel = new JLabel("Store");
					storeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					storeLabel.setBounds(28, 25, 78, 23);
					addRequestPanel.add(storeLabel);

					JLabel titleLabel = new JLabel("Title");
					titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					titleLabel.setBounds(214, 25, 78, 23);
					addRequestPanel.add(titleLabel);

					JLabel commentLabel = new JLabel("Comments");
					commentLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					commentLabel.setBounds(28, 86, 151, 23);
					addRequestPanel.add(commentLabel);

					JLabel issueStartLabel = new JLabel("Issue Start");
					issueStartLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					issueStartLabel.setBounds(28, 138, 78, 23);
					addRequestPanel.add(issueStartLabel);

					JLabel issueEndLabel = new JLabel("Issue End");
					issueEndLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					issueEndLabel.setBounds(214, 138, 151, 23);
					addRequestPanel.add(issueEndLabel);

					JLabel quantityLabel = new JLabel("Quantity");
					quantityLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					quantityLabel.setBounds(408, 138, 151, 23);
					addRequestPanel.add(quantityLabel);

					JLabel costLabel = new JLabel("Cost");
					costLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					costLabel.setBounds(28, 200, 78, 23);
					addRequestPanel.add(costLabel);

					JComboBox<?> storeField = new JComboBox<Object>(stores);
					storeField.setBounds(28, 48, 136, 20);
					storeField.setSelectedIndex(storeIndex);
					addRequestPanel.add(storeField);

					JComboBox<?> titleField = new JComboBox<Object>(titles);
					titleField.setBounds(214, 48, 400, 20);
					titleField.setSelectedIndex(titleIndex);
					addRequestPanel.add(titleField);

					JTextField commentField = new JTextField(comments);
					commentField.setColumns(10);
					commentField.setBounds(28, 109, 644, 20);
					addRequestPanel.add(commentField);
					addRequestFrame.add(addRequestPanel);

					JTextField issueStartField = new JTextField(String.valueOf(issueStart));
					issueStartField.setBounds(28, 160, 136, 20);
					addRequestPanel.add(issueStartField);
					issueStartField.setColumns(10);

					JTextField issueEndField = new JTextField(String.valueOf(issueEnd));
					issueEndField.setColumns(10);
					issueEndField.setBounds(214, 160, 136, 20);
					addRequestPanel.add(issueEndField);

					JTextField quantityField = new JTextField(String.valueOf(quantity));
					quantityField.setColumns(10);
					quantityField.setBounds(408, 160, 136, 20);
					addRequestPanel.add(quantityField);

					JTextField costField = new JTextField(String.valueOf(cost));
					costField.setColumns(10);
					costField.setBounds(28, 221, 136, 20);
					addRequestPanel.add(costField);
					addRequestFrame.add(addRequestPanel);

					JButton editBtn = new JButton("Edit Request");
					editBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
					editBtn.setBounds(214, 220, 140, 25);
					addRequestPanel.add(editBtn);
					
					JButton deleteBtn = new JButton("Delete Request");
					deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
					deleteBtn.setBounds(407, 220, 140, 25);
					addRequestPanel.add(deleteBtn);
					
					deleteBtn.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							control.deleteOrder(id);
							createMessage(addRequestFrame, addRequestPanel, "Request deleted successfully!");
							reportsData = control.getRequests(
									customerTable.getValueAt(customerTable.getSelectedRow(), customerCodeColumn).toString());

							reportsModel.setDataVector(reportsData, reportColumns);
							titleTable.setModel(reportsModel);

							titleTable.getColumnModel().getColumn(0).setPreferredWidth(3);
							titleTable.getColumnModel().getColumn(1).setPreferredWidth(200);
							titleTable.getColumnModel().getColumn(2).setPreferredWidth(3);
							titleTable.getColumnModel().getColumn(3).setPreferredWidth(3);
							titleTable.getColumnModel().getColumn(4).setMinWidth(0); // Must be set before maxWidth!!
							titleTable.getColumnModel().getColumn(4).setMaxWidth(0);
							titleTable.getColumnModel().getColumn(4).setWidth(0);
							titleTable.getColumnModel().getColumn(5).setPreferredWidth(3);
						}
						
					});

					editBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							try {
								if (commentField.getText().equals("")) {
									commentField.setText(null);
								}
								if (issueStartField.getText().equals("")) {
									issueStartField.setText("-1");
								}
								if (issueEndField.getText().equals("")) {
									issueEndField.setText("-1");
								}
								if (quantityField.getText().equals("")) {
									quantityField.setText("-1");
								}
								if (costField.getText().equals("")) {
									costField.setText("-1");
								}

								control.updateOrder((String) storeField.getSelectedItem(), customerCode,
										(String) titleField.getSelectedItem(), commentField.getText(), Integer.parseInt(issueStartField.getText()), Integer.parseInt(issueEndField.getText()),
										Integer.parseInt(quantityField.getText()), Float.parseFloat(costField.getText()), id);
								createMessage(addRequestFrame, addRequestPanel, "Request updated successfully!");
								reportsData = control.getRequests(
										customerTable.getValueAt(customerTable.getSelectedRow(), customerCodeColumn).toString());

								reportsModel.setDataVector(reportsData, reportColumns);
								titleTable.setModel(reportsModel);

								titleTable.getColumnModel().getColumn(0).setPreferredWidth(3);
								titleTable.getColumnModel().getColumn(1).setPreferredWidth(200);
								titleTable.getColumnModel().getColumn(2).setPreferredWidth(3);
								titleTable.getColumnModel().getColumn(3).setPreferredWidth(3);
								titleTable.getColumnModel().getColumn(4).setMinWidth(0); // Must be set before maxWidth!!
								titleTable.getColumnModel().getColumn(4).setMaxWidth(0);
								titleTable.getColumnModel().getColumn(4).setWidth(0);
								titleTable.getColumnModel().getColumn(5).setPreferredWidth(3);
							} catch (Exception e) {
								createMessage(addRequestFrame, addRequestPanel, "Request could not be created.");
							}
						}
					});

				}
			}
		});
	}

	/**
	 * Handler to center a JComponent.
	 * 
	 * @param frame     The components frame.
	 * @param component The component to center.
	 * @param y         The y coordinate.
	 * @param width     The component width.
	 * @param height    The component height.
	 */
	private void centerComponentHorizontal(JComponent frame, JComponent component, int y, int width, int height) {
		component.setBounds((frame.getWidth() - width) / 2, y, width, height);
	}

	/**
	 * Handler for center the frame on the screen.
	 *
	 * @param frame  The frame to center.
	 * @param width  the frame width.
	 * @param height frame height.
	 */
	@SuppressWarnings("unused")//these methods are only visible locally, because of private tag
	private void centerFrame(JComponent frame, int width, int height) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);
	}

	/**
	 * Handler for center the frame on the screen.
	 * 
	 * @param frame The frame to center.
	 */
	private void centerFrame(JComponent frame) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(dim.width / 2 - 400 / 2, dim.height / 2 - 250 / 2, 400, 225);
	}

	/**
	 * Handler for center the frame on the screen.
	 *
	 * @param frame  The frame to center.
	 * @param width  the frame width.
	 * @param height frame height.
	 */
	@SuppressWarnings("unused")//these methods are only visible locally, because of private tag
	private void centerFrame(JFrame frame, int width, int height) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);
	}

	/**
	 * Handler for center the frame on the screen.
	 * 
	 * @param frame The frame to center.
	 */
	private void centerFrame(JFrame frame) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(dim.width / 2 - 400 / 2, dim.height / 2 - 250 / 2, 400, 225);
	}

	/**
	 * Handler to create a successful message.
	 * 
	 * @param frame     The component frame.
	 * @param component The component to display the message.
	 * @param message   The message to display.
	 */
	private void createMessage(JFrame frame, JComponent component, String message) {
		int x = component.getX();
		int y = component.getY();
		int width = component.getWidth();
		int height = component.getHeight();

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(x, y, width, height);
		panel.setBackground(color);
		component.setVisible(false);
		frame.add(panel);

		JTextPane pane = new JTextPane();
		pane.setBounds(x, height / 2 - font.getSize(), width, height);
		pane.setBackground(color);
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
		StyleConstants.setFontFamily(attribs, font.getFamily());
		StyleConstants.setFontSize(attribs, font.getSize());
		StyleConstants.setBold(attribs, true);
		pane.setParagraphAttributes(attribs, true);
		pane.setText(message);
		panel.add(pane);
	}
	
}
