package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Connection.DBConnection;
import Model.BestOF100Playes;
import Model.CreateYourOwnMatchWithFriends;
import Model.Invited;
import Model.PlayedIn;
import Model.Player;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class GamesList extends JFrame{

	public static Player player;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GamesList window = new GamesList();
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
	public GamesList() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		player = DBConnection.getPlayer(LoginPage.playerId);
    	if(player==null) player =DBConnection.getPlayer(4);
    	setTitle("Personal information");
    	getContentPane().setBackground(new Color(47, 79, 79));
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/Joystick-icon.png"));
		setIconImage(logo.getImage());
		getContentPane().setForeground(new Color(0, 0, 128));
		getContentPane().setFont(new Font("Monotype Corsiva", Font.PLAIN, 23));
		setBounds(100, 100, 1013, 709);
		getContentPane().setLayout(null);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 40, 331, 279);
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] { 
					"GameID","Points", "Times Died"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		Object[] data = new Object[6];
		ArrayList<CreateYourOwnMatchWithFriends> matchesCreated = DBConnection.getCreatedOfPlayer(player.getPlayerID());
		for(CreateYourOwnMatchWithFriends m: matchesCreated) {
			data[0] = m.getGameID();
			data[1] = m.getGameStartDateTime();
			data[2] = m.getGameEndDateTime();
			data[3] = m.getPoints();
			data[4] = m.getTimesDied();
			((DefaultTableModel)table.getModel()).addRow(data);
		}
		JLabel lblGamesWithFriends = new JLabel("Created Games With Friends List");
		lblGamesWithFriends.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
		lblGamesWithFriends.setForeground(Color.BLACK);
		lblGamesWithFriends.setBounds(34, 0, 319, 48);
		getContentPane().add(lblGamesWithFriends);
		
		
		
		JLabel lblBestOf = new JLabel("Best Of 100 Playes Games List");
		lblBestOf.setForeground(Color.BLACK);
		lblBestOf.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
		lblBestOf.setBounds(394, -1, 319, 50);
		getContentPane().add(lblBestOf);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(387, 40, 596, 279);
		getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] { 
					"GameID","Exists ","Effectiveness","Accidents", "Points","Location","Overtakes"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(22, 370, 416, 279);
		getContentPane().add(scrollPane_2);
		
		table_2 = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(table_2);
		table_2.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] { 
					"GameID","Sent Date ","RecieveDate","Points", "Times Died"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		JLabel lblGamesInvitedTo = new JLabel("Games Invited To");
		lblGamesInvitedTo.setForeground(Color.BLACK);
		lblGamesInvitedTo.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
		lblGamesInvitedTo.setBounds(32, 320, 319, 48);
		getContentPane().add(lblGamesInvitedTo);
		
		
		
		JLabel lblSelectGameId = new JLabel("Select Game ID to Show Time Started and Ended: ");
		lblSelectGameId.setForeground(Color.BLACK);
		lblSelectGameId.setFont(new Font("Monotype Corsiva", Font.PLAIN, 22));
		lblSelectGameId.setBounds(450, 332, 419, 48);
		getContentPane().add(lblSelectGameId);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(485, 443, 460, 164);
		getContentPane().add(scrollPane_3);
		
		table_3 = new JTable();
		scrollPane_3.setViewportView(table_3);
		table_3.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] { 
					"Game ID", "Date/Time Start", "Date/Time End"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		ArrayList<PlayedIn> matchesBest100= DBConnection.getBest100GamesOfPlayer(player.getPlayerID());
		Object[] data1 = new Object[7];
		for(PlayedIn m: matchesBest100) {
			data1[0] = m.getGameID();
			data1[1] = m.getExists();
			data1[2] = m.getEffectiveness();
			data1[3] = m.getAccidents();
			data1[4] = m.getPoints();
			data1[5] = m.getLocation();
			data1[6] = m.getOvertakes();
			((DefaultTableModel)table_1.getModel()).addRow(data1);
		}
		
		ArrayList<Invited> matchesInvited = DBConnection.getInvitedOfPlayer(player.getPlayerID());
		Object[] data2 = new Object[5];
		for(Invited m: matchesInvited) {
			data2[0] = m.getGameID();
			data2[1] = m.getSentDate();
			data2[2] = m.getRecieveDate();
			data2[3] = m.getPoints();
			data2[4] = m.getTimesDied();
			
			((DefaultTableModel)table_2.getModel()).addRow(data2);
		}
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(881, 339, 102, 37);
		getContentPane().add(comboBox);
		
		JButton btnShowDatetime = new JButton("Show Date/Time");
		btnShowDatetime.setFont(new Font("Monotype Corsiva", Font.PLAIN, 22));
		btnShowDatetime.setBounds(600, 393, 193, 37);
		getContentPane().add(btnShowDatetime);
		
		JButton btnClearTabel = new JButton("Clear Table");
		btnClearTabel.setFont(new Font("Monotype Corsiva", Font.PLAIN, 16));
		btnClearTabel.setBounds(636, 612, 123, 27);
		getContentPane().add(btnClearTabel);
		for(PlayedIn m: matchesBest100) {
			comboBox.addItem(m.getGameID());
		}
		for(Invited m: matchesInvited) {
			comboBox.addItem(m.getGameID());
		}
		for(CreateYourOwnMatchWithFriends m: matchesCreated) {
			comboBox.addItem(m.getGameID());
		}
		
		btnShowDatetime.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String item =comboBox.getSelectedItem().toString(); 
				long gameId = Long.parseLong(item);
				Object[] data3 = new Object[3];
				if(DBConnection.getBest100ByGameID(gameId)!=null) {
					BestOF100Playes b = DBConnection.getBest100ByGameID(gameId);
					data3[0] = b.getGameID();
					data3[1] = b.getGameStartDateTime();
					data3[2] = b.getGameEndDateTime();
				}else if(DBConnection.getGameWithFriendsByGameID(gameId)!=null) {
					CreateYourOwnMatchWithFriends c = DBConnection.getGameWithFriendsByGameID(gameId);
					data3[0] = c.getGameID();
					data3[1] = c.getGameStartDateTime();
					data3[2] = c.getGameEndDateTime();
				}
				((DefaultTableModel)table_3.getModel()).addRow(data3);
				
				
				
			}
		});
		btnClearTabel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				((DefaultTableModel)table_3.getModel()).setRowCount(0);				
			}
		});
		
	}
}
