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
import model.entity.Message;

/**
 *
 * @author ThanhNhan
 */
public class LoadSender extends HttpServlet {

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
            out.println("<title>Servlet LoadSender</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoadSender at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String userID = request.getParameter("userID");
        for (Message m : MessageDB.getListSender()) {
            out.println("<a href='messenger?userID=" + m.getUserID().trim() + "'>");
            if (m.getUserID().equals(userID)) {
                out.println("<li class='choose'>");
            } else {
                out.println("<li class=''>");
            }
            if(m.getJob().equals("Học sinh")){
                out.println("<div class='name'><i style='color: gray'>" + m.getJob() + ":</i> " + m.getFullName() + " - lớp: " + m.getClas() + "</div>");
            }
            else{
                out.println("<div class='name'><i style='color: gray'>" + m.getJob() + ":</i> " + m.getFullName() + "</div>");
            }            
            out.println("<div>" + m.getMessage() + "</div>");
            out.println("<hr>");
            out.println("<i style='color: gray'>" + m.getTime() + "</i>");
            out.println("</li>");
            out.println("</a>");
            out.println("<hr>");
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
