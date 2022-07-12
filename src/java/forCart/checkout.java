/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forCart;

import DAO.DAOHistory;
import entity.News;
import DAO.DAONews;
import DAO.DAOProduct;
import entity.Account;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dinhd
 */
@WebServlet(name = "checkout", urlPatterns = {"/checkout"})
public class checkout extends HttpServlet {

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
            Cookie cookies[] = request.getCookies();
            Vector<Product> v = new Vector<>();
//            DAONews d = new DAONews();
            DAOProduct d = new DAOProduct();
            for (Cookie c : cookies) {
                if (c.getName().equals("id")) {
                    String txt[] = c.getValue().split(",");
                    for (String s : txt) {
//                        System.out.println(s);
                        int i = Integer.parseInt(s);
                        int pID = i;
//                        System.out.println(i + " hihi");
                        v.add(d.getProductByProductID(pID));
                    }
//                    System.out.println(">>>>>>>>>>>>>>>>");
                }
            }
            for (int i = 0; i < v.size(); i++) {
                int count = 1;
                if (v.size() == 1) {
                    v.get(i).setAmount(count);
                    break;
                }
                v.get(i).setAmount(count);
                for (int j = i + 1; j < v.size(); j++) {
                    if (v.get(i).getpID()== v.get(j).getpID()) {
                        count++;
                        v.remove(j);
                        j--;
                        v.get(i).setAmount(count);
                    }
                }
            }
            System.out.println("-----------------------------");
//            for (News news : v) {
//                System.out.println(news.getProductName());
//                System.out.println(news.getAmount());
//                System.out.println(news.getPrice());
//                
//            }
//             System.out.println("-----------------------------");
            HttpSession ses = request.getSession();
            Account a = (Account) ses.getAttribute("acc");
            String name = request.getParameter("fNameF");
            String address = request.getParameter("addressF");
            String phone = request.getParameter("phoneF");
//            System.out.println("name: "+name);
//            System.out.println("address: "+address);
//            System.out.println("phone: "+phone);
            if (a == null || ses.getAttribute("acc") == null) {
                response.sendRedirect("login");
            } else {
                int uid = a.getId();
//                System.out.println("user ID: " + a.getId());
                DAOHistory dh = new DAOHistory();
                for (Product product : v) {
//                    System.out.println(news.getProductName());
//                    System.out.println(news.getId());
                    dh.insertPurcase(product, uid, product.getpID(),phone,address,name,product.getAmount(),product.getPrice());
//                    System.out.println(d.getpIDbynID(news.getId()));
//                    System.out.println(news.getPrice());
//                    System.out.println(news.getAmount());
                }

//            System.out.println("total: "+request.getAttribute("total"));
                for (Cookie o : cookies) {
                    if (o.getName().equals("id")) {
                        o.setMaxAge(0);
                        response.addCookie(o);
                    }
                }
                response.sendRedirect("home");
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
