package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Sparep {
    public Sparep() throws SQLException {
        int centerX = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
        int centerY = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);

        JButton b;
        JTextField t1, t2, t3, t4;
        JLabel l1, l2, l3, l4;
        JFrame frame = new JFrame();
        frame.setLayout(null);
        //////////////////

        String userName = "root";
        String password = "root";
        String connectionURL = "jdbc:mysql://localhost:3306/mysystem?verifyServerCertificate=false&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        Connection con = null;
        con = DriverManager.getConnection(connectionURL, userName, password);

        Statement st = con.createStatement();
        Sparept model = new Sparept();
        model.addDate(con);
        int num = model.getRowCount()+1;
        //System.out.println(num);
        //System.out.println(num);

        //System.out.println(typeof(st.executeUpdate("select count(*) from sparep;")));

        /////////////////////
        b = new JButton("Сохранить");
        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();

        l1 = new JLabel("Наименование");
        l2 = new JLabel("Количество");
        l3 = new JLabel("Единица измерения");
        l4 = new JLabel("Стоимоть");

        l1.setBounds(20, 20, 100, 20);
        t1.setBounds(20, 60, 100, 20);
        l2.setBounds(20, 100, 100, 20);
        t2.setBounds(20, 140, 100, 20);
        l3.setBounds(20, 180, 150, 20);
        t3.setBounds(20, 220, 100, 20);
        l4.setBounds(20, 260, 100, 20);
        t4.setBounds(20, 300, 100, 20);
        b.setBounds(20, 340, 100, 20);

        frame.add(t1);
        frame.add(t2);
        frame.add(t3);
        frame.add(t3);
        frame.add(t4);
        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        frame.add(l4);
        frame.add(b);


        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    st.executeUpdate("insert into sparep (id, namep, coun, unit, price) values (" + num +", '" + t1.getText() + "', '" +
                            t2.getText() + "', '" + t3.getText() + "', '" + t4.getText() +  "')");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                try {
                    Master app2 = new Master();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                frame.setVisible(false);
            }
        });


        frame.setSize(180,440);
        frame.setLocation(centerX-250, centerY-250);
        frame.setVisible(true);
    }
}
