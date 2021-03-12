package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Result {
    int centerX = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
    int centerY = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);

    public Result() throws SQLException {
        JFrame frame = new JFrame("Результат");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton b1 = new JButton("Сохранить");
        JButton b2 = new JButton("Назад");

       b1.setBounds(40, 30, 100, 30);
        b2.setBounds(180, 30, 100, 30);

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

        //
        ResultSet rs = stmt.executeQuery("select a.F, a.Lis, b.coun, b.price from master a, sparep b where (a.Lis=b.namep);");
        ArrayList<String> names = new ArrayList<String>();/* возвращаем список объектов для работы */

        while (rs.next()) {
            names.add(rs.getString(1));
            names.add(rs.getString(2));
            names.add(rs.getString(3));
            names.add(rs.getString(4));

        }

        String[] nameArr = new String[names.size()];
        nameArr = names.toArray(nameArr);
        int num = nameArr.length/4;

        String str2="";


        String str1 = "<html>\n" +
                "\n" +
                "\n" +
                "<br/>\n" +
                "\n" +
                "<h2 align=\"center\" style=\"color:Black\"><b>СПРАВКА\n" +
                "                 О РАСПРЕДЕЛЕНИИ О РАСПРЕДЕЛЕНИЕ ЗАПЧАСТЕЙ\n" +
                " \t\t\t\t\t(МАТЕРИАЛОВ) ПО МАСТЕРАМ </h2></b><br>\n" +
                "\t\t\t\t\t<br>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<table border=\"2\" width=\"100%\" cellpadding=\"2\" cellspacing=\"2\" style=\"border-collapse: collapse; width: 100%; font-family: Arial;\">\n" +
                "    <thead>\n" +
                "    <tr>\n" +
                "        <th style=\"width:13mm; \">№</th>\n" +
                "       \n" +
                "        <th>Фамилия мастера</th>\n" +
                "        <th >Запчасти</th>\n" +
                "        <th >Кол-во</th>\n" +
                "        <th >Цена</th>\n" +
                "       \n" +
                "    </tr>\n" +
                "    </thead>\n" +
                "    <tbody >\n" +
                "      \n";
        for (int i=0; i<num; i++){
            str2+= "\t <tr>\n" +
                    "        <td style=\"width:13mm; \">" + (i+1) + "</td>\n" +
                    "       \n" +
                    "        <td>" + nameArr[0 + i*4] + "</td>\n" +
                    "        <td>" + nameArr[1+i*4]+"</td>\n" +
                    "        <td>" + nameArr[2+i*4] + "</td>\n" +
                    "        <td>" + nameArr[3+i*4]+ "</td>\n" +
                    "       </tr>\n";
        }

        String str3 = "    </tbody>\n" +
                "</table>\n" +
                "\n" +
                "<br>\n" +
                "<br>\n" +
                "<table border=\"0\" width=\"100%\" cellpadding=\"2\" cellspacing=\"2\" style=\"border-collapse: collapse; width: 100%; font-family: Arial;\">\n" +
                "    <thead>\n" +
                "    <tr>\n" +
                "        <th></th>\n" +
                "        <th style=\"width:20mm; \"></th>\n" +
                "           \n" +
                "    </tr>\n" +
                "    </thead>\n" +
                "    <tbody >\n" +
                "\t<tr>\n" +
                "        <td>Руководитель страховой организации</td>\n" +
                "        <td style=\"width:20mm; \">(подпись)</td>\n" +
                "         </tr>\n" +
                "\t\t <tr>\n" +
                "        <th></th>\n" +
                "        <th style=\"width:20mm; \"></th>\n" +
                "           \n" +
                "    </tr>\n" +
                "      \n" +
                "\t <tr>\n" +
                "        <td>Главный бухгалтер</td>\n" +
                "        <td style=\"width:20mm; \">(подпись)</td>\n" +
                "         </tr>\n" +
                "\t\t \n" +
                "\t\t \n" +
                "    </tbody>\n" +
                "</table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<br />\n" +
                "\n" +
                "  <br/>  <br /><br /><br/>  <br /><br /><br/>  <br /><br />\n" +
                "</html>\n";


        String str = str1+str2+str3;


        frame.add(b1);
        frame.add(b2);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try(FileWriter writer = new FileWriter("C:\\Users\\79532\\Desktop\\result\\result.doc", false))
                {
                    writer.write(str);
                    // запись по символам

                    writer.flush();
                }
                catch(IOException ex){

                    System.out.println(ex.getMessage());
                }

            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                Wworker app2 = new Wworker();

                frame.setVisible(false);
            }
        });
        frame.setSize(320,110);
        frame.setLocation(centerX-250, centerY-250);
        frame.setVisible(true);
    }
}
