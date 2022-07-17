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
import java.io.File;
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
@WebServlet(name = "loadByFilter", urlPatterns = {"/loadByFilter"})
public class loadByFilter extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        DAONews d = new DAONews();
        int num = 0;
        String radio = request.getParameter("filterType");
//            out.println("<h1>" + radio + "</h1>");
        int type = 2;
        if (radio.equals("ProductsF")) {
            type = 0;
        } else if (radio.equals("NewsF")) {
            type = 1;
        }
        System.out.println("type-FFFF===============" + type);
        Vector<News> v = null;
        Vector<Product> v2 = null;
        if (type==1) {
            v = d.getListNewsFromXML(0);
        }  else if(type == 0) {
               v2 = new DAOProduct().getListProductFromXML(0);
        }else{
            v = d.getListNewsFromXML(0);
            v2 = new DAOProduct().getListProductFromXML(0);
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
