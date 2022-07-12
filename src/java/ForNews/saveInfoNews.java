/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ForNews;

import DAO.DAONews;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@MultipartConfig
@WebServlet(name = "saveInfoNews", urlPatterns = {"/saveInfoNews"})
public class saveInfoNews extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");
    }

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("newsManager.jsp").forward(request, response);
//        request.setCharacterEncoding("UTF-8");
    }
    String id;
    private String s;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String UPLOAD_DIRECTORY = "C:\\Users\\dinhd\\OneDrive\\Java Web\\MyProject\\web\\img\\test";
        HashMap<String, String> fh = null;
        if (ServletFileUpload.isMultipartContent(request)) {
            id = request.getParameter("sID");
            try {
                fh = new HashMap<>();
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                s = multiparts.get(0) + "";
                for (FileItem item : multiparts) {
                    fh.put(item.getFieldName(), item.getString());
                    if (!item.isFormField()) {
                        String name = new File(item.getName()).getName();
                        File f = new File(UPLOAD_DIRECTORY + File.separator + name);
                        System.out.println(f.getPath());
//                        s = f.getPath();
                        item.write(f);
                    }
                }
                request.setAttribute("message", "File uploaded successfully.");
            } catch (Exception ex) {
                request.setAttribute("message", "File upload failed due to : " + ex);
            }
        } else {
            request.setAttribute("message", "Sorry this servlet only handles file upload request.");
        }
        System.out.println("===============");
        String id = fh.get("siD");
        System.out.println(id);
        System.out.println("================");
        request.getRequestDispatcher("newsControl").forward(request, response);
    }
//    public String s;
    public static final String UPLOAD_DIR = "img/test";
    public String dbFileName = "";

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
