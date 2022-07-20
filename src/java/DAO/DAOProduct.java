/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import connection.DBConnection;
import entity.News;
import entity.Product;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import path.PathFile;

/**
 *
 * @author dinhd
 */
public class DAOProduct {

    public Vector<Product> getListProductFromXML(int position) {
        File file = new File(PathFile.PATH_DATA_PRODUCTS);
        Vector<Product> listProduct = new Vector<>();
        try {
            XMLInputFactory factory = XMLInputFactory.newFactory();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(file));
            Product p = new Product();
            while (reader.hasNext()) {
                int type = reader.next();
                String urlImg = "";
                if (type == XMLStreamReader.START_ELEMENT) {
                    String nameTag = reader.getName().toString();

                    if (nameTag.equals("title")) {
                        String title = reader.getElementText();
//                        System.out.println(title);
                        p.setpName(title);
                    }
                    if (nameTag.equals("product")) {
                        String id = reader.getAttributeValue("", "id");
                        p.setpID(Integer.parseInt(id));
                    }
                    if (nameTag.equals("urlImg")) {
                        urlImg = reader.getElementText();
                        p.setUrlImage(urlImg);
//                        System.out.println("url===1 " + p.getUrlImage());
                    }
                    if (nameTag.equals("amount")) {
                        String amount = reader.getElementText();
                        p.setAmount(Integer.parseInt(amount));
                    }
                    if (nameTag.equals("price")) {
                        String price = reader.getElementText();
                        p.setPrice(Float.parseFloat(price));
                    }
                    if (nameTag.equals("active")) {
                        String active = reader.getElementText();
                        p.setActive(Integer.parseInt(active));
                    }
                    if (nameTag.equals("view")) {
                        String view = reader.getElementText();
//                        System.out.println("content view: " + view);
                        p.setView(Integer.parseInt(view));

                        listProduct.add(p);
                        p = new Product();
                    }
                    if (nameTag.equals("userID")) {
                        String uID = reader.getElementText();
                        p.setuID(Integer.parseInt(uID));
                    }
                    if (nameTag.equals("description")) {
                        String des = reader.getElementText();
                        p.setDes(des);

                    }
                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (position != -1) {
            Vector<Product> listProducts2 = new Vector<>();
            for (int i = 0; i < 6; i++) {
                Product p = listProduct.get(i + position);
                listProducts2.add(p);
            }
            return listProducts2;
        } else {
            return listProduct;
        }
    }

    public void writeListProductToXML(Vector<Product> listProduct) {
        try {
            XMLOutputFactory outFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter writer
                    = outFactory.createXMLStreamWriter(new FileOutputStream(PathFile.PATH_DATA_PRODUCTS));
            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeStartElement("catalog");
            for (Product p : listProduct) {

                writer.writeStartElement("product");
                writer.writeAttribute("id", p.getpID() + "");

                writer.writeStartElement("title");
                writer.writeCharacters(p.getpName());
                writer.writeEndElement();

                writer.writeStartElement("amount");
                writer.writeCharacters(p.getAmount() + "");
                writer.writeEndElement();

                writer.writeStartElement("price");
                writer.writeCharacters(p.getPrice() + "");
                writer.writeEndElement();

                writer.writeStartElement("active");
                writer.writeCharacters(1 + "");
                writer.writeEndElement();

                writer.writeStartElement("description");
                writer.writeCharacters(p.getDes() + "");
                writer.writeEndElement();

                writer.writeStartElement("urlImg");
                writer.writeCharacters(p.getUrlImage());
                writer.writeEndElement();

                writer.writeStartElement("userID");
                writer.writeCharacters("1");
                writer.writeEndElement();

                writer.writeStartElement("view");
                writer.writeCharacters(p.getView() + "");
                writer.writeEndElement();

                writer.writeEndElement();
            }

            writer.writeEndElement();
            writer.writeEndDocument();

            writer.close();
        } catch (XMLStreamException ex) {
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Product getProductByIDFromXML(int id) {
        Vector<Product> listProducts = new DAOProduct().getListProductFromXML(-1);
        for (Product p : listProducts) {
            if (p.getpID() == id) {
                System.out.println("Foundedd==================???");
                System.out.println("id= " + id);
                System.out.println("name= " + p.getpName());
                return p;
            }
        }
        return null;
    }

    public Vector<Product> getTop3ProductsViewsFromXML() {
        Vector<Product> listProduct = new DAOProduct().getListProductFromXML(-1);
        listProduct.sort(Comparator.comparing(a -> a.getView()));
        Vector<Product> listProduct2 = new Vector<>();
        Collections.reverse(listProduct);
        try {
            for (int i = 0; i < 3; i++) {
                listProduct2.add(listProduct.get(i));
                System.out.println("url " + i + ": " + listProduct.get(i).getUrlImage());
                System.out.println("View:===" + listProduct.get(i).getView());
            }
        } catch (Exception e) {
        }
        return listProduct2;
    }

    public int getLastIDTopProductsViewsFromXML() {
        Vector<Product> listProduct = new DAOProduct().getListProductFromXML(-1);
        listProduct.sort(Comparator.comparing(a -> a.getView()));

        return listProduct.get(listProduct.size() - 1).getpID();
    }

    public Vector<Product> getListProductByNameFromXML(int position, String txtSearch) {
        Vector<Product> listProduct = new DAOProduct().getListProductFromXML(-1);
        Vector<Product> listProduct2 = new Vector<>();//list product for search filter or load more
        position++;
        if (position == 0) {
            for (Product p : listProduct) {
                if (p.getpName().toLowerCase().contains(txtSearch.toLowerCase())) {
                    listProduct2.add(p);
                }
            }
            return listProduct2;
        }
        for (int i = 0; i < 6; i++) {
            try {
                Product p = listProduct.get(i + position);
                if (p.getpName().toLowerCase().contains(txtSearch.toLowerCase())) {
                    listProduct2.add(p);
                }
            } catch (Exception e) {
                return listProduct2;
            }
        }

        return listProduct2;
    }

    public void updateProductToXML(Product p) {
        DAOProduct daoProduct = new DAOProduct();
        Vector<Product> listProducts = daoProduct.getListProductFromXML(-1);
        for (Product p1 : listProducts) {
            if (p1.getpID() == p.getpID()) {
                p1.setpName(p.getpName());
                p1.setAmount(p.getAmount());
                p1.setPrice(p.getPrice());
                p1.setDes(p.getDes());
                p1.setUrlImage(p.getUrlImage());
                break;
            }
        }
        daoProduct.writeListProductToXML(listProducts);

    }

    public int getLastIDProductFromXML() {
        DAOProduct daoProduct = new DAOProduct();
        Vector<Product> listProducts = daoProduct.getListProductFromXML(-1);
        return listProducts.get(listProducts.size() - 1).getpID();
    }

    public void addProductToXML(Product p) {
        DAOProduct daoProduct = new DAOProduct();
        Vector<Product> listProducts = daoProduct.getListProductFromXML(-1);
        listProducts.add(p);
        daoProduct.writeListProductToXML(listProducts);
    }

    Connection conn;

    private void initConnection() {
        try {
            conn = new DBConnection().getConn();
        } catch (Exception e) {
        }
    }

    public DAOProduct() {
        initConnection();
    }

    public Product getProductByProductID(int id) {
        String sql = "SELECT * FROM dbo.HE141081_ducdv_Product WHERE ProductID = ?";
        Product p = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1));
                int pID = rs.getInt(1);
                String pName = rs.getString(2);
                int amount = rs.getInt(3);
                float price = rs.getFloat(4);
                int active = rs.getInt(5);
                String urlImage = rs.getString(6);
                int userID = rs.getInt(7);
                String des = rs.getString(8);
                p = new Product(pID, userID, urlImage,
                        amount, price, pName, des, active);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public Vector<Product> getAllProduct() {
        Vector<Product> v = new Vector<>();
        String sql = "SELECT * FROM dbo.HE141081_ducdv_Product";
        Product p = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//                System.out.println(rs.getString(1));
                int pID = rs.getInt(1);
                String pName = rs.getString(2);
                int amount = rs.getInt(3);
                float price = rs.getFloat(4);
                int active = rs.getInt(5);
                String urlImage = rs.getString(6);
                int userID = rs.getInt(7);
                String des = rs.getString(8);
                int view = rs.getInt(9);
                p = new Product(pID, userID, urlImage,
                        amount, price, pName, des, active, view);
                v.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

    public void insertToProduct(Product p) {
        String sql = "INSERT dbo.HE141081_ducdv_Product\n"
                + "	      "
                + "	VALUES  ( ? ,"
                + "	          ? ,"
                + "	          ? , "
                + "	          ? , "
                + "	          ? , "
                + "	          ? , "
                + "	          ? , "
                + "	          ? "
                + "	        )";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getpName());
            ps.setInt(2, p.getAmount());
            ps.setFloat(3, p.getPrice());
            ps.setInt(4, 1);
            ps.setString(5, p.getUrlImage());
            ps.setInt(6, p.getuID());
            ps.setString(7, p.getDes());
            ps.setInt(8, 0);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateProductToDB(Product p) {
        String sql = "UPDATE dbo.HE141081_ducdv_Product SET"
                + " [Product Name] = ?, Amount = ?, Price = ?,"
                + " Active = ?, [URL Image]=?, Describe =?"
                + " WHERE ProductID = ?";
        if (p.getUrlImage().equals("img\\test\\")) {
            sql = "UPDATE dbo.HE141081_ducdv_Product SET [Product Name] = ?,"
                    + " Amount = ?, Price = ?, Active = ? , "
                    + " Describe = ? WHERE ProductID = ?";
            System.out.println("okaay");
        }
        if (!p.getUrlImage().equals("img\\test\\")) {
            try {
                System.out.println(p.getpID());
                System.out.println("nono");
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, p.getpName());
                ps.setInt(2, p.getAmount());
                ps.setFloat(3, p.getPrice());
                ps.setInt(4, 1);
                ps.setString(5, p.getUrlImage());
                ps.setString(6, p.getDes());
                ps.setInt(7, p.getpID());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, p.getpName());
                ps.setInt(2, p.getAmount());
                ps.setFloat(3, p.getPrice());
                ps.setInt(4, 1);
                ps.setString(5, p.getDes());
                ps.setInt(6, p.getpID());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        DAOProduct d = new DAOProduct();
        Product p = new Product(1, "img\\test\\", 1, 90, "hehe", "oh", 1);
        d.getProductByOffSet(0);
        Vector<Product> v = new Vector<>();
        v = d.getProductByOffSet(3);
        for (Product product : v) {
            System.out.println(product.toString());

        }
    }

    public Vector<Product> getProductByOffSet(int num) {
        Vector<Product> v = new Vector<>();
        String sql = "SELECT * FROM dbo.HE141081_ducdv_Product ORDER BY ProductID DESC  OFFSET ? ROW FETCH NEXT 6 ROWS ONLY ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, num);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//                System.out.println(rs.getString(1));
                int pID = rs.getInt(1);
                String pName = rs.getString(2);
                int amount = rs.getInt(3);
                float price = rs.getFloat(4);
                int active = rs.getInt(5);
                String urlImage = rs.getString(6);
                int userID = rs.getInt(7);
                String des = rs.getString(8);
                int view = rs.getInt(9);
                v.add(new Product(pID, userID, urlImage,
                        amount, price, pName, des, active, view));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

    public void updateProductToDB2(Product p) {
        String sql = "UPDATE dbo.HE141081_ducdv_Product SET [Product Name] = ?, "
                + "Amount = ?, Price = ?, Active = ?, [URL Image]=?, "
                + "userID = ?, Describe =? WHERE ProductID = ?";

        if (!p.getUrlImage().equals("img\\test\\")) {
            try {
                System.out.println(p.getpID());
                System.out.println("nono");
                PreparedStatement ps = conn.prepareStatement(sql);
                //product name
                ps.setString(1, p.getpName());
                //amount
                ps.setInt(2, p.getAmount());
                //price
                ps.setFloat(3, p.getPrice());
                //active
                ps.setInt(4, 1);
                //url
                ps.setString(5, p.getUrlImage());
                //userID
                ps.setInt(6, p.getuID());
                //des
                ps.setString(7, p.getDes());
                //pID
                ps.setInt(8, p.getpID());
            } catch (SQLException ex) {
                Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public Vector<Product> getTop3Product() {
        Vector<Product> p = new Vector<>();
        String sql = "SELECT TOP (3) * FROM dbo.HE141081_ducdv_Product ORDER BY [View] DESC ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int pID = rs.getInt(1);
                String pName = rs.getString(2);
                int amount = rs.getInt(3);
                float price = rs.getFloat(4);
                int active = rs.getInt(5);
                String urlImage = rs.getString(6);
                int userID = rs.getInt(7);
                String des = rs.getString(8);
                int view = rs.getInt(9);
                p.add(new Product(pID, userID, urlImage,
                        amount, price, pName, des, active, view));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }

    public int getLastIDProduct() {
        int p = 0;

        String sql = "SELECT TOP (1) * FROM dbo.HE141081_ducdv_Product ORDER BY [View] DESC ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int pID = rs.getInt(1);
                String pName = rs.getString(2);
                int amount = rs.getInt(3);
                float price = rs.getFloat(4);
                int active = rs.getInt(5);
                String urlImage = rs.getString(6);
                int userID = rs.getInt(7);
                String des = rs.getString(8);
                int view = rs.getInt(9);
                Product product = new Product(pID, userID, urlImage,
                        amount, price, pName, des, active, view);
                p = product.getpID();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }

    public void setView(int id) {
        String sql = "	UPDATE dbo.HE141081_ducdv_Product SET [View] = [View] + 1 WHERE	ProductID = ?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
