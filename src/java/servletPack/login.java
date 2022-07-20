/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

import DAO.ConnectDB;
import DAO.DAOAccount;
import entity.Account;
import entity.ListAccount;
import entity.UserInformation;
import helper.JAXBHelper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import path.PathFile;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    ConnectDB con;

    @Override
    public void init() throws ServletException {
        con = new ConnectDB();

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

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
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("userNameA1")) {
                    request.setAttribute("u", c.getValue());
                }
                if (c.getName().equals("passWordA1")) {
                    request.setAttribute("p", c.getValue());
                }
            }
        }

        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        String name = request.getParameter("uname");
        String psw = request.getParameter("psw");
        String rm = request.getParameter("remember");
        System.out.println("PASS : " + psw);
        System.out.println("NAME : " + name);
        if (name.equals("") || psw.equals("")) {
            request.setAttribute("mess", "Please!Fill in the blanks completely");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        DAOAccount d = new DAOAccount();
        UserInformation a1 = null;
        Account a;
        try {
            a1 = d.loadUserInformation(name);
            a = login(name, psw);
        } catch (JAXBException ex) {
            a = null;
            a1 = null;
        }
        if (a == null) {
            request.setAttribute("mess", "Wrong username or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            HttpSession ses = request.getSession();

            ses.setAttribute("acc", a);
            ses.setAttribute("acc1", a1);
            ses.setAttribute("uIDD", a.getId());
            Cookie u2 = new Cookie("userNameA1", name);
            Cookie p2 = new Cookie("passWordA1", psw);
            if (rm != null) {
                p2.setMaxAge(60);
            } else {
                p2.setMaxAge(0);
            }
            u2.setMaxAge(60);
            ses.setMaxInactiveInterval(60 * 60 * 24);
            response.addCookie(u2);
            response.addCookie(p2);
            response.sendRedirect("home");

        }
    }

    public Account login(String name, String pass) throws JAXBException {
        JAXBHelper helper = new JAXBHelper(ListAccount.class);
        ListAccount listAccount = (ListAccount) helper.readXml(PathFile.ACCOUNT_XML_FILE_PATH);
        for (Account accountXml : listAccount.getAccounts()) {
            if (accountXml.getUserName().equals(name) && accountXml.getPass().equals(pass)) {
                return accountXml;
            }
        }
        return null;
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
