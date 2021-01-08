package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Connection.DBConnection;
import Model.Country;
import Model.Player;
import javax.swing.JLabel;

public class PersonalInfo {

	private JFrame frame;

	public static Player player;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonalInfo window = new PersonalInfo();
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
	public PersonalInfo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	    	player = DBConnection.getPlayer(LoginPage.playerId);
	    	if(player==null) player =DBConnection.getPlayer(4);
	    	frame = new JFrame("Personal information");
	    	frame.getContentPane().setBackground(new Color(47, 79, 79));
			ImageIcon logo = new ImageIcon(this.getClass().getResource("/Joystick-icon.png"));
			frame.setIconImage(logo.getImage());
			frame.getContentPane().setForeground(new Color(0, 0, 128));
			frame.getContentPane().setFont(new Font("Monotype Corsiva", Font.PLAIN, 23));
			frame.setBounds(100, 100, 462, 470);
			//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			JLabel lblName = new JLabel("Name: "+player.getFirstName()+" "+player.getLastName());
			lblName.setForeground(Color.BLACK);
			lblName.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
			lblName.setBounds(69, 13, 521, 45);
			frame.getContentPane().add(lblName);
			
			JLabel lblDateOfBirth = new JLabel("Date Of Birth: "+ player.getBirthDate().toString());
			lblDateOfBirth.setForeground(Color.BLACK);
			lblDateOfBirth.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
			lblDateOfBirth.setBounds(69, 57, 521, 45);
			frame.getContentPane().add(lblDateOfBirth);
			
			JLabel lblCreationDate = new JLabel("Creation Date: "+player.getCreationDate().toString());
			lblCreationDate.setForeground(Color.BLACK);
			lblCreationDate.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
			lblCreationDate.setBounds(69, 103, 521, 45);
			frame.getContentPane().add(lblCreationDate);
			
			String Gender = "";
			if(player.getGinder().equals("M") || player.getGinder().equals("m")) Gender = "Male";
			else  Gender = "Female";
			
			JLabel lblGender = new JLabel("Gender: "+Gender);
			lblGender.setForeground(Color.BLACK);
			lblGender.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
			lblGender.setBounds(69, 151, 521, 45);
			frame.getContentPane().add(lblGender);
			
			JLabel lblNickname = new JLabel("NickName: "+player.getNickName());
			lblNickname.setForeground(Color.BLACK);
			lblNickname.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
			lblNickname.setBounds(69, 195, 521, 45);
			frame.getContentPane().add(lblNickname);
			
			JLabel lblTotalPoints = new JLabel("Total Points: "+player.getPointsCalculatedField());
			lblTotalPoints.setForeground(Color.BLACK);
			lblTotalPoints.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
			lblTotalPoints.setBounds(69, 242, 521, 45);
			frame.getContentPane().add(lblTotalPoints);
			
			JLabel lblTotalWins = new JLabel("Total Wins: " + player.getWinsCalculatedField());
			lblTotalWins.setForeground(Color.BLACK);
			lblTotalWins.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
			lblTotalWins.setBounds(69, 283, 521, 45);
			frame.getContentPane().add(lblTotalWins);
			
			Country c = DBConnection.getCountry(player.getCountryID());
			JLabel lblCountry = new JLabel("Country: "+c.getName());
			lblCountry.setForeground(Color.BLACK);
			lblCountry.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
			lblCountry.setBounds(69, 329, 521, 45);
			frame.getContentPane().add(lblCountry);
			
			JLabel lblPassword = new JLabel("Password: "+ player.getPassword());
			lblPassword.setForeground(Color.BLACK);
			lblPassword.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
			lblPassword.setBounds(69, 376, 521, 45);
			frame.getContentPane().add(lblPassword);
			
			JLabel label = new JLabel("");
			Image image = new ImageIcon(this.getClass().getResource("/profile-icon (1).png")).getImage();
			label.setIcon(new ImageIcon(image));
			label.setBounds(6, 0, 51, 69);
			frame.getContentPane().add(label);
			
			frame.setVisible(true);
		//	else player = PlayerPage
	}
}
