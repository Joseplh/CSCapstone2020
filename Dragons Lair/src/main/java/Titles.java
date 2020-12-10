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
	private JTextField fullTitleField;
	private JTextField seriesField;
	private JTextField distributorField;
	private JTextField tCodeField;
	private JTextField releaseField;
	private JTextField issueField;
	private JCheckBox newReleaseCheck;
	private JCheckBox uniqueCheck;
	private String[][] titlesData;
	private DefaultTableModel titlesModel;

	private String[][] fetchTimedData(){
		titlesData = control.getTimeSensitiveTitles();
		for (int i = 0; i < titlesData.length; i++) {
			if (titlesData[i][0].equals("1")) {
				titlesData[i][0] = "Yes";
			}
			else {
				titlesData[i][0] = "No";
			}

			if (titlesData[i][5].equals("1")) {
				titlesData[i][5] = "Yes";
			}
			else {
				titlesData[i][5] = "No";
			}
		}
		return titlesData;
	}

	private String[][] fetchAllData(){
		titlesData = control.getAllTitles();
		for (int i = 0; i < titlesData.length; i++) {
			if (titlesData[i][0].equals("1")) {
				titlesData[i][0] = "Yes";
			}
			else {
				titlesData[i][0] = "No";
			}

			if (titlesData[i][5].equals("1")) {
				titlesData[i][5] = "Yes";
			}
			else {
				titlesData[i][5] = "No";
			}
		}
		return titlesData;
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
		titlePanel.setBounds(505, 117, 459, 318);
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
		discardBtn.setBounds(755, 463, 107, 33);
		add(discardBtn);

		JButton saveBtn = new JButton("Save");
		saveBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		saveBtn.setEnabled(false);
		saveBtn.setBounds(607, 463, 107, 33);
		add(saveBtn);

//		JButton btnExportSingleTitle = new JButton("Export Requested\r\n");
//		btnExportSingleTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		btnExportSingleTitle.setEnabled(false);
//		btnExportSingleTitle.setBounds(782, 523, 162, 49);
//		add(btnExportSingleTitle);

		/* TextField deceleration */
		fullTitleField = new JTextField();
		fullTitleField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fullTitleField.setEditable(false);
		fullTitleField.setColumns(10);
		fullTitleField.setBounds(10, 43, 440, 33);

		seriesField = new JTextField();
		seriesField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		seriesField.setEditable(false);
		seriesField.setColumns(10);
		seriesField.setBounds(10, 108, 355, 33);

		issueField = new JTextField();
		issueField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		issueField.setEditable(false);
		issueField.setColumns(10);
		issueField.setBounds(375, 108, 75, 33);

		distributorField = new JTextField();
		distributorField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		distributorField.setEditable(false);
		distributorField.setColumns(10);
		distributorField.setBounds(10, 173, 330, 33);

		tCodeField = new JTextField();
		tCodeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tCodeField.setEditable(false);
		tCodeField.setColumns(10);
		tCodeField.setBounds(350, 173, 100, 33);

		newReleaseCheck = new JCheckBox();
		newReleaseCheck.setEnabled(false);
		newReleaseCheck.setFont(new Font("Tahoma", Font.PLAIN, 14));
		newReleaseCheck.setBounds(10, 238, 21, 21);

		uniqueCheck = new JCheckBox();
		uniqueCheck.setEnabled(false);
		uniqueCheck.setFont(new Font("Tahoma", Font.PLAIN, 14));
		uniqueCheck.setBounds(125, 238, 21, 21);

		releaseField = new JTextField();
		releaseField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		releaseField.setEditable(false);
		releaseField.setColumns(10);
		releaseField.setBounds(350, 238, 100, 33);

		/* Label deceleration */
		JLabel lblFullTitle = new JLabel("Full Title");
		lblFullTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFullTitle.setBounds(10, 22, 82, 17);

		JLabel lblSeries = new JLabel("Series");
		lblSeries.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSeries.setBounds(10, 87, 82, 17);

		JLabel lblIssue = new JLabel("Issue #");
		lblIssue.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIssue.setBounds(375, 87, 82, 14);

		JLabel lblDistributor = new JLabel("Distributor");
		lblDistributor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDistributor.setBounds(10, 152, 82, 17);

		JLabel lblTCode = new JLabel("Catalog ID");
		lblTCode.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTCode.setBounds(350, 152, 82, 17);

		JLabel lblFlag = new JLabel("New Release");
		lblFlag.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFlag.setBounds(10, 217, 125, 17);

		JLabel lblUnique = new JLabel("Unique Print");
		lblUnique.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUnique.setBounds(125, 217, 125, 17);

		JLabel lblRelease = new JLabel("Release Date");
		lblRelease.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRelease.setBounds(350, 217, 150, 17);



		titlePanel.add(fullTitleField);
		titlePanel.add(seriesField);
		titlePanel.add(uniqueCheck);
		titlePanel.add(distributorField);
		titlePanel.add(tCodeField);
		titlePanel.add(newReleaseCheck);
		titlePanel.add(releaseField);
		titlePanel.add(issueField);
		titlePanel.add(lblFullTitle);
		titlePanel.add(lblUnique);
		titlePanel.add(lblDistributor);
		titlePanel.add(lblTCode);
		titlePanel.add(lblFlag);
		titlePanel.add(lblRelease);
		titlePanel.add(lblSeries);
		titlePanel.add(lblIssue);

		/* Title Data */
		titlesData = fetchTimedData();

		/* Title Table Column Names */
		/* TODO: update column names */
		String titlesColumn[] = { "New", "Series", "Issue #", "Release", "Distributor", "Unique", "Title", "Catalog ID"};

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

		titleTable.getColumnModel().getColumn(6).setMinWidth(0); // Must be set before maxWidth!!
		titleTable.getColumnModel().getColumn(6).setMaxWidth(0);
		titleTable.getColumnModel().getColumn(6).setWidth(0);

		titleTable.getColumnModel().getColumn(7).setMinWidth(0); // Must be set before maxWidth!!
		titleTable.getColumnModel().getColumn(7).setMaxWidth(0);
		titleTable.getColumnModel().getColumn(7).setWidth(0);

		/* Listener for when a cell is selected from the titleTable */
		titleTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				/*
				 * If the text boxes displaying the title information is editable, warn user
				 * to save the information
				 */
				if (fullTitleField.isEditable()) {
					JOptionPane.showMessageDialog(null,
							"Please save or discard the changes made to the current title", "Save Warning",
							JOptionPane.WARNING_MESSAGE);
				}

				/* Otherwise set the text boxes editable so the data can be changed */
				else {
					int i = titleTable.getSelectedRow();
					fullTitleField.setText((String) titleTable.getValueAt(i, 6));
					seriesField.setText((String) titleTable.getValueAt(i,1));
					issueField.setText((String) titleTable.getValueAt(i,2));
					distributorField.setText((String) titleTable.getValueAt(i,4));
					tCodeField.setText((String) titleTable.getValueAt(i,7));
					releaseField.setText((String) titleTable.getValueAt(i, 3));

					if (titleTable.getValueAt(i, 0).equals("Yes")) {
						newReleaseCheck.setSelected(true);
					}
					else {
						newReleaseCheck.setSelected(false);
					}

					if (titleTable.getValueAt(i, 5).equals("Yes")) {
						uniqueCheck.setSelected(true);
					}
					else {
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
					JFrame newTitlePanel = new JFrame("Add New Title");
					newTitlePanel.setVisible(true);
					newTitlePanel.setBounds(505, 117, 348, 268);
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
					titlePanel.setBounds(505, 117, 348, 268);
					titlePanel.setLayout(null);



					JTextField newTitleField = new JTextField();
					newTitleField.setBounds(10, 31, 308, 20);
					newTitleField.setColumns(10);
					titlePanel.add(newTitleField);

					JTextField newSeriesField = new JTextField();
					newSeriesField.setBounds(10, 76, 220, 20);
					newSeriesField.setColumns(10);
					titlePanel.add(newSeriesField);

					JTextField newIssueField = new JTextField();
					newIssueField.setBounds(240, 76, 78, 20);
					newIssueField.setColumns(10);
					titlePanel.add(newIssueField);

					JTextField newDistributorField = new JTextField();
					newDistributorField.setBounds(10, 121, 220, 20);
					newDistributorField.setColumns(10);
					titlePanel.add(newDistributorField);

					JTextField newTCodeField = new JTextField();
					newTCodeField.setBounds(240, 121, 78, 20);
					newTCodeField.setColumns(10);
					titlePanel.add(newTCodeField);

					JCheckBox newNewReleaseCheck = new JCheckBox();
					newNewReleaseCheck.setBounds(15, 166, 20, 20);
					titlePanel.add(newNewReleaseCheck);

					JCheckBox newUniqueCheck = new JCheckBox();
					newUniqueCheck.setBounds(91, 166, 20, 20);
					titlePanel.add(newUniqueCheck);

					JTextField newReleaseDateField = new JTextField();
					newReleaseDateField.setBounds(142, 166, 176, 20);
					newReleaseDateField.setColumns(10);
					titlePanel.add(newReleaseDateField);



					JLabel newTitleLabel = new JLabel("Title");
					newTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					newTitleLabel.setBounds(10, 10, 82, 17);
					titlePanel.add(newTitleLabel);

					JLabel newSeriesLabel = new JLabel("Series");
					newSeriesLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					newSeriesLabel.setBounds(10, 55, 82, 17);
					titlePanel.add(newSeriesLabel);

					JLabel newIssueLabel = new JLabel("Issue #");
					newIssueLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					newIssueLabel.setBounds(240, 55, 82, 17);
					titlePanel.add(newIssueLabel);

					JLabel newDistributorLabel = new JLabel("Distributor");
					newDistributorLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					newDistributorLabel.setBounds(10, 100, 151, 23);
					titlePanel.add(newDistributorLabel);

					JLabel newTCodeLabel = new JLabel("Catalog ID");
					newTCodeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					newTCodeLabel.setBounds(240, 100, 151, 23);
					titlePanel.add(newTCodeLabel);

					JLabel newNewReleaseLabel = new JLabel("New");
					newNewReleaseLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					newNewReleaseLabel.setBounds(10, 145, 151, 23);
					titlePanel.add(newNewReleaseLabel);

					JLabel newUniqueLabel = new JLabel("Unique");
					newUniqueLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					newUniqueLabel.setBounds(76, 145, 151, 23);
					titlePanel.add(newUniqueLabel);

					JLabel newReleaseDateLabel = new JLabel("Release (YYYY-MM-DD)");
					newReleaseDateLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					newReleaseDateLabel.setBounds(142, 145, 200, 23);
					titlePanel.add(newReleaseDateLabel);



					JButton addTitlePanelBtn = new JButton("Add");
					addTitlePanelBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
					addTitlePanelBtn.setBounds(119, 201, 90, 23);
					titlePanel.add(addTitlePanelBtn);



					newTitlePanel.add(titlePanel);



					/* Action listener for when add button clicked on create new title frame */
					addTitlePanelBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							control.insertTitle(newTitleField.getText(),
									newSeriesField.getText(),
									newIssueField.getText(),
									newDistributorField.getText(),
									newTCodeField.getText(),
									newNewReleaseCheck.isSelected(),
									newUniqueCheck.isSelected(),
									newReleaseDateField.getText());

							titlesData = fetchTimedData();
							titlesModel.setDataVector(titlesData, titlesColumn);
							titleTable.setModel(titlesModel);
							titleTable.getTableHeader().setReorderingAllowed(false);

							titleTable.getColumnModel().getColumn(6).setMinWidth(0); // Must be set before maxWidth!!
							titleTable.getColumnModel().getColumn(6).setMaxWidth(0);
							titleTable.getColumnModel().getColumn(6).setWidth(0);

							titleTable.getColumnModel().getColumn(7).setMinWidth(0); // Must be set before maxWidth!!
							titleTable.getColumnModel().getColumn(7).setMaxWidth(0);
							titleTable.getColumnModel().getColumn(7).setWidth(0);

							JPanel titleAddedPanel = new JPanel();
							titleAddedPanel.setLayout(null);
							titleAddedPanel.setBounds(572, 91, 388, 207);
							titleAddedPanel.setBackground(new Color(240, 240, 240));
							titlePanel.setVisible(false);
							newTitlePanel.add(titleAddedPanel);

							JTextArea txtrTitleHasBeen = new JTextArea();
							txtrTitleHasBeen.setEditable(false);
							txtrTitleHasBeen.setBackground(new Color(240, 240, 240));
							txtrTitleHasBeen.setText("Title has been added!");
							txtrTitleHasBeen.setBounds(99, 87, 194, 33);
							titleAddedPanel.add(txtrTitleHasBeen);
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
					
					fullTitleField.setEditable(false);
					seriesField.setEditable(false);
					issueField.setEditable(false);
					distributorField.setEditable(false); // Distributor and Catalog ID cannot be
					tCodeField.setEditable(false);       // edited due to being primary keys
					uniqueCheck.setEnabled(false);
					newReleaseCheck.setEnabled(false);
					releaseField.setEditable(false);

					control.updateTitle(fullTitleField.getText(), seriesField.getText(), issueField.getText(), distributorField.getText(), tCodeField.getText(), newReleaseCheck.isSelected(), uniqueCheck.isSelected(), releaseField.getText());

					titlesData = fetchTimedData();
					titlesModel.setDataVector(titlesData, titlesColumn);
					titleTable.setModel(titlesModel);
					titleTable.getTableHeader().setReorderingAllowed(false);

					titleTable.getColumnModel().getColumn(6).setMinWidth(0); // Must be set before maxWidth!!
					titleTable.getColumnModel().getColumn(6).setMaxWidth(0);
					titleTable.getColumnModel().getColumn(6).setWidth(0);

					titleTable.getColumnModel().getColumn(7).setMinWidth(0); // Must be set before maxWidth!!
					titleTable.getColumnModel().getColumn(7).setMaxWidth(0);
					titleTable.getColumnModel().getColumn(7).setWidth(0);

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

				fullTitleField.setEditable(true);
				seriesField.setEditable(true);
				issueField.setEditable(true);
				distributorField.setEditable(false); // Distributor and Catalog ID cannot be
				tCodeField.setEditable(false);       // edited due to being primary keys
				newReleaseCheck.setEnabled(true);
				uniqueCheck.setEnabled(true);
				releaseField.setEditable(true);

				saveBtn.setEnabled(true);
				discardBtn.setEnabled(true);

			}
		});
		/* Action listener for discard btn */
		/* TODO: default to clear details when no row is selected */
		discardBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fullTitleField.setEditable(false);
				seriesField.setEditable(false);
				issueField.setEditable(false);
				distributorField.setEditable(false); // Distributor and Catalog ID cannot be
				tCodeField.setEditable(false);       // edited due to being primary keys
				newReleaseCheck.setEnabled(false);
				uniqueCheck.setEnabled(false);
				releaseField.setEditable(false);
				
				discardBtn.setEnabled(false);
				saveBtn.setEnabled(false);

				int i = titleTable.getSelectedRow();
				fullTitleField.setText((String) titleTable.getValueAt(i, 6));
				seriesField.setText((String) titleTable.getValueAt(i,1));
				issueField.setText((String) titleTable.getValueAt(i,2));
				distributorField.setText((String) titleTable.getValueAt(i,4));
				tCodeField.setText((String) titleTable.getValueAt(i,7));
				releaseField.setText((String) titleTable.getValueAt(i, 3));

				if (titleTable.getValueAt(i, 0).equals("Yes")) {
					newReleaseCheck.setSelected(true);
				}
				else {
					newReleaseCheck.setSelected(false);
				}

				if (titleTable.getValueAt(i, 5).equals("Yes")) {
					uniqueCheck.setSelected(true);
				}
				else {
					uniqueCheck.setSelected(false);
				}
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

					titleTable.getColumnModel().getColumn(6).setMinWidth(0); // Must be set before maxWidth!!
					titleTable.getColumnModel().getColumn(6).setMaxWidth(0);
					titleTable.getColumnModel().getColumn(6).setWidth(0);

					titleTable.getColumnModel().getColumn(7).setMinWidth(0); // Must be set before maxWidth!!
					titleTable.getColumnModel().getColumn(7).setMaxWidth(0);
					titleTable.getColumnModel().getColumn(7).setWidth(0);

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

					titleTable.getColumnModel().getColumn(6).setMinWidth(0); // Must be set before maxWidth!!
					titleTable.getColumnModel().getColumn(6).setMaxWidth(0);
					titleTable.getColumnModel().getColumn(6).setWidth(0);

					titleTable.getColumnModel().getColumn(7).setMinWidth(0); // Must be set before maxWidth!!
					titleTable.getColumnModel().getColumn(7).setMaxWidth(0);
					titleTable.getColumnModel().getColumn(7).setWidth(0);

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

				titleTable.getColumnModel().getColumn(6).setMinWidth(0); // Must be set before maxWidth!!
				titleTable.getColumnModel().getColumn(6).setMaxWidth(0);
				titleTable.getColumnModel().getColumn(6).setWidth(0);

				titleTable.getColumnModel().getColumn(7).setMinWidth(0); // Must be set before maxWidth!!
				titleTable.getColumnModel().getColumn(7).setMaxWidth(0);
				titleTable.getColumnModel().getColumn(7).setWidth(0);
			}
		});

		btnGetTimedTitles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				titlesData = fetchTimedData();
				titlesModel.setDataVector(titlesData, titlesColumn);
				titleTable.setModel(titlesModel);
				titleTable.getTableHeader().setReorderingAllowed(false);

				titleTable.getColumnModel().getColumn(6).setMinWidth(0); // Must be set before maxWidth!!
				titleTable.getColumnModel().getColumn(6).setMaxWidth(0);
				titleTable.getColumnModel().getColumn(6).setWidth(0);

				titleTable.getColumnModel().getColumn(7).setMinWidth(0); // Must be set before maxWidth!!
				titleTable.getColumnModel().getColumn(7).setMaxWidth(0);
				titleTable.getColumnModel().getColumn(7).setWidth(0);
			}
		});
	}
}
