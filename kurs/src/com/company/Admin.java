package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static java.awt.Frame.MAXIMIZED_BOTH;

public class Admin {


    TableModelMaster model2 = new TableModelMaster();
    TableModelWorker model3 = new TableModelWorker();

    JTable tablem = new JTable(model2);
    JTable tablew = new JTable(model3);
    JTable table = new JTable();

    public void actionPerformed(ActionEvent e) {
        System.out.println(table.getSelectedRow());
        System.out.println(table.getSelectedColumn());

    }



    public Admin() throws SQLException {
        // write your code here
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

        Statement stmt2 = con.createStatement();
        Statement stmt3 = con.createStatement();

        ///////////
        JFrame frame = new JFrame("Окно администратора");
        //frame.setExtendedState(MAXIMIZED_BOTH);

        frame.setSize(new Dimension(1420, 570));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        //frame.setLocationRelativeTo(null);
        //frame.setLayout(new GridBagLayout());

        //////////

        JScrollPane scrollPane2 = new JScrollPane(tablem);
        JScrollPane scrollPane3 = new JScrollPane(tablew);


        JButton b1 = new JButton("Добавить");
        JButton b2 = new JButton("Удалить");
        JButton b3 = new JButton("Редактировать");
        JButton b4 = new JButton("Выход");
        JRadioButton j1 = new JRadioButton("Сотрудник");
        JRadioButton j2 = new JRadioButton("Мастер");

        ButtonGroup G = new ButtonGroup();
        G.add(j1);
        G.add(j2);

        /*if(b1.isSelected()){

        }*/
        //scrollPane.setPreferredSize(new Dimension(800, 600));
        scrollPane2.setBounds(180, 40, 1200, 450);
        scrollPane3.setBounds(180, 40, 1200, 450);

        b1.setBounds(20, 40, 140, 30);
        b2.setBounds(20, 100, 140, 30);
        b3.setBounds(20, 160, 140, 30);
        b4.setBounds(20, 460, 140, 30);
        j1.setBounds(20, 250, 100, 20);
        j2.setBounds(20, 280, 100, 20);



        tablew.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {

                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {
                          //  System.out.print((tablew.getSelectedRow() +1)+ "\t" + (tablew.getSelectedColumn() +1) + "\t" + tablew.getColumnName(tablew.getSelectedColumn()) + "\n");

                        }
                    }
                });
        tablem.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {

                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {
                            //System.out.print((table.getSelectedRow() +1)+ "\t");

                        }
                    }
                });
        //System.out.print(n1[0]);

        model2.addDate(con);
        model3.addDate(con);

        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);
        frame.add(j1);
        frame.add(j2);
       j2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              frame.remove(scrollPane3);
                frame.add(scrollPane2);

            }
        });
        j1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               frame.remove(scrollPane2);
                frame.add(scrollPane3);

            }
        });
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                MyWindow app2 = new MyWindow();

                frame.setVisible(false);
            }
        });
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (j1.isSelected()){
                    try {
                        Worker app2 = new Worker();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    frame.setVisible(false);
                }
                if (j2.isSelected()){
                    try {
                        Master app2 = new Master();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    frame.setVisible(false);
                }


            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {



                if (j2.isSelected()){
                    try {

                        stmt2.executeUpdate("delete from master where id_master=" + (tablem.getSelectedRow() +1));
                        frame.setVisible(false);
                        Admin app2 = new Admin();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    //System.out.print("delete from master where id=" + (tablem.getSelectedRow() +1));
                }
                if (j1.isSelected()){
                    try {
                        stmt3.executeUpdate("delete from worker where id_worker =" + (tablew.getSelectedRow() +1));
                        frame.setVisible(false);
                        Admin app2 = new Admin();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    //System.out.print("delete from worker where id=" + (tablew.getSelectedRow() +1));
                }

            }
        });
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if (j2.isSelected()){
                    try {
                        String result = JOptionPane.showInputDialog(
                                "Новое значение",
                                "");
                        stmt2.executeUpdate("update master set " + tablem.getColumnName(tablem.getSelectedColumn()) + "='" + result +"' where id_master=" + (tablem.getSelectedRow() +1) );
                        frame.setVisible(false);
                        Admin app2 = new Admin();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    //System.out.print("delete from master where id=" + (tablem.getSelectedRow() +1));
                }
                if (j1.isSelected()){
                    try {
                        String result = JOptionPane.showInputDialog(
                                "Новое значение",
                                "");
                        stmt3.executeUpdate("update worker set " + tablew.getColumnName(tablew.getSelectedColumn()) + "='" + result +"' where id_worker=" + (tablew.getSelectedRow() +1) );

                        frame.setVisible(false);
                        Admin app2 = new Admin();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }

            }
        });
        //////////
        frame.setVisible(true);
        frame.setLocation(100, 100);

    }
}
