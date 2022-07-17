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
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author dinhd
 */
@MultipartConfig
@WebServlet(name = "uploadImg", urlPatterns = {"/uploadImg"})
public class updateNewsToDB extends HttpServlet {

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
//      request.setCharacterEncoding("UTF-8");

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
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
////        request.setCharacterEncoding("UTF-8");
//        request.getRequestDispatcher("newsManager.jsp").forward(request, response);
//    }
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
        PrintWriter out = response.getWriter();
        System.out.println("going=================");
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
            System.out.println("going=================");
            while (it.hasNext()) {
                System.out.println("going1.1=================");
                FileItem fileItem = it.next();
                boolean isFormField = fileItem.isFormField();
                 System.out.println("going1=================");
                if (isFormField) {
                     System.out.println("going1.2=================");
                    try {
                        fh.put(fileItem.getFieldName(), fileItem.getString());
                         if (file_name == null) {
                         System.out.println("going1.3=================");
                        if (fileItem.getFieldName().equals("file_name")) {
                            file_name = fileItem.getString();
                        }
                         System.out.println("going1.4=================");
                    }
                    } catch (Exception e) {
                        System.err.println("error put data============");
                    }
                    System.out.println("going1.22=================");
                   
                   

                } else {
                    s = fileItem.getName();
                    System.out.println(s);
                    System.out.println("going2=================");

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
            System.out.println("going3=================");

            DAONews d = new DAONews();
            String id = fh.get("newID");
            String txtA = fh.get("txtA");
            String[] part = txtA.split("\\n");
            String amount = fh.get("amount");
            System.out.println(amount);
            String urlTextFile = "C:\\Users\\dinhd\\OneDrive\\Java Web\\MyProject\\web\\txt\\n_" + id + ".txt";
            int type = d.getTypeByID(Integer.parseInt(id));

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
            System.out.println("type===========" + type);
//            if (type == 1) {
//                if (!check) {
            new DAONews().updateNew(Integer.parseInt(id),
                    fh.get("titleA"), url, urlTextFile);
//                } else {
//                    new DAONews().updateNew(Integer.parseInt(id),
//                            fh.get("titleA"), urlTextFile);
//                }
            if (type == 0) {
//                if (!check) {
//                new DAONews().updateNew(Integer.parseInt(id),
//                        fh.get("titleA"), url, urlTextFile);
//                } else {
//                    new DAONews().updateNew(Integer.parseInt(id),
//                            fh.get("titleA"), urlTextFile);
//                }
                News n1 = new News();
                n1.setId(Integer.parseInt(id));
                n1.setProductName(fh.get("pName"));
                n1.setAmount(Integer.parseInt(fh.get("amount")));
                n1.setPrice(Float.parseFloat(fh.get("price")));
                System.out.println(url);
                System.out.println(n1.getId());
                System.out.println(n1.getProductName());
                System.out.println(n1.getAmount());
                System.out.println(n1.getPrice());
                d.updateProduct(n1);
//                n1.setTitle(fh.get("titleA"));
// News n1 = new News();
//                n1.setId(Integer.parseInt(id));
//                n1.setUrlImage(url);
//                n1.setTitle(fh.get("titleA"));
//                n1.setUrlTxt(urlTextFile);
//                n1.setType(1);
//                n1.setProductName(fh.get("pName"));
//                n1.setAmount(Integer.parseInt(fh.get("amount")));
//                n1.setPrice(Float.parseFloat(fh.get("price")));
            }
        } catch (Exception e) {
            System.out.println("errorr================");
        }
//          request.getRequestDispatcher("newsControl").forward(request, response);

        response.sendRedirect("newsControl");
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
