package com.company;

import java.time.LocalDate;

public class Reiziger {
    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private LocalDate geboortedatum;

    Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, LocalDate geboortedatum){
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
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
                .append(geboortedatum)
                .append(")");
        return reiziger.toString();
    }
}
