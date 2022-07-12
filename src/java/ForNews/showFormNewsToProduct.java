/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ForNews;

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
@WebServlet(name = "showFormNewsToProduct", urlPatterns = {"/showFormNewsToProduct"})
public class showFormNewsToProduct extends HttpServlet {

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
//         request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String id = request.getParameter("nID");
           out.println("<form name=\"formNews\" method=\"post\" id=\"formNews\" action=\"makeNewsToProduct?id="+id+"\">\n"
                        + "                                        <br>\n"
                        + "                                        <label for=\"pName\">Product Name</label>\n"
                        + "                                        <input value=\"\" type=\"text\" id=\"pName1\" name=\"pName1\" placeholder=\"Product Name ..\">\n"
                        + "                                        <br>\n"
                        + "                                        <label for=\"amount\">Amount</label>  <br>\n"
                        + "                                        <input value=\"\" type=\"number\" id=\"amount1\" name=\"amount1\" min=\"0\">\n"
                        + "                                        <br>\n"
                        + "                                        <label for=\"price\">Price</label>\n"
                        + "                                        <input value=\"\" onchange=\"returns checkInp()\" type=\"text\" id=\"price1\" name=\"price1\" >\n"
                        + "\n"
                        + "                                        <input type=\"submit\" value=\"Add\">\n"
                        + "                                    </form>");
//            }
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
