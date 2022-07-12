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
@WebServlet(name = "loadPurchaseByFilter", urlPatterns = {"/loadPurchaseByFilter"})
public class loadPurchaseByFilter extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String op1 = request.getParameter("filter1");
            if (op1.equalsIgnoreCase("asc")) {
                op1 = "Total ASC";
            } else if (op1.equalsIgnoreCase("desc")) {
                op1 = "Total DESC";
            }
            String op2 = request.getParameter("filter2");
            if (op2.equalsIgnoreCase("newest")) {
                op2 = "purchaseDate ASC";
            } else if (op2.equalsIgnoreCase("lastest")) {
                op2 = "purchaseDate DESC";
            }
//            int num = Integer.parseInt(request.getParameter("numberR"));
            int num = 0;
            DAOHistory d = new DAOHistory();
            Vector<History> v = null;
            if (op1.equalsIgnoreCase("none") && op2.equalsIgnoreCase("none")) {
                v = d.getHistoryPurchase(num);
            } else if (op1.equalsIgnoreCase("none") && !op2.equalsIgnoreCase("none")) {
                v = d.getHistoryPurchase(num, "", op2 + " , ");
            } else if (!op1.equalsIgnoreCase("none") && op2.equalsIgnoreCase("none")) {
                v = d.getHistoryPurchase(num, op1 + " , ", "");
            } else if (!op1.equalsIgnoreCase("none") && !op2.equalsIgnoreCase("none")) {
                v = d.getHistoryPurchase(num, op1 + ", ", op2 + ", ");
            }
            for (History h : v) {
                out.println("  <tr class=\"numberRow\" class=\"tr-shadow\">\n"
                        + "                                                <td>" + h.getaID() + "</td>\n"
                        + "<td><span id=\"uname\" >"+h.getUsername()+"</span></td>"
                        + "                                                <td class=\"desc\">" + h.getpID() + "</td>\n"
                        + "                                                <td>" + h.getAmount() + "</td>\n"
                        + "                                                <td>" + h.getPrice() + "</td>\n"
                        + "                                                <td>" + h.getTotal() + "</td>\n"
                        + "                                                <td>" + h.getDate() + "</td>\n"
                        + "                                                <td>\n"
                        + "                                                    <button  value=\""+h.getUsername()+"\"  onclick=\"showAllInfo(this)\" class=\"item\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"More\">\n"
                        + "                                                        <i class=\"fas fa-eye\"></i>\n"
                        + "                                                    </button>\n"
                        + "                                                </td>"
                        + "  </tr>");
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
