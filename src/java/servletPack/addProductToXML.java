/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

import DAO.DAOProduct;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dinhd
 */
@WebServlet(name = "addProductToXML", urlPatterns = {"/addProductToXML"})
public class addProductToXML extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        try {
            String name = request.getParameter("pName");
            int amount = Integer.parseInt(request.getParameter("amount"));
            float price = Float.parseFloat(request.getParameter("price"));
            String urlImg = request.getParameter("urlImg");
            String des = request.getParameter("txtA");
            Product p = new Product();
            p.setpName(name);
            p.setAmount(amount);
            p.setPrice(price);
            p.setUrlImage(urlImg);
            p.setDes(des);
            System.out.println("addProductToXML=======================");
            DAOProduct daoProduct = new DAOProduct();
            int id = daoProduct.getLastIDProductFromXML();
            p.setpID(++id);
            p.setActive(1);
            p.setView(0);
            p.setuID(1);
            daoProduct.addProductToXML(p);
        } catch (Exception e) {
            System.out.println("Input invalid data");
        }
        response.sendRedirect("newsControl");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
