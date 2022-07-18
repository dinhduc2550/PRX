package ForNews;

import DAO.DAOProduct;
import entity.Product;
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
@WebServlet(name = "updateProductToXML", urlPatterns = {"/updateProductToXML"})
public class updateProductToXML extends HttpServlet {

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
        String id = request.getParameter("proID");
        String name = request.getParameter("pName");
        int amount = Integer.parseInt(request.getParameter("amount") + "");
//String amount1 = request.getParameter("amount");
//        System.out.println("amounttt========"+amount1);
        float price = Float.parseFloat(request.getParameter("price"));
        String des = request.getParameter("txtA");
        String urlImg = request.getParameter("urlImg");
        Product p = new Product();
        p.setpID(Integer.parseInt(id));
        p.setpName(name);
        p.setAmount(amount);
        p.setDes(des);
        p.setPrice(price);
        if (!urlImg.isEmpty()) {
            p.setUrlImage(urlImg);
        }
        new DAOProduct().updateProductToXML(p);
        response.sendRedirect("newsControl");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
