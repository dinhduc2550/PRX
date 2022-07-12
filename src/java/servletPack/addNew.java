/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

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
@WebServlet(name = "addNew", urlPatterns = {"/addNew"})
public class addNew extends HttpServlet {

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
            String option = request.getParameter("optionB");
            System.out.println(option);
            if (option.contains("News")) {
                out.println("<form enctype=\"multipart/form-data\" method=\"post\" id=\"formNews\" action=\"addNewToDB?type=1\">\n"
                        + "                                        "
                        + "                                        "
                        + "\n"
                        + "                                        <label for=\"title\">News Title</label>\n"
                        + "                                        \n"
                        + "                                        <textarea name=\"titleA\" id=\"titleA\"></textarea>\n"
                        + "                                        <label for=\"img\">Image</label>\n"
                        + "\n"
                        + "                                        <input name=\"file_upload\" type=\"file\" id=\"file_upload\" onchange=\"previewImg(event)\">\n"
                        + "                                        <br>\n"
                        + "                                        <image name=\"imgF\" src=\"img\\photo-icon-1.jpg\" width=\"200\" height=\"250\" id=\"image-field\">\n"
                        + "                                        <br>\n"
                        + "                                        <label for=\"txtA\">Content</label>\n"
                        + "                                        <textarea style=\"height: 300px;\" name=\"txtA\" id=\"txtA\"></textarea>\n"
                        + "                                        <input type=\"submit\" value=\"Save\">\n"
                        + "                                    </form>");
            }else{
                out.println("<form enctype=\"multipart/form-data\" name=\"formNews\" method=\"post\" id=\"formNews\" action=\"addProductToDB\">\n"
                        + "                                        <br>\n"
                        + "                                        <label for=\"pName\">Product Name</label>\n"
                        + "                                        <input value=\"\" type=\"text\" id=\"pName\" name=\"pName\" placeholder=\"Product Name ..\">\n"
                        + "                                        <br>\n"
                        + "                                        <label for=\"amount\">Amount</label>  <br>\n"
                        + "                                        <input value=\"\" type=\"number\" id=\"amount\" name=\"amount\" min=\"0\">\n"
                        + "                                        <br>\n"
                        + "                                        <label for=\"price\">Price</label>\n"
                        + "                                        <input value=\"\" onchange=\"returns checkInp()\" type=\"text\" id=\"price\" name=\"price\" >\n"
                        + "\n"
                        + "                                        "
                        + "                                        <label for=\"img\">Image</label>\n"
                        + "\n"
                        + "                                        <input name=\"file_upload\" type=\"file\" id=\"file_upload\" onchange=\"previewImg(event)\">\n"
                        + "                                        <br>\n"
                        + "                                        <image name=\"imgF\" src=\"img\\photo-icon-1.jpg\" width=\"200\" height=\"250\" id=\"image-field\">\n"
                        + "                                        <br>\n"
                        + "                                        <label for=\"txtA\">Content</label>\n"
                        + "                                        <textarea style=\"height: 450px;\" name=\"txtA\" id=\"txtA\"></textarea>\n"
                        + "                                        <input type=\"submit\" value=\"Save\">\n"
                        + "                                    </form>");
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
