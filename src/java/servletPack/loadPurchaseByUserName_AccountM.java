/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

import DAO.DAOHistory;
import entity.History;
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
@WebServlet(name = "loadPurchaseByUserName_AccountM", urlPatterns = {"/loadPurchaseByUserName1"})
public class loadPurchaseByUserName_AccountM extends HttpServlet {

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
            String uname = request.getParameter("userName")+"";
            DAOHistory dh = new DAOHistory();
            Vector<History> v = dh.getHistoryPurchase(0,uname);
            request.setAttribute("vectorH", v);
            for (History h : v) {
            out.println("  <tr class=\"numberRow\" class=\"tr-shadow\">\n"
                    + "                                                <td class=\"desc\">" + h.getpID() + "</td>\n"
                    + "                                                <td>" + h.getAmount() + "</td>\n"
                    + "                                                <td>" + h.getPrice() + "</td>\n"
                    + "                                                <td>" + h.getTotal() + "</td>\n"
                    + "                                                <td>" + h.getDate() + "</td>\n"
                    + "                                                <td>\n"
                    + "                                                    <button  value=\""+h.getUsername()+"\"   class=\"item\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"More\">\n"
                    + "                                                        <i class=\"fas fa-eye\"></i>\n"
                    + "                                                    </button>\n"
                    + "                                                </td>"
                    + "   </tr>");
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
