package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import Connection.DBConnection;
import Model.PlayedIn;
import Model.Player;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class DashBoard extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JTable q1Tbl;
	private JTable qTbl;
	private JTable q3Tbl;
	private JTable q4Tbl;
	private JTable q5Tbl;
	private JTable q6Tbl;
	private JTable q7Tbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashBoard window = new DashBoard();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DashBoard() {
		setVisible(true);
		setBounds(100,100,1275,700);
		setExtendedState(frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		getContentPane().setLayout(null);
		
		JLabel l1 = new JLabel("Please select a player:");
		l1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l1.setBounds(37, 57, 184, 20);
		getContentPane().add(l1);
		
		JComboBox pBox = new JComboBox();
		pBox.setBounds(231, 60, 334, 20);
		getContentPane().add(pBox);
		

		
		
		ArrayList<Integer> allId= DBConnection.getPlayersID();
		DefaultPieDataset dataSet = new DefaultPieDataset();
		DefaultPieDataset dataSet1 = new DefaultPieDataset();
		ArrayList<String> values1 = new ArrayList<>();
		ArrayList<String> values2 = new ArrayList<>();
		
		for (Integer id : allId) {
			Player player = DBConnection.getPlayer(id);
			pBox.addItem(player.getFirstName() + " " + player.getLastName() + ", ID = " + player.getPlayerID());
		}
		
		for (Integer id : allId) {
			Player player = DBConnection.getPlayer(id);
			dataSet.setValue("Games Won", player.getWinsCalculatedField());
			dataSet.setValue("Games Lost", DBConnection.getPlayerNumberOfGames(player.getPlayerID()));
			dataSet1.setValue("Best Of 100 Games", DBConnection.getCountBestOf100(player.getPlayerID()));
			dataSet1.setValue("Games With Friends", DBConnection.getCountGamesWithFriends(player.getPlayerID()));
			values1 = DBConnection.getBest100GameIDPoints(player.getPlayerID());
			values2 = DBConnection.getFriendsMatchGameIDPoints(player.getPlayerID());
			break;
		}
		
		DefaultCategoryDataset lineChartDataset = new DefaultCategoryDataset();
		String line1 = "Best Of 100 Match";
		String line2 = "Match With Friends";
		for(int i = 0 ; i<values1.size();i+=2) {
			lineChartDataset.addValue(Integer.parseInt(values1.get(i+1)), line1, values1.get(i));
		}
		for(int i = 0 ; i<values2.size();i+=2) {
			lineChartDataset.addValue(Integer.parseInt(values2.get(i+1)), line2, values2.get(i));
		}
		
		LineChart panel_1 = new LineChart(lineChartDataset, "Games Line Chart", "GameID", "Points");
		panel_1.setBounds(938, 104, 953, 384);
		getContentPane().add(panel_1);
		
		Pie panel = new Pie(dataSet, "Wins Loses Chart");
		panel.setBackground(new Color(47, 79, 79));
		panel.setForeground(new Color(47, 79, 79));
		panel.setBorder(null);
		panel.setBounds(482, 104, 446, 384);
		getContentPane().add(panel);
		Pie pie = new Pie(dataSet1, "Games Types Chart");
		pie.setForeground(new Color(47, 79, 79));
		pie.setBackground(new Color(47, 79, 79));
		pie.setBorder(null);
		pie.setBounds(37, 104, 446, 384);
		getContentPane().add(pie);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(47, 79, 79));
		panel_2.setBackground(new Color(47, 79, 79));
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), Color.LIGHT_GRAY));
		panel_2.setBounds(10, 514, 1897, 529);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(376, 67, 481, 138);
		panel_2.add(scrollPane);
		
		q1Tbl = new JTable();
		scrollPane.setViewportView(q1Tbl);
		q1Tbl.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Nick Name", "In Best of 100", "In FriendsGame"
			}
		));
		
		JLabel lblQuery_1 = new JLabel("Query 2:");
		lblQuery_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuery_1.setBounds(376, 42, 65, 14);
		panel_2.add(lblQuery_1);
		
		JLabel lblQuery = new JLabel("Query 1:");
		lblQuery.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuery.setBounds(32, 42, 75, 14);
		panel_2.add(lblQuery);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 67, 333, 138);
		panel_2.add(scrollPane_1);
		
		qTbl = new JTable();
		qTbl.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Nick Name"
			}
		));
		scrollPane_1.setViewportView(qTbl);
		
		JLabel lblQuery_2 = new JLabel("Query 3:");
		lblQuery_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuery_2.setBounds(877, 42, 65, 14);
		panel_2.add(lblQuery_2);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(877, 67, 341, 138);
		panel_2.add(scrollPane_2);
		
		q3Tbl = new JTable();
		q3Tbl.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Nick Name"
			}
		));
		scrollPane_2.setViewportView(q3Tbl);
		
		JLabel lblQuery_3 = new JLabel("Query 4:");
		lblQuery_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuery_3.setBounds(32, 248, 75, 14);
		panel_2.add(lblQuery_3);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(32, 273, 970, 195);
		panel_2.add(scrollPane_3);
		
		q4Tbl = new JTable();
		q4Tbl.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Nick Name", "Game type", "Game count", "First game", "Last game"
			}
		));
		scrollPane_3.setViewportView(q4Tbl);
		
		JLabel lblQuery_4 = new JLabel("Query 5:");
		lblQuery_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuery_4.setBounds(1082, 249, 65, 14);
		panel_2.add(lblQuery_4);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(1070, 273, 787, 195);
		panel_2.add(scrollPane_4);
		
		q5Tbl = new JTable();
		q5Tbl.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Nick Name", "Gift ID", "Shot Dest", "Rank ID", "Rank Name"
			}
		));
		scrollPane_4.setViewportView(q5Tbl);
		
		JLabel lblQuery_5 = new JLabel("Query 6:");
		lblQuery_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuery_5.setBounds(1239, 42, 89, 14);
		panel_2.add(lblQuery_5);
		
		JLabel lblQuery_5_1 = new JLabel("Query 7:");
		lblQuery_5_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuery_5_1.setBounds(1574, 42, 89, 14);
		panel_2.add(lblQuery_5_1);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(1239, 67, 313, 138);
		panel_2.add(scrollPane_5);
		
		q6Tbl = new JTable();
		q6Tbl.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Gift ID", "Shot Destination", "Price"
			}
		));
		scrollPane_5.setViewportView(q6Tbl);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(1574, 67, 287, 138);
		panel_2.add(scrollPane_6);
		
		q7Tbl = new JTable();
		q7Tbl.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"ID", "Name", "Nick Name"
			}
		));
		scrollPane_6.setViewportView(q7Tbl);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setForeground(new Color(47, 79, 79));
		panel_2_1.setBackground(new Color(47, 79, 79));
		panel_2_1.setBounds(10, 34, 1897, 467);
		getContentPane().add(panel_2_1);
		panel_2_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), Color.LIGHT_GRAY));
		
		
		JButton btnNext = new JButton("Next Page >");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoard2 db = new DashBoard2();
				db.setVisible(true);
				dispose();
			}
		});
		btnNext.setBounds(10, 13, 115, 23);
		getContentPane().add(btnNext);
		

		pBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp = ((String) pBox.getSelectedItem()).substring(((String) pBox.getSelectedItem()).lastIndexOf("=") + 1);
				String idS = temp.substring(temp.lastIndexOf(" ")+1);
				int id = Integer.parseInt(idS);
				for (Integer idd : allId) {
				if(id ==idd ) {
					Player player = DBConnection.getPlayer(id);
					ArrayList<String> values3 = new ArrayList<>();
					ArrayList<String> values4 = new ArrayList<>();
					dataSet.setValue("Games Won", player.getWinsCalculatedField());
					dataSet.setValue("Games Lost", DBConnection.getPlayerNumberOfGames(player.getPlayerID()));
					dataSet1.setValue("Best Of 100 Games", DBConnection.getCountBestOf100(player.getPlayerID()));
					dataSet1.setValue("Games With Friends", DBConnection.getCountGamesWithFriends(player.getPlayerID()));
					values3 = DBConnection.getBest100GameIDPoints(player.getPlayerID());
					values4 = DBConnection.getFriendsMatchGameIDPoints(player.getPlayerID());
					Pie panel = new Pie(dataSet, "Wins Loses Chart");
					panel.setBackground(new Color(47, 79, 79));
					panel.setForeground(new Color(47, 79, 79));
					panel.setBorder(null);
					panel.setBounds(495, 139, 446, 384);
					getContentPane().add(panel);
					
					
					Pie pie = new Pie(dataSet1, "Games Types Chart");
					pie.setForeground(new Color(47, 79, 79));
					pie.setBackground(new Color(47, 79, 79));
					pie.setBorder(null);
					pie.setBounds(50, 139, 446, 384);
					getContentPane().add(pie);
					
					DefaultCategoryDataset lineChartDataset = new DefaultCategoryDataset();
					String line1 = "Best Of 100 Match";
					String line2 = "Match With Friends";
					for(int i = 0 ; i<values3.size();i+=2) {
						lineChartDataset.addValue(Integer.parseInt(values3.get(i+1)), line1, values3.get(i));
					}
					for(int i = 0 ; i<values4.size();i+=2) {
						lineChartDataset.addValue(Integer.parseInt(values4.get(i+1)), line2, values4.get(i));
					}
					}
				}
			}
		});
		
		ArrayList<String> Q2 = DBConnection.getQuery2();
		ArrayList<String> Q1 = DBConnection.getQuery1();
		ArrayList<String> Q3 = DBConnection.getQuery3();
		ArrayList<String> Q4 = DBConnection.getQuery4();
		ArrayList<String> Q5 = DBConnection.getQuery5();
		ArrayList<String> Q6 = DBConnection.getQuery6();
		ArrayList<String> Q7 = DBConnection.getQuery7();
		
		Object[] data = new Object[5];
		
		for (String string : Q2) {
			String[] afterSplit = string.split(", ");
		    for (int i=0; i < afterSplit.length; i++)
		    {
				data[i] = afterSplit[i];
		    }
		    ((DefaultTableModel)q1Tbl.getModel()).addRow(data);
		}
		
		Object[] data2 = new Object[3];
		for (String string : Q1) {
			String[] afterSplit = string.split(", ");
		    for (int i=0; i < afterSplit.length; i++)
		    {
		    	data2[i] = afterSplit[i];
		    }
		    ((DefaultTableModel)qTbl.getModel()).addRow(data2);
		}
		
		Object[] data3 = new Object[3];
		for (String string : Q3) {
			String[] afterSplit = string.split(", ");
		    for (int i=0; i < afterSplit.length; i++)
		    {
		    	data3[i] = afterSplit[i];
		    }
		    ((DefaultTableModel)q3Tbl.getModel()).addRow(data3);
		}
		
		Object[] data4 = new Object[7];
		for (String string : Q4) {
			String[] afterSplit = string.split(", ");
		    for (int i=0; i < afterSplit.length; i++)
		    {
		    	data4[i] = afterSplit[i];
		    }
		    ((DefaultTableModel)q4Tbl.getModel()).addRow(data4);
		}
		
		Object[] data5 = new Object[7];
		for (String string : Q5) {
			String[] afterSplit = string.split(", ");
		    for (int i=0; i < afterSplit.length; i++)
		    {
		    	data5[i] = afterSplit[i];
		    }
		    ((DefaultTableModel)q5Tbl.getModel()).addRow(data5);
		}
		
		
		
		Object[] data6 = new Object[5];
		
		for (String string : Q6) {
			String[] afterSplit = string.split(", ");
		    for (int i=0; i < afterSplit.length; i++)
		    {
				data6[i] = afterSplit[i];
		    }
		    ((DefaultTableModel)q6Tbl.getModel()).addRow(data6);
		}
		
		Object[] data7 = new Object[5];
		for (String string : Q7) {
			String[] afterSplit = string.split(", ");
		    for (int i=0; i < afterSplit.length; i++)
		    {
				data7[i] = afterSplit[i];
		    }
		    ((DefaultTableModel)q7Tbl.getModel()).addRow(data7);
		}
		
		pack();
		setResizable(false);
		show();

	}
}
