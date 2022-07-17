/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ForNews;

import DAO.DAONews;
import DAO.DAOProduct;
import entity.News;
import entity.Product;
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
@WebServlet(name = "showInfoNews", urlPatterns = {"/showInfoNews"})
public class showInfoNews extends HttpServlet {

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
            int id = Integer.parseInt(request.getParameter("nID"));
            DAONews d = new DAONews();
            News n = d.getNewsByIDFromXML(id);
//            int type = d.getTypeByID(id);
//            System.out.println("url111111111111====="+n.getUrlImage());
            String s = d.readUnicodeClassicForTextArea(n.getUrlTxt());
            request.setAttribute("portID", n.getId());
//            if (type == 1) {
            out.println("<form method=\"post\" id=\"formNews\" action=\"updateNewToXML\">\n"
                    + "                                        <label for=\"newID\">News ID</label>\n"
                    + "                                        <input value=\"" + n.getId() + "\" readonly type=\"text\" id=\"newID\" name=\"newID\" placeholder=\"New id ..\">\n"
                    + "\n"
                    + "                                        <label for=\"title\">News Title</label>\n"
                    + "                                        \n"
                    + "                                        <textarea name=\"titleA\" id=\"titleA\">" + n.getTitle() + "</textarea>\n"
                    + "                                        <label for=\"img\">Image</label>\n"
                    + "\n                                      <input type=\"text\" id=\"urlImg\" name=\"urlImg\" placeholder=\"URL Image\">\n"
                    + "                                        "
                    + "                                        <br>\n"
                    + "                                        <image name=\"imgF\" src=\"" + n.getUrlImage() + "\" width=\"200\" height=\"250\" id=\"image-field\">\n"
                    + "                                        <br>\n"
                    + "                                        <label for=\"txtA\">Content</label>\n"
                    + "                                        <textarea style=\"height: 450px;\" name=\"txtA\" id=\"txtA\">" + s + "</textarea>\n"
                    + "                                        <input type=\"submit\" value=\"Save\">\n"
                    + "                                    </form>");
            

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
