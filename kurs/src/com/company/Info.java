package com.company;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Enumeration;


import static javafx.application.ConditionalFeature.SWT;

public class Info {
    private JLabel f;
    private JLabel i;
    private JLabel o;
    private JButton n, v;

public Info(){
        int centerX = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
        int centerY = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);

        JFrame frame = new JFrame("Личная информация");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JRadioButton j1 = new JRadioButton("Сотрудник");
    JRadioButton j2 = new JRadioButton("Мастер");
    JRadioButton j3 = new JRadioButton("Клиент");
    n = new JButton("Назад");
    v = new JButton("Далее");

    ButtonGroup G = new ButtonGroup();
    G.add(j1);
    G.add(j2);
    G.add(j3);

    f = new JLabel("Фамилия: ");
    i = new JLabel("Имя:");
    o = new JLabel("Отчество:");
    TextField ft = new TextField();
    TextField it = new TextField();
    TextField ot = new TextField();

    f.setBounds(40, 30, 100, 30);
    i.setBounds(40, 80, 100, 30);
    o.setBounds(40, 130, 100, 30);
    ft.setBounds(200, 30, 250, 20);
    it.setBounds(200, 80, 250, 20);
    ot.setBounds(200, 130, 250, 20);
    j1.setBounds(200, 200, 300, 20);
    j2.setBounds(200, 240, 300, 20);
    j3.setBounds(200, 280, 300, 20);
    n.setBounds(40, 350, 100, 30);
    v.setBounds(320, 350, 100, 30);

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

    v.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            if (j1.isSelected()){ //worker
                try {
                    Worker app2 = new Worker();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                frame.setVisible(false);
            };
            if (j2.isSelected()){ //master
                try {
                    Master app2 = new Master();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                frame.setVisible(false);
            };
            if (j3.isSelected()){ //client

                try {
                    Client app2 = new Client();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                frame.setVisible(false);
            };
        }
    });

    frame.add(f);
    frame.add(i);
    frame.add(o);
    frame.add(ft);
    frame.add(it);
    frame.add(ot);
    frame.add(j1);
    frame.add(j2);
    frame.add(j3);
    frame.add(n);
    frame.add(v);

        frame.setSize(500,450);
        frame.setLocation(centerX-250, centerY-250);
        frame.setVisible(true);
    }
}

