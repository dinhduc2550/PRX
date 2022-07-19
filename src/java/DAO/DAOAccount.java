/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.Account;
import entity.ListAccount;
import entity.ListUserProfile;
import entity.UserInformation;
import entity.UserProfile;
import helper.JAXBHelper;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import path.PathFile;

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
            fileReader = new FileReader(PathFile.ACCOUNT_XML_FILE_PATH);
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
                                    System.out.println("role: " + currentText);
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

    public static void writeAccounts(Account account) {
        List<Account> accounts = readAllAccounts();
        accounts.add(account);

        try {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter streamWriter = factory.createXMLStreamWriter(
                    new FileOutputStream(PathFile.ACCOUNT_XML_FILE_PATH));
            streamWriter.writeStartDocument("UTF-8", "1.0");
            streamWriter.writeCharacters("\n");
            streamWriter.writeStartElement("accounts");

            for (int i = 0; i < accounts.size(); i++) {
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

    public int changePass(String oldPass, String newPass, int id) throws JAXBException {
        JAXBHelper helperAccount = new JAXBHelper(ListAccount.class);
        ListAccount listAccount = helperAccount.readXml(PathFile.ACCOUNT_XML_FILE_PATH);

        for (Account account : listAccount.getAccounts()) {
            if (!account.getPass().equals(oldPass)) {
                return -1;
            }
            account.setPass(newPass);
        }
        helperAccount.writeXml(PathFile.ACCOUNT_XML_FILE_PATH, listAccount);
        return 1;
    }

    public UserInformation loadUserInformation(String userName) throws JAXBException {
        JAXBHelper helperForAccount = new JAXBHelper(ListAccount.class);
        JAXBHelper helperForProfile = new JAXBHelper(ListUserProfile.class);

        ListAccount listAccount = (ListAccount) helperForAccount.readXml(PathFile.ACCOUNT_XML_FILE_PATH);
        ListUserProfile listUserProfile = (ListUserProfile) helperForProfile.readXml(PathFile.PROFILE_XML_FILE_PATH);

        Map<Integer, Object> profileWithId = new HashMap<>();
        listUserProfile.getUserProfiles().forEach(profile -> {
            profileWithId.put(profile.getId(), profile);
        });

        UserInformation information = new UserInformation();
        listAccount.getAccounts().forEach(account -> {
            if (account.getUserName().equals(userName)) {
                UserProfile profile = (UserProfile) profileWithId.get(account.getId());
                information.setpName(profile.getName());
                information.setAddress(profile.getAddress());
                information.setGender(profile.getGender());
                information.setPhone(profile.getPhone());
                information.setYear(profile.getDate());
                information.setIsActive(account.getIsActive());
                information.setRole(account.getRole());
                information.setName(userName);
            }
        });
        return information;
    }

    public int updateInfo(UserInformation newInfor) throws JAXBException {
        int id = newInfor.getId();
        String fullName = newInfor.getpName();
        String address = newInfor.getAddress();
        String phone = newInfor.getPhone();
        String gender = newInfor.getGender();
        int isActive = newInfor.getIsActive();
        String year = newInfor.getYear();

        JAXBHelper helperForAccount = new JAXBHelper(ListAccount.class);
        JAXBHelper helperForProfile = new JAXBHelper(ListUserProfile.class);

        ListAccount listAccount = (ListAccount) helperForAccount.readXml(PathFile.ACCOUNT_XML_FILE_PATH);
        ListUserProfile listUserProfile = (ListUserProfile) helperForProfile.readXml(PathFile.PROFILE_XML_FILE_PATH);
        AtomicInteger atomicInt = new AtomicInteger(-1);
        listAccount.getAccounts().forEach(
                account -> {
                    if (account.getId() == id) {
                        account.setIsActive(isActive);
                    }
                });

        listUserProfile.getUserProfiles().forEach(
                profile -> {
                    if (profile.getId() == id) {
                        profile.setAddress(address);
                        profile.setDate(year);
                        profile.setGender(gender);
                        profile.setName(fullName);
                        profile.setPhone(phone);

                        atomicInt.set(1);
                    }
                });
        return atomicInt.get();
    }

    public Vector<UserInformation> getAllAccountWithName() throws JAXBException {
        JAXBHelper helperForAccount = new JAXBHelper(ListAccount.class);
        JAXBHelper helperForProfile = new JAXBHelper(ListUserProfile.class);

        ListAccount listAccount = (ListAccount) helperForAccount.readXml(PathFile.ACCOUNT_XML_FILE_PATH);
        ListUserProfile listUserProfile = (ListUserProfile) helperForProfile.readXml(PathFile.PROFILE_XML_FILE_PATH);

        Map<Integer, Object> profileWithId = new HashMap<>();
        listUserProfile.getUserProfiles().forEach(profile -> {
            profileWithId.put(profile.getId(), profile);
        });

        UserInformation information = new UserInformation();
        Vector<UserInformation> getInformations = new Vector<>();
        listAccount.getAccounts().forEach(account -> {
            UserProfile profile = (UserProfile) profileWithId.get(account.getId());
            information.setpName(profile.getName());
            information.setAddress(profile.getAddress());
            information.setGender(profile.getGender());
            information.setPhone(profile.getPhone());
            information.setYear(profile.getDate());
            information.setIsActive(account.getIsActive());
            information.setRole(account.getRole());
            information.setName(account.getUserName());
            getInformations.add(information);
        });
        return getInformations;
    }

    public Vector<UserInformation> loadAccByFilter(String option, String txt) throws JAXBException {
        JAXBHelper helperForAccount = new JAXBHelper(ListAccount.class);
        JAXBHelper helperForProfile = new JAXBHelper(ListUserProfile.class);

        ListAccount listAccount = (ListAccount) helperForAccount.readXml(PathFile.ACCOUNT_XML_FILE_PATH);
        ListUserProfile listUserProfile = (ListUserProfile) helperForProfile.readXml(PathFile.PROFILE_XML_FILE_PATH);

        Map<Integer, Object> profileWithId = new HashMap<>();
        listUserProfile.getUserProfiles().forEach(profile -> {
            profileWithId.put(profile.getId(), profile);
        });
        UserInformation information = new UserInformation();
        Vector<UserInformation> getInformations = new Vector<>();
        switch (option) {
            case "none":
                listAccount.getAccounts().forEach(account -> {
                    UserProfile profile = (UserProfile) profileWithId.get(account.getId());
                    information.setpName(profile.getName());
                    information.setAddress(profile.getAddress());
                    information.setGender(profile.getGender());
                    information.setPhone(profile.getPhone());
                    information.setYear(profile.getDate());
                    information.setIsActive(account.getIsActive());
                    information.setRole(account.getRole());
                    information.setName(account.getUserName());
                    getInformations.add(information);
                });
                return getInformations;
            case "id":
                listAccount.getAccounts().forEach(account -> {
                    if (String.valueOf(account.getId()).contains(txt)) {
                        UserProfile profile = (UserProfile) profileWithId.get(account.getId());
                        information.setpName(profile.getName());
                        information.setAddress(profile.getAddress());
                        information.setGender(profile.getGender());
                        information.setPhone(profile.getPhone());
                        information.setYear(profile.getDate());
                        information.setIsActive(account.getIsActive());
                        information.setRole(account.getRole());
                        information.setName(account.getUserName());
                        getInformations.add(information);
                    }
                });
                return getInformations;
            case "uname":
                listAccount.getAccounts().forEach(account -> {
                    if (account.getUserName().contains(txt)) {
                        UserProfile profile = (UserProfile) profileWithId.get(account.getId());
                        information.setpName(profile.getName());
                        information.setAddress(profile.getAddress());
                        information.setGender(profile.getGender());
                        information.setPhone(profile.getPhone());
                        information.setYear(profile.getDate());
                        information.setIsActive(account.getIsActive());
                        information.setRole(account.getRole());
                        information.setName(account.getUserName());
                        getInformations.add(information);
                    }
                });
                return getInformations;
            case "role":
                listAccount.getAccounts().forEach(account -> {
                    if (account.getRole().contains(txt)) {
                        UserProfile profile = (UserProfile) profileWithId.get(account.getId());
                        information.setpName(profile.getName());
                        information.setAddress(profile.getAddress());
                        information.setGender(profile.getGender());
                        information.setPhone(profile.getPhone());
                        information.setYear(profile.getDate());
                        information.setIsActive(account.getIsActive());
                        information.setRole(account.getRole());
                        information.setName(account.getUserName());
                        getInformations.add(information);
                    }
                });
                return getInformations;
            case "fname":
                listAccount.getAccounts().forEach(account -> {

                    UserProfile profile = (UserProfile) profileWithId.get(account.getId());
                    if (profile.getName().contains(txt)) {
                        information.setpName(profile.getName());
                        information.setAddress(profile.getAddress());
                        information.setGender(profile.getGender());
                        information.setPhone(profile.getPhone());
                        information.setYear(profile.getDate());
                        information.setIsActive(account.getIsActive());
                        information.setRole(account.getRole());
                        information.setName(account.getUserName());
                        getInformations.add(information);
                    }
                });
                return getInformations;
        }
        return null;
    }

}
