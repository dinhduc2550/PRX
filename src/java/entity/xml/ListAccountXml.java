/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType; 
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Thanh Dang
 */
@XmlRootElement(name = "accounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListAccountXml {
    private List<AccountXml> account = null; 
   
    public List<AccountXml> getAccounts() {
        return account;
    }
    
    @XmlElement
    public void setAccounts(AccountXml a) {
        List<AccountXml> list = new ArrayList<>();
        list.add(a);
        this.account = list;
    }
    
}
