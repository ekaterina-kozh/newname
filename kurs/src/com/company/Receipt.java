package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Receipt {
    int centerX = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
    int centerY = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);

    public Receipt() throws SQLException {
        JFrame frame = new JFrame("Квитанция");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextArea area = new JTextArea();
        JButton b1 = new JButton("Назад");
        JButton b2 = new JButton("Печатать чек");

        JScrollPane scrollPane2 = new JScrollPane(area);

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
        ResultSet rs = stmt.executeQuery("select b.ID_Client, a.res, a.dater, b.F, b.I, b.O, b.phone, a.mark, a.breakr, c.price from request a, client b, sparep c where (a.res=b.Num AND c.namep=a.sparep) order by b.ID_Client desc limit 1;");
        ArrayList<String> names = new ArrayList<String>();/* возвращаем список объектов для работы */

        while (rs.next()) {
            names.add(rs.getString(2));
            names.add(rs.getString(3));
            names.add(rs.getString(4));
            names.add(rs.getString(5));
            names.add(rs.getString(6));
            names.add(rs.getString(7));
            names.add(rs.getString(8));
            names.add(rs.getString(9));
            names.add(rs.getString(10));
        }

        String[] nameArr = new String[names.size()];
        nameArr = names.toArray(nameArr);

        scrollPane2.setBounds(40, 40, 320, 200);
        b1.setBounds(30, 280, 150, 30);
        b2.setBounds(200, 280, 150, 30);
        frame.add(scrollPane2);
        frame.add(b1);
        frame.add(b2);



        String str = "" +
                "<html>\n" +
                "\n" +
                "\n" +
                "<br/>\n" +
                "\n" +
                "<div style=\"font-weight: bold; font-size: 25pt; padding-left:5px; font-family: Arial;\">\n" +
                "    Счет № " + nameArr[0] + " от  " +nameArr[1] + "</div>\n" +
                "<br/>\n" +
                "\n" +
                "<div style=\"background-color:#000000; width:100%; font-size:1px; height:2px;\">&nbsp;</div>\n" +
                "\n" +
                "<table width=\"100%\" style=\"font-family: Arial;\">\n" +
                "    <tr>\n" +
                "        <td style=\"width: 30mm; vertical-align: top;\">\n" +
                "            <div style=\" padding-left:2px; \">Поставщик:    </div>\n" +
                "        </td>\n" +
                "        <td>\n" +
                "            <div style=\"font-weight:bold;  padding-left:2px;\">\n" +
                "                ООО \"Телеателье\" <br>\n" +
                "<span style=\"font-weight: normal;\">Республика Беларусь, г. Новополоцк, ул. Молодежная, д. 109,<br> тел.: 2539175  </span>          </div>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td style=\"width: 30mm; vertical-align: top;\">\n" +
                "            <div style=\" padding-left:2px;\">Покупатель:  </td>\n" +
                "        <td>\n" +
                "            <div style=\"font-weight:bold;  padding-left:2px;\">\n" +
                nameArr[2] + " " + nameArr[3] + " " + nameArr[4] + "</div>\n" +" <br><span style=\"font-weight: normal;\">тел.: " +  nameArr[5]+ "</span>            </div>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "\n" +
                "\n" +
                "<table border=\"2\" width=\"100%\" cellpadding=\"2\" cellspacing=\"2\" style=\"border-collapse: collapse; width: 100%; font-family: Arial;\">\n" +
                "    <thead>\n" +
                "    <tr>\n" +
                "        <th style=\"width:13mm; \">№</th>\n" +
                "       \n" +
                "        <th>Товары (услуги)</th>\n" +
                "        <th style=\"width:20mm; \">Кол-во</th>\n" +
                "        <th style=\"width:17mm; \">Ед.</th>\n" +
                "        <th style=\"width:27mm;  \">Цена</th>\n" +
                "        <th style=\"width:27mm;  \">Сумма</th>\n" +
                "    </tr>\n" +
                "    </thead>\n" +
                "    <tbody >\n" +
                "      <tr>\n" +
                "        <td style=\"width:13mm; \">№1</td>\n" +
                "       \n" +
                "        <td>" +  nameArr[6] + "(" + nameArr[7]  + ")" + "</td>\n" +
                "        <td style=\"width:20mm; \">1</td>\n" +
                "        <td style=\"width:17mm; \">Шт.</td>\n" +
                "        <td style=\"width:27mm; text-align: center; \">" + nameArr[8] + "</td>\n" +
                "        <td style=\"width:27mm; text-align: center; \">" + nameArr[8] + "</td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table>\n" +
                "\n" +
                "<table style=\"font-family: Arial;\" border=\"0\" width=\"100%\" cellpadding=\"1\" cellspacing=\"1\">\n" +
                "    <tr>\n" +
                "        <td></td>\n" +
                "        <td style=\"width:27mm; font-weight:bold;  text-align:right;\">Итого:</td>\n" +
                "        <td style=\"width:27mm; font-weight:bold;  text-align: center; \">" + nameArr[8] + ".00</td>\n" +
                "    </tr>\n" +
                "  <tr>\n" +
                "        <td></td>\n" +
                "        <td style=\"width:27mm; font-weight:bold;  text-align:right;\">Итого НДС:</td>\n" +
                "        <td style=\"width:27mm; font-weight:bold;  text-align: center; \">" + Integer.parseInt(nameArr[8])*0.2 +"0</td>\n" +
                "    </tr>\n" +
                "  <tr>\n" +
                "        <td></td>\n" +
                "        <td style=\"width:37mm; font-weight:bold;  text-align:right;\">Всего к оплате:</td>\n" +
                "        <td style=\"width:27mm; font-weight:bold;  text-align: center; \">" + nameArr[8]+".00</td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "\n" +
                "<br />\n" +
                "\n" +
                "  <br/>  <br /><br /><br/>  <br /><br /><br/>  <br /><br />\n" +
                "</html>\n";




        String str2 = "Номер квитанции: " + nameArr[0] + "\nДата заявления: " + nameArr[1] + "\nФамилия клиента: " + nameArr[2] + "\nИмя клиента: " + nameArr[3] +
                "\nОтчество клиента: " + nameArr[4] + "\nТелефон: " + nameArr[5] + "\nМарка ТВ: " + nameArr[6] + "\nНеисправность: " + nameArr[7] + "\nСтоимость: " + nameArr[8];

        area.setText(str2);


        String[] finalNameArr = nameArr;
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try(FileWriter writer = new FileWriter("C:\\Users\\79532\\Desktop\\result\\" + finalNameArr[0] + ".doc", false))
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
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Wworker app2 = new Wworker();
                frame.setVisible(false);
            }

        });

        frame.setSize(400,400);
        frame.setLocation(0, 0);
        frame.setVisible(true);
    }
}
