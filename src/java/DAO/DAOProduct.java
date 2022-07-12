/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import connection.DBConnection;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dinhd
 */
public class DAOProduct {

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
                        amount, price, pName, des, active,view);
                v.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

    public void insertToProduct(Product p) {
        String sql = "INSERT dbo.HE141081_ducdv_Product\n" +
"	      "+
"	VALUES  ( ? ," +
"	          ? ," +
"	          ? , " +
"	          ? , " +
"	          ? , " +
"	          ? , " +
"	          ? , " +
"	          ? " +
"	        )";
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
        Product p = new Product( 1, "img\\test\\", 1, 90,"hehe", "oh", 1);
        d.getProductByOffSet(0);
        Vector<Product> v = new Vector<>();
        v=d.getProductByOffSet(3);
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
                        amount, price, pName, des, active,view));
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
                        amount, price, pName, des, active,view));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }
    
    public int getLastIDProduct(){
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
                        amount, price, pName, des, active,view);
                p=product.getpID();
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
