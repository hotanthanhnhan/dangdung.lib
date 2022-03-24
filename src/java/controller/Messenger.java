/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class Messenger extends HttpServlet {

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
        request.setCharacterEncoding("utf-8");
        try {
            /* TODO output your page here. You may use following sample code. */
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
                        String userID = request.getParameter("userID");
                        if (userID == null) {
                            request.setAttribute("client", new Account(MessageDB.getListSender().get(0).getUserID()));
                            request.setAttribute("mess", MessageDB.getMessage(MessageDB.getListSender().get(0).getUserID()));
                            request.setAttribute("tag", MessageDB.getListSender().get(0).getUserID());
                        } else {
                            request.setAttribute("client", new Account(userID));
                            request.setAttribute("mess", MessageDB.getMessage(userID));
                            request.setAttribute("tag", userID);
                        }
                        request.setAttribute("o", "checked");
                        request.setAttribute("sender", MessageDB.getListSender());
                        request.getRequestDispatcher("Messenger.jsp").include(request, response);
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
        PrintWriter out = response.getWriter();
        Account a = (Account) request.getSession().getAttribute("acc");
        String mess = request.getParameter("mess");
        String userID = request.getParameter("userID");
        boolean typeID = Boolean.parseBoolean(request.getParameter("typeID"));
        MessageDB.addMessage(new Message(typeID, userID, mess));        
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
