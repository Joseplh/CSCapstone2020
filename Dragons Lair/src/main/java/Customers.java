package main.java;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.*;

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
	
	private int lastnameCodeColumn = 0;
	private int firstnameCodeColumn = 1;
	private int customerCodeColumn = 4;
	private int storeCodeColumn;

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

		JButton editDelSelectedBtn = new JButton("Edit/delete Selected");
		editDelSelectedBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		editDelSelectedBtn.setBounds(860, 411, 107, 48);
		add(editDelSelectedBtn);

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
		String data[][] = control.getCustomers();

		/* Customer Table Column Names */
		String column[] = {"Last Name", "First Name", "Phone Number", "Email", "Customer Code"};

		/* Adding the scroll bar to the customer Table */
		JScrollPane customerScrollPane = new JScrollPane();
		customerScrollPane.setBounds(10, 65, 415, 590);
		add(customerScrollPane);

		customerTable = new JTable(data, column);
		customerTable.setAutoCreateRowSorter(true);
		customerScrollPane.setViewportView(customerTable);
		customerTable.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane reportsScrollPane = new JScrollPane();
		reportsScrollPane.setBounds(455, 288, 395, 367);
		add(reportsScrollPane);
		
		reportColumns = new String[] {"Store Code", "Description", "Issue Start", "Issue End", "Quantity"};
		reportsModel = new DefaultTableModel(reportsData, reportColumns);
		titleTable = new JTable(reportsModel);
		titleTable.setAutoCreateRowSorter(true);
		titleTable.getTableHeader().setReorderingAllowed(false);
		reportsScrollPane.setViewportView(titleTable);

		/* Action listener for the add account button */
		AddCustBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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

							/*
							 * TODO: Code to add customer account to database
							 */
							control.insertCustomer(fnameAccField.getText(), lnameAccField.getText(),
									emailAccField.getText(), phoneAccField.getText());
								

							JPanel accAddedPanel = new JPanel();
							accAddedPanel.setLayout(null);
							accAddedPanel.setBounds(572, 91, 388, 207);
							accAddedPanel.setBackground(new Color(240, 240, 240));
							accPanel.setVisible(false);
							newAcc.add(accAddedPanel);

							/* Code to do something if the account is not added */

							JTextArea txtrAccountHasBeen = new JTextArea();
							txtrAccountHasBeen.setEditable(false);
							txtrAccountHasBeen.setBackground(new Color(240, 240, 240));
							txtrAccountHasBeen.setText("Account has been added!");
							txtrAccountHasBeen.setBounds(99, 87, 194, 33);
							accAddedPanel.add(txtrAccountHasBeen);
							AddCustBtn.setEnabled(true);

						}
					});

				}
			}
		});

		/* Action listener for when the "Save Changes" button is clicked */
		saveCustBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				/* If the save button has been clicked and it is enabled */
				if (saveCustBtn.isEnabled()) {

					/* TODO: Code here to pull the new info and update database */

					saveCustBtn.setEnabled(false);
					editCustBtn.setEnabled(false);
					discardBtn.setEnabled(false);

					lNameBox.setEditable(false);
					fNameBox.setEditable(false);
					phoneBox.setEditable(false);
					emailBox.setEditable(false);
					ccodeBox.setEditable(false);

					control.updateCustomer(Integer.parseInt(ccodeBox.getText()), fNameBox.getText(), lNameBox.getText(), emailBox.getText(), phoneBox.getText());

					JOptionPane.showMessageDialog(null, "Changes have been saved!", "Message",
							JOptionPane.PLAIN_MESSAGE);
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
		/* Listener for when a cell is selected from the customerTable */
		customerTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				addRequestBtn.setEnabled(true);
				reportsData = control.getRequests(
						customerTable.getValueAt(customerTable.getSelectedRow(), customerCodeColumn).toString()
				);
				
				reportsModel.setDataVector(reportsData, reportColumns);
				titleTable.setModel(reportsModel);

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

		/* action listener for when the edit button for customer info is clicked */
		editCustBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				lNameBox.setEditable(true);
				fNameBox.setEditable(true);
				phoneBox.setEditable(true);
				emailBox.setEditable(true);

				saveCustBtn.setEnabled(true);
				discardBtn.setEnabled(true);

			}
		});
		/* Action listener for discard changes btn */
		discardBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		delCustBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				/* If they want to delete an account */
				int reply = JOptionPane.showConfirmDialog(null,
						"Are you sure you would like to delete the selected account from the system? This can't be undone.",
						"Delete User", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {

					control.deleteCustomer(Integer.parseInt(ccodeBox.getText()));
					customerTable.revalidate();

					// refresh jtable please

					/* Code here to remove the user from the database */

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
			    
		addRequestBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (addRequestBtn.isEnabled()) {

					addRequestBtn.setEnabled(false);
					JFrame addRequestFrame = new JFrame("Add Request");
					addRequestFrame.setVisible(true);
					centerFrame(addRequestFrame);
					addRequestFrame.setResizable(false);
					addRequestFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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

					JLabel titleLabel = new JLabel("Title");
					titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					titleLabel.setBounds(28, 25, 78, 23);
					addRequestPanel.add(titleLabel);

					JLabel quantityLabel = new JLabel("Quantity");
					quantityLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					quantityLabel.setBounds(214, 25, 78, 23);
					addRequestPanel.add(quantityLabel);

					JLabel issueLabel = new JLabel("Issue Number");
					issueLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					issueLabel.setBounds(28, 86, 151, 23);
					addRequestPanel.add(issueLabel);
					
					JTextField titleField = new JTextField();
					titleField.setBounds(28, 48, 136, 20);
					addRequestPanel.add(titleField);
					titleField.setColumns(10);

					JTextField quantityField = new JTextField();
					quantityField.setColumns(10);
					quantityField.setBounds(28, 109, 136, 20);
					addRequestPanel.add(quantityField);

					JTextField issueField = new JTextField();
					issueField.setColumns(10);
					issueField.setBounds(214, 48, 136, 20);
					addRequestPanel.add(issueField);
					addRequestFrame.add(addRequestPanel);

					JButton addBtn = new JButton("Add Request");
					addBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));

					centerComponentHorizontal(addRequestPanel, addBtn, addRequestPanel.getHeight() - 75, 140, 25);
					addRequestPanel.add(addBtn);

					addBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							try {
								control.addRequest(customerTable.getValueAt(customerTable.getSelectedRow(), customerCodeColumn).toString(), 
										titleField.getText(), Integer.parseInt(quantityField.getText()),
										Integer.parseInt(issueField.getText()));
								createMessage(addRequestFrame, addRequestPanel, "Request successfully created.");
								reportsData = control.getRequests(
										customerTable.getValueAt(customerTable.getSelectedRow(), customerCodeColumn).toString()
								);
								
								reportsModel.setDataVector(reportsData, reportColumns);
								titleTable.setModel(reportsModel);
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
