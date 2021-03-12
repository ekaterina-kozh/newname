package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MyWindow {

    private int balance = 0;
    private JLabel loginLabel;
    private JLabel pasLabel;
    private TextField log;
    private TextField pas;
    private JFrame Admin;

    public MyWindow() {


        JFrame frame = new JFrame("Авторизация");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Создадим лейбл и поместим его о фрейм
        loginLabel = new JLabel("Логин: ");
        pasLabel = new JLabel("Пароль: ");

        JButton btnsingout = new JButton("Выйти");
        JButton btnsingin = new JButton("Войти");
        TextField log = new TextField();

        JPasswordField pas = new JPasswordField(12);
        pas.setEchoChar('*');

        frame.setLayout(null);
        loginLabel.setBounds(32, 22, 80, 30);
        pasLabel.setBounds(32, 69, 80, 30);

        btnsingout.setBounds(19, 114, 80, 30);
        btnsingin.setBounds(260, 114, 80, 30);

        log.setBounds(154, 22, 200, 20);
        pas.setBounds(154, 69, 200, 20);


        frame.add(loginLabel);
        frame.add(pasLabel);
        frame.add(btnsingin);
        frame.add(btnsingout);
        frame.add(log);
        frame.add(pas);

        int centerX = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
        int centerY = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);

        frame.setLocation(centerX - 200, centerY - 100);

        final int[] count = {-1};
        final int[] pem = {-1};

        btnsingout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnsingin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

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

                Statement st = null;

                try {
                    st = con.createStatement();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                // Результирующий запрос
                ResultSet rs = null;
                try {
                    rs = st.executeQuery("select count(login), login, password, pem from account where (login='" + log.getText() + "' and password='" + pas.getText() + "');");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                while (true) {
                    try {
                        if (!rs.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        count[0] = rs.getInt(1);
                        String passw = rs.getString(3);
                        String login = rs.getString(2);
                        pem[0] = rs.getInt(4);

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                if (st != null) {
                    try {
                        st.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

                ///////////////////////////

                if (count[0] == 0) {
                    JOptionPane.showMessageDialog(null, "Неправильный логин или пароль", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (pem[0] == 1) {

                        // окно админа
                        try {
                            Admin app2 = new Admin();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        frame.setVisible(false);

                    }
                    if (pem[0] == 0) {

                        //окно сотрудника
                        Wworker app2 = new Wworker();
                        frame.setVisible(false);

                    }
                }

            }
        });


        frame.setSize(400, 200);

        frame.setVisible(true);
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        MyWindow app = new MyWindow();

    }

}