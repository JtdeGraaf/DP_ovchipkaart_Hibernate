package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {


        String url = "jdbc:postgresql://localhost/ovchip";
        String username = "postgres";
        String password = "1103";

        Connection conn = DriverManager.getConnection(url, username, password);

        Statement query = conn.createStatement();
        ResultSet rs = query.executeQuery("select * from reiziger");

        while(rs.next()) {
            StringBuilder reiziger = new StringBuilder();
            reiziger.append("#")
                    .append(rs.getString("reiziger_id"))
                    .append(": ")
                    .append(rs.getString("voorletters"))
                    .append(". ");
            if(rs.getString("tussenvoegsel") != null){
                reiziger.append(rs.getString("tussenvoegsel"))
                        .append(" ");
            }
            reiziger.append(rs.getString("achternaam"))
                    .append(" (")
                    .append(rs.getString("geboortedatum"))
                    .append(")");

            System.out.println(reiziger);
        }
    }
}
