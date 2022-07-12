/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

import DAO.DAONews;
import DAO.DAOProduct;
import entity.News;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
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
@WebServlet(name = "detailControl", urlPatterns = {"/detailControl"})
public class detailControl extends HttpServlet {

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
            DAONews n = new DAONews();
            Vector<News> v = n.getTop3News();
            Vector<News> v2 = n.getNews();
            Vector<Product> v3 = new DAOProduct().getTop3Product();
            int v4 = new DAOProduct().getLastIDProduct();
            request.setAttribute("productss", v3);
            request.setAttribute("lastID", v4);
            int id = Integer.parseInt(request.getParameter("newID"));

            News o = n.getNewByID(id);
//            System.out.println("=============================== "+id);
            request.setAttribute("id", id);
//            int type = n.getTypeByID(id);
            n.setView(id);
            String jspPath = o.getUrlTxt();
            String line;
            DAONews d = new DAONews();
            line = d.readUnicodeClassic(jspPath);
//            System.out.println("adsadasdasdasd" + o.getTitle());
            HttpSession ses = request.getSession();
            request.setAttribute("o1", o);
            request.setAttribute("string", line);
            request.setAttribute("newsRight", v2);
            request.setAttribute("productss", v3);
            request.setAttribute("lastID", v4);
            request.setAttribute("type", 1);
//            System.out.println("typeeeeeeeeeeeeeeeee================="+type);
//        for (News news : v3) {
//            System.out.println(news.getUrlImage());
//        }
            request.getRequestDispatcher("detailNews.jsp").forward(request, response);

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
