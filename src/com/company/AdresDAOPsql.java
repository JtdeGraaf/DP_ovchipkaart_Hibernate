package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdresDAOPsql implements AdresDAO{
    private Connection conn;
    private ReizigerDAOPsql rdao;

    AdresDAOPsql(Connection conn){
        this.conn = conn;
    }

    @Override
    public boolean save(Adres adres) {
        try {
            if(adres == null){return false;}
            Statement statement = conn.createStatement();
            String queryString = "INSERT INTO adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id)" +
                    "VALUES (" + adres.getId() + ", '" + adres.getPostcode() + "', '" + adres.getHuisnummer() + "', '" +
                    adres.getStraat() + "', '" + adres.getWoonplaats() + "', " + adres.getId() +")";

            statement.executeUpdate(queryString);

            return true;
        }
        catch(SQLException E){
            System.out.println(E.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        if(adres == null){return false;}
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE adres SET postcode = '" + adres.getPostcode() + "', "+
                    "huisnummer = '" + adres.getHuisnummer() + "', " +
                    "straat = '" + adres.getStraat() + "', " +
                    "woonplaats = '" + adres.getWoonplaats() + "' " +
                    "WHERE adres_id = " + adres.getId());
            return true;
        }
        catch(SQLException E){
            return false;
        }
    }

    @Override
    public boolean delete(Adres adres) {
        if(adres == null){return false;}
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM adres WHERE adres_id = " + adres.getId());
            return true;
        }
        catch(SQLException E) {
            System.out.println(E.getMessage());
            return false;
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * FROM adres WHERE reiziger_id = " + reiziger.getId());
            rs.next();
            return new Adres(rs.getInt("adres_id"), rs.getString("postcode"),
                    rs.getString("huisnummer"), rs.getString("straat"), rs.getString("woonplaats"));
        }
        catch (SQLException E){
            System.out.println(E.getMessage());
            return null;
        }
    }

    @Override
    public List<Adres> findAll() {
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from adres");

            List<Adres> adressen = new ArrayList<Adres>();
            while(rs.next()) {
                int id = rs.getInt("adres_id");
                String postcode = rs.getString("postcode");
                String huisnummer = rs.getString("huisnummer");
                String straat = rs.getString("straat");
                String woonplaats = rs.getString("woonplaats");
                adressen.add(new Adres(id, postcode, huisnummer, straat, woonplaats));
            }
            return adressen;
        }
        catch (SQLException E) {
            return null;
        }
    }


}
