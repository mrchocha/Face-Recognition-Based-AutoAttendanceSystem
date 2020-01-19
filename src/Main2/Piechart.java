package Main2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.List;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultKeyedValuesDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author 
 */
public class Piechart{

    void piechr(int presant, int abset) {
        DefaultPieDataset pieDataset = new DefaultKeyedValuesDataset();
        pieDataset.setValue("present", presant);
        pieDataset.setValue("absent", abset);
        JFreeChart chart = ChartFactory.createPieChart("Total Attendance", pieDataset, true, true, true);        
        chart.setBackgroundPaint(Color.DARK_GRAY);
        PiePlot p = (PiePlot) chart.getPlot();
        p.setBaseSectionPaint(Color.DARK_GRAY);
        p.setSectionPaint("absent", Color.LIGHT_GRAY);
        if (presant >= 80) {
            p.setSectionPaint("present", Color.GREEN);
        } else {
            p.setSectionPaint("present", Color.RED);
        }
        ChartFrame frame = new ChartFrame("piechart", chart);
        frame.setVisible(true);
        frame.setSize(930, 570);
        frame.setLocationRelativeTo(null);
        

    }
}
