package com.product;

import com.ovchipkaart.OVChipkaart;

import java.util.ArrayList;

public class Product {
    private int productNummer;
    private String naam;
    private String beschrijving;
    private double prijs;
    private ArrayList<OVChipkaart> ovChipkaarten;

    public Product(int productNummer, String naam, String beschrijving, double prijs){
        this.setProductNummer(productNummer);
        this.setNaam(naam);
        this.setBeschrijving(beschrijving);
        this.setPrijs(prijs);
        this.ovChipkaarten = new ArrayList<OVChipkaart>();
    }


    public int getProductNummer() {
        return productNummer;
    }

    public void setProductNummer(int productNummer) {
        this.productNummer = productNummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public void voegOVKaartToe(OVChipkaart ov){
        this.ovChipkaarten.add(ov);
    }

    public void verwijderOVKaart(OVChipkaart ov){
        this.ovChipkaarten.remove(ov);
    }

    public ArrayList<OVChipkaart> getOVKaarten(){
        return ovChipkaarten;
    }
}
