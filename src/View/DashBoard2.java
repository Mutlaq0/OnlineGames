package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import Connection.DBConnection;
import Model.PlayedIn;
import Model.Player;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class DashBoard2 extends JFrame {

	private JPanel contentPane;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashBoard2 window = new DashBoard2();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DashBoard2() {
		setVisible(true);
		setBounds(100,100,1275,700);
		setBackground(new Color(47, 79, 79));
		setExtendedState(frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		getContentPane().setLayout(null);
		

		
		
		ArrayList<Integer> allId= DBConnection.getPlayersID();
		DefaultPieDataset dataSet = new DefaultPieDataset();
		DefaultPieDataset dataSet1 = new DefaultPieDataset();
		ArrayList<String> values1 = new ArrayList<>();
		ArrayList<String> values2 = new ArrayList<>();
		

		
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
		
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ArrayList<Integer> allId1= DBConnection.getPlayersID();
		
		for (Integer id : allId1) {
			Player player = DBConnection.getPlayer(id);
			dataset.setValue(player.getWinsCalculatedField(), "Wins", player.getFirstName());
			
		}
		
		JFreeChart winChart = ChartFactory.createBarChart("Wins Per Player", "Name", "Wins", dataset, PlotOrientation.VERTICAL, false, true, false);
		
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		ArrayList<Integer> allId2= DBConnection.getPlayersID();
		
		for (Integer id : allId2) {
			Player player = DBConnection.getPlayer(id);
			dataset2.setValue(DBConnection.getCountBestOf100(player.getPlayerID()), "Best100", player.getFirstName());
			
		}
		
		JFreeChart Chart2 = ChartFactory.createBarChart("Best100 Count", "Name", "Played", dataset2, PlotOrientation.VERTICAL, false, true, false);
		CategoryPlot p2 = Chart2.getCategoryPlot();
		p2.setRangeGridlinePaint(Color.black);
		
		
		
		DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
		
		for (Integer id : allId1) {
			Player player = DBConnection.getPlayer(id);
			dataset3.setValue(DBConnection.getCountGamesWithFriends(player.getPlayerID()), "With Friends", player.getFirstName());
			
		}
		
		JFreeChart chart3 = ChartFactory.createBarChart("With Friends Count", "Name", "Played", dataset3, PlotOrientation.VERTICAL, false, true, false);
		
		validate();
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0),Color.LIGHT_GRAY));
		panel_2.setBackground(new Color(47, 79, 79));
		panel_2.setBounds(10, 34, 1880, 1026);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		ChartPanel cp = new ChartPanel(winChart);
		cp.setBackground(new Color(47, 79, 79));
		cp.setMouseWheelEnabled(true);
		cp.setVisible(true);
		
			

			
			JPanel panel = new JPanel();
			panel.setBounds(272, 55, 690, 430);
			panel_2.add(panel);
			panel.add(cp);
			ChartPanel cp2 = new ChartPanel(Chart2);
			cp2.setMouseWheelEnabled(true);
			cp2.setVisible(true);
			
			ChartPanel cp3 = new ChartPanel(chart3);
			cp3.setMouseWheelEnabled(true);
			cp3.setVisible(true);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(959, 55, 677, 430);
			panel_2.add(panel_1);
			panel_1.add(cp2);
			
			
			
			
			JPanel panel_3 = new JPanel();
			panel_3.setBounds(592, 530, 690, 418);
			panel_3.add(cp3);
			panel_2.add(panel_3);
		
		JButton btnPrev = new JButton("< Previous Page");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoard db = new DashBoard();
				db.setVisible(true);
				dispose();
			}
		});
		btnPrev.setBounds(23, 11, 136, 23);
		getContentPane().add(btnPrev);
		
		
		
		
		

		

		
		
		pack();
		setResizable(false);
		show();

	}
}
