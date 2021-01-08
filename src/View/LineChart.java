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
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart extends JPanel {
	DefaultCategoryDataset dataSet;
	public LineChart(DefaultCategoryDataset dataSet, String title,String xAxis,String yAxis ) {

        super();
      this.dataSet =dataSet;


      setBounds(953, 500, 700, 1000);
        JFreeChart chart = ChartFactory.createLineChart(title, xAxis, yAxis, dataSet);
        
        chart.getTitle().setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));

        ChartPanel chartPanel = new ChartPanel(chart);
        chart.getRenderingHints().put(JFreeChart.KEY_SUPPRESS_SHADOW_GENERATION, Boolean.TRUE);
        chartPanel.setPreferredSize(new Dimension(953, 316));
        Color trans = new Color(0xFF, 0xFF, 0xFF, 0);
        chart.setBackgroundPaint(Color.white);
        chartPanel.setBackground(new Color(47, 79, 79));
        add(chartPanel);
        chartPanel.setLayout(null);
	}
	
	public static void main(String [] args) {
		JFrame frame = new JFrame ();
		frame.setSize(400, 600);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String series1 = "Best100";
		String series2 = "Game With Friends";
		dataset.addValue(200, series1, "2016-1019");
		dataset.addValue(150, series1, "2016-9-19");
		dataset.addValue(100, series1, "2016-8-19");
		dataset.addValue(400, series2, "2016-12-17");
		dataset.addValue(350, series2, "2016-11-17");
		dataset.addValue(300, series2, "2016-10-14");
		LineChart lc = new LineChart(dataset, "try", "Points", "Date");
		frame.setBounds(100, 100, 900, 700);
		frame.add(lc);
		frame.setVisible(true);
	}
	
	
}
