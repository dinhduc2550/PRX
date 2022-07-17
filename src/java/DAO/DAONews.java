package DAO;

import connection.DBConnection;
import entity.News;
import entity.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
//import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import path.PathFile;

public class DAONews {

//    private final String PATH_DATA_NEWS = "I:\\PRX301\\MyProject\\news.xml";

    public Vector<News> getListNewsFromXML(int position) {
        File file = new File(PathFile.PATH_DATA_NEWS);
        Vector<News> listNews = new Vector<>();
        try {
            XMLInputFactory factory = XMLInputFactory.newFactory();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(file));
            News n = new News();
            while (reader.hasNext()) {
                int type = reader.next();
                String urlImg = "";
                if (type == XMLStreamReader.START_ELEMENT) {
                    String nameTag = reader.getName().toString();

                    if (nameTag.equals("title")) {
                        String title = reader.getElementText();
//                        System.out.println(title);
                        n.setTitle(title);
                    }
                    if (nameTag.equals("news")) {
                        String id = reader.getAttributeValue("", "id");
//                        System.out.println("id==" + id);
                        n.setId(Integer.parseInt(id));
                    }
                    if (nameTag.equals("urlImg")) {
                        urlImg = reader.getElementText();
                        n.setUrlImage(urlImg);
//                        System.out.println("url===1 " + n.getUrlImage());
                    }

                    if (nameTag.equals("type")) {
                        String active = reader.getElementText();
                        n.setType(Integer.parseInt(active));
                    }
                    if (nameTag.equals("view")) {
                        String view = reader.getElementText();
//                        System.out.println("content view: " + view);
                        n.setView(Integer.parseInt(view));

                    }
                    if (nameTag.equals("userID")) {
                        String uID = reader.getElementText();
//                        n.set(Integer.parseInt(uID));
                    }
                    if (nameTag.equals("urlTxt")) {
                        String urlTxt = reader.getElementText();
                        n.setUrlTxt(urlTxt);
                    }
                    if (nameTag.equals("productID")) {
                        String id = reader.getElementText();
                        Product p = new DAOProduct().getProductByIDFromXML(Integer.parseInt(id));
                        if (p != null) {
                            n.setProductName(p.getpName());
                        }
                        listNews.add(n);
                        n = new News();
                    }
                }

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listNews;
    }

    public Vector<News> getTop3NewsFromXML() {
        Vector<News> listNews = new DAONews().getListNewsFromXML(-1);
        listNews.sort(Comparator.comparing(a -> a.getView()));
        Vector<News> listNews2 = new Vector<>();
        Collections.reverse(listNews);
        try {
            for (int i = 0; i < 3; i++) {
                listNews2.add(listNews.get(i));
            }
        } catch (Exception e) {
        }
        return listNews2;
    }

    public Vector<News> getNextNewsFromXML(int type, int position, String txtSearch) {
        Vector<News> listNews = new DAONews().getListNewsFromXML(-1);
        Vector<News> listNews2 = new Vector<>();
        position++;
        if (position == 0) {
            return listNews;
        }
        try {
            for (int i = 0; i < 6; i++) {
                try {
                    if (!txtSearch.isEmpty()) {
                        News n = listNews.get(i + position);
                        if (n.getTitle().toLowerCase().contains(txtSearch.toLowerCase())) {
                            listNews2.add(listNews.get(i + position));
                        }

                    } else {
                        listNews2.add(listNews.get(i + position));
                    }
                } catch (Exception e) {
                    break;
                }
            }
        } catch (Exception e) {
        }
        return listNews2;
    }

    public News getNewsByIDFromXML(int newsID) {
        Vector<News> listNews = new DAONews().getListNewsFromXML(-1);
        for (News n : listNews) {
            if (n.getId() == newsID) {
                return n;
            }
        }
        return new News();
    }

    public void writeNewsToXML(Vector<News> listNews) {
        try {
            XMLOutputFactory outFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter writer
                    = outFactory.createXMLStreamWriter(new FileOutputStream(PathFile.PATH_DATA_NEWS));
            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeStartElement("catalog");
            for (News n : listNews) {

                writer.writeStartElement("news");
                writer.writeAttribute("id", n.getId() + "");

                writer.writeStartElement("urlImg");
                writer.writeCharacters(n.getUrlImage());
                writer.writeEndElement();

                writer.writeStartElement("view");
                writer.writeCharacters(n.getView() + "");
                writer.writeEndElement();

                writer.writeStartElement("title");
                writer.writeCharacters(n.getTitle());
                writer.writeEndElement();

                writer.writeStartElement("urlTxt");
                writer.writeCharacters(n.getUrlTxt() + "");
                writer.writeEndElement();

                writer.writeStartElement("type");
                writer.writeCharacters(n.getType() + "");
                writer.writeEndElement();

                writer.writeStartElement("userID");
                writer.writeCharacters("1");
                writer.writeEndElement();

                writer.writeStartElement("productID");
                writer.writeCharacters("-1");
                writer.writeEndElement();

                writer.writeEndElement();
            }

            writer.writeEndElement();
            writer.writeEndDocument();

            writer.close();
        } catch (XMLStreamException | FileNotFoundException ex) {
        }
    }

    //when user click to news, view will be increase 1 unit
    public void updateViewsOfNewsClicked(int newsID) {
        Vector<News> listNews = new DAONews().getListNewsFromXML(-1);
        for (News n : listNews) {
            if (n.getId() == newsID) {
                int views = n.getView();
                n.setView(++views);
                break;
            }
        }
        new DAONews().writeNewsToXML(listNews);
    }

    public void updateNewsToXML(News n1) {
        Vector<News> listNews = new DAONews().getListNewsFromXML(-1);
        int newsID = n1.getId();
        for (News n : listNews) {
            if (n.getId() == newsID) {
                if(!n1.getUrlImage().isEmpty())
                n.setUrlImage(n1.getUrlImage());
                n.setTitle(n1.getTitle());
                n.setUrlTxt(n1.getUrlTxt());
                n.setType(n1.getType());
                break;
            }
        }
        new DAONews().writeNewsToXML(listNews);

    }

    Connection conn;

    private void initConnection() {
        try {
            conn = new DBConnection().getConn();
        } catch (Exception e) {
        }
    }

    public DAONews() {
        initConnection();
    }

    public Vector<News> getNews() {
        Vector<News> news = new Vector<>();
        String sql = "SELECT * FROM dbo.HE141081_ducdv_News ORDER BY NewsID DESC OFFSET 0 ROWS FETCH NEXT 9 ROW ONLY";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String urlImage = rs.getString(2);
                int view = rs.getInt(3);
                String title = rs.getString(4);
                String urlTxt = rs.getString(5);
                news.add(new News(id, urlImage, view, title, urlTxt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return news;
    }

    public int getView(int id) {
        int view = 0;
        String sql = "SELECT [View] FROM dbo.HE141081_ducdv_News WHERE NewsID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                view = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return view;
    }

    public void setView(int id) {
        int view = getView(id);
        view++;
        String sql = "UPDATE dbo.HE141081_ducdv_News SET [View] = ? WHERE NewsID = ?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, view);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Vector<News> getNext(int type, int num, String txt, int uid) {
        Vector<News> news = new Vector<>();
        String sql;
        switch (type) {
            case 0:
                sql = "SELECT * FROM dbo.HE141081_ducdv_News WHERE type = 0 AND userID = ? ORDER BY NewsID DESC OFFSET ? ROWS FETCH NEXT 3 ROW ONLY";
                if (!txt.equals("")) {
                    sql = "SELECT * FROM dbo.HE141081_ducdv_News WHERE Title LIKE ? AND userID = ? and type = 0 ORDER BY NewsID DESC OFFSET ? ROWS FETCH NEXT 3 ROW ONLY";
                }
                try {
                    PreparedStatement ps = conn.prepareStatement(sql);
//                  ps.setInt(1, type);

                    if (!txt.equals("")) {
                        ps.setString(1, "%" + txt + "%");
                        ps.setInt(2, uid);
                        ps.setInt(3, num);
                    } else {
                        ps.setInt(1, uid);
                        ps.setInt(2, num);
                    }
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String urlImage = rs.getString(2);
                        int view = rs.getInt(3);
                        String title = rs.getString(4);
                        String urlTxt = rs.getString(5);
                        news.add(new News(id, urlImage, view, title, urlTxt));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                sql = "SELECT * FROM dbo.HE141081_ducdv_News WHERE type = 1 AND userID = ? ORDER BY NewsID DESC OFFSET ? ROWS FETCH NEXT 3 ROW ONLY";
                if (!txt.equals("")) {
                    sql = "SELECT * FROM dbo.HE141081_ducdv_News WHERE Title LIKE ? AND userID=? and type = 1 ORDER BY NewsID DESC OFFSET ? ROWS FETCH NEXT 3 ROW ONLY";
                }
                try {
                    PreparedStatement ps = conn.prepareStatement(sql);
//                ps.setInt(1, type);
                    if (!txt.equals("")) {
                        ps.setString(1, "%" + txt + "%");
                        ps.setInt(2, uid);
                        ps.setInt(3, num);
                    } else {
                        ps.setInt(1, uid);
                        ps.setInt(2, num);
                    }

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String urlImage = rs.getString(2);
                        int view = rs.getInt(3);
                        String title = rs.getString(4);
                        String urlTxt = rs.getString(5);
                        news.add(new News(id, urlImage, view, title, urlTxt));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                sql = "SELECT * FROM dbo.HE141081_ducdv_News WHERE userID = ?  ORDER BY NewsID DESC OFFSET ? ROWS FETCH NEXT 3 ROW ONLY";
                if (!txt.equals("")) {
                    sql = "SELECT * FROM dbo.HE141081_ducdv_News WHERE Title LIKE ? AND userID = ? ORDER BY NewsID DESC OFFSET ? ROWS FETCH NEXT 3 ROW ONLY";
                }
                try {
                    PreparedStatement ps = conn.prepareStatement(sql);
                    if (!txt.equals("")) {
                        ps.setString(1, "%" + txt + "%");
                        ps.setInt(2, uid);
                        ps.setInt(3, num);
                    } else {
                        ps.setInt(1, uid);
                        ps.setInt(2, num);
                    }
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String urlImage = rs.getString(2);
                        int view = rs.getInt(3);
                        String title = rs.getString(4);
                        String urlTxt = rs.getString(5);
                        news.add(new News(id, urlImage, view, title, urlTxt));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }

        return news;
    }

    public Vector<News> getNextHomePage(int type, int num, String txt) {
        Vector<News> news = new Vector<>();
        String sql;
        switch (type) {
            case 0:
                sql = "SELECT * FROM dbo.HE141081_ducdv_News WHERE type = 0 ORDER BY NewsID DESC OFFSET ? ROWS FETCH NEXT 3 ROW ONLY";
                if (!txt.equals("")) {
                    sql = "SELECT * FROM dbo.HE141081_ducdv_News WHERE Title LIKE ? and type = 0 ORDER BY NewsID DESC OFFSET ? ROWS FETCH NEXT 3 ROW ONLY";
                }
                try {
                    PreparedStatement ps = conn.prepareStatement(sql);
//                  ps.setInt(1, type);

                    if (!txt.equals("")) {
                        ps.setString(1, "%" + txt + "%");
                        ps.setInt(2, num);
                    } else {
                        ps.setInt(1, num);
                    }
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String urlImage = rs.getString(2);
                        int view = rs.getInt(3);
                        String title = rs.getString(4);
                        String urlTxt = rs.getString(5);
                        news.add(new News(id, urlImage, view, title, urlTxt));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                sql = "SELECT * FROM dbo.HE141081_ducdv_News WHERE type = 1 ORDER BY NewsID DESC OFFSET ? ROWS FETCH NEXT 3 ROW ONLY";
                if (!txt.equals("")) {
                    sql = "SELECT * FROM dbo.HE141081_ducdv_News WHERE Title LIKE ? and type = 1 ORDER BY NewsID DESC OFFSET ? ROWS FETCH NEXT 3 ROW ONLY";
                }
                try {
                    PreparedStatement ps = conn.prepareStatement(sql);
//                ps.setInt(1, type);
                    if (!txt.equals("")) {
                        ps.setString(1, "%" + txt + "%");
                        ps.setInt(2, num);
                    } else {
                        ps.setInt(1, num);
                    }

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String urlImage = rs.getString(2);
                        int view = rs.getInt(3);
                        String title = rs.getString(4);
                        String urlTxt = rs.getString(5);
                        news.add(new News(id, urlImage, view, title, urlTxt));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                sql = "SELECT * FROM dbo.HE141081_ducdv_News  ORDER BY NewsID DESC OFFSET ? ROWS FETCH NEXT 3 ROW ONLY";
                if (!txt.equals("")) {
                    sql = "SELECT * FROM dbo.HE141081_ducdv_News WHERE Title LIKE ? ORDER BY NewsID DESC OFFSET ? ROWS FETCH NEXT 3 ROW ONLY";
                }
                try {
                    PreparedStatement ps = conn.prepareStatement(sql);
                    if (!txt.equals("")) {
                        ps.setString(1, "%" + txt + "%");
                        ps.setInt(2, num);
                    } else {
                        ps.setInt(1, num);
                    }
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String urlImage = rs.getString(2);
                        int view = rs.getInt(3);
                        String title = rs.getString(4);
                        String urlTxt = rs.getString(5);
                        news.add(new News(id, urlImage, view, title, urlTxt));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }

        return news;
    }

    public Vector<News> getNextAll(int num) {
        Vector<News> news = new Vector<>();
        String sql;
        sql = "SELECT * FROM dbo.HE141081_ducdv_News ORDER BY NewsID OFFSET ? ROWS FETCH NEXT 3 ROW ONLY";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, num);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String urlImage = rs.getString(2);
                int view = rs.getInt(3);
                String title = rs.getString(4);
                String urlTxt = rs.getString(5);
                news.add(new News(id, urlImage, view, title, urlTxt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return news;
    }

    public Vector<News> getTop3News() {
        Vector<News> news = new Vector<>();
        String sql = "SELECT TOP (3) * FROM dbo.HE141081_ducdv_News WHERE TYPE = 1 ORDER BY [View] DESC ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String urlImage = rs.getString(2);
                int view = rs.getInt(3);
                String title = rs.getString(4);
                String urlTxt = rs.getString(5);
                news.add(new News(id, urlImage, view, title, urlTxt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return news;
    }

    public Vector<News> getTop3Product() {
        Vector<News> news = new Vector<>();
        String sql = "SELECT TOP (3) * FROM dbo.HE141081_ducdv_News WHERE type = 0 ORDER BY [View] DESC ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String urlImage = rs.getString(2);
                int view = rs.getInt(3);
                String title = rs.getString(4);
                String urlTxt = rs.getString(5);
                news.add(new News(id, urlImage, view, title, urlTxt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return news;
    }

    public int getLastProduct() {
        String sql = "SELECT TOP (1) * FROM dbo.HE141081_ducdv_News WHERE type = 0 ORDER BY [View] DESC ";
        News news = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String urlImage = rs.getString(2);
                int view = rs.getInt(3);
                String title = rs.getString(4);
                String urlTxt = rs.getString(5);
                news = new News(id, urlImage, view, title, urlTxt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return news.getId();
    }

    public Vector<News> getBySearch(String txt, String radio) {
        int type = 2;
        if (radio.equals("ProductsF")) {
            type = 0;
        } else if (radio.equals("NewsF")) {
            type = 1;
        }
        Vector<News> news = new Vector<>();
        String sql = "SELECT * FROM dbo.HE141081_ducdv_News WHERE Title LIKE ? and type = ? ORDER BY NewsID OFFSET 0 ROWS FETCH NEXT 3 ROW ONLY";
        if (type == 2) {
            sql = "SELECT * FROM dbo.HE141081_ducdv_News WHERE Title LIKE ? ORDER BY NewsID OFFSET 0 ROWS FETCH NEXT 3 ROW ONLY ";
        }
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if (type == 2) {
                ps.setString(1, "%" + txt + "%");
            } else {
                ps.setString(1, "%" + txt + "%");
                ps.setInt(2, type);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String urlImage = rs.getString(2);
                int view = rs.getInt(3);
                String title = rs.getString(4);
                String urlTxt = rs.getString(5);
                news.add(new News(id, urlImage, view, title, urlTxt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return news;
    }

    //get New by search without off set
    public Vector<News> getBySearch2(String txt, String radio) {
        int type = 2;
        if (radio.contains("Pro")) {
            type = 0;
        } else if (radio.contains("News")) {
            type = 1;
        }
        Vector<News> news = new Vector<>();
        String sql = "SELECT * FROM dbo.HE141081_ducdv_News WHERE Title LIKE ? and type = ?";
        if (type == 2) {
            sql = "SELECT * FROM dbo.HE141081_ducdv_News WHERE Title LIKE ? ";
        }
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if (type == 2) {
                ps.setString(1, "%" + txt + "%");
            } else {
                ps.setString(1, "%" + txt + "%");
                ps.setInt(2, type);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String urlImage = rs.getString(2);
                int view = rs.getInt(3);
                String title = rs.getString(4);
                String urlTxt = rs.getString(5);
                news.add(new News(id, urlImage, view, title, urlTxt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return news;
    }

//    public static void main(String[] args) {
//        DAONews d = new DAONews();
//        News n = d.getProductByID(1);
////        if(n!=null)
//////System.out.println("aaa");
////        System.out.println(n.getTitle());
//        Vector<News> v = d.getBySearch2("", "Pro");
//        for (News news : v) {
//            System.out.println(news.getProductName());
//        }
//    }
    public News getNewByID(int id) {
        String sql = "SELECT * FROM dbo.HE141081_ducdv_News WHERE NewsID = ?";
        News n = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id1 = rs.getInt(1);
                String urlImage = rs.getString(2);
                int view = rs.getInt(3);
                String title = rs.getString(4);
                String urlTextFile = rs.getString(5);
                int type = rs.getInt(6);
                n = new News(id1, urlImage, view, title, urlTextFile);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

//   
    public News getProductByID(int id) {
        News n = null;
        String sql = "SELECT dbo.HE141081_ducdv_News.*,Amount,"
                + "Price,[Product Name] FROM dbo.HE141081_ducdv_News "
                + "INNER JOIN dbo.HE141081_ducdv_Product"
                + " ON  HE141081_ducdv_News.NewsID = "
                + "HE141081_ducdv_Product.NewsID  WHERE  HE141081_ducdv_Product.ProductID = ?";
        int pID = getpIDbynID(id);
        System.out.println(pID);
        if (pID == -1) {
            return n;
        }
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int nID = rs.getInt(1);

                String urlImg = rs.getString(2);
                int view = rs.getInt(3);
                String title = rs.getString(4);
                String urlTxt = rs.getString(5);
                int type = rs.getInt(6);
                int uID = rs.getInt(7);
                int pID1 = rs.getInt(8);
                String keyWord = rs.getString(9);
                int amount = rs.getInt(10);
                float price = rs.getFloat(11);
                String pName = rs.getString(12);
                n = new News(nID, urlImg, view, title, urlTxt);
                n.setType(type);
                n.setPrice(price);
                n.setAmount(amount);
                n.setProductName(pName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public News getProductByID(String id2) {
        int id = Integer.valueOf(id2);
        News n = null;
        String sql = "SELECT dbo.HE141081_ducdv_News.*,Amount,"
                + "Price,[Product Name] FROM dbo.HE141081_ducdv_News "
                + "INNER JOIN dbo.HE141081_ducdv_Product"
                + " ON  HE141081_ducdv_News.NewsID = "
                + "HE141081_ducdv_Product.NewsID  WHERE  HE141081_ducdv_Product.ProductID = ?";
        int pID = id;
//        System.out.println(pID);
        if (pID == -1) {
            return n;
        }
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int nID = rs.getInt(1);

                String urlImg = rs.getString(2);
                int view = rs.getInt(3);
                String title = rs.getString(4);
                String urlTxt = rs.getString(5);
                int type = rs.getInt(6);
                int uID = rs.getInt(7);
                int pID1 = rs.getInt(8);
                String keyWord = rs.getString(9);
                int amount = rs.getInt(10);
                float price = rs.getFloat(11);
                String pName = rs.getString(12);
                n = new News(nID, urlImg, view, title, urlTxt);
                n.setType(type);
                n.setPrice(price);
                n.setAmount(amount);
                n.setProductName(pName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    //get product id by new id
    public int getpIDbynID(int id) {
        int pID = -1;
        String sql = " SELECT TOP(1) ProductID FROM "
                + "dbo.HE141081_ducdv_Product WHERE NewsID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pID;
    }

    public int getTypeByID(int id) {
        int type = -1;
        String sql = "SELECT type FROM dbo.HE141081_ducdv_News WHERE NewsID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                type = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return type;
    }

    //Count New
    public int countNews() {
        int n = 0;
        String sql = "SELECT  COUNT(NewsID) FROM  dbo.HE141081_ducdv_News ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    //End count

    public String readData(String url) {
        BufferedReader reader;
        String s = "";
        try {
            reader = new BufferedReader(new FileReader(url));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                // read next line
                s += line;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public String readUnicodeClassic(String fileName) {

        File file = new File(fileName);
        String s = "";
        try (FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(isr)) {

            String str;
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
                s += "<br>" + str;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public String readUnicodeClassicForTextArea(String fileName) {

        File file = new File(fileName);
        String s = "";
        try (FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(isr)) {

            String str;
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
                s += "\n" + str;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public Vector<News> getNewByType(int type) {
        Vector<News> news = new Vector<>();
        String sql = "SELECT * FROM dbo.HE141081_ducdv_News WHERE [type] = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, type);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String urlImage = rs.getString(2);
                int view = rs.getInt(3);
                String title = rs.getString(4);
                String urlTxt = rs.getString(5);
                news.add(new News(id, urlImage, view, title, urlTxt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return news;
    }

    //Add
    public void addNew(String title, String url, String urlTextFile, int type, int uid) {
        String sql = "INSERT dbo.HE141081_ducdv_News ( [URL Image] ,  [View] ,  Title ,    URL_txtFile ,  type )\n"
                + "VALUES  ( ?,?,?,?,?,?,null)";
        String sql2 = "INSERT dbo.HE141081_ducdv_News VALUES  ( ?,?,?,?,1,?,0,null)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql2);
            ps.setString(1, url);
            ps.setInt(2, 0);
            ps.setString(3, title);
            ps.setString(4, urlTextFile);
            ps.setInt(5, uid);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addNew(String title, String url, String urlTextFile, int type, int uid, int pid) {
        String sql = "INSERT dbo.HE141081_ducdv_News ( [URL Image] ,  [View] ,  Title ,    URL_txtFile ,  type )\n"
                + "VALUES  ( ?,?,?,?,?,?,3)";
        String sql2 = "INSERT dbo.HE141081_ducdv_News VALUES  ( ?,?,?,?,1,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql2);
            ps.setString(1, url);
            ps.setInt(2, 0);
            ps.setString(3, title);
            ps.setString(4, urlTextFile);
            ps.setInt(5, uid);
            ps.setInt(6, pid);
//            ps.setInt(7, 3);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        DAONews d = new DAONews();
        Vector<News> v = d.getTop3Product();
        d.addNewForProduct("abc", "khong", "khong", 3, 1, 1);
    }

    public void addNewForProduct(String title, String url, String urlTextFile,
            int type, int uid, int pid) {
        String sql = "INSERT dbo.HE141081_ducdv_News ( [URL Image] ,  [View] ,  Title ,    URL_txtFile ,  type )\n"
                + "VALUES  ( ?,?,?,?,?,?,3)";
        String sql2 = "INSERT dbo.HE141081_ducdv_News VALUES  ( ?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql2);
            ps.setString(1, url);
            //view
            ps.setInt(2, 0);
            ps.setString(3, title);
            ps.setString(4, urlTextFile);
            ps.setInt(5, type);
            ps.setInt(6, uid);
            ps.setString(7, "");
            ps.setInt(8, pid);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertProductByNews(int id, String pname, int amount, float price) {
        String sql = "INSERT dbo.HE141081_ducdv_Product ( NewsID , [Product Name] ,"
                + "  Amount , Price , Active )VALUES  (?,?,?,?,1)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, pname);
            ps.setInt(3, amount);
            ps.setFloat(4, price);
            ps.execute();
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        } catch (SQLException ex) {
            Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql2 = "UPDATE dbo.HE141081_ducdv_News SET type = 0 WHERE NewsID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql2);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //End Add
    //Update
    public void updateNew(int id, String title, String url, String urlTextFile) {
        if (url.equals("img\\test\\")) {

        }
        String sql = " UPDATE dbo.HE141081_ducdv_News SET [URL Image] = ?, Title =?,URL_txtFile = ? WHERE NewsID =?";
        if (url.equals("img\\test\\")) {
            sql = "UPDATE dbo.HE141081_ducdv_News SET "
                    + " Title =?,URL_txtFile = ? WHERE NewsID =?";
        }
        if (!url.equals("img\\test\\")) {
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, url);
                ps.setString(2, title);
                ps.setString(3, urlTextFile);
                ps.setInt(4, id);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, title);
                ps.setString(2, urlTextFile);
                ps.setInt(3, id);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void updateNew(int id, String title, String urlTextFile) {
        String sql = " UPDATE dbo.HE141081_ducdv_News SET Title =?,URL_txtFile=? WHERE NewsID =?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, urlTextFile);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateUrlContent(String urlTextFile, StringBuffer text1) {
        StringBuffer text = text1;
        List<StringBuffer> lines = new ArrayList<>();
        int loc = (new String(text)).indexOf('\n');
        while (loc > 0) {
            StringBuffer s1 = text.replace(loc, loc + 1, "\n");
            lines.add(s1);
            loc = (new String(text)).indexOf('\n');
        }

        File file = new File(urlTextFile);
        try (FileOutputStream fos = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                BufferedWriter writer = new BufferedWriter(osw)) {
            for (StringBuffer line : lines) {
                writer.append(line);
                writer.newLine();
            }
//            writer.append(text);
            writer.close();
            fos.close();
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(News n1) {
        String sql = " UPDATE dbo.HE141081_ducdv_Product "
                + "SET [Product Name] = ?,Amount=?,Price=? "
                + "WHERE ProductID =  ?";
        DAONews d = new DAONews();
        int id = d.getpIDbynID(n1.getId());
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, n1.getProductName());
            ps.setInt(2, n1.getAmount());
            ps.setFloat(3, n1.getPrice());
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAONews.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
