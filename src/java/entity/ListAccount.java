/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import entity.Account;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType; 
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "accounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListAccount {
    private List<Account> account = null; 
   
    public List<Account> getAccounts() {
        return account;
}
    
    @XmlElement
    public void setAccounts(Account a) {
        List<Account> list = new ArrayList<>();
        list.add(a);
        this.account = list;
    }
    
}
