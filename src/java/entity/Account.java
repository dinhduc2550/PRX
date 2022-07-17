/*

 */
package entity;

import java.util.Date;

public class Account {
    private int id;
    private String userName, pass;
    private String role ;
    private int isActive;
   
    public Account() {
    }

    public Account(int id, String userName, String pass, String role, int isActive) {
        this.id = id;
        this.userName = userName;
        this.pass = pass;
        this.role = role;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", userName=" + userName + ", pass=" + pass + ", role=" + role + ", isActive=" + isActive + '}';
    }

   

}
