package main.java;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Titles extends JPanel implements Tile {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable titleTable;
	private Controller control;
	private JTextField titleField;
	private JCheckBox uniqueCheck;
	private JTextField distributorField;
	private JTextField tCodeField;
	private JCheckBox newReleaseCheck;
	private JTextField releaseField;
	private Object[][] titlesData;
	private DefaultTableModel titlesModel;

	private Object[][] fetchTimedData(){
		return titlesData = control.getTimeSensitiveTitles();
	}

	private Object[][] fetchAllData(){
		return titlesData = control.getAllTitles();
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
		titlePanel.setBounds(505, 117, 459, 268);
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

		JButton btnGetAllTitles = new JButton("Show All Titles");
		btnGetAllTitles.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGetAllTitles.setEnabled(true);
		btnGetAllTitles.setBounds(505, 11, 142, 33);
		add(btnGetAllTitles);

		JButton btnGetTimedTitles = new JButton("Show Near Titles");
		btnGetTimedTitles.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGetTimedTitles.setEnabled(true);
		btnGetTimedTitles.setBounds(663, 11, 142, 33);
		add(btnGetTimedTitles);

		JButton insertCSV = new JButton("Insert from CSV.");
		insertCSV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		insertCSV.setEnabled(true);
		insertCSV.setBounds(822, 11, 142, 33);
		add(insertCSV);
		
		JButton delTitleBtn = new JButton("Delete");
		delTitleBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		delTitleBtn.setEnabled(false);
		delTitleBtn.setBounds(802, 63, 162, 33);
		add(delTitleBtn);

		JButton discardBtn = new JButton("Discard");
		discardBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		discardBtn.setEnabled(false);
		discardBtn.setBounds(755, 413, 107, 33);
		add(discardBtn);

		JButton saveBtn = new JButton("Save");
		saveBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		saveBtn.setEnabled(false);
		saveBtn.setBounds(607, 413, 107, 33);
		add(saveBtn);

		JButton btnExportSingleTitle = new JButton("Export Requested\r\n");
		btnExportSingleTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExportSingleTitle.setEnabled(false);
		btnExportSingleTitle.setBounds(782, 523, 162, 49);
		add(btnExportSingleTitle);

		/* TextField deceleration */
		titleField = new JTextField();
		titleField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titleField.setEditable(false);
		titleField.setColumns(10);
		titleField.setBounds(10, 43, 440, 33);

		uniqueCheck = new JCheckBox();
		uniqueCheck.setEnabled(false);
		uniqueCheck.setFont(new Font("Tahoma", Font.PLAIN, 14));
		uniqueCheck.setBounds(125, 177, 21, 33);

		newReleaseCheck = new JCheckBox();
		newReleaseCheck.setEnabled(false);
		newReleaseCheck.setFont(new Font("Tahoma", Font.PLAIN, 14));
		newReleaseCheck.setBounds(10, 177, 21, 33);

		distributorField = new JTextField();
		distributorField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		distributorField.setEditable(false);
		distributorField.setColumns(10);
		distributorField.setBounds(10, 112, 330, 33);

		tCodeField = new JTextField();
		tCodeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tCodeField.setEditable(false);
		tCodeField.setColumns(10);
		tCodeField.setBounds(350, 112, 100, 33);

		releaseField = new JTextField();
		releaseField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		releaseField.setEditable(false);
		releaseField.setColumns(10);
		releaseField.setBounds(350, 195, 100, 33);

		/* Label deceleration */
		JLabel lblTitle = new JLabel("Description");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitle.setBounds(10, 22, 82, 17);

		JLabel lblDistributor = new JLabel("Distributor");
		lblDistributor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDistributor.setBounds(10, 87, 82, 14);

		JLabel lblDisctSub = new JLabel("Unique Print");
		lblDisctSub.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDisctSub.setBounds(125, 152, 125, 14);

		JLabel lblFlag = new JLabel("New Release");
		lblFlag.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFlag.setBounds(10, 152, 125, 14);

		JLabel lblTCode = new JLabel("Catalog ID");
		lblTCode.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTCode.setBounds(350, 87, 82, 14);

		JLabel lblRelease = new JLabel("Release Date");
		lblRelease.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRelease.setBounds(350, 175, 82, 14);

		titlePanel.add(titleField);
		titlePanel.add(uniqueCheck);
		titlePanel.add(distributorField);
		titlePanel.add(tCodeField);
		titlePanel.add(newReleaseCheck);
		titlePanel.add(releaseField);
		titlePanel.add(lblTitle);
		titlePanel.add(lblDisctSub);
		titlePanel.add(lblDistributor);
		titlePanel.add(lblTCode);
		titlePanel.add(lblFlag);
		titlePanel.add(lblRelease);

		/* Title Data */
		titlesData = fetchTimedData();

		/* Title Table Column Names */
		String titlesColumn[] = { "New", "Release", "Description", "Distributor", "Catalog ID", "Unique"};

		JScrollPane titleScrollPane = new JScrollPane();
		titleScrollPane.setBounds(10, 63, 459, 592);
		add(titleScrollPane);

		titlesModel = new DefaultTableModel(titlesData, titlesColumn)  {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};

		titleTable = new JTable(titlesModel);
		titleTable.setAutoCreateRowSorter(true);
		titleTable.getTableHeader().setReorderingAllowed(false);
		titleScrollPane.setViewportView(titleTable);

		titleTable.getColumnModel().getColumn(0).setMinWidth(50); // Must be set before maxWidth!!
		titleTable.getColumnModel().getColumn(0).setMaxWidth(50);
		titleTable.getColumnModel().getColumn(0).setWidth(50);

		titleTable.getColumnModel().getColumn(5).setMinWidth(50); // Must be set before maxWidth!!
		titleTable.getColumnModel().getColumn(5).setMaxWidth(50);
		titleTable.getColumnModel().getColumn(5).setWidth(50);

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
					releaseField.setText((String) titleTable.getValueAt(i, 1));
					titleField.setText((String) titleTable.getValueAt(i, 2));
					distributorField.setText((String) titleTable.getValueAt(i,3));
					tCodeField.setText((String) titleTable.getValueAt(i,4));

					if (newReleaseCheck.isSelected()) {
						newReleaseCheck.setSelected(false);
					}
					if (uniqueCheck.isSelected()) {
						uniqueCheck.setSelected(false);
					}

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
					newDistributorLabel.setBounds(28, 69, 151, 23);
					titlePanel.add(newDistributorLabel);

					JLabel newReleaseLabel = new JLabel("Release (YYYY-MM-DD)");
					newReleaseLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					newReleaseLabel.setBounds(200, 69, 200, 23);
					titlePanel.add(newReleaseLabel);

					JLabel newTCodeLabel = new JLabel("Catalog ID");
					newTCodeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					newTCodeLabel.setBounds(28, 110, 151, 23);
					titlePanel.add(newTCodeLabel);



					JTextField newTitleField = new JTextField();
					newTitleField.setBounds(28, 48, 332, 20);
					newTitleField.setColumns(10);
					titlePanel.add(newTitleField);

					JTextField newDistributorField = new JTextField();
					newDistributorField.setBounds(28, 92, 136, 20);
					newDistributorField.setColumns(10);
					titlePanel.add(newDistributorField);

					JTextField newReleaseField = new JTextField();
					newReleaseField.setBounds(200, 92, 136, 20);
					newReleaseField.setColumns(10);
					titlePanel.add(newReleaseField);

					JTextField newTCodeField = new JTextField();
					newTCodeField.setBounds(28, 133, 136, 20);
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

							control.insertTitle(newTitleField.getText(), newDistributorField.getText(), newReleaseField.getText(), newTCodeField.getText());

							titlesData = fetchTimedData();
							titlesModel.setDataVector(titlesData, titlesColumn);
							titleTable.setModel(titlesModel);
							titleTable.getTableHeader().setReorderingAllowed(false);

							JPanel accAddedPanel = new JPanel();
							accAddedPanel.setLayout(null);
							accAddedPanel.setBounds(572, 91, 388, 207);
							accAddedPanel.setBackground(new Color(240, 240, 240));
							titlePanel.setVisible(false);
							newTitlePanel.add(accAddedPanel);

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
					distributorField.setEditable(false);
					uniqueCheck.setEnabled(false);
					newReleaseCheck.setEnabled(false);
					releaseField.setEnabled(false);

					control.updateTitle(newReleaseCheck.isSelected(), titleField.getText(), releaseField.getText(), uniqueCheck.isSelected(), tCodeField.getText());

					titlesData = fetchTimedData();
					titlesModel.setDataVector(titlesData, titlesColumn);
					titleTable.setModel(titlesModel);
					titleTable.getTableHeader().setReorderingAllowed(false);

					JOptionPane.showMessageDialog(null,
						    "Changes have been saved!",
						    "Message",
						    JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		/* Action listener for when the "Save Changes" button is clicked */
		insertCSV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reader read = new reader();
				read.getHomeFrame();
			}
		});
		
		/* action listener for when the edit button for customer info is clicked */
		editTitleBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				titleField.setEditable(true);
				distributorField.setEditable(true);
				newReleaseCheck.setEnabled(true);
				uniqueCheck.setEnabled(true);
				releaseField.setEditable(true);

				saveBtn.setEnabled(true);
				discardBtn.setEnabled(true);

			}
		});
		/* Action listener for discard btn */
		discardBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				titleField.setEditable(false);
				distributorField.setEditable(false);
				newReleaseCheck.setEnabled(false);
				uniqueCheck.setEnabled(false);
				releaseField.setEnabled(false);
				
				discardBtn.setEnabled(false);
				saveBtn.setEnabled(false);
				
				int i = titleTable.getSelectedRow();
				titleField.setText((String) titleTable.getValueAt(i, 0));

				
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

					titlesData = fetchTimedData();
					titlesModel.setDataVector(titlesData, titlesColumn);
					titleTable.setModel(titlesModel);
					titleTable.getTableHeader().setReorderingAllowed(false);

				    JOptionPane.showMessageDialog(null, "Title Deleted");
				}
			}
		});

		btnResetNewWeek.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to reset all new release flags? This can't be undone.", "Reset Release Flags", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {

					control.resetFlags();

					titlesData = fetchTimedData();
					titlesModel.setDataVector(titlesData, titlesColumn);
					titleTable.setModel(titlesModel);
					titleTable.getTableHeader().setReorderingAllowed(false);

					JOptionPane.showMessageDialog(null, "Flags Reset");
				}
			}
		});

		btnGetAllTitles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				titlesData = fetchAllData();
				titlesModel.setDataVector(titlesData, titlesColumn);
				titleTable.setModel(titlesModel);
				titleTable.getTableHeader().setReorderingAllowed(false);
			}
		});

		btnGetTimedTitles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				titlesData = fetchTimedData();
				titlesModel.setDataVector(titlesData, titlesColumn);
				titleTable.setModel(titlesModel);
				titleTable.getTableHeader().setReorderingAllowed(false);
			}
		});
	}
}
