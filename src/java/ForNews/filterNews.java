/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ForNews;

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
@WebServlet(name = "filterNews", urlPatterns = {"/filterNews"})
public class filterNews extends HttpServlet {

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
            String option = request.getParameter("optionA");
            String txtSearch = request.getParameter("txtA");
            
            Vector<News> vn = new DAONews().getBySearch2(txtSearch, option);
//            for (News news : vn) {
//                
//            }
            if (option.contains("News")) {
                for (News o : vn) {
                    out.println("<tr>\n"
                            + "                                                    <td>\n"
                            + "                                                        " + o.getId() + "\n"
                            + "                                                    </td>\n"
                            + "                                                    <td>\n"
                            + "                                                        <div class=\"table-data__info\">\n"
                            + "                                                            <p>" + o.getTitle() + "</p>\n"
                            + "                                                        </div>\n"
                            + "                                                    </td>\n"
                            + "                                                    <td>\n"
                            + "                                                        <img width=\"50%\" src=\"" + o.getUrlImage() + "\">\n"
                            + "                                                    </td>\n"
                            + "                                                    <td>\n"
                            + "                                                        " + o.getView() + "\n"
                            + "                                                    </td>\n"
                            + "                                                    <td>\n"
                            + "                                                        <button  type=\"button\" value=\"" + o.getId() + "\" onclick=\"showMore4(this)\">\n"
                            + "                                                            <i class=\"fas fa-eye\"></i>\n"
                            + "                                                        </button>"
                            + "                                                        </button>\n"
                            + "  <button value=\"${a.id}\" onclick=\"makeProduct(this)\" type=\"button\">\n"
                            + "                                                            <i class=\"fab fa-product-hunt\"></i></button>\n"
                            + ""
                            + "                                                        \n"
                            + "                                                    </td>\n"
                            + "                                                </tr>");
                }
            } else {
                Vector<Product> v = new DAOProduct().getAllProduct();
                for (Product o : v) {
                    out.println("<tr>\n"
                            + "                                                    <td>\n"
                            + "                                                        " + o.getpID()+ "\n"
                            + "                                                    </td>\n"
                            + "                                                    <td>\n"
                            + "                                                        <div class=\"table-data__info\">\n"
                            + "                                                            <p>" + o.getpName()+ "</p>\n"
                            + "                                                        </div>\n"
                            + "                                                    </td>\n"
                            + "                                                    <td>\n"
                            + "                                                        <img width=\"50%\" src=\"" + o.getUrlImage() + "\">\n"
                            + "                                                    </td>\n"
//                            + "                                                    <td>\n"
//                            + "                                                        " + o.getView() + "\n"
//                            + "                                                    </td>\n"
                            + "                                                    <td>\n"
                            + "                                                        <button  type=\"button\" value=\"" + o.getpID()+ "\" onclick=\"showMoreInfoProduct(this)\">\n"
                            + "                                                            <i class=\"fas fa-eye\"></i>\n"
                            + "                                                        </button>"
                            + "                                                        </button>\n"
                            + "                                                 <button value=\"" + o.getpID() + "\" onclick=\"makeNews(this)\" type=\"button\">\n"
                            + "                                                            <i class=\"far fa-newspaper\"></i></button>\n"
                            + ""
                            + "                                                        \n"
                            + "                                                    </td>\n"
                            + "                                                </tr>");
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
