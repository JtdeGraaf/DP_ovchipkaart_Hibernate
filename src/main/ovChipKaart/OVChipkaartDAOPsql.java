package main.ovChipKaart;

import main.reiziger.ReizigerDAOPsql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {
    private Connection conn;
    private ReizigerDAOPsql rdao;

    public OVChipkaartDAOPsql(Connection conn){
        this.conn = conn;
    }

    @Override
    public boolean save(OVChipkaart ov){
        try {
            Statement statement = conn.createStatement();
            String queryString = "INSERT INTO ov_chipkaart " +
                    "VALUES (" + ov.getKaartNummer() + ", '" + ov.getGeldigTot() + "', " + ov.getKlasse() + ", " +
                    ov.getSaldo() + ", " + ov.getReiziger().getId() + ")";

            statement.executeUpdate(queryString);
            return true;
        }
        catch(SQLException E){
            System.out.println(E.getMessage());
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
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from ov_chipkaart");

            ArrayList<OVChipkaart> OVKaarten = new ArrayList<OVChipkaart>();
            while(rs.next()) {
                int kaartNummer = rs.getInt("kaart_nummer");
                Date date = rs.getDate("geldig_tot");
                LocalDate geldigTot = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                int klasse = rs.getInt("klasse");
                int saldo = rs.getInt("saldo");
                int reiziger_id = rs.getInt("reiziger_id");

                rdao = new ReizigerDAOPsql(conn);
                OVKaarten.add(new OVChipkaart(kaartNummer, geldigTot, klasse, saldo, rdao.findById(reiziger_id)));

            }
            return OVKaarten;
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public OVChipkaart findByKaartnummer(int kaartnummer) {
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from ov_chipkaart WHERE kaart_nummer = " + kaartnummer);
            ArrayList<OVChipkaart> OVKaarten = new ArrayList<OVChipkaart>();
            while(rs.next()) {
                int kaartNummer = rs.getInt("kaart_nummer");
                Date date = rs.getDate("geldig_tot");
                LocalDate geldigTot = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                int klasse = rs.getInt("klasse");
                int saldo = rs.getInt("saldo");
                int reiziger_id = rs.getInt("reiziger_id");

                rdao = new ReizigerDAOPsql(conn);
                OVKaarten.add(new OVChipkaart(kaartNummer, geldigTot, klasse, saldo, rdao.findById(reiziger_id)));

            }
            return OVKaarten.get(0);
        }
        catch(Exception e) {
            return null;
        }
    }
}
