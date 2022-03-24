/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.BookDB;
import model.dao.CategoryDB;
import model.dao.MessageDB;
import model.entity.*;

/**
 *
 * @author ThanhNhan
 */
public class Categoryc extends HttpServlet {

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
            out.println("<title>Servlet Category</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Category at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        try {
            /* TODO output your page here. You may use following sample code. */
            Account a = (Account) request.getSession().getAttribute("acc");
            if (a != null) {
                int type = a.getUserTypeID();
                switch (type) {
                    case 3:
                    case 2:
                    case 0:
                        request.setAttribute("ca", "active");
                        request.setAttribute("listCategory", CategoryDB.getListCategory());
                        String id = request.getParameter("id");
                        if (id == null) {
                            request.setAttribute("list", BookDB.getListBook());
                        } else {
                            if (BookDB.categoryBook(id).isEmpty()) {
                                request.setAttribute("mes", "Không tìm thấy sách có thể loại: " + new Category(id).getCategoryName());
                            } else {
                                request.setAttribute("list", BookDB.categoryBook(id));
                            }
                        }
                        request.setAttribute("messenger", MessageDB.getMessage(a.getUserID()));
                        request.setAttribute("tag", id);
                        request.setAttribute("ca", "active");
                        request.getRequestDispatcher("Category.jsp").include(request, response);
                        break;
                    case 1:
                        response.sendRedirect("manage");
                        break;
                }
            } else {
                response.sendRedirect("login");
            }
        } catch (IOException | ServletException e) {
            response.sendRedirect("login");
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String value = URLDecoder.decode(request.getParameter("v"), "UTF-8");
        String id = request.getParameter("id");
        ArrayList<Book> list = new ArrayList<>();
        if (id == null || id.trim().equals("")) {
            for (Book b : BookDB.getListBook()) {
                if (b.getBookName().toUpperCase().contains(value.toUpperCase().trim())) {
                    list.add(b);
                }
            }
        } else {            
            for (Book b : BookDB.categoryBook(id)) {
                if (b.getBookName().toUpperCase().contains(value.toUpperCase().trim())) {
                    list.add(b);
                }
            }
        }
        Account a = (Account) request.getSession().getAttribute("acc");
        request.setAttribute("messenger", MessageDB.getMessage(a.getUserID()));
        request.setAttribute("listCategory", CategoryDB.getListCategory());
        request.setAttribute("list", list);
        request.setAttribute("v", value);
        request.setAttribute("tag", id);
        request.setAttribute("ca", "active");
        request.getRequestDispatcher("Category.jsp").include(request, response);
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
