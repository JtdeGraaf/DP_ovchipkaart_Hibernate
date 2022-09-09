package com.company;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        String url = "jdbc:postgresql://localhost/ovchip";
        String username = "postgres";
        String password = "1103";
        Connection conn = DriverManager.getConnection(url, username, password);
//        Reiziger reiziger = new Reiziger(11, "P", "de", "Graaf", LocalDate.of(2003,3,11));
//        reiziger.setAdres(new Adres(11, "3254WP", "33", "weg", "Amo"));
//        ReizigerDAOPsql rdaop = new ReizigerDAOPsql(conn);
        AdresDAOPsql adao = new AdresDAOPsql(conn);
        System.out.println(adao.findAll());
//        System.out.println(rdaop.update(reiziger));
//        adao.save(new Adres(10, "3254WP", "33", "straat", "Amo"));
//        testReizigerDAO(new ReizigerDAOPsql(conn));

    }

    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", LocalDate.of(1981, 3, 14));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
        System.out.println("Eerst " + reizigers.size() + " reizigers");
        rdao.delete(sietske);
        reizigers = rdao.findAll();
        System.out.println("Na delete " + reizigers.size() + " reizigers\n");

        System.out.println("FindById Test\n" +
                "Zoeken op Id = 1\n" +
                rdao.findById(1));

        System.out.println("\nFindByGbDatum Test\n" +
                "Zoeken op geboortedatum 1998-08-11\n" +
                rdao.findByGbdatum(":1998-08-11"));


    }

    private static void testAdresDAO(AdresDAO adao){
    }
}


