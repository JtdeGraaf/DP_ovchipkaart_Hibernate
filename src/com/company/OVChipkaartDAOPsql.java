package com.company;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OVChipkaartDAOPsql implements OVChipkaartDAO{
    private Connection conn;
    private ReizigerDAOPsql rdao;

    OVChipkaartDAOPsql(Connection conn){
        this.conn = conn;
    }

    @Override
    public boolean save(OVChipkaart ov){
        try {
            Statement statement = conn.createStatement();
            String queryString = "INSERT INTO ov_chipkaart " +
                    "VALUES (" + ov.getKaartNummer() + ", '" + ov.getGeldigTot() + "', " + ov.getKlasse() + ", " +
                    ov.getReiziger().getId() + ")";

            statement.executeUpdate(queryString);
            return true;
        }
        catch(SQLException E){
            return false;
        }
    }

    @Override
    public boolean update(OVChipkaart ov) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE ov_chipkaart SET reiziger_id = " + ov.getReiziger().getId() + ", "+
                    "geldig_tot = '" + ov.getGeldigTot() + "', " +
                    "klasse = " + ov.getKlasse() + ", " +
                    "saldo = " + ov.getSaldo() + " " +
                    "WHERE kaart_nummer = " + ov.getKaartNummer());

            return true;
        }
        catch(SQLException E){
            System.out.println(E.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(OVChipkaart ov) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM ov_chipkaart WHERE kaart_nummer = " + ov.getKaartNummer());

            return true;
        }
        catch(SQLException E) {
            System.out.println(E.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<OVChipkaart> findAll() {
        return null;
    }
}
