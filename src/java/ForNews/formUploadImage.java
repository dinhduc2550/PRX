/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ForNews;

import DAO.DAONews;
import entity.News;
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
@WebServlet(name = "formUploadImage", urlPatterns = {"/formUploadImage"})
public class formUploadImage extends HttpServlet {

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
            int id = Integer.parseInt(request.getParameter("nID1_1"));
            DAONews d = new DAONews();
            News n = d.getNewByID(id);
            out.println(" <form id=\"uploadForm\"  method=\"post\" action=\"uploadImg\" enctype=\"multipart/form-data\">\n"
                    + "                                <label for=\"ssID\">News ID</label>\n"
                    + "                                <input readonly value=\""+n.getId()+"\" type=\"text\" id=\"ssID\" name=\"sID\" placeholder=\"New id ..\">\n"
                    + "                                <br>\n"
                    + "                                <input name=\"file_upload\" type=\"file\" id=\"file_upload\" onchange=\"previewImg(event)\">\n"
                    + "                                <br>\n"
                    + "                                <image style=\"width: 100%\" name=\"imgF\" src=\""+n.getUrlImage()+"\" width=\"200\" height=\"250\" id=\"image-field3\">\n"
                    + "                                <br>\n"
                    + "                                <input  type=\"submit\" value=\"Submit\">\n"
                    + "                            </form>");
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
