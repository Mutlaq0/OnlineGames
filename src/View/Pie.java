package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import Connection.DBConnection;

public class Pie extends JPanel{

	DefaultPieDataset dataSet;
	public Pie(DefaultPieDataset dataSet, String title) {

        super();
      this.dataSet =dataSet;


      setBounds(953, 70, 321, 217);
        JFreeChart chart = ChartFactory.createPieChart3D(title, dataSet, true, true, true);
        
        PiePlot plot =  (PiePlot) chart.getPlot();
        plot.setCircular(true);
        plot.setLabelFont(new Font("Monotype Corsiva", Font.PLAIN, 16));
        chart.getTitle().setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));

        ChartPanel chartPanel = new ChartPanel(chart);
        chart.getRenderingHints().put(JFreeChart.KEY_SUPPRESS_SHADOW_GENERATION, Boolean.TRUE);
        chartPanel.setPreferredSize(new Dimension(360, 360));
        Color trans = new Color(0xFF, 0xFF, 0xFF, 0);
        		chart.setBackgroundPaint(trans);
        		plot .setBackgroundPaint(trans);
        		PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
        	            "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        	        plot.setLabelGenerator(gen);
        chartPanel.setBackground(new Color(47, 79, 79));
        add(chartPanel);
        chartPanel.setLayout(null);
	}
	public static void main(String [] args) {
		DefaultPieDataset dataSet = new DefaultPieDataset();
		dataSet.setValue("Games Won",5);
		dataSet.setValue("Games Lost", 23);
		Pie panel = new Pie(dataSet, "Games Analysis");
		JFrame frame = new JFrame();
		frame.setBounds(700, 300, 500, 360);
		frame.add(panel);
		frame.setVisible(true);
		
	}
	
}
