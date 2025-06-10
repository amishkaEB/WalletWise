package components;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PieChart extends JPanel {

    private DefaultPieDataset dataset;
    private PiePlot plot;

    public PieChart() {
        setLayout(new BorderLayout());
        setBackground(new Color(51,51,51));

        dataset = new DefaultPieDataset();
        
        JFreeChart chart = ChartFactory.createPieChart(
                "", dataset, true, true, false
        );

        plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})"));

        chart.setBackgroundPaint(new Color(0, 0, 0, 0));
        plot.setBackgroundPaint(new Color(0, 0, 0, 0));
        plot.setOutlinePaint(null);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setOpaque(false);
        add(chartPanel, BorderLayout.CENTER);
    }

    public void updateChart(List<Object[]> data) {
        dataset = new DefaultPieDataset();
        for (Object[] entry : data) {
            String name = (String) entry[0];
            int amount = Integer.parseInt(entry[2].toString());
            dataset.setValue(name, amount);
        }
        plot.setDataset(dataset);
        setChartColors(data);
        revalidate();
        repaint();
    }

    private void setChartColors(List<Object[]> data) {
        

        for (int i = 0; i < data.size(); i++) {
            Object[] entry = data.get(i);
            String name = (String) entry[0];
            Color color = (Color) entry[1];
            plot.setSectionPaint(i, color);
            
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pie Chart Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setBackground(Color.BLACK);

            List<Object[]> sampleData = List.of(
                    new Object[]{"Food", Color.RED, "500"},
                    new Object[]{"Salary", Color.BLUE, "1000"},
                    new Object[]{"Transport", Color.GREEN, "750"}
            );

            frame.add(new PieChart());
            frame.setVisible(true);
        });
    }
}
