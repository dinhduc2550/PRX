/*

 */
package DAO;

import connection.DBConnection;

import entity.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

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
}
