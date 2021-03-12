package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Worker {
    public Worker() throws SQLException {

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


    JFrame frame = new JFrame("Личная информация о сотруднике");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    int centerX = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
    int centerY = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);

    JLabel l1,l2,l3,l4,l5, f, i, o;
    TextField t1, t2, t3, t4, tf, ti, to;
    JTextArea a;
    JButton n;
    JButton s;

    l1 = new JLabel("Адрес:");
    l2 = new JLabel("Дата рождения:");
    l3 = new JLabel("Должность");
    l4 = new JLabel("Оклад:");
    l5 = new JLabel("<html> Сведения о <br> перемещении: <html> ");
    f = new JLabel("Фамилия:");
    i = new JLabel("Имя:");
    o = new JLabel("Отчество:");


    t1 = new TextField();
    t2 = new TextField();
    t3 = new TextField();
    t4 = new TextField();
    tf = new TextField();
    ti = new TextField();
    to = new TextField();

    a = new JTextArea();

    n = new JButton("Назад");
    s = new JButton("Сохранить");

        a.setLineWrap(true);
        a.setWrapStyleWord(true);

        tf.setBounds(200, 30, 200, 20);
        ti.setBounds(200, 80, 200, 20);
        to.setBounds(200, 130, 200, 20);
        f.setBounds(40, 30, 100, 20);
        i.setBounds(40, 80, 100, 20);
        o.setBounds(40, 130, 100, 20);

        l1.setBounds(40, 180, 100, 20);
        l2.setBounds(40, 230, 100, 20);
        l3.setBounds(40, 280, 100, 20);
        l4.setBounds(40, 330, 100, 20);
        l5.setBounds(40, 380, 100, 30);
        t1.setBounds(200, 180, 200, 20);
        t2.setBounds(200, 230, 200, 20);
        t3.setBounds(200, 280, 200, 20);
        t4.setBounds(200, 330, 200, 20);
        a.setBounds(200, 380, 200, 100);
        n.setBounds(40, 500, 100, 30);
        s.setBounds(350, 500, 100, 30);

        ///////////////////
        TableModelWorker model = new TableModelWorker();
        model.addDate(con);
        int num = model.getRowCount()+1;

        /*System.out.println("insert into master (id_master, F, I, O, Spet, Lis) values (" + num +", '" + t1.getText() +
                "', ' " + t2.getText() + "', ' " + t3.getText() + "', ' " + "', ' " + a.getText() + "')");*/
        /////////////////////
        frame.add(f);
        frame.add(i);
        frame.add(o);

        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        frame.add(l4);
        frame.add(l5);
        frame.add(t1);
        frame.add(t2);
        frame.add(t3);
        frame.add(t4);
        frame.add(a);
        frame.add(n);
        frame.add(s);
        frame.add(tf);
        frame.add(ti);
        frame.add(to);

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
        s.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                   /*st.executeUpdate("insert into master (id_master, F, I, O, Spet, Lis) values (" + num +", '" + t1.getText() +
                             "', ' " + t2.getText() + "', ' " + t3.getText() + "', ' " + "', ' " + a.getText() + "')");*/
                try {
                    st.executeUpdate("insert into worker (id_worker, F, I, O, Addres, Dateb, Position, Salary, Infom) values (" + num +", '" + tf.getText() +
                            "', ' " + ti.getText() + "', ' " + to.getText() + "', ' " + t1.getText() + "', '" + t2.getText() + "', ' " + t3.getText() + "', ' " +
                            t4.getText() + "', ' " + a.getText() + "')");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
              /*System.out.println("insert into worker (id_worker, F, I, O, Addres, Dateb, Position, Salary, Infom) values (" + num +", '" + tf.getText() +
                      "', ' " + ti.getText() + "', ' " + to.getText() + "', ' " + t1.getText() + "', '" + t2.getText() + "', ' " + t3.getText() + "', ' " +
                      t4.getText() + "', ' " + a.getText() + "')");*/
                try {
                    Admin app2 = new Admin();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                frame.setVisible(false);

            }
        });

        frame.pack();
        frame.setSize(500,600);
        frame.setLocation(centerX-250, centerY-250);
        frame.setVisible(true);
    }
}
