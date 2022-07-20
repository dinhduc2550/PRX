/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dinhd
 */
@XmlRootElement(name = "history")
@XmlAccessorType(XmlAccessType.FIELD)
public class History {
    private int aID,pID;
    private int amount;
    private float price,total;
    private String date;
    private String username;
    private String address;
    private String phone;

    public History(int aID, int pID, int amount, float price, float total, String date, String username, String address, String phone) {
        this.aID = aID;
        this.pID = pID;
        this.amount = amount;
        this.price = price;
        this.total = total;
        this.date = date;
        this.username = username;
        this.address = address;
        this.phone = phone;
    }
    
    public History(int aID, int pID, int amount, float price, float total, String date) {
        this.aID = aID;
        this.pID = pID;
        this.amount = amount;
        this.price = price;
        this.total = total;
        this.date = date;
    }

    public History(int aID, int pID, int amount, float price, 
            float total, String date, String username) {
        this.aID = aID;
        this.pID = pID;
        this.amount = amount;
        this.price = price;
        this.total = total;
        this.date = date;
        this.username = username;
    }

    public int getaID() {
        return aID;
    }

    public void setaID(int aID) {
        this.aID = aID;
    }

    public int getpID() {
        return pID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
