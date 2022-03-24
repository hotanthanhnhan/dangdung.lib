/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.MessageDB;
import model.entity.Account;
import model.entity.Message;

/**
 *
 * @author ThanhNhan
 */
public class LoadMess extends HttpServlet {

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
            out.println("<title>Servlet LoadMess</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoadMess at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        String userID = request.getParameter("userID");
        Account a = (Account) request.getSession().getAttribute("acc");
        if (a.getUserTypeID() == 1) {
            for (Message m : MessageDB.getMessage(userID)) {
                if (m.isTypeID()) {
                    out.println("<div class='client-mess'><div>" + m.getMessage() + "</div></div>");
                    out.println("<div style=\"text-align: left; font-size: 14px; padding: 0 30px\"><i>" + m.getTime() + "</i></div>");

                } else {
                    out.println("<div class='admin-mess'><div>" + m.getMessage() + "</div></div>");
                    out.println("<div style=\"text-align: right; font-size: 14px; padding: 0 30px\"><i>" + m.getTime() + "</i></div>");
                }
            }
        } else {
            for (Message m : MessageDB.getMessage(userID)) {
                if (m.isTypeID()) {
                    out.println("<div class='client-mess'><div>" + m.getMessage() + "</div></div>");                  
                } else {
                    out.println("<div class='admin-mess'><div>" + m.getMessage() + "</div></div>");                   
                }
            }
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
