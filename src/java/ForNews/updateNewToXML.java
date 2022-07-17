/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ForNews;

import DAO.DAONews;
import entity.News;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import path.PathFile;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dinhd
 */
@WebServlet(name = "updateNewToXML", urlPatterns = {"/updateNewToXML"})
public class updateNewToXML extends HttpServlet {

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

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String id = request.getParameter("newID");
        String title = request.getParameter("titleA");
        String urlImg = request.getParameter("urlImg");
        String des = request.getParameter("txtA");
        String[] part = des.split("\\n");
        String urlTextFile = PathFile.PATH_FOLDER_TXT + "n_" + id + ".txt";
        File file = new File(urlTextFile);
        try (FileOutputStream fos = new FileOutputStream(file, false);
                OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                BufferedWriter writer = new BufferedWriter(osw)) {
            for (String part1 : part) {
                writer.append(part1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("url======= " + urlTextFile);

        News n = new News();
        n.setId(Integer.parseInt(id));
        n.setTitle(title);
        n.setUrlImage(urlImg);
        n.setUrlTxt(urlTextFile);
        n.setType(1);
        new DAONews().updateNewsToXML(n);
        response.sendRedirect("newsControl");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
