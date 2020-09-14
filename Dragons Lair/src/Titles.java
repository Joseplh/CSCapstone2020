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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Titles extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable titleTable;

	public Titles() {
		setLayout(null);
		setBackground(Color.LIGHT_GRAY);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Title Details", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_1.setBounds(505, 117, 459, 168);
		add(panel_1);
		panel_1.setLayout(null);

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
		JTextField titleField = new JTextField();
		titleField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titleField.setEditable(false);
		titleField.setColumns(10);
		titleField.setBounds(10, 43, 162, 33);
		panel_1.add(titleField);

		JTextField priceField = new JTextField();
		priceField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priceField.setEditable(false);
		priceField.setColumns(10);
		priceField.setBounds(10, 112, 97, 33);
		panel_1.add(priceField);

		JTextField notesField = new JTextField();
		notesField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		notesField.setEditable(false);
		notesField.setColumns(10);
		notesField.setBounds(216, 112, 230, 33);
		panel_1.add(notesField);

		/* Label deceleration */
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitle.setBounds(10, 22, 29, 17);
		panel_1.add(lblTitle);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrice.setBounds(10, 87, 82, 14);
		panel_1.add(lblPrice);

		JLabel lblSpecialOrderNotes = new JLabel("Special Order Notes");
		lblSpecialOrderNotes.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSpecialOrderNotes.setBounds(216, 87, 149, 14);
		panel_1.add(lblSpecialOrderNotes);

		/* Dummy Data */
		String data[][] = { { "Title 1", "1", "", }, { "Title 1", "1.99", "", }, { "Title 2", "2.88", "", },
				{ "Title 3", "3.00", "", }, { "Title 4", "3", "", }, { "Title 5", "7.8", "", },
				{ "Title 6", "1.9", "", }, { "Title 7", "1.1", "", }, { "Title 78", "1.2", "", },
				{ "Title 101", "1.3", "", }, { "Title 1110", "1.5", "", }, { "Title 1394", "1.5", "", },
				{ "Title 17", "2.99", "Special notes here :", }, { "Title 59", "2.99", "", },
				{ "Title 3", "3.00", "", }, { "Title 4", "3", "", }, { "Title 5", "7.8", "", },
				{ "Title 6", "1.9", "", }, { "Title 7", "1.1", "", }, { "Title 78", "1.2", "", },
				{ "Title 101", "1.3", "", }, { "Title 1110", "1.5", "", }, { "Title 1394", "1.5", "", },
				{ "Title 17", "2.99", "Special notes here :", }, { "Title 59", "2.99", "", },
				{ "Title 3", "3.00", "", }, { "Title 4", "3", "", }, { "Title 5", "7.8", "", },
				{ "Title 6", "1.9", "", }, { "Title 7", "1.1", "", }, { "Title 78", "1.2", "", },
				{ "Title 101", "1.3", "", }, { "Title 1110", "1.5", "", }, { "Title 1394", "1.5", "", },
				{ "Title 17", "2.99", "Special notes here :", }, { "Title 59", "2.99", "", },
				{ "Title 3", "3.00", "", }, { "Title 4", "3", "", }, { "Title 5", "7.8", "", },
				{ "Title 6", "1.9", "", }, { "Title 7", "1.1", "", }, { "Title 78", "1.2", "", },
				{ "Title 101", "1.3", "", }, { "Title 1110", "1.5", "", }, { "Title 1394", "1.5", "", },
				{ "Title 17", "2.99", "Special notes here :", }, { "Title 59", "2.99", "", },
				{ "Title 39", "2.99", "", }, { "Title 106", "2.99", "", } };

		/* Title Table Column Names */
		String column[] = { "Title", "Price", "Special Order Notes" };

		JScrollPane titleScrollPane = new JScrollPane();
		titleScrollPane.setBounds(10, 63, 459, 592);
		add(titleScrollPane);

		titleTable = new JTable(data, column);
		titleTable.setAutoCreateRowSorter(true);

		titleScrollPane.setViewportView(titleTable);
		
		/* Listener for when a cell is selected from the customerTable */
		titleTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				/*
				 * If the text boxes displaying the customer information is editable, warn user
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
					priceField.setText((String) titleTable.getValueAt(i, 1));
					notesField.setText((String) titleTable.getValueAt(i, 2));
					
					editTitleBtn.setEnabled(true);
					delTitleBtn.setEnabled(true);
				}

			}
		});
		
		
		/* Action listener for when the "Save Changes" button is clicked */
		saveBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				/* If the save button has been clicked and it is enabled */
				if(saveBtn.isEnabled()) {
					
					
					
					
					/* Code here to pull the new info and update database */
					
					
					
					saveBtn.setEnabled(false);
					editTitleBtn.setEnabled(false);
					discardBtn.setEnabled(false);
					
					titleField.setEditable(false);
					notesField.setEditable(false);
					priceField.setEditable(false);
					
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
				notesField.setEditable(true);
				priceField.setEditable(true);
				

				saveBtn.setEnabled(true);
				discardBtn.setEnabled(true);

			}
		});
		/* Action listener for discard btn */
		discardBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				titleField.setEditable(false);
				notesField.setEditable(false);
				priceField.setEditable(false);

				
				discardBtn.setEnabled(false);
				saveBtn.setEnabled(false);
				
				int i = titleTable.getSelectedRow();
				titleField.setText((String) titleTable.getValueAt(i, 0));
				priceField.setText((String) titleTable.getValueAt(i, 1));
				notesField.setText((String) titleTable.getValueAt(i, 2));
				
				
			}
		});
		
		/* Action listener for account delete button */
		delTitleBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				/* If they want to delete an account */
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to delete the selected title from the system? This can't be undone.", "Delete Title", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					
					
					
					
					/* Code here to remove the title from the database */
					
					
				    JOptionPane.showMessageDialog(null, "Title Deleted");
				}
			}
		});
		
	}
}
