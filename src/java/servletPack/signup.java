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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dinhd
 */
public class signup extends HttpServlet {

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
//            String rName = request.getParameter("rName");
//            String rAddress = request.getParameter("rAddress");
//            String rPhone = request.getParameter("rPhone");
//            String rGender = request.getParameter("rGender");
//            int rYear = Integer.parseInt(request.getParameter("rYear"));

            DAO.DAOAccount daoa = new DAOAccount();
            List<Account> listaAccounts = daoa.readAllAccounts();
            for (Account listaAccount : listaAccounts) {
                System.out.println("accounttu2" + listaAccount);
            }
            String rUser = request.getParameter("rUser");
            String rPass = request.getParameter("rPass");
            String rRePass = request.getParameter("rRePass");

//            int id = con.getIdAccount(rUser, rPass);
            if (rUser.equals("") || rPass.equals("")) {
                request.setAttribute("mess2", "Please fill all required field");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            if (!rPass.equals(rRePass)) {
                request.setAttribute("mess2", "Sign Up Failed! The password does not match");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

            int id = listaAccounts.get(listaAccounts.size() - 1).getId();
            Account acc = new Account(id + 1, rUser, rPass, "1", 0);
            daoa.writeAccounts(acc);
            request.setAttribute("mess2", "signup OK");
            request.getRequestDispatcher("login.jsp").forward(request, response);

            out.println("</body>");
            out.println("</html>");
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
