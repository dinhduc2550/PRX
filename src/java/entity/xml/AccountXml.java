/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Thanh Dang
 */
@XmlRootElement(name = "account")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountXml {

    public static final String ADMIN = "admin";
    public static final String SALE = "sale";
    public static final String CUSTOMER = "cus";

    private String id;
    private String userName;
    private String pass;
    private String role;
    private int isActive;

    public AccountXml() {
    }

    public AccountXml(String id, String userName, String pass, String role, int isActive) {
        this.id = id;
        this.userName = userName;
        this.pass = pass;
        this.role = role;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

}
