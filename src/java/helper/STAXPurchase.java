package helper;

import entity.History;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class STAXPurchase {

    @SuppressWarnings("null")
    public List<History> getAllPurchases() {
        List<History> purchases = new ArrayList<>();
        History tmpHistory = null;
        String currentText = null;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            FileReader file = new FileReader("D:\\ki8\\HistoryPurchase.xml");
//            FileReader file = new FileReader("HistoryPurchase.xml");
            XMLStreamReader reader = factory.createXMLStreamReader(file);

            while (reader.hasNext()) {
                int category = reader.next();
                switch (category) {
                    case XMLStreamConstants.START_ELEMENT:
                        if (reader.getLocalName().equals("Purchase")) {
                            tmpHistory = new History();
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        currentText = reader.getText();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        String tag = reader.getLocalName();
                        switch (tag) {
                            case "AccountID":
                                tmpHistory.setaID(Integer.parseInt(currentText));
                                break;
                            case "ProductID":
                                tmpHistory.setpID(Integer.parseInt(currentText));
                                break;
                            case "Amount":
                                tmpHistory.setAmount(Integer.parseInt(currentText));
                                break;
                            case "Price":
                                tmpHistory.setPrice(Float.parseFloat(currentText));
                                break;
                            case "Total":
                                tmpHistory.setTotal(Float.parseFloat(currentText));
                                break;
                            case "Date":
                                tmpHistory.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(currentText));
                                break;
                            case "Username":
                                tmpHistory.setUsername(currentText);
                                break;
                            case "Purchase":
                                purchases.add(tmpHistory);
                                break;
                        }
                        break;
                }
            }

            reader.close();
            file.close();
        } catch (FileNotFoundException | XMLStreamException ex) {
            Logger.getLogger(STAXPurchase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(STAXPurchase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return purchases;
    }
    
    public List<History> loadPurchasesByUsername(String username) {
        List<History> purchases = getAllPurchases();
        List<History> subList = new ArrayList<>();
        
        for (History history : purchases) {
            if (history.getUsername().toLowerCase().contains(username.toLowerCase())){
                subList.add(history);
            }
        }
        
        return subList;
    }
}
