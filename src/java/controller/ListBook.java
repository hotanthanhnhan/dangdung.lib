/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.BookDB;
import model.entity.Account;
import model.entity.Author;
import model.entity.Book;

/**
 *
 * @author ThanhNhan
 */
public class ListBook extends HttpServlet {

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
            out.println("<title>Servlet ListBook</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListBook at " + request.getContextPath() + "</h1>");
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
            Account a = (Account) request.getSession().getAttribute("acc");
            if (a != null) {
                int type = a.getUserTypeID();
                switch (type) {
                    case 3:
                    case 2:
                    case 0:
                        response.sendRedirect("library");
                        break;
                    case 1:
                        request.setAttribute("appa", "display: flex!important");
                        request.setAttribute("action", "listbook");
                        request.setAttribute("list", BookDB.getListBook());
                        request.getRequestDispatcher("ListBook.jsp").include(request, response);
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
        String value = request.getParameter("value");
        ArrayList<Book> list = new ArrayList<>();
        for (Book b : BookDB.getListBook()) {
            boolean find = true;
            for(Author au : b.getAuthors()){
                if(au.getAuthorName().toUpperCase().contains(value.toUpperCase())){
                    list.add(b);
                    find = false;
                    break;
                }
            }
            if ((b.getBookName().toUpperCase().contains(value.toUpperCase()) || b.getCategoryID().toUpperCase().contains(value.toUpperCase()) || b.getCategoryName().toUpperCase().contains(value.toUpperCase())  || b.getSubjectName().toUpperCase().contains(value.toUpperCase())) && find) {
                list.add(b);
            }
        }
        request.setAttribute("appa", "display: flex!important");
        request.setAttribute("action", "listbook");
        request.setAttribute("list", list);
        request.setAttribute("value", value);
        request.getRequestDispatcher("ListBook.jsp").include(request, response);
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
