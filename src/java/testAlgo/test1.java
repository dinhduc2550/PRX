/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testAlgo;

import DAO.DAONews;
import DAO.DAOProduct;
import entity.News;
import entity.Product;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author dinhd
 */
public class test1 {
    
    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
//        Vector<Product> plist = dao.getListProductFromXML(0);
//        for (Product product : plist) {
////            System.out.println(product.getpName());
//        }
        DAONews daoNews = new DAONews();
//        Vector<News> nlist = daoNews.getTop3NewsFromXML();
//        for (News n : nlist) {
////            System.out.println(product.getpName());
//            System.out.println("View: " + n.getView());
//        }
        System.out.println("id==="+dao.getLastIDProductFromXML());
    }
}
