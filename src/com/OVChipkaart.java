package com;

import java.time.LocalDate;
import java.util.ArrayList;

public class OVChipkaart {
    private int kaartNummer;
    private LocalDate geldigTot;
    private int klasse;
    private double saldo;
    private Reiziger reiziger;
    private ArrayList<Product> producten;

    public OVChipkaart(int kaartNummer, LocalDate geldigTot, int klasse, double saldo, Reiziger reiziger){
        this.kaartNummer = kaartNummer;
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
        this.reiziger.voegOVKaartToe(this);
        this.producten = new ArrayList<>();
    }

    public int getKaartNummer() {
        return kaartNummer;
    }

    public LocalDate getGeldigTot() {
        return geldigTot;
    }

    public int getKlasse() {
        return klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    @Override
    public String toString() {
        return "Kaartnummer: " + kaartNummer + " - " + klasse + "e klasse, saldo: " + saldo + "\n deze kaart is van: " + reiziger;
    }

    public ArrayList<Product> getProducten(){
        return this.producten;
    }

    public void voegProductToe(Product product){
        this.producten.add(product);
    }

    public void verwijderProduct(Product product){
        this.producten.remove(product);
    }
}
