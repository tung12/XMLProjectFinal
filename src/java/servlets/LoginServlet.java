/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.UserAccountDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jaxb.user.UserItem;

/**
 *
 * @author ntien
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    
    UserAccountDao accountDao = new UserAccountDao();

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

//         RequestDispatcher dispatcher //
//                = this.getServletContext().getRequestDispatcher();
// 
//        dispatcher.forward(request, response);
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
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher("/WEB-INF/sss/error.jsp");
        
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
        response.setContentType("text/html;charset=UTF-8");
        String userName = request.getParameter("userName").trim();
        String password = request.getParameter("password").trim();
        boolean rememberMe = request.getParameter("remember") == null ? false : true;
        String from = request.getParameter("from");
        String errorString = "";
        boolean hasError = false;
        UserItem user = null;
        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            errorString = "Required username and password!";
        } else {
            user = accountDao.findUser(userName);
            if (user == null) {
                hasError = true;
                errorString = "User Name not exist";
            } else {
                user = accountDao.findUser(userName, password);
                if (user == null) {
                    hasError = true;
                    errorString = "User Name or Password invalid";
                }
            }
        }
        
        if (hasError) {
            user = new UserItem();
            user.setUserName(userName);
            user.setPassword(password);
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
            dispatcher.forward(request, response);
        } else {
            HttpSession session = request.getSession();
            accountDao.storeLoginedUser(session, user);
            if (rememberMe) {
                accountDao.storeUserCookie(response, user);
            } else {
                accountDao.deleteUserCookie(response);
            }
          response.sendRedirect(request.getContextPath()+from);
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
