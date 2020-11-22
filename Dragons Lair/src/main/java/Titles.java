package main.java;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Titles extends JPanel implements Tile {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable titleTable;
	private Controller control;
	private JTextField titleField;
	private JTextField disctSubField;
	private JTextField distributorField;
	private JTextField tCodeField;
	private String[][] data;

	private String[][] fetchData(){
		return data = control.getTitles();
	}

	public Titles(Controller control) {
		this.control = control;
		setLayout(null);
		setBackground(Color.LIGHT_GRAY);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.LIGHT_GRAY);
		titlePanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Title Details", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		titlePanel.setBounds(505, 117, 459, 168);
		add(titlePanel);
		titlePanel.setLayout(null);

		/* Button deceleration */
		JButton addTitleBtn = new JButton("Add New Title");
		addTitleBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		addTitleBtn.setBounds(10, 11, 180, 41);
		add(addTitleBtn);

		JButton btnResetNewWeek = new JButton("Reset New Week Flags?");
		btnResetNewWeek.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnResetNewWeek.setBounds(289, 11, 180, 41);
		add(btnResetNewWeek);

		JButton editTitleBtn = new JButton("Edit");
		editTitleBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		editTitleBtn.setEnabled(false);
		editTitleBtn.setBounds(505, 63, 162, 33);
		add(editTitleBtn);

		JButton delTitleBtn = new JButton("Delete");
		delTitleBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		delTitleBtn.setEnabled(false);
		delTitleBtn.setBounds(802, 63, 162, 33);
		add(delTitleBtn);

		JButton discardBtn = new JButton("Discard");
		discardBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		discardBtn.setEnabled(false);
		discardBtn.setBounds(755, 313, 107, 33);
		add(discardBtn);

		JButton saveBtn = new JButton("Save");
		saveBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		saveBtn.setEnabled(false);
		saveBtn.setBounds(607, 313, 107, 33);
		add(saveBtn);

		JButton btnExportSingleTitle = new JButton("Export Requested\r\n");
		btnExportSingleTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExportSingleTitle.setEnabled(false);
		btnExportSingleTitle.setBounds(782, 423, 162, 49);
		add(btnExportSingleTitle);

		/* TextArea deceleration */
		JTextArea txtrThisTitleCurrently = new JTextArea();
		txtrThisTitleCurrently.setText("This Title Currently has 24\r\nCustomer Requests");
		txtrThisTitleCurrently.setBackground(Color.LIGHT_GRAY);
		txtrThisTitleCurrently.setBounds(517, 423, 230, 109);
		add(txtrThisTitleCurrently);

		/* TextField deceleration */
		titleField = new JTextField();
		titleField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titleField.setEditable(false);
		titleField.setColumns(10);
		titleField.setBounds(10, 43, 440, 33);

		disctSubField = new JTextField();
		disctSubField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		disctSubField.setEditable(false);
		disctSubField.setColumns(10);
		disctSubField.setBounds(10, 112, 97, 33);

		distributorField = new JTextField();
		distributorField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		distributorField.setEditable(false);
		distributorField.setColumns(10);
		distributorField.setBounds(115, 112, 200, 33);

		tCodeField = new JTextField();
		tCodeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tCodeField.setEditable(false);
		tCodeField.setColumns(10);
		tCodeField.setBounds(331, 112, 100, 33);

		/* Label deceleration */
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitle.setBounds(10, 22, 82, 17);

		JLabel lblDistributor = new JLabel("Distributor");
		lblDistributor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDistributor.setBounds(115, 87, 82, 14);

		JLabel lblDisctSub = new JLabel("Disct? Sub");
		lblDisctSub.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDisctSub.setBounds(10, 87, 82, 14);

		JLabel lblTCode = new JLabel("Catalog ID");
		lblTCode.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTCode.setBounds(331, 87, 82, 14);

		titlePanel.add(titleField);
		titlePanel.add(disctSubField);
		titlePanel.add(distributorField);
		titlePanel.add(tCodeField);
		titlePanel.add(lblTitle);
		titlePanel.add(lblDisctSub);
		titlePanel.add(lblDistributor);
		titlePanel.add(lblTCode);

		/* Title Data */
		data = fetchData();

		/* Title Table Column Names */
		String column[] = { "Title", "Disct? Sub", "Distributor", "Catalog ID"};

		JScrollPane titleScrollPane = new JScrollPane();
		titleScrollPane.setBounds(10, 63, 459, 592);
		add(titleScrollPane);
		
		titleTable = new JTable(data, column);
		titleTable.setAutoCreateRowSorter(true);
		titleTable.getTableHeader().setReorderingAllowed(false);

		titleScrollPane.setViewportView(titleTable);
		
		/* Listener for when a cell is selected from the titleTable */
		titleTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				/*
				 * If the text boxes displaying the title information is editable, warn user
				 * to save the information
				 */
				if (titleField.isEditable()) {
					JOptionPane.showMessageDialog(null,
							"Please save or discard the changes made to the current title", "Save Warning",
							JOptionPane.WARNING_MESSAGE);
				}

				/* Otherwise set the text boxes editable so the data can be changed */
				else {
					int i = titleTable.getSelectedRow();
					titleField.setText((String) titleTable.getValueAt(i, 0));
					disctSubField.setText((String) titleTable.getValueAt(i, 1));
					distributorField.setText((String) titleTable.getValueAt(i,2));
					tCodeField.setText((String) titleTable.getValueAt(i,3));


					editTitleBtn.setEnabled(true);
					delTitleBtn.setEnabled(true);
				}

			}
		});

		addTitleBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (addTitleBtn.isEnabled()) {

					addTitleBtn.setEnabled(false);
					JFrame newTitlePanel = new JFrame("Add New Customer");
					newTitlePanel.setVisible(true);
					newTitlePanel.setBounds(128, 91, 400, 240);
					newTitlePanel.setResizable(false);
					newTitlePanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

					/* Action listener for when the add customer frame is closed */
					newTitlePanel.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							addTitleBtn.setEnabled(true);
						}
					});

					/* Creating the panel that will take new customer info */
					JPanel titlePanel = new JPanel();
					titlePanel.setBounds(128, 91, 388, 207);
					titlePanel.setLayout(null);



					JLabel newTitleLabel = new JLabel("Title");
					newTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					newTitleLabel.setBounds(28, 25, 78, 23);
					titlePanel.add(newTitleLabel);

					JLabel newDistributorLabel = new JLabel("Distributor");
					newDistributorLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					newDistributorLabel.setBounds(28, 76, 151, 23);
					titlePanel.add(newDistributorLabel);

					JLabel newDisctSubLabel = new JLabel("Disct? Sub");
					newDisctSubLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					newDisctSubLabel.setBounds(214, 76, 151, 23);
					titlePanel.add(newDisctSubLabel);

					JLabel newTCodeLabel = new JLabel("Catalog ID");
					newTCodeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					newTCodeLabel.setBounds(28, 116, 151, 23);
					titlePanel.add(newTCodeLabel);



					JTextField newTitleField = new JTextField();
					newTitleField.setBounds(28, 48, 332, 20);
					newTitleField.setColumns(10);
					titlePanel.add(newTitleField);

					JTextField newDistributorField = new JTextField();
					newDistributorField.setBounds(28, 99, 136, 20);
					newDistributorField.setColumns(10);
					titlePanel.add(newDistributorField);

					JTextField newDisctSubField = new JTextField();
					newDisctSubField.setBounds(214, 99, 136, 20);
					newDisctSubField.setColumns(10);
					titlePanel.add(newDisctSubField);

					JTextField newTCodeField = new JTextField();
					newTCodeField.setBounds(28, 139, 136, 20);
					newTCodeField.setColumns(10);
					titlePanel.add(newTCodeField);



					JButton addTitlePanelBtn = new JButton("Add");
					addTitlePanelBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
					addTitlePanelBtn.setBounds(148, 161, 89, 23);
					titlePanel.add(addTitlePanelBtn);



					newTitlePanel.add(titlePanel);



					/* Action listener for when add button clicked on create new customer frame */
					addTitlePanelBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							control.insertTitle(newTitleField.getText(), newDistributorField.getText(), newDisctSubField.getText(), newTCodeField.getText());

							JPanel accAddedPanel = new JPanel();
							accAddedPanel.setLayout(null);
							accAddedPanel.setBounds(572, 91, 388, 207);
							accAddedPanel.setBackground(new Color(240, 240, 240));
							titlePanel.setVisible(false);
							newTitlePanel.add(accAddedPanel);

							/* Code to do something if the account is not added */

							JTextArea txtrAccountHasBeen = new JTextArea();
							txtrAccountHasBeen.setEditable(false);
							txtrAccountHasBeen.setBackground(new Color(240, 240, 240));
							txtrAccountHasBeen.setText("Title has been added!");
							txtrAccountHasBeen.setBounds(99, 87, 194, 33);
							accAddedPanel.add(txtrAccountHasBeen);
							addTitleBtn.setEnabled(true);

						}
					});

				}
			}
		});

		/* Action listener for when the "Save Changes" button is clicked */
		saveBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				/* If the save button has been clicked and it is enabled */
				if(saveBtn.isEnabled()) {

					saveBtn.setEnabled(false);
					editTitleBtn.setEnabled(false);
					discardBtn.setEnabled(false);
					
					titleField.setEditable(false);
					disctSubField.setEditable(false);
					distributorField.setEditable(false);
					tCodeField.setEditable(false);

					control.updateTitle(titleField.getText(), disctSubField.getText(), tCodeField.getText());
					data = fetchData();
					titleTable.revalidate();

					JOptionPane.showMessageDialog(null,
						    "Changes have been saved!",
						    "Message",
						    JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		/* action listener for when the edit button for customer info is clicked */
		editTitleBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				titleField.setEditable(true);
				disctSubField.setEditable(true);

				saveBtn.setEnabled(true);
				discardBtn.setEnabled(true);

			}
		});
		/* Action listener for discard btn */
		discardBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				titleField.setEditable(false);
				disctSubField.setEditable(false);

				
				discardBtn.setEnabled(false);
				saveBtn.setEnabled(false);
				
				int i = titleTable.getSelectedRow();
				titleField.setText((String) titleTable.getValueAt(i, 0));
				disctSubField.setText((String) titleTable.getValueAt(i, 1));

				
			}
		});
		
		/* Action listener for account delete button */
		delTitleBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				/* If they want to delete an account */
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to delete the selected title from the system? This can't be undone.", "Delete Title", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {

					control.deleteTitle(tCodeField.getText());
					
				    JOptionPane.showMessageDialog(null, "Title Deleted");
				}
			}
		});
	}
}
