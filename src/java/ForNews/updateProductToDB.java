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
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author dinhd
 */
@WebServlet(name = "updateProductToDB", urlPatterns = {"/updateProductToDB"})
public class updateProductToDB extends HttpServlet {

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
        PrintWriter out = response.getWriter();
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
            System.out.println("======================");
            int pID =Integer.parseInt(fh.get("proID"));
            String pName = fh.get("pName");
            int amount  = Integer.parseInt(fh.get("amount"));
            float price = Float.parseFloat(fh.get("price"));
            String des = fh.get("txtA");
            String url = "img\\test\\" + s;
//            String txtA = fh.get("txtA");
            Product p = new Product();
            p.setpID(pID);
            p.setpName(pName);
            p.setAmount(amount);
            p.setPrice(price);
            p.setDes(des);
            p.setUrlImage(url);
            System.out.println(p.toString());
            DAOProduct d = new DAOProduct();
            d.updateProductToDB(p);
//            String[] part = txtA.split("\\n");
//            String amount = fh.get("amount");
//            System.out.println(amount);
//            String urlTextFile = "C:\\Users\\dinhd\\OneDrive\\Java Web\\MyProject\\web\\txt\\n_" + id + ".txt";
//            int type = d.getTypeByID(Integer.parseInt(id));
//
//            File file = new File(urlTextFile);
//            try (FileOutputStream fos = new FileOutputStream(file, false);
//                    OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
//                    BufferedWriter writer = new BufferedWriter(osw)) {
//                for (String part1 : part) {
//                    writer.append(part1);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
           
//            System.out.println("type===========" + type);
//            if (type == 1) {
//                if (!check) {
//            new DAONews().updateNew(Integer.parseInt(id),
//                    fh.get("titleA"), url, urlTextFile);
//                } else {
//                    new DAONews().updateNew(Integer.parseInt(id),
//                            fh.get("titleA"), urlTextFile);
//                }
//            if (type == 0) {
//                if (!check) {
//                new DAONews().updateNew(Integer.parseInt(id),
//                        fh.get("titleA"), url, urlTextFile);
//                } else {
//                    new DAONews().updateNew(Integer.parseInt(id),
//                            fh.get("titleA"), urlTextFile);
//                }
//                News n1 = new News();
//                n1.setId(Integer.parseInt(id));
//                n1.setProductName(fh.get("pName"));
//                n1.setAmount(Integer.parseInt(fh.get("amount")));
//                n1.setPrice(Float.parseFloat(fh.get("price")));
//                System.out.println(url);
//                System.out.println(n1.getId());
//                System.out.println(n1.getProductName());
//                System.out.println(n1.getAmount());
//                System.out.println(n1.getPrice());
//                d.updateProduct(n1);
//                n1.setTitle(fh.get("titleA"));

//            }
        } catch (Exception e) {
            e.printStackTrace();
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
