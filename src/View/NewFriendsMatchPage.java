package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Connection.DBConnection;
import Model.CreateYourOwnMatchWithFriends;
import Model.Invited;
import Model.Player;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.swing.JButton;

public class NewFriendsMatchPage extends JFrame {

	private JFrame frame;
public static Player player;
private JTable table;
private JTable table_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewFriendsMatchPage window = new NewFriendsMatchPage();
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
	public NewFriendsMatchPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		player = DBConnection.getPlayer(LoginPage.playerId);
    	if(player==null) player =DBConnection.getPlayer(4);
    	setTitle("New Match With Friends");
    	getContentPane().setBackground(new Color(47, 79, 79));
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/Joystick-icon.png"));
		setIconImage(logo.getImage());
		getContentPane().setForeground(new Color(0, 0, 128));
		getContentPane().setFont(new Font("Monotype Corsiva", Font.PLAIN, 23));
		setBounds(100, 100, 763, 562);
		getContentPane().setLayout(null);
		
		JLabel lblCreateYourOwn = new JLabel("Create Your Own Match With Friends");
		lblCreateYourOwn.setForeground(Color.BLACK);
		lblCreateYourOwn.setFont(new Font("Monotype Corsiva", Font.PLAIN, 33));
		lblCreateYourOwn.setBounds(26, 13, 480, 45);
		getContentPane().add(lblCreateYourOwn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 100, 321, 297);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setRowHeaderView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] { 
					"Player ID","Name", "NickName"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		
		Object[] data = new Object[3];
		ArrayList<Player> friends = DBConnection.getFriendsOfPlayer(player.getPlayerID());
		for(Player p : friends) {
			data[0] = p.getPlayerID();
			data[1] = p.getFirstName()+" "+p.getLastName();
			data[2] = p.getNickName();
			((DefaultTableModel)table.getModel()).addRow(data);
		}
		
		JLabel lblFriendsTable = new JLabel("Select Friends From The Table:");
		lblFriendsTable.setForeground(Color.BLACK);
		lblFriendsTable.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
		lblFriendsTable.setBounds(12, 71, 301, 35);
		getContentPane().add(lblFriendsTable);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(409, 100, 321, 297);
		getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] { 
					"Player ID","Name", "NickName"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		
			
		
		JLabel lblSelectedFriends = new JLabel("Selected Friends :");
		lblSelectedFriends.setForeground(Color.BLACK);
		lblSelectedFriends.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
		lblSelectedFriends.setBounds(409, 71, 301, 35);
		getContentPane().add(lblSelectedFriends);
		
		JButton btnRemove = new JButton("Remove Friend");
		btnRemove.setFont(new Font("Monotype Corsiva", Font.PLAIN, 24));
		btnRemove.setBounds(483, 410, 161, 35);
		getContentPane().add(btnRemove);
		
		JButton btnAddFriend = new JButton("Add Friend");
		btnAddFriend.setFont(new Font("Monotype Corsiva", Font.PLAIN, 24));
		btnAddFriend.setBounds(88, 410, 161, 35);
		getContentPane().add(btnAddFriend);
		
		ArrayList<Player> alreadySelected = new ArrayList<Player>();
		
		ArrayList<Player> friendsToInvite = new ArrayList<Player>();
		
		btnAddFriend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			int rowSelected = 	table.getSelectedRow();
			int id = (int)((DefaultTableModel)table.getModel()).getValueAt(rowSelected, 0);
			Player pl = DBConnection.getPlayer(id);
			if(!alreadySelected.contains(pl)) {
				alreadySelected.add(pl);
				friendsToInvite.add(pl);
				Object[] data1 = new Object[3];
				data1[0] = pl.getPlayerID();
				data1[1] = pl.getFirstName()+" "+pl.getLastName();
				data1[2] = pl.getNickName();
				((DefaultTableModel)table_1.getModel()).addRow(data1);	
			}
				
			}
			});
		
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowSelected = table_1.getSelectedRow();
			    int id = (int)((DefaultTableModel)table_1.getModel()).getValueAt(rowSelected, 0);
			    Player pl = DBConnection.getPlayer(id);
			    alreadySelected.remove(pl);
			    friendsToInvite.remove(pl);
			    ((DefaultTableModel)table_1.getModel()).removeRow(rowSelected);
			}
		});
		
		
		JButton btnFinish = new JButton("Finish");
		btnFinish.setFont(new Font("Monotype Corsiva", Font.PLAIN, 24));
		btnFinish.setBounds(317, 461, 117, 35);
		getContentPane().add(btnFinish);
		btnFinish.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				long newGameID = DBConnection.getMaxCreatedGameID()+1;
				Date date = new Date();
				Timestamp currentTime = new Timestamp(date.getTime());
				CreateYourOwnMatchWithFriends newMatch = new CreateYourOwnMatchWithFriends(newGameID, currentTime, null, player.getPlayerID(), 0, 0);
				DBConnection.createNewMatchWithFriends(newMatch);
				for(Player p : friendsToInvite) {
                Invited invited =new Invited(newGameID, p.getPlayerID(), currentTime, null, 0, 1);
                System.out.println(invited);
                DBConnection.inviteFriends(invited);
				}
				
			}
		});
	}

}
