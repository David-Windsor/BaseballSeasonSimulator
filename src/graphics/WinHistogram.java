package graphics;

import models.Season;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.ui.ApplicationFrame;

import java.awt.*;
import java.text.NumberFormat;

public class WinHistogram extends ApplicationFrame {
    private HistogramDataset dataset;
    private Season season;
    private JFreeChart chart;

    public WinHistogram(String title, Season season) {
        super(title);
        dataset = new HistogramDataset();
        this.season = season;
        configDataset();
        chart = createChart();
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(960, 540));
        panel.setMouseWheelEnabled(false);
        panel.setMouseZoomable(true, false);
        setContentPane(panel);
    }

    private JFreeChart createChart() {
        JFreeChart c = ChartFactory.createHistogram("Win Distribution for Simulated Season", "Wins", "Amount of Teams",
                dataset, PlotOrientation.VERTICAL, true, false, false);
        XYPlot xyPlot = c.getXYPlot();
        xyPlot.setForegroundAlpha(0.75f);
        NumberAxis rangeAxis = (NumberAxis) xyPlot.getRangeAxis();
        rangeAxis.setNumberFormatOverride(NumberFormat.getPercentInstance());
        return c;
    }

    private void configDataset() {
        Double[] tempData = season.getWinNumbersAsDoubles();
        double[] data = new double[tempData.length];
        for (int i = 0; i < tempData.length; ++i) {
            data[i] = tempData[i];
        }
        dataset.setType(HistogramType.RELATIVE_FREQUENCY);
        dataset.addSeries("Wins", data, 10);
    }

    public Season getSeason() {
        return season;
    }

    public HistogramDataset getDataset() {
        return dataset;
    }

    public void setDataset(HistogramDataset dataset) {
        this.dataset = dataset;
    }


}
