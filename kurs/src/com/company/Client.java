package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class Client {
    private JLabel l1,l2,l3,l4, lf, li, lo;
    private TextField t1, t2, tf, ti, to, treq;
    private JButton n;
    private JButton s, cv;
    int centerX = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
    int centerY = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
    public String[] elements = new String[] {"Офис", "Склад", "Гараж",
            "Производство", "Столовая"};
    public Client() throws SQLException {
        JFrame frame = new JFrame("Личная информация о клиенте");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ////////////////////////////

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
        Statement stmt4 = con.createStatement();

        ResultSet rs = stmt.executeQuery("select res from request;");
        ResultSet rs2 = stmt2.executeQuery("select id from request;");
        ArrayList<String> names = new ArrayList<String>();/* возвращаем список объектов для работы */
        ArrayList<String> ids = new ArrayList<String>();/* возвращаем список объектов для работы */

        while (rs.next()) {
            names.add(rs.getString(1));
        }
        while (rs2.next()) {
            ids.add(rs2.getString(1));
        }

        String[] nameArr = new String[names.size()];
        nameArr = names.toArray(nameArr);

        String[] idsArr = new String[ids.size()];
        idsArr = ids.toArray(idsArr);

        ///////////////////
         JComboBox comboBox2 = new JComboBox(nameArr);

        lf = new JLabel("Фамилия:");
        li = new JLabel("Имя:");
        lo = new JLabel("Отчество:");
        l1 = new JLabel("Адрес:");
        l2 = new JLabel("Телефон:");
        l3 = new JLabel("Заявка:");
        l4 = new JLabel("<html> Номер <br> квитанции: </html>");
        treq = new TextField();
        treq.setText(ids.get(ids.size() - 1));


        lf.setBounds(40, 30, 100, 20);
        li.setBounds(40, 80, 100, 20);
        lo.setBounds(40, 130, 100, 20);
        l1.setBounds(40, 180, 100, 20);
        l2.setBounds(40, 230, 100, 20);
        l3.setBounds(40, 280, 100, 20);
        l4.setBounds(40, 330, 100, 30);

        frame.add(lf);
        frame.add(li);
        frame.add(lo);
        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        frame.add(l4);

        n = new JButton("Назад");
        s = new JButton("Сохранить");
        cv = new JButton("Квитанция");

        n.setBounds(30, 400, 100, 30);
        s.setBounds(300, 400, 100, 30);
        cv.setBounds(165, 400, 100, 30);

        frame.add(s);
        frame.add(n);
        frame.add(cv);

        t1 = new TextField();
        t2 = new TextField();
        tf = new TextField();
        ti = new TextField();
        to = new TextField();

        tf.setBounds(160, 30, 200, 20);
        ti.setBounds(160, 80, 200, 20);
        to.setBounds(160, 130, 200, 20);
        t1.setBounds(160, 180, 200, 20);
        t2.setBounds(160, 230, 200, 20);

        frame.add(tf);
        frame.add(ti);
        frame.add(to);
        frame.add(t1);
        frame.add(t2);

        comboBox2.setBounds(160, 330, 200, 20);
        treq.setBounds(160, 280, 200, 20);

        frame.add(comboBox2);
        frame.add(treq);



        final String[] text = new String[1];
        comboBox2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                text[0] = (String) comboBox2.getSelectedItem();
                //System.out.println(text[0]);
            }});

        n.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Wworker app2 = new Wworker();
                frame.setVisible(false);
            }
        });
        s.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ResultSet rs3 = null;
                try {
                    rs3 = stmt3.executeQuery("select ID_Client from client;");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                ArrayList<String> req = new ArrayList<String>();/* возвращаем список объектов для работы */

                while (true) {
                    try {
                        if (!rs3.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        req.add(rs3.getString(1));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
                int num = req.size()+1;

                try {
                    stmt4.executeUpdate("insert into client (ID_Client, F, I, O, Address, Phone, Application, Num)" +
                            "values (" + num  + ", '"+ tf.getText() +"', '"+ ti.getText() +"', '"+ to.getText() + "', '" + t1.getText() + "', '" +
                            t2.getText() + "', '"+ treq.getText() + "', '" + text[0] +"')");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


            }


            });

        cv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    Receipt app2 = new Receipt();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                frame.setVisible(false);
            }
        });


        frame.setSize(450,500);
        frame.setLocation(centerX-250, centerY-250);
        frame.setVisible(true);
    }
}
