/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import connection.DBConnection;
import entity.History;
import entity.News;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dinhd
 */
public class DAOHistory {

    Connection conn;

    private void initConnection() {
        try {
            conn = new DBConnection().getConn();
        } catch (Exception e) {
        }
    }

    public DAOHistory() {
        initConnection();
    }

    public int countHistoryPurchase() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM dbo.HE141081_ducdv_HistoryBuy";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
        }
        return count;
    }

    public float totalPurchase() {
        float count = 0;
        String sql = "SELECT SUM(Total) FROM dbo.HE141081_ducdv_HistoryBuy";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getFloat(1);
            }
        } catch (SQLException ex) {
        }
        return count;
    }

    public Vector<History> getHistoryPurchase(int numRows) {
        Vector<History> v = new Vector<>();
        String sql = "SELECT HE141081_ducdv_Account.AccountID,\n"
                + "		HE141081_ducdv_Account.UserName,\n"
                + "		HE141081_ducdv_HistoryBuy.ProductID,\n"
                + "		dbo.HE141081_ducdv_HistoryBuy.Amount,\n"
                + "		Price,Total,purchaseDate\n"
                + "               FROM dbo.HE141081_ducdv_HistoryBuy\n"
                + "               INNER JOIN dbo.HE141081_ducdv_Account ON"
                + " HE141081_ducdv_HistoryBuy.AccountID "
                + "= HE141081_ducdv_Account.AccountID"
                + " ORDER BY purchaseID OFFSET ? ROWS FETCH NEXT 1 ROW ONLY";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, numRows);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int aID = rs.getInt(1);
                String username = rs.getString(2);
                int pID = rs.getInt(3);
                int amount = rs.getInt(4);
                float price = rs.getFloat(5);
                float total = rs.getFloat(6);
                Date date = rs.getDate(7);
                v.add(new History(aID, pID, amount, price, total, date, username));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

    public Vector<History> getHistoryPurchase(int numRows, String uname) {
        Vector<History> v = new Vector<>();
        String sql = "SELECT HE141081_ducdv_Account.AccountID,\n"
                + "		HE141081_ducdv_Account.UserName,\n"
                + "		HE141081_ducdv_HistoryBuy.ProductID,\n"
                + "		dbo.HE141081_ducdv_HistoryBuy.Amount,\n"
                + "		Price,Total,purchaseDate\n"
                + " FROM dbo.HE141081_ducdv_HistoryBuy\n"
                + "INNER JOIN dbo.HE141081_ducdv_Account ON HE141081_ducdv_HistoryBuy.AccountID = HE141081_ducdv_Account.AccountID WHERE UserName = ?\n"
                + "ORDER BY Total DESC, purchaseDate ASC, purchaseID OFFSET ? ROWS FETCH NEXT 6 ROW ONLY";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, uname);
            ps.setInt(2, numRows);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int aID = rs.getInt(1);
                String username = rs.getString(2);
                int pID = rs.getInt(3);
                int amount = rs.getInt(4);
                float price = rs.getFloat(5);
                float total = rs.getFloat(6);
                Date date = rs.getDate(7);
                v.add(new History(aID, pID, amount, price, total, date, username));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

    public Vector<History> getHistoryPurchase(int numRows, String op1, String op2) {
        Vector<History> v = new Vector<>();
        String sql = "SELECT HE141081_ducdv_Account.AccountID,\n"
                + "		HE141081_ducdv_Account.UserName,\n"
                + "		HE141081_ducdv_HistoryBuy.ProductID,\n"
                + "		dbo.HE141081_ducdv_HistoryBuy.Amount,\n"
                + "		Price,Total,purchaseDate\n"
                + "               FROM dbo.HE141081_ducdv_HistoryBuy\n"
                + "               INNER JOIN dbo.HE141081_ducdv_Account ON"
                + " HE141081_ducdv_HistoryBuy.AccountID "
                + "= HE141081_ducdv_Account.AccountID"
                + " ORDER BY " + op1 + " " + op2 + " purchaseID OFFSET ? ROWS FETCH NEXT 1 ROW ONLY";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, numRows);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int aID = rs.getInt(1);
                String username = rs.getString(2);
                int pID = rs.getInt(3);
                int amount = rs.getInt(4);
                float price = rs.getFloat(5);
                float total = rs.getFloat(6);
                Date date = rs.getDate(7);
                v.add(new History(aID, pID, amount, price, total, date, username));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

    //insert to history purchase
    public void insertPurcase(Product n,int uid,int pid,String phone,String address,String name,int amount,float price) {
        String sql = "INSERT INTO dbo.HE141081_ducdv_HistoryBuy\n" +
"VALUES  ( ? ,  ? ,   ? ,  ? ,?, GETDATE(),  ?,?,?)";
        try {
            PreparedStatement ps =conn.prepareStatement(sql);
            ps.setInt(1, uid);
            ps.setInt(2, pid);
            ps.setInt(3, n.getAmount());
            ps.setFloat(4, n.getPrice());
            float total = price*(float)amount;
            ps.setFloat(5, total);
            ps.setString(6, address);
            ps.setString(7, name);
            ps.setString(8, phone);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DAOHistory d = new DAOHistory();
//        System.out.println("count = " + d.countHistoryPurchase());
        News n = new News();
        n.setAmount(1);
        n.setPrice(800);
        n.setId(1);
        int uid = 1;
//        d.insertPurcase(n,2,1,"0123456789","hải phòng","Đức",3,800);
        
    }
}
