/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import connection.DBConnection;
import entity.Account;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import servletPack.saveInfoToDB;

/**
 *
 * @author dinhd
 */
public class DAOAccount {

    Connection con;

    private void initConnection() {
        try {
            con = new DBConnection().getConn();
        } catch (Exception e) {
        }
    }

    public DAOAccount() {
        initConnection();
    }

    public Vector<Account> getAllAccountWithName() {
        String sql = "SELECT HE141081_ducdv_Account.*,dbo.HE141081_ducdv_UserInfo.Name FROM dbo.HE141081_ducdv_Account \n"
                + "INNER JOIN dbo.HE141081_ducdv_UserInfo ON HE141081_ducdv_Account.AccountID = HE141081_ducdv_UserInfo.AccountID";
        Vector<Account> v = new Vector<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String uname = rs.getString(2);
                String pass = rs.getString(3);
                String role = rs.getString(4);
                int active = rs.getInt(5);
                String name = rs.getString(6);
                Account a = new Account(id, uname, pass, role);
                a.setActive(active);
                a.setpName(name);
                v.add(a);
            }
        } catch (SQLException ex) {
        }
        return v;
    }

    public Vector<Account> loadAccByFilter(String op, String txt) {
        Vector<Account> v = new Vector<>();
        String sql = null;
        if (op.equalsIgnoreCase("none")) {
            sql = "SELECT HE141081_ducdv_Account.*,dbo.HE141081_ducdv_UserInfo.Name FROM dbo.HE141081_ducdv_Account\n"
                    + "INNER JOIN dbo.HE141081_ducdv_UserInfo ON HE141081_ducdv_Account.AccountID = HE141081_ducdv_UserInfo.AccountID";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String uname = rs.getString(2);
                    String pass = rs.getString(3);
                    String role = rs.getString(4);
                    int active = rs.getInt(5);
                    String name = rs.getString(6);
                    Account a = new Account(id, uname, pass, role);
                    a.setActive(active);
                    a.setpName(name);
                    v.add(a);
                }
            } catch (SQLException ex) {
            }
        } else {
            if (op.equalsIgnoreCase("id")) {
                sql = "SELECT HE141081_ducdv_Account.*,dbo.HE141081_ducdv_UserInfo.Name FROM dbo.HE141081_ducdv_Account\n"
                        + "INNER JOIN dbo.HE141081_ducdv_UserInfo ON HE141081_ducdv_Account.AccountID = HE141081_ducdv_UserInfo.AccountID WHERE HE141081_ducdv_Account.AccountID LIKE ?";

            } else if (op.equalsIgnoreCase("uname")) {
                sql = "SELECT HE141081_ducdv_Account.*,dbo.HE141081_ducdv_UserInfo.Name FROM dbo.HE141081_ducdv_Account\n"
                        + "INNER JOIN dbo.HE141081_ducdv_UserInfo ON HE141081_ducdv_Account.AccountID = HE141081_ducdv_UserInfo.AccountID WHERE UserName LIKE ?";
//            try {
//                PreparedStatement ps = con.prepareStatement(sql);
//                ps.setString(1, "%" + txt + "%");
//                ResultSet rs = ps.executeQuery();
//                while (rs.next()) {
//                    int id = rs.getInt(1);
//                    String uname = rs.getString(2);
//                    String pass = rs.getString(3);
//                    String role = rs.getString(4);
//                    int active = rs.getInt(5);
//                    String name = rs.getString(6);
//                    Account a = new Account(id, uname, pass, role);
//                    a.setActive(active);
//                    a.setpName(name);
//                    v.add(a);
//                }
//            } catch (SQLException ex) {
//            }
            } else if (op.equalsIgnoreCase("role")) {
                sql = "SELECT HE141081_ducdv_Account.*,dbo.HE141081_ducdv_UserInfo.Name FROM dbo.HE141081_ducdv_Account\n"
                        + "INNER JOIN dbo.HE141081_ducdv_UserInfo ON HE141081_ducdv_Account.AccountID = "
                        + "HE141081_ducdv_UserInfo.AccountID WHERE Role LIKE ?";
//            try {
//                PreparedStatement ps = con.prepareStatement(sql);
//                ps.setString(1, "%" + txt + "%");
//                ResultSet rs = ps.executeQuery();
//                while (rs.next()) {
//                    int id = rs.getInt(1);
//                    String uname = rs.getString(2);
//                    String pass = rs.getString(3);
//                    String role = rs.getString(4);
//                    int active = rs.getInt(5);
//                    String name = rs.getString(6);
//                    Account a = new Account(id, uname, pass, role);
//                    a.setActive(active);
//                    a.setpName(name);
//                    v.add(a);
//                }
//            } catch (SQLException ex) {
//            }
            } else if (op.equalsIgnoreCase("fname")) {
                sql = "SELECT HE141081_ducdv_Account.*,dbo.HE141081_ducdv_UserInfo.Name FROM dbo.HE141081_ducdv_Account\n"
                        + "INNER JOIN dbo.HE141081_ducdv_UserInfo ON HE141081_ducdv_Account.AccountID = HE141081_ducdv_UserInfo.AccountID WHERE Name LIKE ?";
//            try {
//                PreparedStatement ps = con.prepareStatement(sql);
//                ps.setString(1, "%" + txt + "%");
//                ResultSet rs = ps.executeQuery();
//                while (rs.next()) {
//                    int id = rs.getInt(1);
//                    String uname = rs.getString(2);
//                    String pass = rs.getString(3);
//                    String role = rs.getString(4);
//                    int active = rs.getInt(5);
//                    String name = rs.getString(6);
//                    Account a = new Account(id, uname, pass, role);
//                    a.setActive(active);
//                    a.setpName(name);
//                    v.add(a);
//                }
//            } catch (SQLException ex) {
//            }
            }
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, "%" + txt + "%");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String uname = rs.getString(2);
                    String pass = rs.getString(3);
                    String role = rs.getString(4);
                    int active = rs.getInt(5);
                    String name = rs.getString(6);
                    Account a = new Account(id, uname, pass, role);
                    a.setActive(active);
                    a.setpName(name);
                    v.add(a);
                }
            } catch (SQLException ex) {
            }
        }
