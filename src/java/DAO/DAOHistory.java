/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import connection.DBConnection;
import entity.History;
import entity.ListHistory;
import entity.News;
import entity.Product;
import helper.JAXBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBException;
import path.PathFile;

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

//    public Vector<History> getHistoryPurchase(int numRows, String uname) throws JAXBException, JAXBException, JAXBException {
//        Vector<History> v = new Vector<>();
//        String sql = "SELECT HE141081_ducdv_Account.AccountID,\n"
//                + "		HE141081_ducdv_Account.UserName,\n"
//                + "		HE141081_ducdv_HistoryBuy.ProductID,\n"
//                + "		dbo.HE141081_ducdv_HistoryBuy.Amount,\n"
//                + "		Price,Total,purchaseDate\n"
//                + " FROM dbo.HE141081_ducdv_HistoryBuy\n"
//                + "INNER JOIN dbo.HE141081_ducdv_Account ON HE141081_ducdv_HistoryBuy.AccountID = HE141081_ducdv_Account.AccountID WHERE UserName = ?\n"
//                + "ORDER BY Total DESC, purchaseDate ASC, purchaseID OFFSET ? ROWS FETCH NEXT 6 ROW ONLY";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, uname);
//            ps.setInt(2, numRows);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                int aID = rs.getInt(1);
//                String username = rs.getString(2);
//                int pID = rs.getInt(3);
//                int amount = rs.getInt(4);
//                float price = rs.getFloat(5);
//                float total = rs.getFloat(6);
//                Date date = rs.getDate(7);
//                v.add(new History(aID, pID, amount, price, total, date, username));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOHistory.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return v;
//    }
    
        public Vector<History> getHistoryPurchase(int numRows, String uname) throws JAXBException{
            JAXBHelper helper = new JAXBHelper(ListHistory.class);
            ListHistory listHistory = (ListHistory)helper.readXml(PathFile.HISTORY_XML_FILE_PATH);
            Predicate<History> p = (t) -> {
               return t.getUsername().endsWith(uname);
            };
            int totalItemPerPage = 6;
            List<History> listHistoryFilterd = listHistory.getHistory()
                    .stream()
                    .filter(p)
                    .limit(totalItemPerPage)
                    .skip(numRows*totalItemPerPage)
                    .collect(Collectors.toList());
            return new Vector<History>(listHistoryFilterd);
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
    public void insertPurcase(ListHistory history) throws JAXBException {
        JAXBHelper helper = new JAXBHelper(ListHistory.class);
        ListHistory parseToHistory = (ListHistory)helper.readXml(PathFile.HISTORY_XML_FILE_PATH);
        List<History> listHistory = parseToHistory.getHistory();
            listHistory.addAll(history.getHistory());
            helper.writeXml(PathFile.HISTORY_XML_FILE_PATH, listHistory);
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
