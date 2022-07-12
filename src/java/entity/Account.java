/*

 */
package entity;

import java.util.Date;

public class Account {
    private int id;
    private String name, pass;
    private String role;
    private int active;
    private String pName,address,phone,gender;
    private Date year;
    public Account() {
    }

    public Account(int id, String name, String pass, String role) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.role = role;
    }

    public Account(int id, String name, String pass, String pName, String address,
            String phone, String gender, Date year) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.pName = pName;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.year = year;
    }

    public Account(int id, String name, String pass, String role, 
            int active, String pName, String address, String phone,
            String gender, Date year) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.role = role;
        this.active = active;
        this.pName = pName;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.year = year;
    }
    
    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    

    @Override
    public String toString() {
        return id+""+name + " " + pass +" "+pName+" "+address+" "+gender+" "+phone+" "+year;
    }

}
