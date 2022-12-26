package edu.escuelaing.arep;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {

    private static String urlDB = "jdbc:postgresql://postgres://zjxqhghrgjalxw:f3a3125db3ebe3da87546173675720d0fa883ecbb4decd665676b5f82d750063@ec2-18-215-44-132.compute-1.amazonaws.com:5432/dbpdb8g303e02k";
    private static String usuarioDB = "imphifymnzqnhp";
    private static String passwordDB = "deef352548ffa641a75f444f903f9d272f59f630bab2ef59ffe68928f5d76022";
    private static Connection connection = null;

    public DBConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(urlDB, usuarioDB, passwordDB);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(){
        String CREATE_TABLE="CREATE TABLE Personas ("
                + "Cedula Numeric(15) NOT NULL,"
                + "Nombres VARCHAR(60) NOT NULL,"
                + "Apellidos VARCHAR(60) NOT NULL,"
                + "PRIMARY KEY (Cedula))";

        try {
            Statement stmnt = connection.createStatement();
            stmnt.executeQuery(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> getInformation(){
        ArrayList<String[]> list = new ArrayList<>();
        String select = "SELECT * FROM Information;";
        try {

            Statement statement = connection.createStatement();
            ResultSet rs =statement.executeQuery(select);
            while(rs.next()){
                String usern = rs.getString("usern");
                String address = rs.getString("address");
                String[] tmp = {usern,address};
                list.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}