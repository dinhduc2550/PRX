/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

import DAO.DAOAccount;
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
@WebServlet(name = "filterAccount", urlPatterns = {"/filterAccount"})
public class filterAccount extends HttpServlet {

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
            String option = request.getParameter("optionA") + "";
            String txt = request.getParameter("txtA") + "";
            System.out.println("option============="+option);
            DAOAccount d = new DAOAccount();
            String s = "",s2="";
            Vector<Account> v = d.loadAccByFilter(option, txt);
            for (Account o : v) {
                if (o.getRole().equalsIgnoreCase("admin")) {
                    s = " <span class=\"role admin\">Admin</span>";
                }else if(o.getRole().equalsIgnoreCase("cus")){
                    s = "<span class=\"role user\">User</span>";
                }else if(o.getRole().equalsIgnoreCase("sale")){
                    s = "<span class=\"role member\">Saler</span>";
                }
                if(o.getActive()==1){
                    s2="<p class=\"text-success\">Activated</p>\n";
                }else{
                    s2 ="<p class=\"text-danger\">Banned</p>";
                }
                out.println("<tr>\n"
                        + "                                                    <td>\n"
                        + "                                                       " + o.getId() + "\n"
                        + "                                                    </td>\n"
                        + "                                                    <td>\n"
                        + "                                                        <div class=\"table-data__info\">\n"
                        + "                                                            <h6>" + o.getpName() + "</h6>\n"
                        + "                                                            <span>\n"
                        + "                                                                <a href=\"#\">" + o.getName() + "</a>\n"
                        + "                                                            </span>\n"
                        + "                                                        </div>\n"
                        + "                                                    </td>\n"
                        + "                                                    <td>\n"
                        + "                                                        "
                        + "                                                        "+s+""
                        + "                                                    </td>\n"
                        + "                                                    <td>\n"
                        + "                                                        "+s2+""
                        + "                                                    </td>\n"
                        + "                                                    <td>\n"
                        + "                                                        <!--<span class=\"more\">-->\n"
                        + "                                                        <button  type=\"button\" value=\""+o.getName()+"\" onclick=\"showMore2(this)\">\n"
                        + "                                                            <i class=\"fas fa-eye\"></i>\n"
                        + "                                                        </button>\n"
                        + "                                                        <!--</button>-->\n"
                        + "                                                        </span>\n"
                        + "                                                    </td>\n"
                        + "                                                </tr>");
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
