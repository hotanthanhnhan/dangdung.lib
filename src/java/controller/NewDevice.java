/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.dao.DeviceDB;
import model.dao.SubjectDB;
import model.entity.Account;
import model.entity.Device;

/**
 *
 * @author ThanhNhan
 */

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 200,
        maxFileSize = 1024 * 1024 * 200,
        maxRequestSize = 1024 * 1024 * 200)
public class NewDevice extends HttpServlet {

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
            out.println("<title>Servlet NewDevice</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewDevice at " + request.getContextPath() + "</h1>");
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
                        request.setAttribute("su", SubjectDB.getListSubject());
                        request.getRequestDispatcher("NewDevice.jsp").include(request, response);
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
        Part img = request.getPart("deviceImg");
        String realPath = "D:\\workspace\\java\\DangDungLibrary\\web\\assets\\covers\\";
        Random rd = new Random();
        int n = rd.nextInt();
        if(n < 0) n = -n;
        String fileName = Paths.get(img.getSubmittedFileName()).getFileName().toString();
        img.write(realPath + n  + fileName);
        img.write(n + fileName);
        realPath = request.getServletContext().getRealPath("./assets/covers/");
        img.write(realPath + n +  fileName);
        String deviceName = request.getParameter("deviceName");
        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String deviceImg = "./assets/covers/" + n + fileName;
        new Device(0, deviceName, subjectID, deviceImg, quantity).add();
        response.sendRedirect("listdevice");
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
