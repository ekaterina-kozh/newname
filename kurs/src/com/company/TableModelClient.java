package com.company;

import com.mysql.cj.protocol.Resultset;

import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;

public class TableModelClient extends AbstractTableModel {

    private int columncount = 8;
    private ArrayList<String[]> dataArrayList;
    public TableModelClient(){
        dataArrayList = new ArrayList<String[]>();
        for (int i=0; i < dataArrayList.size(); i++){
            dataArrayList.add(new String [getColumnCount()]);
        }
    }

    @Override
    public int getRowCount() {
        return dataArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columncount;
    }

    public String getColumnName(int columnIndex){
        switch (columnIndex){
            case 0: return "ID_Client";
            case 1: return  "F";
            case 2: return "I";
            case 3: return "O";
            case 4: return "Address";
            case 5: return "Phone";
            case 6: return "Application";
            case 7: return "Num";
        }
        return "";
    }
    @Override
    public Object getValueAt(int i, int i1) {
        String rows[] = dataArrayList.get(i);
        return rows[i1];
    }

    public void addDate(String []row){
        String[] rowTable = new String[getColumnCount()];
        rowTable = row;
        dataArrayList.add(rowTable);
    }

    public void addDate (Connection cn){

        Statement st = null;
        try {
            st = cn.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // Результирующий запрос
        ResultSet rs = null;
        try {
            rs = st.executeQuery("select * from client");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        while (true){
            try {
                if (!rs.next()) break;
                String []row = {rs.getString("ID_Client"),
                        rs.getString("F"), rs.getString("I"),
                        rs.getString("O"), rs.getString("Address"),
                rs.getString("Phone"), rs.getString("Application"),
                rs.getString("Num")};

                addDate(row);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
