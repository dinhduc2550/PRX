/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author dinhd
 */
public class Product {
     private int pID,uID;
    private String urlImage;
    private int amount;
    private float price;
    private String pName;
    private String des;
    private int active;
    private int view;
    public Product() {
    }

    public Product( int pID, int uID, String urlImage, int amount, float price, String pName, String des, int active) {
        this.pID = pID;
        this.uID = uID;
        this.urlImage = urlImage;
        this.amount = amount;
        this.price = price;
        this.pName = pName;
        this.des = des;
        this.active = active;
    }
    public Product(int uID, String urlImage, int amount, float price, String pName, String des, int active) {
        this.uID = uID;
        this.urlImage = urlImage;
        this.amount = amount;
        this.price = price;
        this.pName = pName;
        this.des = des;
        this.active = active;
    }
    public Product(int pID, int uID, String urlImage, int amount, float price, String pName, String des, int active, int view) {
        this.pID = pID;
        this.uID = uID;
        this.urlImage = urlImage;
        this.amount = amount;
        this.price = price;
        this.pName = pName;
        this.des = des;
        this.active = active;
        this.view = view;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }


    public int getpID() {
        return pID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
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

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return pName +"\n"+ pID+"\n"+amount+"\n"+price+"\n"+urlImage+"\n"+des;
    }

   
    
}