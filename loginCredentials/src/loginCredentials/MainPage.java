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
	private final Controller control = new Controller();
	private final Security security = new Security();
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
				homeFrame.setSize(540, 320);
				homeFrame.setLocationRelativeTo(null);
				homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				homeFrame.getContentPane().setLayout(null);
				homeFrame.setVisible(true);

				JButton delAccount = new JButton("!!!DELETE!!!");
				delAccount.setBackground(new Color(255, 215, 0));
				delAccount.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
				delAccount.setBounds(354, 136, 162, 33);
				homeFrame.getContentPane().add(delAccount);
				homeFrame.getRootPane().setDefaultButton(delAccount);
				
				JLabel labelA = new JLabel("ADD Account");
				labelA.setBounds(10,11,162,33);
				labelA.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
				homeFrame.getContentPane().add(labelA);
				
				JLabel labelB = new JLabel("DEL Account");
				labelB.setBounds(10,99,162,33);
				labelB.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
				homeFrame.getContentPane().add(labelB);
				
				addUser = new JTextField();
				addUser.setText("[user name]");
				addUser.setEditable(true);
				addUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
				addUser.setBounds(10, 55, 162, 33);
				addUser.setColumns(10);
				homeFrame.getContentPane().add(addUser);
				
				delUser = new JTextField();
				delUser.setText("[user name]");
				delUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
				delUser.setEditable(true);
				delUser.setColumns(10);
				delUser.setBounds(10, 135, 162, 33);
				homeFrame.getContentPane().add(delUser);
				
				addPassword = new JTextField();
				addPassword.setText("[password]");
				addPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
				addPassword.setEditable(true);
				addPassword.setColumns(10);
				addPassword.setBounds(182, 55, 162, 33);
				homeFrame.getContentPane().add(addPassword);
				
				modUser = new JTextField();
				modUser.setText("[user name]");
				modUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
				modUser.setEditable(true);
				modUser.setColumns(10);
				modUser.setBounds(10, 227, 162, 33);
				homeFrame.getContentPane().add(modUser);
				
				JLabel lblChangePassword = new JLabel("Change Password");
				lblChangePassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
				lblChangePassword.setBounds(10, 179, 195, 33);
				homeFrame.getContentPane().add(lblChangePassword);
				
				changePassword = new JTextField();
				changePassword.setBackground(new Color(255, 255, 255));
				changePassword.setText("[new password]");
				changePassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
				changePassword.setEditable(true);
				changePassword.setColumns(10);
				changePassword.setBounds(182, 227, 162, 33);
				homeFrame.getContentPane().add(changePassword);
				
				JButton addAccount = new JButton("ADD Account");
				addAccount.setForeground(new Color(0, 0, 0));
				addAccount.setBackground(new Color(255, 215, 0));
				addAccount.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
				addAccount.setBounds(354, 55, 162, 33);
				homeFrame.getContentPane().add(addAccount);
				
				JButton changePassBtn = new JButton("Change Password");
				changePassBtn.setBackground(new Color(255, 215, 0));
				changePassBtn.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
				changePassBtn.setBounds(354, 227, 162, 33);
				homeFrame.getContentPane().add(changePassBtn);

				userPass = new JTextField();
				userPass.setEditable(true);
				userPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
				userPass.setBounds(200, 120, 162, 33);
				userPass.setColumns(10);
				
				addAccount.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//add user and password
						addAccount(addUser.getText(), addPassword.getText());
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "Account has been added.");
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
				changePassBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//change password for given user
						changePass(addUser.getText(), changePassword.getText());
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "Password changed to "+changePassword.getText());
					}
				});
	}
	protected void changePass(String user, String newPass) {
		// TODO Auto-generated method stub
		control.insert("UPDATE [Account] SET [Pass] = '" + security.makeHash(newPass) + "' WHERE [User] = '"+ user +"';");
	}
	protected void delAccount(String delUser) {
		// TODO Auto-generated method stub
		control.insert("DELETE FROM [Account] WHERE [User] = '" + delUser + "';");
	}
	protected void addAccount(String addUser, String addPass) {
		// TODO Auto-generated method stub
		control.insert("INSERT INTO [Account] ([User],[Pass]) VALUES ('"+addUser+"','"+security.makeHash(addPass)+"');");
	}
}
