package Main2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Main2.StudentPortal;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author jeet
 */
public class Barchart extends StudentPortal {

    void makeBar(String[] Sub, int[] att) {
        DefaultCategoryDataset Dcd = new DefaultCategoryDataset();
        for (int i = 0; i < att.length; i++) {
            Dcd.setValue(att[i], "Attendance", Sub[i]);
        }
        JFreeChart jchart = ChartFactory.createBarChart("Attendance ", "Subjects", "Attendance in per(%)", Dcd, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = jchart.getCategoryPlot();
        jchart.setBackgroundPaint(Color.gray);
        plot.setRangeGridlinePaint(Color.BLACK);
        ChartFrame chartFrm = new ChartFrame("Attendance", jchart, true);
        chartFrm.setVisible(true);
        chartFrm.setSize(930, 570);
        chartFrm.setLocationRelativeTo(null);
    }
}