//        else if(op.equalsIgnoreCase("status")){
//            sql = "SELECT * FROM dbo.HE141081_ducdv_Account WHERE Active = ?";
//        }
        return v;
    }

    public int updateInfo(Account a) {
        int i = -1;
        String sql = "UPDATE dbo.HE141081_ducdv_UserInfo SET Name = ?"
                + " ,Address = ?, Phonenumber = ?,Gender = ?,DoB = ?"
                + " WHERE AccountID = ?";
        try {
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1, a.getpName());
            ps.setString(2, a.getAddress());
            ps.setString(3, a.getPhone());
            ps.setString(4, a.getGender());
            ps.setDate(5, (Date) a.getYear());
            ps.setInt(6, a.getId());
            i = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql2 = "UPDATE dbo.HE141081_ducdv_Account SET Active = ? WHERE AccountID = ?";
        try {
            PreparedStatement ps = con.prepareCall(sql2);
            ps.setInt(1, a.getActive());
            ps.setInt(2, a.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    public String getPassByID(int id) {
        String s = "";
        String sql = "SELECT PassWord FROM dbo.HE141081_ducdv_Account WHERE AccountID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                s = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public int changePass(String pass, String newPass, int id) {
        int i = -1;
        String s = getPassByID(id);
        if (s.equals("")) {
            return i;
        }
        if (!pass.equals(s)) {
            return i;
        }
        String sql = "UPDATE dbo.HE141081_ducdv_Account SET PassWord = ? WHERE AccountID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newPass);
            ps.setInt(2, id);
            i = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }

        return i;
    }

    public Account login(String userName, String psw) {
        Account a = null;
        String sql = "SELECT * FROM dbo.HE141081_ducdv_Account WHERE UserName = ? AND PassWord = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            System.out.println("a1");
            ps.setString(1, userName);
            System.out.println("a2");
            ps.setString(2, psw);
            System.out.println("s3");
            ResultSet rs = ps.executeQuery();
            System.out.println("a4");
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                a = new Account();
                a.setId(rs.getInt(1));
                a.setName(rs.getString(2));
                a.setPass(rs.getString(3));
                a.setRole(rs.getString(4));
                a.setActive(rs.getInt(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return a;
    }

    public static void main(String[] args) {
        DAOAccount d = new DAOAccount();
        Account a = d.login("prj321", "123");
        System.out.println(a.getName());
//        Vector<Account> v = new DAOAccount().loadAccByFilter("uname", "a");
                //        for (Account account : v) {
                //            System.out.println("name===" + account.getName());
                //            System.out.println("rolll==== " + account.getRole());
                //        }
                //        Account a = new Account();
                //        a.setpName("Đinh Đức 2");
                //        a.setAddress("Hải Phòng 2");
                //        a.setPhone("123");
                //        a.setGender("Male");
                //        String dob = "2000/01/01";
                //        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                //        java.util.Date parsed = null;
                //        try {
                //            parsed = format.parse(dob);
                //        } catch (ParseException ex) {
                //            Logger.getLogger(saveInfoToDB.class.getName()).log(Level.SEVERE, null, ex);
                //        }
                //        java.sql.Date sql = new java.sql.Date(parsed.getTime());
                //        a.setYear(sql);
                //        a.setActive(0);
                //        a.setId(1);
                //        int i = new DAOAccount().updateInfo(a);
                //        if (i == -1) {
                //            System.out.println("Faill!!!");
                //        } else {
                //            System.out.println("Successs");
    }
//    }
}
