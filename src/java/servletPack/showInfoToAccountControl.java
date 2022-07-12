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
@WebServlet(name = "showInfoToAccountControl", urlPatterns = {"/showInfo2"})
public class showInfoToAccountControl extends HttpServlet {

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
            ConnectDB da = new ConnectDB();
            Account acc = da.getAllAttibute(uname);
            String gender = acc.getGender();
            String s;
            System.out.println("statussssssssssssssssssss: ==========="+acc.getActive());
            System.out.println("statussssssssssssssssssss: ==========="+gender);
            out.println("<label for=\"fname\">Account ID</label>\n" +
"                                        <input value=\""+acc.getId()+"\" readonly type=\"text\" id=\"aid\" name=\"idF\" placeholder=\"\">\n" +
"\n" +                                  "<label for=\"uname\">Username</label>\n" +
"                                        <input readonly value=\""+acc.getName()+"\" type=\"text\" id=\"unameF\" name=\"unameF\" placeholder=\"Your username..\">"+
"                                        <label for=\"pname\">Full Name</label>\n" +
"                                        <input value=\""+acc.getpName()+"\" type=\"text\" id=\"lname\" name=\"fNameF\" placeholder=\"Your full name..\">\n" +
"\n" +                                  "<label for=\"address\">Address</label>\n" +
"                                        <input value=\""+acc.getAddress()+"\" type=\"text\" id=\"lname\" name=\"addressF\" placeholder=\"Your address..\">"+
"                                        <label for=\"phone\">Phone number</label>\n" +
"                                        <input value=\""+acc.getPhone()+"\"  type=\"text\" id=\"lname\" name=\"phoneF\" placeholder=\"Your phone number name..\">\n" +
"\n" +
"                                        <label for=\"gender\">Gender</label>\n" +
"                                        <select id=\"gender\" name=\"gender\">\n" +
"                                            <option "+(gender.equalsIgnoreCase("male")?"selected=\"selected\"":"")+" value=\"Male\">Male</option>\n" +
"                                            <option "+(gender.equalsIgnoreCase("female")?"selected=\"selected\"":"")+" value=\"Female\">Female</option>\n" +
"                                            <option "+(gender.equalsIgnoreCase("none")?"selected=\"selected\"":"")+" value=\"None\">None</option>\n" +
"                                        </select>\n" +
"\n" +                                  " <label for=\"Status\">Status</label>"
        + "                              <select id=\"status\" name=\"status\">\n" +
"                                            <option "+((acc.getActive()==1)?"selected=\"selected\"":"")+" class=\"text-success\" value=\"Active\">Active</option>\n" +
"                                            <option "+((acc.getActive()==0)?"selected=\"selected\"":"")+" class=\"text-danger\" value=\"Ban\">Ban</option>\n" +
"                                        </select>"+
"                                        <label for=\"DoB\">Date of Birth</label>\n" +
"                                        <br>\n" +
"                                        <input value="+acc.getYear()+" type=\"date\" name=\"dob\" id=\"\">\n" +
"                                        <input  type=\"submit\" value=\"Save\">");
            
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
