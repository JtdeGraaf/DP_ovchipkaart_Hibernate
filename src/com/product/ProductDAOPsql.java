package com.product;

import com.ovchipkaart.OVChipkaart;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDAOPsql implements ProductDAO{
    private final Connection conn;

    public ProductDAOPsql(Connection conn){
        this.conn = conn;
    }

    @Override
    public boolean save(Product product) {
        try {
            Statement statement = conn.createStatement();
            String queryString = "INSERT INTO product (product_nummer, naam, beschrijving, prijs)" +
                    "VALUES (" + product.getProductNummer() + ", '" + product.getNaam() + "', '" + product.getBeschrijving() + "', " +
                    product.getPrijs() + ")";

            statement.executeUpdate(queryString);

            for(OVChipkaart ov : product.getOVKaarten()) {
                Statement statementRelatie = conn.createStatement();
                String queryStringRelatie = "INSERT INTO ov_chipkaart_product (kaart_nummer, product_nummer)" +
                        "VALUES (" + ov.getKaartNummer() + ", " + product.getProductNummer() + ")";
                statementRelatie.executeUpdate(queryStringRelatie);
            }
            return true;
        }
        catch(SQLException E){
            E.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Product product) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE product SET naam = '" + product.getNaam() + "', "+
                    "beschrijving = '" + product.getBeschrijving() + "', " +
                    "prijs = " + product.getPrijs() +
                    " WHERE product_nummer = " + product.getProductNummer());

            return true;
        }
        catch(SQLException E){
            E.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM product WHERE product_nummer = " + product.getProductNummer() + ";" +
                    "DELETE FROM ov_chipkaart_product WHERE product_nummer = " + product.getProductNummer());

            return true;
        }

        catch(SQLException E) {
            E.printStackTrace();
            return false;
        }
    }

    public ArrayList<Product> findByOVChipkaart(OVChipkaart ov) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select product.product_nummer, product.naam, product.beschrijving, product.prijs from product" +
                " INNER JOIN ov_chipkaart_product on product.product_nummer = ov_chipkaart_product.product_nummer" +
                " WHERE ov_chipkaart_product.kaart_nummer = " + ov.getKaartNummer());

        ArrayList<Product> producten = new ArrayList<>();
        while(rs.next()){
            int productNummer = rs.getInt("product_nummer");
            String naam = rs.getString("naam");
            String beschrijving = rs.getString("beschrijving");
            double prijs = rs.getDouble("prijs");

            producten.add(new Product(productNummer, naam, beschrijving, prijs));
        }
        return producten;
    }

    public ArrayList<Product> findAll() throws SQLException{
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM product");

        ArrayList<Product> producten = new ArrayList<>();
        while(rs.next()){
            int productNummer = rs.getInt("product_nummer");
            String naam = rs.getString("naam");
            String beschrijving = rs.getString("beschrijving");
            double prijs = rs.getDouble("prijs");

            producten.add(new Product(productNummer, naam, beschrijving, prijs));
        }
        return producten;
    }
}
