package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class Request {
    private JLabel l1,l2,l3,l4,l5, l6, l7;
    private JButton b1, b3;
    private TextField t1,t2, t3, t4, t5;


    int centerX = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
    int centerY = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
    public Request() throws SQLException {
        JFrame frame = new JFrame("Окно заявки");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComboBox c = null,c2;

        l1 = new JLabel("Марка ТВ:");
        l2 = new JLabel("Дата заявки:");
        l3 = new JLabel("Мастер:");
        l4 = new JLabel("Дата выполнения:");
        l5 = new JLabel("Неисправность:");
        l6 = new JLabel("Список запчастей:");
        l7 = new JLabel("Номер квитанции:");
        b1 = new JButton("Назад");
        b3 = new JButton("Сохранить");
        t1 = new TextField();
        t2 = new TextField();
        t3 = new TextField();
        t4 = new TextField();
        t5 = new TextField();

        //////////////
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

        //
        ResultSet rs = stmt.executeQuery("select F from master;");

        ArrayList<String> names = new ArrayList<String>();/* возвращаем список объектов для работы */

        while (rs.next()) {
            names.add(rs.getString(1));

        }

       // rs.close();

        ResultSet rs2 = stmt2.executeQuery("select namep from sparep;");
        ArrayList<String> det = new ArrayList<String>();/* возвращаем список объектов для работы */

        while (rs2.next()) {
            det.add(rs2.getString(1));

        }

        int a = (int) (10000 + Math.random()*99999);
        t5.setText(Integer.toString(a));
        //rs2.close();

        String[] nameArr = new String[names.size()];
        nameArr = names.toArray(nameArr);
        String[] detArr = new String[det.size()];
        detArr = det.toArray(detArr);
        /////////////
        c = new JComboBox(nameArr);
        c2 = new JComboBox(detArr);

        JComboBox finalC = c;
        JComboBox finalC2 = c2;
        final String[] text = {""};
        final String[] text2 = {""};
        c.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                text[0] = (String) finalC.getSelectedItem();

            }});
        c2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                text2[0] = (String) finalC2.getSelectedItem();

            }});

        l1.setBounds(40, 30, 130, 30);
        l2.setBounds(40, 80, 130, 30);
        l3.setBounds(40, 130, 130, 30);
        l4.setBounds(40, 180, 130, 30);
        l5.setBounds(40, 230, 130, 30);
        l6.setBounds(40, 280, 130, 30);
        l7.setBounds(40, 330, 130, 30);
        b1.setBounds(30, 400, 100, 30);
        b3.setBounds(370, 400, 100, 30);
        t1.setBounds(200, 30, 250, 30);
        t2.setBounds(200, 80, 250, 30);
        c.setBounds(200, 130, 250, 30);
        t3.setBounds(200, 180, 250, 30);
        t4.setBounds(200, 230, 250, 30);
        c2.setBounds(200, 280, 250, 30);
        t5.setBounds(200, 330, 250, 30);

        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        frame.add(l4);
        frame.add(l5);
        frame.add(l6);
        frame.add(l7);
        frame.add(b1);
        frame.add(b3);
        frame.add(t1);
        frame.add(t2);
        frame.add(t3);
        frame.add(t4);
        frame.add(t5);
        frame.add(c);
        frame.add(c2);


        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                com.company.Wworker app2 = new Wworker();
                frame.setVisible(false);
            }
        });


        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ResultSet rs3 = null;
                try {
                    rs3 = stmt2.executeQuery("select id from request;");
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
                    stmt3.executeUpdate("insert into request (id, mark, dater, master, datec, breakr, sparep, res)" +
                            "values (" + num  + ", '"+ t1.getText() +"', '"+ t2.getText() +"', '"+ text[0] +"', '"+
                            t3.getText() +"', '"+ t4.getText()
                            +"', '"+ text2[0] +"', '"+ t5.getText()+"')");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                try {
                    Client app2 = new Client();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                frame.setVisible(false);
            }
        });
        frame.setSize(500,500);
        frame.setLocation(centerX-250, centerY-250);
        frame.setVisible(true);
    }
}
