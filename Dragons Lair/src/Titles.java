import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
		panel_1.setBorder(new TitledBorder(null, "Title Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		
		JButton button = new JButton("Discard");
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.setEnabled(false);
		button.setBounds(755, 313, 107, 33);
		add(button);
	
		JButton btnSaveChanges = new JButton("Save");
		btnSaveChanges.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSaveChanges.setEnabled(false);
		btnSaveChanges.setBounds(607, 313, 107, 33);
		add(btnSaveChanges);
		
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
		
		
		titleTable = new JTable();
		titleTable.setBounds(10, 63, 459, 592);
		add(titleTable);
	}
}
