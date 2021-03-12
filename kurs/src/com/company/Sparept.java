package com.company;

import com.mysql.cj.protocol.Resultset;

import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;

public class Sparept extends AbstractTableModel {

    private int columncount = 5;
    private ArrayList<String[]> dataArrayList;
    public Sparept(){
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
            case 0: return "id";
            case 1: return  "namep";
            case 2: return  "coun";
            case 3: return  "unit";
            case 4: return  "price";
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
            rs = st.executeQuery("select * from sparep");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        while (true){
            try {
                if (!rs.next()) break;
                String []row = {rs.getString("id"),
                        rs.getString("namep"), rs.getString("coun"), rs.getString("unit"), rs.getString("price")};

                addDate(row);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
