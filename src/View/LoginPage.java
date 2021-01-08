package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Connection.DBConnection;
import Model.GiftRankPlayer;
import Model.Player;

 class LoginPage {

	private JFrame frame;
	public static int playerId;
	JLabel lblIncorrectEmailOr;
	JPasswordField passwordField;
     JLabel lblEmpty;
	 JLabel lblEmpty_1 ;
	JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Online Games");
	//	frame.setUndecorated(true);
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/Joystick-icon.png"));
		frame.setIconImage(logo.getImage());
		frame.getContentPane().setForeground(new Color(0, 0, 128));
		frame.getContentPane().setFont(new Font("Monotype Corsiva", Font.PLAIN, 23));
		frame.setBounds(100, 100, 644, 432);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		DBConnection.writeToFile(DBConnection.readRanksPlayers());
		ArrayList<Object> temp = DBConnection.getTable("Players");
		ArrayList<Player> players = new ArrayList<Player>();
		for(Object o : temp) {
			players.add((Player)o);
		}
		JLabel lblUsername = new JLabel("User ID :");
		lblUsername.setForeground(new Color(0, 0, 0));
		lblUsername.setFont(new Font("Monotype Corsiva", Font.BOLD, 38));
		lblUsername.setBounds(98, 86, 163, 82);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setFont(new Font("Monotype Corsiva", Font.BOLD, 38));
		lblNewLabel.setBounds(80, 164, 163, 52);
		frame.getContentPane().add(lblNewLabel);
		
		 textField = new JTextField();
		textField.setBounds(255, 110, 208, 42);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		Border greyBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY);
	

	    passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(255, 174, 208, 41);
		frame.getContentPane().add(passwordField);
		
		JLabel welcomeLbl = new JLabel("ONLINE GAMES");
		welcomeLbl.setFont(new Font("Monotype Corsiva", Font.BOLD, 38));
		welcomeLbl.setBounds(174, 24, 296, 49);
		frame.getContentPane().add(welcomeLbl);
		
	    lblIncorrectEmailOr = new JLabel("Incorrect ID number or Password.");
		lblIncorrectEmailOr.setVisible(false);
		lblIncorrectEmailOr.setForeground(Color.RED);
		lblIncorrectEmailOr.setBackground(Color.WHITE);
		lblIncorrectEmailOr.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIncorrectEmailOr.setBounds(174, 284, 289, 16);
		frame.getContentPane().add(lblIncorrectEmailOr);
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setForeground(Color.BLACK);
		
		lblEmpty = new JLabel("Empty!");
		lblEmpty.setForeground(Color.RED);
		lblEmpty.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblEmpty.setBounds(477, 179, 63, 26);
		lblEmpty.setVisible(false);
		frame.getContentPane().add(lblEmpty);
		
		 lblEmpty_1 = new JLabel("Empty!");
		lblEmpty_1.setForeground(Color.RED);
		lblEmpty_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblEmpty_1.setBounds(475, 116, 63, 32);
		lblEmpty_1.setVisible(false);
		frame.getContentPane().add(lblEmpty_1);
	//	Border border = BorderFactory.createLineBorder(Color.RED,1);
		
		
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.GRAY);
		btnLogin.setForeground(SystemColor.desktop);
		btnLogin.setFont(new Font("Monotype Corsiva", Font.BOLD, 38));
		btnLogin.setBounds(230, 229, 130, 42);
		frame.getContentPane().add(btnLogin);
		
		JLabel label = new JLabel("");
		label.setBounds(34, 24, 56, 52);
		Image image1 = new ImageIcon(this.getClass().getResource("/secrecy-icon.png")).getImage();
		label.setIcon(new ImageIcon(image1));
		frame.getContentPane().add(label);
		
		Image image2 = new ImageIcon(this.getClass().getResource("/pexilsBG.png")).getImage();
		JLabel label_1 = new JLabel("");
		label_1.setBounds(0, -58, 636, 458);
		label_1.setIcon(new ImageIcon(image2));
		frame.getContentPane().add(label_1);
		
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblEmpty.setVisible(false);
				lblIncorrectEmailOr.setVisible(false);
				lblEmpty_1.setVisible(false);
				
				
				
				if(passwordField.getPassword().toString().isEmpty() || textField.getText().isEmpty() ) {
					if(textField.getText().isEmpty()) {
						lblEmpty_1.setVisible(true);
					}
					if(passwordField.getPassword().toString().isEmpty()) {
						lblEmpty.setVisible(true);
						return;
					}
//System.out.println(password.equals(DBConnection.getPlayerPassword(id)));
				}else {
					
					
					int  id = Integer.parseInt(textField.getText());
					playerId =id;
						String password = new String(passwordField.getPassword());
						if(id==0 && (password.equals("admin")|| password.equals("Admin"))) {
							DashBoard d = new DashBoard();
							d.setVisible(true);
							frame.dispose();
						}else if( !DBConnection.getPlayersID().contains(id) || !(password.equals(DBConnection.getPlayerPassword(id))) ) {
							lblIncorrectEmailOr.setVisible(true);
						}else {
							
								PlayerPage p = new PlayerPage();
								
								p.setVisible(true);
								frame.dispose();
							}
				
		
				}
				
			}
		});
		
				
		
		
	}
	

	
	public JFrame getJframe() {
		return frame;
	}
}
