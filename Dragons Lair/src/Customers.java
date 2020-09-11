import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class Customers extends JPanel {
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

	public Customers() {
		setLayout(null);
		setBackground(Color.LIGHT_GRAY);
		
		
		JPanel customerDetails = new JPanel();
		customerDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
		customerDetails.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Customer Details", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
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
		String data[][] = { { "Jesse", "Schrack", "40234454", "jschrack@unomaha.edu" },
				{ "John", "Doe", "403499504", "jdoe@unomaha.edu" },
				{ "John1", "Doe1", "403499504", "jdoe1@unomaha.edu" },
				{ "John2", "Doe2", "403499504", "jdoe2@unomaha.edu" },
				{ "John3", "Doe3", "403499504", "jdoe3@unomaha.edu" },
				{ "John4", "Doe4", "403499504", "jdoe4@unomaha.edu" },
				{ "John5", "Doe5", "403499504", "jdoe5@unomaha.edu" },
				{ "John6", "Doe6", "403499504", "jdoe6@unomaha.edu" },
				{ "John7", "Doe7", "403499504", "jdoe7@unomaha.edu" },
				{ "John", "Doe", "403499504", "jdoe@unomaha.edu" },
				{ "John1", "Doe1", "403499504", "jdoe1@unomaha.edu" },
				{ "John2", "Doe2", "403499504", "jdoe2@unomaha.edu" },
				{ "John3", "Doe3", "403499504", "jdoe3@unomaha.edu" },
				{ "John4", "Doe4", "403499504", "jdoe4@unomaha.edu" },
				{ "John5", "Doe5", "403499504", "jdoe5@unomaha.edu" },
				{ "John6", "Doe6", "403499504", "jdoe6@unomaha.edu" },
				{ "John7", "Doe7", "403499504", "jdoe7@unomaha.edu" },
				{ "John", "Doe", "403499504", "jdoe@unomaha.edu" },
				{ "John1", "Doe1", "403499504", "jdoe1@unomaha.edu" },
				{ "John2", "Doe2", "403499504", "jdoe2@unomaha.edu" },
				{ "John3", "Doe3", "403499504", "jdoe3@unomaha.edu" },
				{ "John4", "Doe4", "403499504", "jdoe4@unomaha.edu" },
				{ "John5", "Doe5", "403499504", "jdoe5@unomaha.edu" },
				{ "John6", "Doe6", "403499504", "jdoe6@unomaha.edu" },
				{ "John7", "Doe7", "403499504", "jdoe7@unomaha.edu" },
				{ "John", "Doe", "403499504", "jdoe@unomaha.edu" },
				{ "John1", "Doe1", "403499504", "jdoe1@unomaha.edu" },
				{ "John2", "Doe2", "403499504", "jdoe2@unomaha.edu" },
				{ "John3", "Doe3", "403499504", "jdoe3@unomaha.edu" },
				{ "John4", "Doe4", "403499504", "jdoe4@unomaha.edu" },
				{ "John5", "Doe5", "403499504", "jdoe5@unomaha.edu" },
				{ "John6", "Doe6", "403499504", "jdoe6@unomaha.edu" },
				{ "John7", "Doe7", "403499504", "jdoe7@unomaha.edu" },
				{ "John8", "Doe8", "403499504", "jdoe8@unomaha.edu" } };
		
		
		/* Customer Table Column Names */
		String column[] = { "Last Name", "First Name", "Phone Number", "Email" };
		
		
		/* Adding the scroll bar to the customer Table */
		JScrollPane customerScrollPane = new JScrollPane();
		customerScrollPane.setBounds(10, 65, 415, 590);
		add(customerScrollPane);

		customerTable = new JTable(data, column);
		customerTable.setAutoCreateRowSorter(true);
		customerScrollPane.setViewportView(customerTable);
		
		
		
		titleTable = new JTable();
		titleTable.setBounds(455, 288, 395, 367);
		add(titleTable);
		

		/* Action listener for when the "Save Changes" button is clicked */
		saveCustBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				/* If the save button has been clicked and it is enabled */
				if(saveCustBtn.isEnabled()) {
					
					
					/* Code here to pull the new info and update database */
					
					saveCustBtn.setEnabled(false);
					editCustBtn.setEnabled(false);
					discardBtn.setEnabled(false);
					
					lNameBox.setEditable(false);
					fNameBox.setEditable(false);
					phoneBox.setEditable(false);
					emailBox.setEditable(false);
					
					JOptionPane.showMessageDialog(null,
						    "Changes have been saved!",
						    "Message",
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
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to delete the selected account from the system? This can't be undone.", "Delete User", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					
					/* Code here to remove the user from the database */
					
					
				    JOptionPane.showMessageDialog(null, "Account Deleted");
				}
			}
		});
	}
}
