/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.admin;

import dao.NewsDao;
import dao.ProductDao;
import dao.UserAccountDao;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import jaxb.news.ListNews;
import jaxb.news.NewsItem;
import jaxb.newsimage.NewsImage;
import jaxb.product.ListProduct;
import jaxb.product.ProductItem;
import jaxb.user.ListUser;

/**
 *
 * @author ntien
 */
@WebServlet(name = "DetailNewsServlet", urlPatterns = {"/detailNews"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class DetailNewsServlet extends HttpServlet {

    NewsDao newsDao = new NewsDao();
    ProductDao productDao = new ProductDao();
    public static final String SAVE_DIRECTORY = "imageDir";

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
            out.println("<title>Servlet DetailNewsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailNewsServlet at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("id");
        NewsItem newsItem = newsDao.getNewsById(BigInteger.valueOf(Long.parseLong(id)));              
        ListNews listNews = new ListNews();
        listNews.getNew().add(newsItem);

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ListNews.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            jaxbMarshaller.marshal(listNews, response.getOutputStream());
            
        } catch (JAXBException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        String appPath = request.getServletContext().getRealPath("");
        appPath = appPath.replace('\\', '/');
        String fullSavePath = null;
        if (appPath.endsWith("/")) {
            fullSavePath = appPath + SAVE_DIRECTORY;
        } else {
            fullSavePath = appPath + "/" + SAVE_DIRECTORY;
        }
        File fileSaveDir = new File(fullSavePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        NewsItem newsItem = new NewsItem();
        String name = request.getParameter("name");        
        String category = request.getParameter("category");        
        String discription = request.getParameter("discription");
        String title = request.getParameter("title");
        String idProduct = request.getParameter("idProduct");
        newsItem.setName(name);
        newsItem.setDiscription(discription);
        newsItem.setTitle(title);
        newsItem.setIdCategory(category);
        newsItem.setIdProduct(BigInteger.valueOf(Long.parseLong(idProduct)));
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);           
            if (fileName != null && fileName.length() > 0) {
                newsItem.getImageList().add(new NewsImage(fileName));
                String filePath = fullSavePath + File.separator + fileName;                             
                part.write(filePath);
            }
        }                       
        newsDao.addNews(newsItem);
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                return clientFileName.substring(i + 1);
            }
        }
        return null;
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
