package main.reiziger;

import main.adres.Adres;
import main.ovChipKaart.OVChipkaart;

import java.time.LocalDate;
import java.util.ArrayList;

public class Reiziger {
    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private LocalDate geboortedatum;
    private Adres adres;
    private ArrayList<OVChipkaart> OVChipkaarten;

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, LocalDate geboortedatum){
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        this.adres = null;
        this.OVChipkaarten = new ArrayList<OVChipkaart>();
    }
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getVoorletters(){
        return voorletters;
    }

    public String getTussenvoegsel(){
        return tussenvoegsel;
    }

    public String getAchternaam(){
        return achternaam;
    }

    public LocalDate getGeboortedatum(){
        return geboortedatum;
    }

    @Override
    public String toString(){
        StringBuilder reiziger = new StringBuilder();
        reiziger.append("#")
                .append(id)
                .append(": ")
                .append(voorletters)
                .append(". ");
        if(tussenvoegsel != null){
            reiziger.append(tussenvoegsel)
                    .append(" ");
        }
        reiziger.append(achternaam)
                .append(" (")
                .append(geboortedatum);
        if(adres != null) {
            reiziger.append(") - Adres: #")
                    .append(adres.getId())
                    .append(" ")
                    .append(adres.getPostcode());
        }
        else {reiziger.append(")");}
        return reiziger.toString();
    }

    public Adres getAdres(){
        return adres;
    }

    public void setAdres(Adres adres){
        this.adres = adres;
    }

    public void voegOVKaartToe(OVChipkaart ov){
        this.OVChipkaarten.add(ov);
    }

    public ArrayList<OVChipkaart> getOVKaarten(){
        return this.OVChipkaarten;
    }
}
