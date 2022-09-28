package com;

import com.adres.Adres;
import com.adres.AdresDAO;
import com.adres.AdresDAOPsql;
import com.ovchipkaart.OVChipkaart;
import com.ovchipkaart.OVChipkaartDAO;
import com.ovchipkaart.OVChipkaartDAOPsql;
import com.reiziger.Reiziger;
import com.reiziger.ReizigerDAO;
import com.reiziger.ReizigerDAOPsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        String url = "jdbc:postgresql://localhost/ovchip";
        String username = "postgres";
        String password = "0000";
        Connection conn = DriverManager.getConnection(url, username, password);

        testReizigerDAO(new ReizigerDAOPsql(conn));
        testAdresDAO(new AdresDAOPsql(conn));
        testOVChipkaartDAO(new OVChipkaartDAOPsql(conn));

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
        System.out.println("---------------------------------------");
        System.out.println("Eerst " + reizigers.size() + " reizigers");
        rdao.delete(sietske);
        reizigers = rdao.findAll();
        System.out.println("Na delete " + reizigers.size() + " reizigers\n");
        System.out.println("---------------------------------------");

        System.out.println("FindById Test\n" +
                "Zoeken op Id = 1\n" +
                rdao.findById(1));
        System.out.println("---------------------------------------");

        System.out.println("\nFindByGbDatum Test\n" +
                "Zoeken op geboortedatum 1998-08-11\n" +
                rdao.findByGbdatum(":1998-08-11"));


    }

    private static void testAdresDAO(AdresDAO adao){
        System.out.println("\n\n\n\n---------- Test AdresDAO -------------");

        // Haal alle adressen op uit de database
        List<Adres> adressen = adao.findAll();
        System.out.println("[Test] AdresDAO.findAll() geeft de volgende Adressen:");
        for (Adres a : adressen) {
            System.out.println(a);
        }
        System.out.println();

        Adres adres = new Adres(100, "3872DJ", "67", "Boers Straat", "Utrecht");
        System.out.print("[Test] Eerst " + adressen.size() + " adressen, na AdresDAO.save() ");
        adao.save(adres);
        adressen = adao.findAll();
        System.out.println(adressen.size() + " adressen\n");


        System.out.println("---------------------------------------");
        System.out.println("Eerst " + adressen.size() + " adressen");
        adao.delete(adres);
        adressen = adao.findAll();
        System.out.println("Na AdresDAO.delete() " + adressen.size() + " reizigers\n");
        System.out.println("---------------------------------------\n");

        Adres oudAdres = new Adres(100, "3872DJ", "67", "Boers Straat", "Utrecht");
        adao.save(oudAdres);
        System.out.println("Adressen:");
        System.out.println(adao.findAll());
        System.out.println("\nOud adres: "+ oudAdres);
        Adres niewAdres =new Adres(100, "5472DX", "33", "Boers Weg", "Amsterdam");
        adao.update(niewAdres);
        System.out.println("\nNa adresDAO.update() zou het nieuwe adres postcode 5472DX moeten hebben\n");
        System.out.println(adao.findAll());
        adao.delete(niewAdres);

        System.out.println("---------------------------------------\n");
        System.out.println("FindByReiziger test postcode: 6707AA \n");
        Reiziger reiziger = new Reiziger(3, "H", null, "Lubben", LocalDate.of(1998, 8, 11));
        System.out.println("Resultaat: " + adao.findByReiziger(reiziger));
    }

    private static void testOVChipkaartDAO(OVChipkaartDAO odao){
        System.out.println("\n\n\n\n --------- OVChipkaartDAO test --------");
        for (OVChipkaart ov : odao.findAll()) {
            System.out.println(ov + "\n");
        }
        Reiziger reiziger = new Reiziger(100, "JT", "de", "Graaf", LocalDate.of(2000,10,10));
        System.out.println("\nOVChipKaart Save Test\n" +
                "Voor de save " + odao.findAll().size() + " OV kaarten gevonden");
        OVChipkaart ov = new OVChipkaart(12345, LocalDate.of(2023,10,10), 1, 30, reiziger);
        odao.save(ov);
        System.out.println("\nNa de save " + odao.findAll().size() + " OV kaarten gevonden");

        System.out.println("\n\nOVChipkaart Update Test\n" +
                "Na de update moet het saldo op 1000.00 staan");
        OVChipkaart ovUpdate = new OVChipkaart(12345, LocalDate.of(2023,10,10), 1, 1000, reiziger);
        odao.update(ovUpdate);
        System.out.println("\nResultaat: " + odao.findByKaartnummer(12345));

        System.out.println("\n\nOVChipkaart Delete Test\n" +
                "Voor de delete " + odao.findAll().size() +" OV kaarten gevonden");
        odao.delete(ov);
        System.out.println("\nNa de delete " + odao.findAll().size() + " OV kaarten gevonden");



    }
}


