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

public class ReizigerDAOPsql implements ReizigerDAO{
    private Connection conn;
    private AdresDAO adao;

    ReizigerDAOPsql(Connection conn){
        this.conn = conn;
    }

    @Override
    public boolean save(Reiziger reiziger){
        try {
            Statement statement = conn.createStatement();
            String queryString = "INSERT INTO reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum)" +
                    "VALUES (" + reiziger.getId() + ", '" + reiziger.getVoorletters() + "', '" + reiziger.getTussenvoegsel() + "', '" +
                    reiziger.getAchternaam() + "', '" + reiziger.getGeboortedatum() + "')";

            statement.executeUpdate(queryString);
            adao = new AdresDAOPsql(conn);
            adao.save(reiziger.getAdres());

            return true;
        }
        catch(SQLException E){
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE reiziger SET voorletters = '" + reiziger.getVoorletters() + "', "+
                    "tussenvoegsel = '" + reiziger.getTussenvoegsel() + "', " +
                    "achternaam = '" + reiziger.getAchternaam() + "', " +
                    "geboortedatum = '" + reiziger.getGeboortedatum().toString() + "' " +
                    "WHERE reiziger_id = " + reiziger.getId());

            adao = new AdresDAOPsql(conn);
            adao.update(reiziger.getAdres());
            return true;
        }
        catch(SQLException E){
            System.out.println(E.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            adao = new AdresDAOPsql(conn);
            adao.delete(reiziger.getAdres());
            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM reiziger WHERE reiziger_id = " + reiziger.getId());

            return true;
        }
        catch(SQLException E) {
            System.out.println(E.getMessage());
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) {
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from reiziger WHERE reiziger_id = " + id);
            rs.next();
            String voorletters = rs.getString("voorletters");
            String tussenvoegsel = rs.getString("tussenvoegsel");
            String achternaam = rs.getString("achternaam");
            Date date = rs.getDate("geboortedatum");
            LocalDate geboortedatum = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            return new Reiziger(id, voorletters, tussenvoegsel, achternaam, geboortedatum);


        }
        catch(SQLException E){
            return null;
        }
    }

    @Override
    public List<Reiziger> findByGbdatum(String gbdatum) {
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from reiziger WHERE geboortedatum = '" + gbdatum + "'");

            List<Reiziger> reizigers = new ArrayList<Reiziger>();
            while(rs.next()) {
                int id = rs.getInt("reiziger_id");
                String voorletters = rs.getString("voorletters");
                String tussenvoegsel = rs.getString("tussenvoegsel");
                String achternaam = rs.getString("achternaam");
                Date date = rs.getDate("geboortedatum");
                LocalDate geboortedatum = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                reizigers.add(new Reiziger(id, voorletters, tussenvoegsel, achternaam, geboortedatum));
            }
            return reizigers;
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public List<Reiziger> findAll() {
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from reiziger");

            List<Reiziger> reizigers = new ArrayList<Reiziger>();
            while(rs.next()) {
                int id = rs.getInt("reiziger_id");
                String voorletters = rs.getString("voorletters");
                String tussenvoegsel = rs.getString("tussenvoegsel");
                String achternaam = rs.getString("achternaam");
                Date date = rs.getDate("geboortedatum");
                LocalDate geboortedatum = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                reizigers.add(new Reiziger(id, voorletters, tussenvoegsel, achternaam, geboortedatum));
            }
            return reizigers;
        }
        catch(Exception e) {
            return null;
        }
    }
}
