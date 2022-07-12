/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forProfile;

import DAO.DAOAccount;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dinhd
 */
@WebServlet(name = "savePass", urlPatterns = {"/savePass"})
public class savePass extends HttpServlet {

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
            int uID = Integer.parseInt(request.getParameter("uid1"));
            String cPass = request.getParameter("pass2-0");
            String nPass = request.getParameter("pass2");
            String nPass2 = request.getParameter("pass2-1");
            if (!nPass.equals(nPass2)) {
                request.setAttribute("mess1", "New Password is not Match!!!");
                request.getRequestDispatcher("loadDataToProfile").forward(request, response);
            } else {
                DAOAccount d = new DAOAccount();
                int i = d.changePass(cPass, nPass, uID);
                if (i == -1) {
                    request.setAttribute("mess1", "Wrong Old Password!!!");
                    request.getRequestDispatcher("loadDataToProfile").forward(request, response);
                } else {
                    request.setAttribute("mess2", "Change Password Success!");
                    request.getRequestDispatcher("loadDataToProfile").forward(request, response);
                }
            }
        } catch (Exception e) {
        }
//        request.getRequestDispatcher("loadDataToProfile").forward(request, response);
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
