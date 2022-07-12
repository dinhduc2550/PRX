/*

 */
package DAO;

import connection.DBConnection;
import entity.Account;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectDB {

    Connection con;
    private HashMap<String, Account> hmAccount;
    public String status;

    private void initConnection() {
        try {
            con = new DBConnection().getConn();
            status = "Success";
        } catch (Exception e) {
            status = "Fail Connect" + e.getMessage();
        }
    }

    public ConnectDB() {
        initConnection();
    }

    public HashMap<String, Account> getHmAccount() {
        return hmAccount;
    }

    public void setHmAccount(HashMap<String, Account> hmAccount) {
        this.hmAccount = hmAccount;
    }

    public HashMap<String, Account> getDataAccount() {
        HashMap<String, Account> h = new HashMap<>();
        String sql = "SELECT * FROM dbo.HE141081_ducdv_Account";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            status = "Success";
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                h.put(id + "", new Account(id,
                        rs.getString(2),
                        rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            status = "Fail Get" + e.getMessage();
        }
        return h;

    }

    public Account checkLogin(String user, String psw) {
        Account a = null;
        String sql = "SELECT * FROM dbo.HE141081_ducdv_Account WHERE UserName = ? AND PassWord = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, psw);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                a = new Account(id, rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (SQLException e) {
        }
        return a;
    }

    public String getNameByID(int id) {
        String sql = "SELECT Name FROM dbo.HE141081_ducdv_UserInfo WHERE AccountID = ?";
        String name = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e) {
        }
        return name;
    }

    public int countAcc() {
        String sql = "SELECT COUNT(AccountID) FROM dbo.HE141081_ducdv_Account";
        int count = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return count;
    }
  
    public Account getAllAttibute(String userName, String psw) {
        String sql = "SELECT * FROM dbo.HE141081_ducdv_UserInfo LEFT JOIN dbo.HE141081_ducdv_Account"
                + " ON HE141081_ducdv_Account.AccountID = HE141081_ducdv_UserInfo.AccountID"
                + " WHERE  UserName = ? AND PassWord = ?";
        Account a = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, psw);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("aaa");
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String address = rs.getString(3);
                String phone = rs.getString(4);
                String gender = rs.getString(5);
                Date year = rs.getDate(6);
                int id1 = rs.getInt(7);
                String user = rs.getString(8);
                String pass = rs.getString(9);
                String role = rs.getString(10);
                int active = rs.getInt(11);
                a = new Account(id, user, pass, role, active, name,
                        address, phone, gender, year);
            }
        } catch (SQLException e) {
            a=null;
        }
        return a;
    }
    public Account getAllAttibute(String userName) {
        String sql = "SELECT * FROM dbo.HE141081_ducdv_UserInfo LEFT JOIN dbo.HE141081_ducdv_Account"
                + " ON HE141081_ducdv_Account.AccountID = HE141081_ducdv_UserInfo.AccountID"
                + " WHERE  UserName = ?";
        Account a = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("aaa");
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String address = rs.getString(3);
                String phone = rs.getString(4);
                String gender = rs.getString(5);
                Date year = rs.getDate(6);
                int id1 = rs.getInt(7);
                String user = rs.getString(8);
                String pass = rs.getString(9);
                String role = rs.getString(10);
                int active = rs.getInt(11);
                a = new Account(id, user, pass, role, active, name,
                        address, phone, gender, year);
            }
        } catch (SQLException e) {
        }
        return a;
    }

    public void registration(String user, String pass) {
        String sql = "INSERT INTO dbo.HE141081_ducdv_Account ( UserName, PassWord ) VALUES  ( ?,  ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.executeUpdate();
        } catch (SQLException e) {
        }

    }

    public int getIdAccount(String user, String pass) {
        String sql = "SELECT AccountID FROM dbo.HE141081_ducdv_Account WHERE UserName = ? AND PassWord = ? ";
        int id = -1;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            status = "Success";
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            status = "Fail Get" + e.getMessage();
        }
        return id;
    }

    public Account checkAccountExist(String name) {
        String sql = "SELECT * FROM dbo.HE141081_ducdv_Account WHERE UserName = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            status = "Success";
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
            }
        } catch (SQLException e) {
            status = "Fail Get" + e.getMessage();
        }
        return null;
    }

    public int inputInfo(Account a) {
        int n = 0;
        int id = getIdAccount(a.getName(), a.getPass());
        System.out.println("id = " + id);
        String sql = "INSERT INTO dbo.HE141081_ducdv_UserInfo  VALUES  ( ? , ? , ? , ? , ? , ? )";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, a.getId());
            ps.setString(2, a.getpName());
            ps.setString(3, a.getAddress());
            ps.setString(4, a.getPhone());
            ps.setString(5, a.getGender());
            ps.setDate(6, (Date) a.getYear());
            n = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return n;
    }

    public static void main(String[] args) {
//        Account a = new Account(7, "dinhduc2551", "123", "Van A", "Hà Nội", "0123456789", "Male", 2000);
        ConnectDB c = new ConnectDB();
//        Account a = c.getAllAttibute("nguyenvan", "123");
//        if (a == null) {
//            System.out.println("null");
//        } else {
//            System.out.println("Name = " + a.getpName());
//        }
        Account a;
        a = c.getAllAttibute("dinhduc2550");
        System.out.println("name=====" + a.getName());
        System.out.println("passs=====" + a.getPass());
        System.out.println("year =====" + a.getYear());
//        c.insertAll(a);
//        c.inputInfo(a);
//        c.registration(a);
//        int n = c.inputInfo(a);
//        if (n == 0) {
//            System.out.println("fail");
//        }
    }
}
