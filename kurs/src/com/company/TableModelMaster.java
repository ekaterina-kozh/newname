package com.company;

import com.mysql.cj.protocol.Resultset;

import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;

public class TableModelMaster extends AbstractTableModel {

    private int columncount = 6;
    private ArrayList<String[]> dataArrayList;
    public TableModelMaster(){
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
            case 0: return "id_master";
            case 1: return  "F";
            case 2: return "I";
            case 3: return "O";
            case 4: return "Spet";
            case 5: return "Lis";

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
            rs = st.executeQuery("select * from master");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        while (true){
            try {
                if (!rs.next()) break;
                String []row = {rs.getString("id_master"),
                        rs.getString("F"), rs.getString("I"),
                        rs.getString("O"), rs.getString("Spet"),
                        rs.getString("Lis")};

                addDate(row);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
