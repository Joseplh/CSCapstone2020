package main.java;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

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
	private Controller control;

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

		JButton addRequestBtn = new JButton("Add Request");
		addRequestBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		addRequestBtn.setBounds(860, 339, 107, 48);
		add(addRequestBtn);

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
		add(customerDetails);

		/* Dummy Data */
		String data[][] = control.getCustomers();

		/* Customer Table Column Names */
		String column[] = { "Last Name", "First Name", "Phone Number", "Email" };

		/* Adding the scroll bar to the customer Table */
		JScrollPane customerScrollPane = new JScrollPane();
		customerScrollPane.setBounds(10, 65, 415, 590);
		add(customerScrollPane);
		
		/* TODO: initialize control variable and query for 
		 * 		 the 4 columns above. Get String[][] 
		 * 		 and put it in below instead of the dummy data
		 *
		 */
		
		control = new Controller();
		control.select("SELECT * FROM DLC.dbo.Customer");
		
		customerTable = new JTable(data, column);
		customerTable.setAutoCreateRowSorter(true);
		customerScrollPane.setViewportView(customerTable);

		titleTable = new JTable();
		titleTable.setBounds(455, 288, 395, 367);
		add(titleTable);

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

							/* TODO: Code to add customer account to database 
							 */

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

					JOptionPane.showMessageDialog(null, "Changes have been saved!", "Message",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		});

		/* Listener for when a cell is selected from the customerTable */
		customerTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

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

				discardBtn.setEnabled(false);
				saveCustBtn.setEnabled(false);

				int i = customerTable.getSelectedRow();
				lNameBox.setText((String) customerTable.getValueAt(i, 0));
				fNameBox.setText((String) customerTable.getValueAt(i, 1));
				phoneBox.setText((String) customerTable.getValueAt(i, 2));
				emailBox.setText((String) customerTable.getValueAt(i, 3));
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

					/* Code here to remove the user from the database */

					JOptionPane.showMessageDialog(null, "Account Deleted");
				}
			}
		});
	}
}
