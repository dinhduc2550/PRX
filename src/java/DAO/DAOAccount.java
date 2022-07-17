/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import connection.DBConnection;
import entity.Account;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import servletPack.saveInfoToDB;

/**
 *
 * @author Tund
 */
public class DAOAccount {

  public static void main(String[] args) {
        List<Account> accounts = readAllAccounts();
        for (Account account : accounts) {
            System.out.println(account.toString());
        }
    }

    public static List<Account> readAllAccounts() {
        List<Account> accounts = null;
        Account currentAccount = null;
        String currentText = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        FileReader fileReader;
        try {
            fileReader = new FileReader("C:/Users/Admin/Desktop/PRX/PRX/Account.xml");
            XMLStreamReader streamReader = factory.createXMLStreamReader(fileReader);
            while (streamReader.hasNext()) {
                int category = streamReader.next();
                switch (category) {
                    case XMLStreamConstants.START_ELEMENT:
                        String tagname = streamReader.getLocalName();
                        if ("accounts".equals(tagname)) {
                            accounts = new ArrayList<>();
                        }
                        if ("account".equals(tagname)) {
                            currentAccount = new Account();

                        }

                        break;
                    case XMLStreamConstants.CHARACTERS:
                        currentText = streamReader.getText().trim();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        String endtag = streamReader.getLocalName();
                        switch (endtag) {
                            case "id":
                                if (currentAccount != null) {
                                    currentAccount.setId(Integer.parseInt(currentText));
                                }
                                break;
                            case "userName":
                                if (currentAccount != null) {
                                    currentAccount.setUserName(currentText);
                                }
                                break;
                            case "pass":
                                if (currentAccount != null) {
                                    currentAccount.setPass(currentText);
                                }
                                break;
                            case "role":
                                if (currentAccount != null) {
                                    System.out.println("role: "+currentText);
                                    currentAccount.setRole(currentText);
                                    
                                }
                                break;
                            case "isActive":
                                if (currentAccount != null) {
                                    currentAccount.setRole(currentText);
                                }
                                break;
                            case "account":
                                accounts.add(currentAccount);
                                break;

                        }
                        break;
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }

        return accounts;
    }
    public static void writeAccounts(Account account){
    List<Account> accounts = readAllAccounts();
     accounts.add(account);
     
     try {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter streamWriter = factory.createXMLStreamWriter(
                                            new FileOutputStream("C:/Users/Admin/Desktop/PRX/PRX/Account.xml"));
            streamWriter.writeStartDocument("UTF-8", "1.0");
            streamWriter.writeCharacters("\n");
            streamWriter.writeStartElement("accounts");
            
            
            
            for (int i = 0; i < accounts.size(); i ++) {
                streamWriter.writeCharacters("\n\t");
                Account acc = accounts.get(i);
                streamWriter.writeStartElement("account");
               
                writeSimpleElement(streamWriter, "id", String.valueOf(acc.getId()));
                writeSimpleElement(streamWriter, "userName", acc.getUserName());
                writeSimpleElement(streamWriter, "pass", acc.getPass());
                writeSimpleElement(streamWriter, "role", acc.getRole());
                writeSimpleElement(streamWriter, "isActive", String.valueOf(acc.getIsActive()));
                
                streamWriter.writeCharacters("\n\t");
                streamWriter.writeEndElement();
            }
            streamWriter.writeCharacters("\n");
            streamWriter.writeEndElement();
            streamWriter.flush();
            streamWriter.close();
            System.out.println("Success!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
     

    }
    public static void writeSimpleElement(XMLStreamWriter streamWriter, String tag, String content) {
        try {
            streamWriter.writeCharacters("\n\t\t");
            streamWriter.writeStartElement(tag);
            streamWriter.writeCharacters(content);
            streamWriter.writeEndElement();
        } catch (XMLStreamException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
