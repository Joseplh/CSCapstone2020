package loginCredentials;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import java.awt.Button;

public class MainPage {
	private JFrame homeFrame;
	private JTextField addUser;
	private JTextField userPass;
	@SuppressWarnings("unused")//it is not "unused", it is used on line 31 when the MainPage() is created and called.
	private static MainPage window;
	private JTextField delUser;
	private JTextField addPassword;
	private JTextField modUser;
	private JTextField changePassword;
	private final static Controller control = new Controller();
	private final Security security = new Security();
	private JTextField storeName;
	private JPanel panelStores;
	private JPanel panelAccount;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainPage();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
	/**
	 * @wbp.parser.entryPoint
	 */
	public MainPage() {
		initializeHome();
	}
	public Frame getHomeFrame() {
		return homeFrame;
	}
	private void initializeHome() {
		//defining the homepage and the frame it is contained by
		//added by Joseph Maxwell
		homeFrame = new JFrame("Home Page");
		homeFrame.getContentPane().setBackground(new Color(220, 20, 60));
		homeFrame.setBackground(Color.WHITE);
		homeFrame.setResizable(false);
		homeFrame.setTitle("Account Manager");
		homeFrame.setSize(576, 369);
		homeFrame.setLocationRelativeTo(null);
		homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		homeFrame.getContentPane().setLayout(null);
		homeFrame.setVisible(true);
		
		createAccountPanel();
		createStorePanel();
		panelStores.setVisible(false);
		panelAccount.setVisible(false);
						
		JMenuBar menuBar = new JMenuBar();
		homeFrame.setJMenuBar(menuBar);
						
		Button storeBtn = new Button("Store");
		menuBar.add(storeBtn);
				
		Button accountBtn = new Button("Account");
		menuBar.add(accountBtn);
						

		userPass = new JTextField();
		userPass.setEditable(true);
		userPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		userPass.setBounds(200, 120, 162, 33);
		userPass.setColumns(10);
		
		storeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//delete account based on user
				panelStores.setVisible(true);
				panelAccount.setVisible(false);
			}
		});
		accountBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//delete account based on user
				panelStores.setVisible(false);
				panelAccount.setVisible(true);
			}
		});
	}
	private void createStorePanel() {
		panelStores = new JPanel();
		panelStores.setLayout(null);
		panelStores.setBounds(10, 11, 534, 282);
		homeFrame.getContentPane().add(panelStores);
		
		storeName = new JTextField();
		storeName.setText("[store name]");
		storeName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		storeName.setEditable(true);
		storeName.setColumns(10);
		storeName.setBounds(10, 55, 162, 33);
		panelStores.add(storeName);
		
		JLabel labelA_1 = new JLabel("Store");
		labelA_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		labelA_1.setBounds(10, 11, 162, 33);
		panelStores.add(labelA_1);
		
		JButton addAccount_1 = new JButton("ADD Store");
		addAccount_1.setForeground(Color.BLACK);
		addAccount_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		addAccount_1.setBackground(new Color(255, 215, 0));
		addAccount_1.setBounds(10, 99, 162, 33);
		panelStores.add(addAccount_1);
		
		JButton delAccount_1 = new JButton("!!!DELETE!!!");
		delAccount_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		delAccount_1.setBackground(new Color(255, 215, 0));
		delAccount_1.setBounds(182, 99, 162, 33);
		panelStores.add(delAccount_1);
		
		JLabel storeList = new JLabel("Store List");
		storeList.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		storeList.setBounds(361, 25, 117, 24);
		panelStores.add(storeList);
		
		listStores();
		
		addAccount_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//change password for given user
				Component frame = null;
				if(addStore(storeName.getText())) {
					JOptionPane.showMessageDialog(frame, "Added: "+storeName.getText());			
				}else
					JOptionPane.showMessageDialog(frame, "Failed to add: "+storeName.getText());
				refreshStoresPanel();
			}
		});
		delAccount_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//delete account based on user
				Component frame = null;
				if(control.isStore(storeName.getText())) {
					delStore(storeName.getText());
					JOptionPane.showMessageDialog(frame, "STORE "+storeName.getText()+" has been removed!");
				}else
					JOptionPane.showMessageDialog(frame, "STORE Does not Exist to remove!");
				refreshStoresPanel();
			}
		});
	}
	protected void refreshStoresPanel() {
		panelStores.setVisible(false);
		homeFrame.remove(panelStores);
		createStorePanel();
		panelStores.setVisible(true);
		// TODO Auto-generated method stub
	}
	private void listStores() {
		JLabel Store0;
		String[] storeList = control.getStoreList();
		for(int x=0; x<storeList.length; x++) {
			Store0 = new JLabel(storeList[x]);
			Store0.setBounds(354, 55+(x*15), 90, 14);
			panelStores.add(Store0);
		}
	}
	private void createAccountPanel() {
		// TODO Auto-generated method stub
		panelAccount = new JPanel();
		panelAccount.setBounds(10, 11, 534, 282);
		homeFrame.getContentPane().add(panelAccount);
		panelAccount.setLayout(null);
		
		delUser = new JTextField();
		delUser.setBounds(10, 135, 162, 33);
		panelAccount.add(delUser);
		delUser.setText("[user name]");
		delUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		delUser.setEditable(true);
		delUser.setColumns(10);
		
		modUser = new JTextField();
		modUser.setBounds(10, 227, 162, 33);
		panelAccount.add(modUser);
		modUser.setText("[user name]");
		modUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		modUser.setEditable(true);
		modUser.setColumns(10);
		
		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setBounds(10, 179, 195, 33);
		panelAccount.add(lblChangePassword);
		lblChangePassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		
		changePassword = new JTextField();
		changePassword.setBounds(182, 227, 162, 33);
		panelAccount.add(changePassword);
		changePassword.setBackground(new Color(255, 255, 255));
		changePassword.setText("[new password]");
		changePassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		changePassword.setEditable(true);
		changePassword.setColumns(10);
		
		JLabel labelB = new JLabel("DEL Account");
		labelB.setBounds(10, 99, 162, 33);
		panelAccount.add(labelB);
		labelB.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		
		addUser = new JTextField();
		addUser.setBounds(10, 55, 162, 33);
		panelAccount.add(addUser);
		addUser.setText("[user name]");
		addUser.setEditable(true);
		addUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addUser.setColumns(10);
		
		JLabel labelA = new JLabel("ADD Account");
		labelA.setBounds(10, 11, 162, 33);
		panelAccount.add(labelA);
		labelA.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		
		addPassword = new JTextField();
		addPassword.setBounds(182, 55, 162, 33);
		panelAccount.add(addPassword);
		addPassword.setText("[password]");
		addPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addPassword.setEditable(true);
		addPassword.setColumns(10);
		
		JButton addAccount = new JButton("ADD Account");
		addAccount.setBounds(354, 55, 162, 33);
		panelAccount.add(addAccount);
		addAccount.setForeground(new Color(0, 0, 0));
		addAccount.setBackground(new Color(255, 215, 0));
		addAccount.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		
		JButton delAccount = new JButton("!!!DELETE!!!");
		delAccount.setBounds(354, 136, 162, 33);
		panelAccount.add(delAccount);
		delAccount.setBackground(new Color(255, 215, 0));
		delAccount.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		homeFrame.getRootPane().setDefaultButton(delAccount);
						
		JButton changePassBtn = new JButton("Change Password");
		changePassBtn.setBounds(354, 227, 162, 33);
		panelAccount.add(changePassBtn);
		changePassBtn.setBackground(new Color(255, 215, 0));
		changePassBtn.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		
		changePassBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//change password for given user
				changePass(addUser.getText(), changePassword.getText());
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "Password changed to "+changePassword.getText());
			}
		});
		delAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//delete account based on user
				delAccount(delUser.getText());
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "Account has been removed!");
			}
		});

		addAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//add user and password
				addAccount(addUser.getText(), addPassword.getText());
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "Account has been added.");
			}
		});
	}
	protected boolean addStore(String store) {
		// TODO Auto-generated method stub
		if(store.length() < 10) {
			control.execute("INSERT INTO [Stores] VALUES('"+ store+"')");
			return true;
		}
		return false;
	}
	protected void delStore(String store) {
		// TODO Auto-generated method stub
		if(store != "[store same]")
			control.execute("DELETE FROM [Stores] WHERE [Store]='"+store+"';");
	}
	protected void changePass(String user, String newPass) {
		control.execute("UPDATE [Account] SET [Pass] = '" + security.makeHash(newPass) + "' WHERE [User] = '"+ user +"';");
	}
	protected void delAccount(String delUser) {
		control.execute("DELETE FROM [Account] WHERE [User] = '" + delUser + "';");
	}
	protected void addAccount(String addUser, String addPass) {
		control.execute("INSERT INTO [Account] ([User],[Pass]) VALUES ('"+addUser+"','"+security.makeHash(addPass)+"');");
	}
}
