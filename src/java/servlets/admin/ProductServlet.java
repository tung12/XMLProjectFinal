/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.admin;

import dao.CategoryDao;
import dao.ProductDao;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import jaxb.category.ListCategory;
import jaxb.product.ListProduct;
import jaxb.product.ProductItem;
import utils.PaginationHandler;

/**
 *
 * @author ntien
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {

    ProductDao productDao = new ProductDao();
    CategoryDao categoryDao = new CategoryDao();
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        int page = 1;
        int maxResult = 20;
        int maxNavigationResult = 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        PaginationHandler ProductPaging = productDao.getAllProduct(page, maxResult, maxNavigationResult);
        ListProduct listProduct = new ListProduct();
        ListCategory listCategory = new ListCategory();
        listCategory.getCategory().addAll(categoryDao.getAllCategory());        
        listProduct.setProduct(ProductPaging.getListItems());
        request.setAttribute("totalPages", ProductPaging.getTotalPage());
        request.setAttribute("navPages", ProductPaging.getNavigationPages());       
        JAXBContext jaxbContext;
        StringWriter stringWriterProduct = new StringWriter();
        StringWriter stringWriterCategory = new StringWriter();
        try {
            jaxbContext = JAXBContext.newInstance(ListProduct.class,ListCategory.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);      
            jaxbMarshaller.marshal(listProduct, stringWriterProduct);
            jaxbMarshaller.marshal(listCategory, stringWriterCategory);
        } catch (JAXBException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("productXml", stringWriterProduct.toString());
        request.setAttribute("categoryXml", stringWriterCategory.toString());
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher("/WEB-INF/admin/product.jsp");

        dispatcher.forward(request, response);
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
