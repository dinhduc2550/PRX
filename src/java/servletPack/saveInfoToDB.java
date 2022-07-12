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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dinhd
 */
@WebServlet(name = "saveInfoToDB", urlPatterns = {"/saveInfo1"})
public class saveInfoToDB extends HttpServlet {

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
            int id = Integer.parseInt(request.getParameter("idF"));
//            String uname = request.getParameter("uname");
            String fname = request.getParameter("fNameF");
            String address = request.getParameter("addressF");
            String phone = request.getParameter("phoneF");
            String gender = request.getParameter("gender");
            String status = request.getParameter("status");
            String dob = request.getParameter("dob");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = null;
            try {
                parsed = format.parse(dob);
            } catch (ParseException ex) {
                Logger.getLogger(saveInfoToDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            java.sql.Date sql = new java.sql.Date(parsed.getTime());

//            java.sql.Date sql = new java.sql.Date(parsed.getTime());
//            out.println("<h1>"+id+"</h1>");
//            out.println("<h1>"+fname+"</h1>");
//            out.println("<h1>" + address + "</h1>");
//            out.println("<h1>" + gender + "</h1>");
//            out.println("<h1>" + status + "</h1>");
//            out.println("<h1>" + dob + "</h1>");
//            out.println("<h1>" + parsed + "</h1>");
//            out.println("<h1>" + sql + "</h1>");
//            out.println("<h1>" + phone + "</h1>");
            int active;
            if (status.equalsIgnoreCase("ban")) {
                active = 0;
            } else {
                active = 1;
            }
            Account a = new Account();
            a.setpName(fname);
            a.setAddress(address);
            a.setPhone(phone);
            a.setGender(gender);
            a.setActive(active);
            a.setYear(sql);
            a.setId(id);
            DAOAccount d = new DAOAccount();
            int i = d.updateInfo(a);
            if (i != -1) {
                request.setAttribute("success", "<div class=\"alert alert-success alert-dismissible\">\n"
                        + "    <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
                        + "    <strong>Update Success!</strong>\n"
                        + "  </div>");
            } else {
                request.setAttribute("success", "<div class=\"alert alert-danger alert-dismissible\">\n"
                        + "    <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
                        + "    <strong>Update Failed!</strong>\n"
                        + "  </div>");
            }
            request.getRequestDispatcher("accountControl").forward(request, response);
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
