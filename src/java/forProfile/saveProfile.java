/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forProfile;

import DAO.DAOAccount;
import entity.Account;
import entity.UserInformation;
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
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import servletPack.saveInfoToDB;

/**
 *
 * @author dinhd
 */
@WebServlet(name = "saveProfile", urlPatterns = {"/saveProfile"})
public class saveProfile extends HttpServlet {

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
            throws ServletException, IOException, JAXBException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            System.out.println("aaaaaaaaaa");
            HttpSession ses = request.getSession();
            Account a = (Account) ses.getAttribute("acc");
//            try {
//                int uID = a.getId();
//                System.out.println("uID");
//            } catch (Exception e) {
//            }
            System.out.println("aaaaaaaaa");
            System.out.println("\n save================================");
            String fname = request.getParameter("fNameF1");
            System.out.println(fname);
            String address = request.getParameter("addressF1");
            System.out.println(address);
            String phone = request.getParameter("phoneF1");
            System.out.println(phone);
            String gender = request.getParameter("gender1");
//            String status = request.getParameter("status");
            String dob = request.getParameter("dob1");
            System.out.println(dob);
            System.out.println("\n save================================");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = null;
            try {
                parsed = format.parse(dob);
            } catch (ParseException ex) {
                Logger.getLogger(saveInfoToDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            java.sql.Date sql = new java.sql.Date(parsed.getTime());
            UserInformation a1 = new UserInformation();
            a1.setpName(fname);
            a1.setAddress(address);
            a1.setPhone(phone);
            a1.setGender(gender);
            a1.setIsActive(1);
            a1.setYear(sql.toString());
            int uID = Integer.parseInt(ses.getAttribute("uIDD") + "");
//            System.out.println("Uiddddddddddddđ" + uID);
            a1.setId(uID);
            DAOAccount d = new DAOAccount();
            UserInformation b = d.loadUserInformation(a.getUserName());
            int i = -1;
            if (b == null) {
                System.out.println("b isssssssssssssssssssssss nullllllllllllllll");
                request.setAttribute("success", "<div class=\"alert alert-success alert-dismissible\">\n"
                        + "    <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
                        + "    <strong>Add Success!</strong>\n"
                        + "  </div>");
            } else {
                i = d.updateInfo(a1);
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
            }

            request.getRequestDispatcher("loadDataToProfile").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (JAXBException ex) {
            Logger.getLogger(saveProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (JAXBException ex) {
            Logger.getLogger(saveProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
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
