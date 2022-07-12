/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ForNews;

import DAO.DAONews;
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
@WebServlet(name = "showFormAddProductByNews", urlPatterns = {"/showFormAddNewsByProduct"})
public class showFormAddNewsByProduct extends HttpServlet {

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
            String id = request.getParameter("productID");
            System.out.println("id============"+id);
            DAONews d = new DAONews();
            int pid = Integer.valueOf(id+"");
//            int pid=0;
            System.out.println(pid);
            
            HttpSession ses = request.getSession();
            Account a = (Account) ses.getAttribute("acc");
            int uID=0;
            try {
                 uID=a.getId();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("uid============"+uID);
            System.out.println("pidddddd ============="+pid);
            ses.setAttribute("pid3", pid);
            out.println("<form enctype=\"multipart/form-data\" method=\"post\" id=\"formNews\" action=\"addNewByProduct?type=3&uID="+uID+"&pid2="+pid+"\">\n"
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
