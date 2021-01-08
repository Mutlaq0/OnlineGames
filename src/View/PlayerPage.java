package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Connection.DBConnection;
import Model.Player;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.border.LineBorder;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.Color;



public class PlayerPage extends JFrame {

	private JPanel contentPane;
	public static  Player player;
	int posX=0,posY=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerPage frame = new PlayerPage();
					
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

	/**
	 * Create the frame.
	 */
	public PlayerPage() {

		player = DBConnection.getPlayer(LoginPage.playerId);
		if(player==null) player =DBConnection.getPlayer(4);
		setTitle("Online Games");
		setBounds(100,100,1275,700);
		setUndecorated(true);
		setLocationRelativeTo(null);
		
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(47, 79, 79));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128)));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel paneMenu = new JPanel();
		paneMenu.setBackground(new Color(102, 204, 204));
		paneMenu.setBounds(0, 0, 321, 800);
		contentPane.add(paneMenu);
		paneMenu.setLayout(null);
		
		
		
		Image image = new ImageIcon(this.getClass().getResource("/profile-icon.png")).getImage();
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(image));
		label.setBounds(81, 13, 135, 170);
		paneMenu.add(label);
		
		JLabel lblWelcome = new JLabel("Welcome " +player.getFirstName()+" "+player.getLastName());
		lblWelcome.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
		lblWelcome.setBounds(12, 178, 304, 41);
		paneMenu.add(lblWelcome);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(102, 204, 204));
		panel_2.setBounds(12, 232, 304, 58);
		paneMenu.add(panel_2);
		panel_2.setLayout(null);
		
		
		Image image2 = new ImageIcon(this.getClass().getResource("/Profile-icon (1).png")).getImage();
		JLabel lblAdsasd = new JLabel("Personal Information");
		lblAdsasd.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
		lblAdsasd.setIcon(new ImageIcon(image2));
		lblAdsasd.setBounds(0, 0, 304, 58);
		panel_2.add(lblAdsasd);
		lblAdsasd.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				PersonalInfo pi = new PersonalInfo();
				
			}
		});
		
		JLabel lblGamesList = new JLabel("Games History List");
		Image imagelist = new ImageIcon(this.getClass().getResource("/list.png")).getImage();
		lblGamesList.setIcon(new ImageIcon(imagelist));
		lblGamesList.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
		lblGamesList.setBounds(12, 291, 304, 58);
		paneMenu.add(lblGamesList);
		lblGamesList.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				GamesList gl = new GamesList();
				gl.setVisible(true);
			}
		});
		
		JLabel lblNewFriendsMatch = new JLabel("New Friends Match");
		Image imageNew = new ImageIcon(this.getClass().getResource("/write-icon.png")).getImage();
		lblNewFriendsMatch.setIcon(new ImageIcon(imageNew));
		lblNewFriendsMatch.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
		lblNewFriendsMatch.setBounds(12, 353, 304, 58);
		paneMenu.add(lblNewFriendsMatch);
		
		lblNewFriendsMatch.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				NewFriendsMatchPage newMatch = new NewFriendsMatchPage();
				newMatch.setVisible(true);
			}
		});
		
		Image logoutIm = new ImageIcon(this.getClass().getResource("/logout-icon.png")).getImage();
		JLabel lblLogOut = new JLabel("Log Out");
		lblLogOut.setIcon(new ImageIcon(logoutIm));
		lblLogOut.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
		lblLogOut.setBounds(0, 456, 304, 58);
		paneMenu.add(lblLogOut);
		lblLogOut.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginPage newLogIn = new LoginPage();
				newLogIn.getJframe().setVisible(true);
				dispose();
				
			}
		});
		
		Image imageAbout = new ImageIcon(this.getClass().getResource("/trophy.png")).getImage();
		JLabel lblAbout = new JLabel("Achievements");
		lblAbout.setIcon(new ImageIcon(imageAbout));
		lblAbout.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
		lblAbout.setBounds(12, 405, 304, 58);
		paneMenu.add(lblAbout);
		lblAbout.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				AchievementsPage ap = new AchievementsPage();
				ap.setVisible(true);
			}
		});
		
		
		
		
		
		Image image1 = new ImageIcon(this.getClass().getResource("/X.png")).getImage();
		JLabel label_1 = new JLabel("");
		label_1.setBounds(1236, 0, 39, 55);
		label_1.setIcon(new ImageIcon(image1));
		contentPane.add(label_1);
		
		
		DefaultPieDataset dataSet = new DefaultPieDataset();
		dataSet.setValue("Games Won", player.getWinsCalculatedField());
		dataSet.setValue("Games Lost", DBConnection.getPlayerNumberOfGames(player.getPlayerID()));
		Pie panel = new Pie(dataSet, "Wins Loses Chart");
		panel.setBackground(new Color(47, 79, 79));
		panel.setForeground(new Color(47, 79, 79));
		panel.setBorder(null);
		panel.setBounds(766, 0, 446, 384);
		contentPane.add(panel);
		
		DefaultPieDataset dataSet1 = new DefaultPieDataset();
		dataSet1.setValue("Best Of 100 Games", DBConnection.getCountBestOf100(player.getPlayerID()));
		dataSet1.setValue("Games With Friends", DBConnection.getCountGamesWithFriends(player.getPlayerID()));
		Pie pie = new Pie(dataSet1, "Games Types Chart");
		pie.setForeground(new Color(47, 79, 79));
		pie.setBorder(null);
		pie.setBackground(new Color(47, 79, 79));
		pie.setBounds(322, 0, 446, 384);
		contentPane.add(pie);
		
		
		
		DefaultCategoryDataset lineChartDataset = new DefaultCategoryDataset();
		String line1 = "Best Of 100 Match";
		String line2 = "Match With Friends";
		ArrayList<String> values1 = DBConnection.getBest100GameIDPoints(player.getPlayerID());
		ArrayList<String> values2 = DBConnection.getFriendsMatchGameIDPoints(player.getPlayerID());
		for(int i = 0 ; i<values1.size();i+=2) {
			lineChartDataset.addValue(Integer.parseInt(values1.get(i+1)), line1, values1.get(i));
		}
		for(int i = 0 ; i<values2.size();i+=2) {
			lineChartDataset.addValue(Integer.parseInt(values2.get(i+1)), line2, values2.get(i));
		}
		
		LineChart panel_1 = new LineChart(lineChartDataset, "Games Line Chart", "GameID", "Points");
		panel_1.setBounds(322, 384, 953, 316);
		contentPane.add(panel_1);
			
		label_1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Exit?","Confirmation",JOptionPane.YES_NO_OPTION)==0) {
					dispose();
				}
				
			}
		});
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/Joystick-icon.png"));
		setIconImage(logo.getImage());
		player = DBConnection.getPlayer(LoginPage.playerId);		
		/*
		 * allows dragging the Frame
		 */
		this.addMouseListener(new MouseAdapter()
		{
		   public void mousePressed(MouseEvent e)
		   {
		      posX=e.getX();
		      posY=e.getY();
		   }
		});
		this.addMouseMotionListener(new MouseAdapter()
		{
		     public void mouseDragged(MouseEvent evt)
		     {
				//sets frame position when mouse dragged			
				setLocation (evt.getXOnScreen()-posX,evt.getYOnScreen()-posY);
							
		     }
		});
		
		
		
		
		
		
}
	
	
	

	
	
	
	
	private final Action exitAction = new AbstractAction("Exit")
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    };
}
