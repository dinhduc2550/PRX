/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

import DAO.DAONews;
import DAO.DAOProduct;
import entity.Account;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author dinhd
 */
@WebServlet(name = "loadNews", urlPatterns = {"/loadNews"})
public class loadNews extends HttpServlet {

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
        int num = Integer.parseInt(request.getParameter("exists"));
        String radio = request.getParameter("typeF1");
        String search = request.getParameter("searchT");
        System.out.println("radioLLLLLL==========" + radio);
        DAONews d = new DAONews();
        int type = 2;
        if (radio.equals("ProductsF")) {
            type = 0;
        } else if (radio.equals("NewsF")) {
            type = 1;
        }
        if (search.equals("")) {

        }
        HttpSession ses = request.getSession();
        Account a = (Account) ses.getAttribute("acc");
//        if (ses.getAttribute("acc") == null) {
//            response.sendRedirect("login");
//            return;
//        }
        System.out.println("typeLLLLLL===============" + type);
        Vector<News> v = d.getNextHomePage(type, num, search);
//            Vector<News> v = d.getNext(1, 0,"s");
        System.out.println("searchcccc===============" + search);

        PrintWriter out = response.getWriter();
        if (type == 1) {
            for (News o : v) {
                out.println(" <div class=\"numNews text\" class=\"col\">"
                        + "<a href=\"detailControl?newID=" + o.getId() + "\">"
                        + "<img src=\"" + o.getUrlImage() + "\" class=\"img-fluid newsImg\" alt=\"...\"></a>\n"
                        + "                        <div class=\"text-text\">\n"
                        + "                            <a class=\" text\"\n"
                        + "                               href=\"detailControl?newID=" + o.getId() + "\">\n"
                        + "                                " + o.getTitle() + "\n"
                        + "                            </a> \n"
                        + "                        </div>\n"
                        + "                    </div>");
            }
        } else if (type == 0) {
            Vector<Product> vp = new Vector<>();
            vp = new DAOProduct().getProductByOffSet(num);
            for (Product o : vp) {
                out.println(" <div class=\"numNews text\" class=\"col\">"
                        + "<a href=\"detailControl?newID=" + o.getpID() + "\">"
                        + "<img src=\"" + o.getUrlImage() + "\" class=\"img-fluid newsImg\" alt=\"...\"></a>\n"
                        + "                        <div class=\"text-text\">\n"
                        + "                            <a class=\" text\"\n"
                        + "                               href=\"detailControl?newID=" + o.getpID() + "\">\n"
                        + "                                " + o.getpName() + "\n"
                        + "                            </a> \n"
                        + "                        </div>\n"
                        + "                    </div>");
            }
        } else {
            for (News o : v) {
                out.println(" <div class=\"numNews text\" class=\"col\">"
                        + "<a href=\"detailControl?newID=" + o.getId() + "\">"
                        + "<img src=\"" + o.getUrlImage() + "\" class=\"img-fluid newsImg\" alt=\"...\"></a>\n"
                        + "                        <div class=\"text-text\">\n"
                        + "                            <a class=\" text\"\n"
                        + "                               href=\"detailControl?newID=" + o.getId() + "\">\n"
                        + "                                " + o.getTitle() + "\n"
                        + "                            </a> \n"
                        + "                        </div>\n"
                        + "                    </div>");
            }
             Vector<Product> vp = new Vector<>();
            vp = new DAOProduct().getProductByOffSet(num);
            for (Product o : vp) {
                out.println(" <div class=\"numNews text\" class=\"col\">"
                        + "<a href=\"detailControl?newID=" + o.getpID() + "\">"
                        + "<img src=\"" + o.getUrlImage() + "\" class=\"img-fluid newsImg\" alt=\"...\"></a>\n"
                        + "                        <div class=\"text-text\">\n"
                        + "                            <a class=\" text\"\n"
                        + "                               href=\"detailControl?newID=" + o.getpID() + "\">\n"
                        + "                                " + o.getpName() + "\n"
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
