package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.Color;
import java.util.Map;

public class Graphics extends JFrame {

    public Graphics(Map<String, Double> netWorth) {
        initUI(netWorth);
    }

    private void initUI(Map<String, Double> netWorth) {

        CategoryDataset dataset = createDataset(netWorth);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);

        chartPanel.setVerticalAxisTrace(true);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Bar chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private CategoryDataset createDataset(Map<String, Double> netWorth) {
        var dataset = new DefaultCategoryDataset();
        netWorth.forEach((key, value) -> dataset.setValue(value, "netWorth", key));
        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "NetWorth by countries",
                "Countries",
                "NetWorth",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);
    }
}