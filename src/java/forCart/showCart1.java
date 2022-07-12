/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forCart;

import DAO.ConnectDB;
import DAO.DAONews;
import DAO.DAOProduct;
import entity.Account;
import entity.News;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
@WebServlet(name = "showCart2", urlPatterns = {"/showCart2"})
public class showCart1 extends HttpServlet {

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
            Cookie cookies[] = request.getCookies();
//            System.out.println("star==============-");
            for (Cookie c : cookies) {
//                System.out.println(c.getValue());
            }
            Vector<Product> v = new Vector<>();
            boolean first = true;
            HttpSession ses = request.getSession();
            if (ses.getAttribute("acc") == null) {
                response.sendRedirect("login");
            } else {
                DAOProduct dp = new DAOProduct();
                DAONews d = new DAONews();
                for (Cookie c : cookies) {
                    if (c.getName().equals("id")) {
//                    System.out.println(c.getName());
//                    System.out.println(c.getValue());
//                    System.out.println("===============");
                        String txt[] = c.getValue().split(",");
                        for (String s : txt) {
//                        System.out.println(s);
                            int i = Integer.parseInt(s);
//                            int pID = d.getpIDbynID(i);
                            int pID = i;
//                        System.out.println(i + " hihi");
                            v.add(dp.getProductByProductID(pID));
                        }
//                    System.out.println(">>>>>>>>>>>>>>>>");
                    }
                }
//                for (News news : v) {
//                System.out.println(news.toString());
//                System.out.println("size: " + v.size());
//                }
                int countA = Collections.frequency(v, "1");
                for (int i = 0; i < v.size(); i++) {
                    int count = 1;
                    if (v.size() == 1) {
                        v.get(i).setAmount(count);
//                    System.out.println("amount" + v.get(i).getAmount());
                        break;
                    }
                    v.get(i).setAmount(count);
//                System.out.println("iiiiiiii="+i);
                    for (int j = i + 1; j < v.size(); j++) {
//                    System.out.println("inside");
                        if (v.get(i).getpID()== v.get(j).getpID()) {
//                        System.out.println("inside2");
                            count++;
                            v.remove(j);
                            j--;
                            v.get(i).setAmount(count);
//                        System.out.println("i====" + i);
//                        System.out.println(v.get(i).getId() + "==" + count);
//                        System.out.println(v.get(i).getId() + "==" + v.get(i).getAmount());
                        }
                    }
                }
                double total = 0;
//            System.out.println("9999999999999999999999999999");
//            System.out.println("size=" + v.size());
//            System.out.println("amount1: " + v.get(0).getAmount());
//            System.out.println("amount1: "+v.get(1).getAmount());
                for (int i = 0; i < v.size(); i++) {
                    total = total + v.get(i).getAmount() * v.get(i).getPrice();

                System.out.println(v.get(i).getAmount());
                }
                Account a = (Account) ses.getAttribute("acc");
                int aid = a.getId();
                ConnectDB da = new ConnectDB();
                Account acc = da.getAllAttibute(a.getName());
//            System.out.println("99999999999999999999999999999");
                request.setAttribute("list", v);
                ses.setAttribute("listPay", v);
                request.setAttribute("total", total);
                request.setAttribute("totalVat", 1.1 * total);
                request.setAttribute("sum", 1.1 * total);
                request.setAttribute("infoCheckOut", acc);
                request.getRequestDispatcher("cart.jsp").forward(request, response);
//  }
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
