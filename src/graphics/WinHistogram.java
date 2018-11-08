package graphics;

import models.Season;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.ui.ApplicationFrame;

public class WinHistogram extends ApplicationFrame {
    private HistogramDataset histogram;
    private Season season;

    public WinHistogram(String title, Season season) {
        super(title);
        histogram = new HistogramDataset();
        this.season = season;
    }

    private void setBounds() {
    }

    public Season getSeason() {
        return season;
    }

    public HistogramDataset getHistogram() {
        return histogram;
    }

    public void setHistogram(HistogramDataset histogram) {
        this.histogram = histogram;
    }


}
