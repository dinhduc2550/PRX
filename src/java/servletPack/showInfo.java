/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

import DAO.ConnectDB;
import entity.Account;
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
@WebServlet(name = "showInfo", urlPatterns = {"/showInfo"})
public class showInfo extends HttpServlet {

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
            String uname = request.getParameter("userName");
            System.out.println("uname=========="+uname);
            ConnectDB da = new ConnectDB();
            Account acc = da.getAllAttibute(uname);
//            Account a;
//            a = da.getAllAttibute("dinhduc2550");
//            System.out.println("name=====" + a.getName());
//            System.out.println("passs=====" + a.getPass());
            out.println("<tr>\n"
                    + "                                                    <td>Account ID</td>\n"
                    + "                                                    <td class=\"text-right\">" + acc.getId() + "</td>\n"
                    + "                                                </tr>\n"
                    + "                                                <tr>\n"
                    + "                                                    <td>Name</td>\n"
                    + "                                                    <td class=\"text-right\">" + acc.getName() + "</td>\n"
                    + "                                                </tr>\n"
                    + "                                                <tr>\n"
                    + "                                                    <td>Address</td>\n"
                    + "                                                    <td class=\"text-right\">" + acc.getAddress() + "</td>\n"
                    + "                                                </tr>\n"
                    + "                                                <tr>\n"
                    + "                                                    <td>Phone number</td>\n"
                    + "                                                    <td class=\"text-right\">" + acc.getPhone() + "</td>\n"
                    + "                                                </tr>\n"
                    + "                                                <tr>\n"
                    + "                                                    <td>Gender</td>\n"
                    + "                                                    <td class=\"text-right\">" + acc.getGender() + "</td>\n"
                    + "                                                </tr>\n"
                    + "                                                <tr>\n"
                    + "                                                    <td>Birth Year</td>\n"
                    + "                                                    <td class=\"text-right\">" + acc.getYear() + "</td>\n"
                    + "                                                </tr>");

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
