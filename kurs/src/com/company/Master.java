package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Master {

    public Master() throws SQLException {
        int centerX = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
        int centerY = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
        JLabel l1,l2, lf, li, lo;
        JButton butadd;
        JButton n;
        JButton s;

        ///////////////////
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
        Statement stmt = con.createStatement();
        Statement stmt2 = con.createStatement();

        Statement stmt3 = con.createStatement();
        ResultSet rs = stmt.executeQuery("select namep from sparep;");
        ArrayList<String> names = new ArrayList<String>();/* возвращаем список объектов для работы */
        while (rs.next()) {
            names.add(rs.getString(1));
        }

        String[] nameArr = new String[names.size()];
        nameArr = names.toArray(nameArr);

        ////////////

        JFrame frame = new JFrame("Личная информация о мастре");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComboBox comboBox = new JComboBox(nameArr);

        lf = new JLabel("Фамилия:");
        li = new JLabel("Имя:");
        lo = new JLabel("Отчество:");

        l1 = new JLabel("Спецификация:");
        l2 = new JLabel("Список запчастей:");
        TextField t1 = new TextField();
        TextField tf = new TextField();
        TextField ti = new TextField();
        TextField to = new TextField();
        n = new JButton("Назад");
        s = new JButton("Сохранить");
        butadd = new JButton("+");

        final String[] text = new String[1];
        comboBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                text[0] = (String) comboBox.getSelectedItem();
                //System.out.println(text[0]);
            }});

        lf.setBounds(40, 40, 150, 20);
        tf.setBounds(210, 40, 200, 20);
        li.setBounds(40, 80, 150, 20);
        ti.setBounds(210, 80, 200, 20);
        lo.setBounds(40, 120, 150, 20);
        to.setBounds(210, 120, 200, 20);
        l1.setBounds(40, 160, 150, 20);
        l2.setBounds(40, 200, 150, 20);
        t1.setBounds(210, 160, 200, 20);
        comboBox.setBounds(210, 200, 200, 20);
        n.setBounds(40, 240, 100, 30);
        s.setBounds(350, 240, 100, 30);
        butadd.setBounds(430, 200, 20, 20);

        frame.add(l1);
        frame.add(l2);
        frame.add(t1);
        frame.add(comboBox);
        frame.add(n);
        frame.add(s);
        frame.add(lf);
        frame.add(li);
        frame.add(lo);
        frame.add(tf);
        frame.add(ti);
        frame.add(to);
        frame.add(butadd);

        butadd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                try {
                    Sparep app2 = new Sparep();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                frame.setVisible(false);
            }
        });
        s.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ResultSet rs3 = null;
                try {
                    rs3 = stmt2.executeQuery("select id_master from master;");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                ArrayList<String> ids = new ArrayList<String>();/* возвращаем список объектов для работы */

                while (true) {
                    try {
                        if (!rs3.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        ids.add(rs3.getString(1));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
                int num = ids.size()+1;

                try {
                    stmt3.executeUpdate("insert into master (id_master, F, I, O, Spet, Lis)" +
                            "values (" + num  + ", '"+ tf.getText() +"', '"+ ti.getText() +"', '"+ to.getText() + "', '" + t1.getText() + "', '" +
                            text[0] + "')");
                    Admin app2 = new Admin();
                    frame.setVisible(false);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        n.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    Admin app2 = new Admin();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                frame.setVisible(false);
            }
        });
        ////////////////////

        /////////////////////////
        frame.setSize(500,330);
        frame.setLocation(centerX-250, centerY-250);
        frame.setVisible(true);
    }
}
