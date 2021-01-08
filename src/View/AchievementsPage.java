package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Connection.DBConnection;
import Model.Gift;
import Model.GiftRank;
import Model.GiftRankPlayer;
import Model.Player;
import Model.Ranks;
import javax.swing.JLabel;

public class AchievementsPage extends JFrame{


	public static Player player;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AchievementsPage window = new AchievementsPage();
				    window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AchievementsPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		player = DBConnection.getPlayer(LoginPage.playerId);
    	if(player==null) player =DBConnection.getPlayer(4);
    	setTitle("Player Achievements");
    	getContentPane().setBackground(new Color(47, 79, 79));
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/Joystick-icon.png"));
		setIconImage(logo.getImage());
		getContentPane().setForeground(new Color(0, 0, 128));
		getContentPane().setFont(new Font("Monotype Corsiva", Font.PLAIN, 23));
		setBounds(100, 100, 789, 513);
		getContentPane().setLayout(null);		

		
		ArrayList<GiftRankPlayer> giftRankPlayer = DBConnection.getGiftRankPlayer(player.getPlayerID());
		ArrayList<Gift> gifts = new ArrayList<Gift>();
		ArrayList<Ranks> ranks = new ArrayList<Ranks>();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 65, 687, 380);
		getContentPane().add(scrollPane);
		
		JTable table = new JTable();
		scrollPane.setRowHeaderView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] { 
					"Rank","Min Points","Gift", "Date Recieved", "Suspended", "Price","Short.Des"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		
		JLabel lblRankAndGift = new JLabel("Rank And Gift Achievements");
		lblRankAndGift.setForeground(Color.BLACK);
		lblRankAndGift.setFont(new Font("Monotype Corsiva", Font.PLAIN, 33));
		lblRankAndGift.setBounds(34, 13, 386, 45);
		getContentPane().add(lblRankAndGift);
		
		
		Image image = new ImageIcon(this.getClass().getResource("/medal.png")).getImage();
		JLabel label = new JLabel("");
		label.setBounds(391, 13, 46, 39);
		getContentPane().add(label);
		label.setIcon(new ImageIcon(image));
		
		Object[] data = new Object[7];
		for(GiftRankPlayer o : giftRankPlayer) {
			Ranks r = DBConnection.getRankByRankID(o.getRankID());
			Gift g = DBConnection.getGiftByGiftID(o.getGiftID());
			data[0] = r.getName();
			data[1] = r.getMinPoints();
			data[2] = g.getGiftID();
			data[3] = o.getDateRecieve();
			data[4] = o.isSuspended();
			data[5] = g.getPrice();
			data[6] = g.getShortDesc();
			((DefaultTableModel)table.getModel()).addRow(data);
		}
	}
}
