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
import java.util.function.Predicate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.AuthorDB;
import model.dao.BookDB;
import model.dao.DeviceDB;
import model.dao.MessageDB;
import model.entity.Account;
import model.entity.Author;
import model.entity.Book;
import model.entity.Device;

/**
 *
 * @author ThanhNhan
 */
public class Search extends HttpServlet {

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
            out.println("<title>Servlet Search</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Search at " + request.getContextPath() + "</h1>");
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
                        response.sendRedirect("manage");
                        break;
                }
            } else {
                response.sendRedirect("login");
            }
        } catch (IOException e) {
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
        Account ac = (Account) request.getSession().getAttribute("acc");
        String option = request.getParameter("option");
        String value = URLDecoder.decode(request.getParameter("value"), "UTF-8");
        String v = "";
        ArrayList<Book> list1 = new ArrayList<>();
        ArrayList<Device> list2 = new ArrayList<>();
        switch (option) {
            case "all": {
                v = "tất cả danh mục";
                list1 = BookDB.search(new Predicate<Book>() {
                    @Override
                    public boolean test(Book b) {
                        return b.getBookName().toUpperCase().contains(value.toUpperCase()) || b.getCategoryName().toUpperCase().contains(value.toUpperCase()) || b.getSubjectName().toUpperCase().contains(value.toUpperCase());
                    }
                });
                ArrayList<Author> res = new ArrayList<>();
                for (Author a : AuthorDB.getListAuthor()) {
                    if (a.getAuthorName().toUpperCase().contains(value.toUpperCase())) {
                        res.add(a);
                    }
                }
                for (Author a : res) {
                    for (Book b : BookDB.authorBook(a.getAuthorID())) {
                        list1.add(b);
                    }
                }
                if (ac.getUserTypeID() == 0 || ac.getUserTypeID() == 2) {
                    for (Device d : DeviceDB.getListDevice()) {
                        if (d.getDeviceName().toUpperCase().contains(value.toUpperCase())) {
                            list2.add(d);
                        }
                    }
                }
                break;
            }
            case "sB": {
                v = "sách";
                list1 = BookDB.search(new Predicate<Book>() {
                    @Override
                    public boolean test(Book b) {
                        return b.getBookName().toUpperCase().contains(value.toUpperCase());
                    }
                });
                break;
            }
            case "sA": {
                v = "tác giả";
                ArrayList<Author> res = new ArrayList<>();
                for (Author a : AuthorDB.getListAuthor()) {
                    if (a.getAuthorName().toUpperCase().contains(value.toUpperCase())) {
                        res.add(a);
                    }
                }
                for (Author a : res) {
                    for (Book b : BookDB.authorBook(a.getAuthorID())) {
                        list1.add(b);
                    }
                }
                break;
            }
            case "sC": {
                v = "thể loại";
                list1 = BookDB.search(new Predicate<Book>() {
                    @Override
                    public boolean test(Book b) {
                        return b.getCategoryName().toUpperCase().contains(value.toUpperCase());
                    }
                });
                break;
            }
            case "sD": {
                v = "thiết bị";
                for (Device d : DeviceDB.getListDevice()) {
                    if (d.getDeviceName().toUpperCase().contains(value.toUpperCase())) {
                        list2.add(d);
                    }
                }
                break;
            }
            case "sS": {
                v = "môn học";
                if (ac.getUserTypeID() == 0 || ac.getUserTypeID() == 2) {
                    for (Device d : DeviceDB.getListDevice()) {
                        if (d.getDeviceName().toUpperCase().contains(value.toUpperCase())) {
                            list2.add(d);
                        }
                    }
                }
                list1 = BookDB.search(new Predicate<Book>() {
                    @Override
                    public boolean test(Book b) {
                        return b.getSubjectName().toUpperCase().contains(value.toUpperCase());
                    }
                });
                break;
            }
        }
        if (!list1.isEmpty() || !list2.isEmpty()) {
            request.setAttribute("list1", list1);
            request.setAttribute("list2", list2);
            request.setAttribute("mes", "Có " + (list1.size() + list2.size()) + " kết quả tìm kiếm với từ khóa: " + value);
        } else {
            request.setAttribute("mes", "Không tìm thấy kết quả tìm kiếm " + v + " với từ khóa: \"" + value + "\"");
        }
        Account a = (Account) request.getSession().getAttribute("acc");
        request.setAttribute("messenger", MessageDB.getMessage(a.getUserID()));
        request.setAttribute(option, "selected");
        request.setAttribute("value", value);
        request.getRequestDispatcher("uSearch.jsp").include(request, response);
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
