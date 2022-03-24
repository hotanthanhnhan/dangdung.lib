/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.BookOrderDB;
import model.dao.MessageDB;
import model.entity.Account;
import model.entity.Book;
import model.entity.BookOrder;
import model.entity.BookReceipt;
import model.entity.Books;
import model.entity.Message;

/**
 *
 * @author ThanhNhan
 */
public class BookOrders extends HttpServlet {

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
            out.println("<title>Servlet BookOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookOrder at " + request.getContextPath() + "</h1>");
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
                        response.setContentType("text/html;charset=UTF-8");
                        request.setAttribute("list", BookOrderDB.getListBookOrder());
                        request.getRequestDispatcher("BookOrder.jsp").include(request, response);
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
        String type = request.getParameter("type");
        int bookOrderID = Integer.parseInt(request.getParameter("o"));
        BookOrder b = new BookOrder(bookOrderID);
        switch (type) {
            case "1":
                PrintWriter out = response.getWriter();
                int bookCode = Integer.parseInt(request.getParameter("bookCode"));
                String time = request.getParameter("time");
                time = time.substring(11, time.length()) + " " + time.substring(8, 10) + "-" + time.substring(5, 7) + "-" + time.substring(0, 4);
                SimpleDateFormat f = new SimpleDateFormat("HH:mm dd-MM-yyyy");
                try {
                    Date d = f.parse(time);
                    if (d.getTime() < new java.util.Date().getTime()) {
                        throw new RuntimeException("Vui lòng không chọn thời gian trong quá khứ!");
                    } else {
                        int bookReceiptID = new BookReceipt(b.getUserID(), bookCode).add();
                        new BookReceipt(bookReceiptID).borrow();
                        MessageDB.addMessage(new Message(false, b.getUserID(), "<b>Sách bạn mượn:</b> " + b.getBookName() + " <br><b style='color: blue'>ĐƯỢC ĐỒNG Ý!</b><br>Vui lòng ghé thư viện vào lúc: " + time + " để nhận sách!"));
                        b.delete();
                        response.sendRedirect("bookorder");
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(BookOrders.class.getName()).log(Level.SEVERE, null, ex);
                }
//                out.print(bookCode);
                break;
            case "2":
                String reason = request.getParameter("reason");
                MessageDB.addMessage(new Message(false, b.getUserID(), "<b>Sách bạn mượn:</b> " + b.getBookName() + " <br><b style='color: red'>BỊ TỪ CHỐI!</b><br><b>Lý do:</b> " + reason));
                b.delete();
                response.sendRedirect("bookorder");
                break;
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
