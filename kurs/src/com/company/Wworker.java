package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Wworker {
    int centerX = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
    int centerY = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
    private JButton b1;
    private JButton b2;
    private JButton b3, b4;
    private JTable table;
    public Wworker () {
        ////////////
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

        ///////////
        JFrame frame = new JFrame("Окно сотрудника");
        frame.setLayout(null);

        JRadioButton j1 = new JRadioButton("Клиенты");
        JRadioButton j2 = new JRadioButton("Заявки");

        ButtonGroup G = new ButtonGroup();
        G.add(j1);
        G.add(j2);



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b1 = new JButton("<html>Формировать <br> гистограмму</html>");
        b2 = new JButton("<html>Формировать <br> заявку</html>");
        b3 = new JButton("<html>Формировать <br> справку</html>");
        b4 = new JButton("Выход");
        table = new JTable(3, 5);

        b1.setBounds(20, 40, 140, 30);
        b2.setBounds(20, 100, 140, 30);
        b3.setBounds(20, 160, 140, 30);
        b4.setBounds(20, 460, 140, 30);
        j1.setBounds(20, 250, 100, 20);
        j2.setBounds(20, 280, 100, 20);


        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);
        frame.add(table);

        TableModelClient model = new TableModelClient();
        TableModelRequest model2 = new TableModelRequest();

        JTable table = new JTable(model);
        JTable tablem = new JTable(model2);
         JScrollPane scrollPane = new JScrollPane(table);
        JScrollPane scrollPane2 = new JScrollPane(tablem);

        scrollPane.setBounds(180, 40, 1200, 450);
        scrollPane2.setBounds(180, 40, 1200, 450);

        model.addDate(con);
        model2.addDate(con);

        frame.add(j1);
        frame.add(j2);

        j2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.remove(scrollPane);
                frame.add(scrollPane2);

            }
        });
        j1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.remove(scrollPane2);
                frame.add(scrollPane);

            }
        });

        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                com.company.MyWindow app2 = new MyWindow();
                frame.setVisible(false);
            }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    Request app2 = new Request();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                frame.setVisible(false);
            }
        });

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Histogramma app2 = new Histogramma();
                frame.setVisible(false);
            }
        });

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    Result app2 = new Result();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                frame.setVisible(false);
            }
        });


        frame.setSize(new Dimension(1420, 570));
        frame.setLocation(100, 100);
        frame.setVisible(true);
    }
}
