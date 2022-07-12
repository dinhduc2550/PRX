/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

import DAO.DAONews;
import entity.Account;
import entity.News;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author dinhd
 */
@WebServlet(name = "addNewToDB", urlPatterns = {"/addNewToDB"})
public class addNewToDB extends HttpServlet {

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
        String file_name = null;
        boolean check = true;
        response.setContentType("text/html");
        boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
        if (!isMultipartContent) {
            return;
        }
        String s = "";
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List< FileItem> fields = upload.parseRequest(request);
            Iterator< FileItem> it = fields.iterator();
            if (!it.hasNext()) {
                return;
            }
            HashMap<String, String> fh = new HashMap<>();
            while (it.hasNext()) {
                FileItem fileItem = it.next();
                boolean isFormField = fileItem.isFormField();
                if (isFormField) {
                    fh.put(fileItem.getFieldName(), fileItem.getString());
                    if (file_name == null) {
                        if (fileItem.getFieldName().equals("file_name")) {
                            file_name = fileItem.getString();
                        }
                    }
                } else {
                    s = fileItem.getName();
                    System.out.println(s);
                    if (fileItem.getSize() > 0) {
                        File f = new File("C:\\Users\\dinhd\\OneDrive\\Java Web\\MyProject\\web\\img\\test\\" + fileItem.getName());
                        if (f.exists() && !f.isDirectory()) {
                            s = fileItem.getName();
                            check = false;
                        } else {
                            fileItem.write(new File("C:\\Users\\dinhd\\OneDrive\\Java Web\\MyProject\\web\\img\\test\\" + fileItem.getName()));
                            check = true;
                        }
                    }
                }
            }
            DAONews d = new DAONews();
//            String id = fh.get("newID");
            String txtA = fh.get("txtA");
            String[] part = txtA.split("\\n");
            String amount = fh.get("amount");
            int count = d.countNews();
            String urlTextFile = "C:\\Users\\dinhd\\OneDrive\\Java Web\\MyProject\\web\\txt\\ntxt_" + count + ".txt";
            int type = Integer.parseInt(request.getParameter("type"));
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
            String url = "img\\test\\" + s;
//            System.out.println("type===========" + type);
            String title = fh.get("titleA");
            System.out.println(type);
            System.out.println(title);
            System.out.println(url);
            System.out.println(urlTextFile);
            System.out.println(txtA);
            HttpSession ses = request.getSession();
            Account a = (Account) ses.getAttribute("acc");
            
            d.addNew(title, url, urlTextFile, type,a.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//          response.sendRedirect("newsControl");
            request.getRequestDispatcher("newsControl").forward(request, response);

        }
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
