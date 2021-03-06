/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

import DAO.DAONews;
import DAO.DAOProduct;
import entity.News;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dinhd
 */
@WebServlet(name = "search", urlPatterns = {"/search"})
public class search extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            request.setCharacterEncoding("UTF-8");
            String txtSearch = request.getParameter("txt");
            String radio = request.getParameter("typeF1");
            System.out.println("search type F1========="+radio);
            DAONews d = new DAONews();
            int type = 2;
            if (radio.equals("ProductsF")) {
                type = 0;
            } else if (radio.equals("NewsF")) {
                type = 1;
            }
            Vector<News> v = null;
            Vector<Product> v2 = null;
            if (type == 1) {
                v = d.getNextNewsFromXML(1,0,txtSearch);
            } else if (type == 0) {
                v2 = new DAOProduct().getListProductByNameFromXML(-1,txtSearch);
            } else {
                v = d.getNextNewsFromXML(1,0,txtSearch);
                v2 = new DAOProduct().getListProductByNameFromXML(-1,txtSearch);
            }
            if ( type == 1) {
            for (News o : v) {
                out.println(" <div class=\"numNews text\" class=\"col\"><img src=\"" + o.getUrlImage() + "\" class=\"img-fluid newsImg\" alt=\"...\">\n"
                        + "                        <div class=\"text-text\">\n"
                        + "                            <a class=\" text\"\n"
                        + "                               href=\"detailControl?newID=" + o.getId() + "\">\n"
                        + "                                " + o.getTitle() + "\n"
                        + "                            </a> \n"
                        + "                        </div>\n"
                        + "                    </div>");
            }
        } else if(type == 0) {
            for (Product o : v2) {
                out.println(" <div class=\"numNews text\" class=\"col\"><img src=\"" + o.getUrlImage() + "\" class=\"img-fluid newsImg\" alt=\"...\">\n"
                        + "                        <div class=\"text-text\">\n"
                        + "                            <a class=\" text\"\n"
                        + "                               href=\"detailControlForProduct?pID=" + o.getpID()+ "\">\n"
                        + "                                " + o.getpName()+ "\n"
                        + "                            </a> \n"
                        + "                        </div>\n"
                        + "                    </div>");
            }
        }
        else{
            for (News o : v) {
                out.println(" <div class=\"numNews text\" class=\"col\"><img src=\"" + o.getUrlImage() + "\" class=\"img-fluid newsImg\" alt=\"...\">\n"
                        + "                        <div class=\"text-text\">\n"
                        + "                            <a class=\" text\"\n"
                        + "                               href=\"detailControl?newID=" + o.getId() + "\">\n"
                        + "                                " + o.getTitle() + "\n"
                        + "                            </a> \n"
                        + "                        </div>\n"
                        + "                    </div>");
            }
            
            for (Product o : v2) {
                out.println(" <div class=\"numNews text\" class=\"col\"><img src=\"" + o.getUrlImage() + "\" class=\"img-fluid newsImg\" alt=\"...\">\n"
                        + "                        <div class=\"text-text\">\n"
                        + "                            <a class=\" text\"\n"
                        + "                               href=\"detailControlForProduct?pID=" + o.getpID()+ "\">\n"
                        + "                                " + o.getpName()+ "\n"
                        + "                            </a> \n"
                        + "                        </div>\n"
                        + "                    </div>");
            }
        }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
