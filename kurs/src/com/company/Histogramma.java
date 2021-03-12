package com.company;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Histogramma {
    public Histogramma(){
        JButton but = new JButton("Назад");

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String userName = "root";
        String password = "root";
        String connectionURL = "jdbc:mysql://localhost:3306/mysystem?verifyServerCertificate=false&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        Connection con = null;
        try {
            con = DriverManager.getConnection(connectionURL, userName, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Statement st = null;
        try {
            st = con.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // Результирующий запрос
        ResultSet rs = null;

        try {
            rs = st.executeQuery(" select count(mark), breakr, mark from request group by mark, breakr;");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        while (true){
            try {
                if (!rs.next()) break;
                String []row = {rs.getString("count(mark)"),
                        rs.getString("breakr"), rs.getString("mark"),
                };
                dataset.addValue(rs.getInt("count(mark)"), rs.getString("breakr"), rs.getString("mark"));
                //addDate(row);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        JFreeChart chart = ChartFactory.createBarChart( "Гистограмма неисправностей",
                "Модели ТВ",                   // x-axis label
                "Неисправность",                // y-axis label
                dataset, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setRangeGridlinePaint(Color.BLACK);


        but.setBounds(840, 660, 80, 20);

        ChartPanel chartframe = new ChartPanel(chart, true);
        chartframe.setVisible(true);
        chartframe.setLocation(40, 40);
        chartframe.setSize(800, 600);
        //chartframe.add(but);

        JFrame frame = new JFrame("Name");
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setSize(1000, 800);
        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(false);
                Wworker app2 = new Wworker();
            }
        });

        frame.add(chartframe);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(but);
    }
}
